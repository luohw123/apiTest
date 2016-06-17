package com.javaapi.test.testUtil.fileUtil.Util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 统计到底有多少letv 和 youku
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class StatUtil {

    @Resource
    private JdbcTemplate jdbcTemplate;


    @Test
    public void testGetAllLetvSid() throws Exception {
        List<String> letv = getAllSidByType("letv");
        List<String> youku = getAllSidByType("youku");
        List<String> haveLetv = removeAll(letv, youku);
        List<String> haveYouku = removeAll(youku, letv);
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/haveLetv.txt"),haveLetv);
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/haveYouku.txt"), haveYouku);
    }


    @Test
    public void test() throws IOException {
        List<String> haveYouku = FileUtils.readLines(new File("/Users/user/program/shellTmp/haveYouku.txt"));
        List<String> statusEqual = getStatusEqual(haveYouku, "2");
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/过审的只有Youku没有letv.txt"), statusEqual);
    }

    @Test
    public void test2() throws IOException {
        List<String> haveYouku = FileUtils.readLines(new File("/Users/user/program/shellTmp/haveYouku.txt"));
        List<String> statusEqual = getStatusNotEqual(haveYouku, "2");
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/未过审的只有Youku没有letv.txt"), statusEqual);
    }
    @Test
    public void test3() throws IOException {
        List<String> haveYouku = FileUtils.readLines(new File("/Users/user/program/shellTmp/haveYouku.txt"));
        List<String> statusEqual = getStatusNotEqual(haveYouku, "2");
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/未过审的只有Youku没有letv.txt"), statusEqual);
    }

    @Test
    public void testGetSidInfo() throws IOException {
        List<String> strings1 = FileUtils.readLines(new File("/Users/user/program/shellTmp/只有dilian没有youku的并且状态为2的.txt"));

        StringJoiner sj = new StringJoiner(",","(",")");
        strings1.forEach((s -> sj.add(s)));

        String s = "select av.source_id as 'sid',acv.video_id as 'video_id',acv.content_id as 'ac' from ac_new_video av \n" +
                "right join ac_content_video acv \n" +
                "on acv.video_id = av.video_id\n" +
                "\n" +
                "where av.source_id in " + sj.toString();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(s);
        List<String> collect = maps.stream().map((m) -> m.get("sid") + "," + m.get("video_id") + "," + m.get("ac")).collect(Collectors.toList());

        String pathname = "/Users/user/program/shellTmp/只有dilian没有youku的并且状态为2的info.txt";
        FileUtils.write(new File(pathname),"sid,video_id,ac\n");
        FileUtils.writeLines(new File(pathname), collect, true);
    }






    private List<String> getStatusEqual(List<String> strings1, String status) throws IOException {
        StringJoiner sj = new StringJoiner(",","(",")");
        strings1.forEach((s -> sj.add(s)));
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT source_id FROM ac_new_video where source_type='zhuzhan' and status =" + status + " and  source_id in " + sj.toString() + ";");

        List<String> result = new ArrayList<>();
        for (Map<String, Object> map : maps) {

            String source_id = (String) map.get("source_id");
            result.add(source_id);

        }
        return result;
    }

    private List<String> getStatusNotEqual(List<String> strings1, String status) throws IOException {
        StringJoiner sj = new StringJoiner(",","(",")");
        strings1.forEach((s -> sj.add(s)));
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT source_id FROM ac_new_video where source_type='zhuzhan' and status !=" + status + " and  source_id in " + sj.toString() + ";");

        List<String> result = new ArrayList<>();
        for (Map<String, Object> map : maps) {

            String source_id = (String) map.get("source_id");
            result.add(source_id);

        }
        return result;
    }









    private List<String> removeAll(List<String> collection, List<String> toBeRemove) {
        Map<String, String> collect = toBeRemove.stream().collect(Collectors.toMap((s) -> s, (s1 -> s1)));

        List<String> result = collection.stream().filter((s -> !collect.containsKey(s))).collect(Collectors.toList());


        return result;
    }


    private List<String> getAllSidByType(String letv) {
        String allLetvSid = "select video_id from ac_video_source where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_2 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_3 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_4 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_5 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_6 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_7 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_8 where source='" + letv + "';\n";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(allLetvSid);
        maps.forEach((sid) -> result.add((sid.get("video_id")).toString()));
        return result;
    }
}
