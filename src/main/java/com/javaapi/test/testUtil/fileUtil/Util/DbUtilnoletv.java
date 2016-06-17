package com.javaapi.test.testUtil.fileUtil.Util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class DbUtilnoletv {

    private Set<Integer> youkuSet = new HashSet<>();
    private Set<Integer> dilianSet = new HashSet<>();


    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 有dilian 没有youku的数据1
     * 1 与目前的letv 比较
     */
    @Test
    public void test() throws IOException {
        List<String> strings = readAllLinesFromDir();
        Files.write(Paths.get("/Users/user/program/tmp2/allHaveDilianNoyouku.txt"), strings, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        List<String> allLetvSid = getAllLetvSid();
        Files.write(Paths.get("/Users/user/program/tmp2/allLetv.txt"), allLetvSid, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);


        List<String> strings1 = removeAll(strings, allLetvSid);

//        getStatus2(strings1);



    }

    @Test
    public void test2() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("/Users/user/program/tmp2/noLetv.txt"));
        getStatusnot2(strings);
    }


    private void getStatusnot2(List<String> strings1) throws IOException {
        StringJoiner sj = new StringJoiner(",","(",")");
        strings1.forEach((s -> sj.add(s)));
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT video_id,source_id FROM ac_new_video where source_type='zhuzhan' and status!=2 and  source_id in " + sj.toString()+";");

        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("/Users/user/program/tmp2/statusnot2.txt"), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        for (Map<String, Object> map : maps) {
            bufferedWriter.write(map.get("video_id")+"-"+map.get("source_Id"));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }


    private void getStatus2(List<String> strings1) throws IOException {
        StringJoiner sj = new StringJoiner(",","(",")");
        strings1.forEach((s -> sj.add(s)));
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT video_id,source_id FROM ac_new_video where source_type='zhuzhan' and status=2 and  source_id in " + sj.toString()+";");

        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("/Users/user/program/tmp2/status2.txt"), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        for (Map<String, Object> map : maps) {
            bufferedWriter.write(map.get("video_id")+"-"+map.get("source_Id"));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private List<String> removeAll(List<String> strings, List<String> allLetvSid) throws IOException {
        Map<String, String> allLetvSidMap = allLetvSid.stream().collect(Collectors.toMap((s1 -> s1), (s2 -> s2)));

        List<String> collect = strings.stream().filter((s -> !allLetvSidMap.containsKey(s))).collect(Collectors.toList());
        Files.write(Paths.get("/Users/user/program/tmp2/noLetv.txt"), collect, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        return collect;

    }

    private List<String> getAllLetvSid() {
        String allLetvSid = "select video_id from ac_video_source where source='letv'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_2 where source='letv'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_3 where source='letv'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_4 where source='letv'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_5 where source='letv'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_6 where source='letv'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_7 where source='letv'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_8 where source='letv';\n";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(allLetvSid);
        maps.forEach((sid) -> result.add((sid.get("video_id")).toString()));
        return result;
    }

    private List<String> readAllLinesFromDir() throws IOException {
        Path path = Paths.get("/Users/user/program/tmp/onlyDilian_405217_20160408-写入100Tyouku数据后剩下的只有帝联没有优酷的数据");
        List<Path> collect = Files.list(path).collect(Collectors.toList());
        System.out.println("collect = " + collect);
        List<String> result = new ArrayList<>();

        collect.stream().forEach((tmpPath) -> {
            try {
                List<String> strings = Files.readAllLines(tmpPath);
                result.addAll(strings);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("result size= " + result.size());
        return result;
    }
}
