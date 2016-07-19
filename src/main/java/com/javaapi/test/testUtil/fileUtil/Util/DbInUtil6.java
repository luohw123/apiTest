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

/**
 * sid,vid,size
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class DbInUtil6 {

    @Resource
    private JdbcTemplate jdbcTemplate;


    @Test
    public void test() throws IOException {
//        List<String> strings = Files.readAllLines(Paths.get("/Users/user/program/shellTmp/过审的只有letv没有youku_sid_vid_unique.txt"));
//        // 已经处理过的
//        Map<String, String> collect = strings.stream().collect(Collectors.toMap((s) -> {
//            String[] split = s.split(",");
//            String letvVid = split[1];
//            return letvVid;
//        }, (s2) -> s2, (old, newVal) -> newVal));

        String s = "/Users/user/program/shellTmp/未过审的只有letv没有youku_sid_letvVid_size.txt";
        List<String> strings1 = Files.readAllLines(Paths.get(s));
        int index = 0;
        List<String> result1 = new ArrayList<>();
        List<String> result2 = new ArrayList<>();
        List<String> result3 = new ArrayList<>();
        List<String> result4 = new ArrayList<>();
        List<String> result5 = new ArrayList<>();
        List<String> result6 = new ArrayList<>();
        int limit = 6;
        for (String s1 : strings1) {
            String[] split = s1.split(",");
            String sid = split[0];
            String letvVid = split[1];
//            if (collect.containsKey(letvVid)) {
//                continue;
//            }
            if (index % limit == 0) {
                result1.add(sid + "," + letvVid);
            } else if (index % limit == 1) {
                result2.add(sid + "," + letvVid);
            } else if (index % limit == 2) {
                result3.add(sid + "," + letvVid);
            } else if (index % limit == 3) {
                result4.add(sid + "," + letvVid);
            } else if (index % limit == 4) {
                result5.add(sid + "," + letvVid);
            } else if (index % limit == 5) {
                result6.add(sid + "," + letvVid);
            }
            index++;
        }

        System.out.println("result1 = " + result1.size());
        System.out.println("result2 = " + result2.size());
        System.out.println("result3 = " + result3.size());
        System.out.println("result4 = " + result4.size());
        System.out.println("result5 = " + result5.size());
        System.out.println("result6 = " + result6.size());
        System.out.println("总数为:" + index);
        System.out.println("所有result的总数为:" + (result1.size()+result2.size()+result3.size()+result4.size()+result5.size()+result6.size()));

        FileUtils.writeLines(new File("/Users/user/program/shellTmp/未过审的只有letv没有youku_sid_letvVid_area1.txt"), result1);
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/未过审的只有letv没有youku_sid_letvVid_area2.txt"),result2);
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/未过审的只有letv没有youku_sid_letvVid_area3.txt"),result3);
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/未过审的只有letv没有youku_sid_letvVid_area4.txt"),result4);
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/未过审的只有letv没有youku_sid_letvVid_area5.txt"),result5);
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/未过审的只有letv没有youku_sid_letvVid_area6.txt"),result6);

    }

}
