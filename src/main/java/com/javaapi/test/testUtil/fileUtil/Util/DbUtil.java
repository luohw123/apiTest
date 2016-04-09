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
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class DbUtil {

    private Set<Integer> youkuSet = new HashSet<>();
    private Set<Integer> dilianSet = new HashSet<>();


    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * only dilian ,no youku
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String ac_video_source = "ac_video_source_7";
//        List<Map<String, Object>> youku = jdbcTemplate.queryForList("SELECT video_id FROM " + ac_video_source + " WHERE source='youku'");
        List<Map<String, Object>> youku = jdbcTemplate.queryForList("SELECT DISTINCT(video_id) FROM " + ac_video_source + " WHERE source='D_LIAN'");
        StringJoiner sj = new StringJoiner(",","(",")");

        for (Map<String, Object> map : youku) {
            Object video_id = map.get("video_id");
            sj.add(String.valueOf(video_id));
            youkuSet.add((Integer) video_id);
        }


//        String sql = "SELECT DISTINCT(video_id) FROM " + ac_video_source + " WHERE source='D_LIAN' and video_id in " + sj.toString();
        String sql = "SELECT video_id FROM " + ac_video_source + " WHERE source='youku' and video_id in " + sj.toString();
//        System.out.println(sql);
        List<Map<String, Object>> dilian = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> map : dilian) {
            Object video_id = map.get("video_id");
            dilianSet.add((Integer) video_id);
        }

        youkuSet.removeAll(dilianSet);
//        System.out.println(youkuSet);

        FileUtils.writeLines(new File("/Users/user/program/tmp/onlyDilian_"+ac_video_source+".txt"),youkuSet);




//        int progress = 0;
//        for (Map<String, Object> map : youku) {
//            Integer video_id = (Integer) map.get("video_id");
//            int i = jdbcTemplate.queryForInt("SELECT count(1) FROM ac_video_source WHERE source='D_LIAN' and video_id = " + video_id);
//            if(i == 0) {
//                youkuSet.add(video_id);
//            }
//            progress++;
//            if(progress % 1000 == 0) {
//                System.out.println("进度 = " + progress);
//            }
//        }
//        FileUtils.writeLines(new File("/Users/user/program/shell/no_dilian/no_dilian.txt"), youkuSet);
    }
}
