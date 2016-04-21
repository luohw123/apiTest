package com.javaapi.test.testUtil.fileUtil.Util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 处理换源失败
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class ClientUpate {
    @Autowired
    JdbcTemplate jdbcTemplate;
     //3424614
    @Test
    public void test() throws IOException {
        List<String> list = new ArrayList<>();
        List<String> listTfileinfo = new ArrayList<>();

        List<Integer> idBakUp = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select video_id,source_id from ac_new_video where video_id > 3422940 and video_id < 5000000 and source_type='zhuzhan' order by video_id ;");
        for (Map<String, Object> map : maps) {
            Integer video_id = (Integer) map.get("video_id");
            String source_id = (String) map.get("source_id");


            List<Map<String, Object>> source_map = jdbcTemplate.queryForList("select id from ac_video_source_7 where video_id=" + video_id + ";");
            for (Map<String, Object> stringObjectMap : source_map) {
                Integer id = (Integer) stringObjectMap.get("id");
                String updateSql = getUpdateSql(source_id, id);
                list.add(updateSql);
                idBakUp.add(id);

            }
        }
        FileUtils.writeLines(new File("/Users/user/program/shell/bakXiufuCHuanyuan/xiufuhuanyuan.sql"),list);
        FileUtils.writeLines(new File("/Users/user/program/shell/bakXiufuCHuanyuan/ac_video_source_7_bak.id"),idBakUp);

    }

    private String getUpdateSql(String source_id, Integer id) {
        String s = "update ac_video_source_7 set video_id=%s where id=%s;";
        String format = String.format(s, source_id, id);
        return format;
    }

}
