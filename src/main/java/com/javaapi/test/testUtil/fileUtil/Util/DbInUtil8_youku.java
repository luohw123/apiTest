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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * sid,vid,size
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class DbInUtil8_youku {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testName() throws Exception {


        List<String> list = getList("/Users/user/Downloads/003_成功.txt");


        String str = "/Users/user/program/shellTmp/过审的只有letv没有youku.txt";
        List<String> strings = Files.readAllLines(Paths.get(str));
        StringJoiner sj = new StringJoiner(",", "(", ")");
        strings.stream().forEach((s -> sj.add(s)));

        List<String> youkuSids = getAllSidByType("youku", sj.toString());
        List<String> always_noyouku = removeAll(strings, youkuSids);
        always_noyouku = removeAll(always_noyouku, list);
        FileUtils.writeLines(new File("/Users/user/Downloads/always_noyouku.txt"), always_noyouku);


    }

    private List<String> getList(String s) {
        List<String> strings;
        try {
            strings = Files.readAllLines(Paths.get(s));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return strings;
    }

    private List<String> getAllSidByType(String letv, String sids) {
        String allLetvSid = "select video_id from ac_video_source where source='" + letv + " and video_id in " + sids + " '\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_2 where source='" + letv + "' and video_id in " + sids + " \n" +
                "UNION all \n" +
                "select video_id from ac_video_source_3 where source='" + letv + "' and video_id in " + sids + " \n" +
                "UNION all \n" +
                "select video_id from ac_video_source_4 where source='" + letv + "' and video_id in " + sids + " \n" +
                "UNION all \n" +
                "select video_id from ac_video_source_5 where source='" + letv + "' and video_id in " + sids + " \n" +
                "UNION all \n" +
                "select video_id from ac_video_source_6 where source='" + letv + "' and video_id in " + sids + " \n" +
                "UNION all \n" +
                "select video_id from ac_video_source_7 where source='" + letv + "' and video_id in " + sids + " \n" +
                "UNION all \n" +
                "select video_id from ac_video_source_8 where source='" + letv + "' and video_id in " + sids + " ;\n";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(allLetvSid);
        maps.forEach((sid) -> result.add((sid.get("video_id")).toString()));
        return result;
    }

    private List<String> removeAll(List<String> collection, List<String> toBeRemove) {
        Map<String, String> collect = toBeRemove.stream().collect(Collectors.toMap((s) -> s, (s1 -> s1)));

        List<String> result = collection.stream().filter((s -> !collect.containsKey(s))).collect(Collectors.toList());


        return result;
    }


}
