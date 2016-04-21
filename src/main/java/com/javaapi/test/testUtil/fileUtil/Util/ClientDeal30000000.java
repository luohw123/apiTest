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
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class ClientDeal30000000 {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Map<String, Integer> map = new HashMap<>();

    @Test
    public void test() throws IOException {

        List<String> acNew = new ArrayList<>();
        List<String> acSource = new ArrayList<>();
        List<String> acSourceIdList = new ArrayList<>();


        int start = 3423427;
        List<String> strings = FileUtils.readLines(new File(("/Users/user/program/shell/bak30000000/bak-01.txt")));
        for (int i = 0; i < strings.size(); i++) {
            if (i == 0) {
                continue;
            }
            String string = strings.get(i);
            String[] split = string.split(",");
            start++;
//            String updateAcNewVideo = getUpdateSql(split, start);
            String sourceUpdate = getInsertSource(split, start);
//            acNew.add(updateAcNewVideo);
            acSource.add(sourceUpdate);

            String sourceId = split[0];
            map.put(sourceId, start);
            acSourceIdList.add(sourceId);
        }

        StringJoiner sj = new StringJoiner(",", "(", ")");
        for (String s : acSourceIdList) {
            sj.add(s);
        }
        List<Map<String, Object>> youku  = jdbcTemplate.queryForList("SELECT video_id,source_id FROM system32.ac_new_video WHERE source_type='zhuzhan' and source_id in " + sj.toString());
        for (Map<String, Object> stringObjectMap : youku) {
            Integer video_id = (Integer) stringObjectMap.get("video_id");
            String source_id = (String) stringObjectMap.get("source_id");
            Integer startIndex = map.get(source_id);
            String update = getNewUpdateSql(String.valueOf(video_id), String.valueOf(source_id), startIndex);
            acNew.add(update);
        }



        FileUtils.writeLines(new File("/Users/user/program/shell/bak30000000/insertSourceFor3000.sql"),acSource);
        FileUtils.writeLines(new File("/Users/user/program/shell/bak30000000/updateNewVideoFor3000.sql"),acNew);
    }

    private String getNewUpdateSql(String video_id, String source_id, Integer startIndex) {
        String s = "update system32.ac_new_video set source_id="+startIndex+" where video_id=" + video_id+";";
        return s;
    }

    private String getInsertSource(String[] split, int start) {
//        String source_id = split[0];
        String status = split[1];
        String user_id = split[2];
        String creator = split[3];
        String create_time = split[4];
        String last_updator = split[5];
        String last_update_time = split[6];

//        String update = "update system32.ac_source set id=" + start + " where id=" + source_id+";";
        String insert = "insert into ac_source(id, status, user_id, creator, create_time, last_updator, last_update_time) value(%s,%s,%s,%s,'%s',%s,'%s');";
        String insertSql= String.format(insert, start, status, user_id, creator, create_time, last_updator, last_update_time);
        return insertSql;
    }

    private String getUpdateSql(String[] split, int start) {
        String source_id = split[0];
        String s = "update system32.ac_new_video set source_id=" + start + " where source_type='zhuzhan' and source_id=" + source_id+";";
        return s;
    }
}
