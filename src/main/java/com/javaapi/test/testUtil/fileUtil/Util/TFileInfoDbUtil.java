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
import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class TFileInfoDbUtil {

    private Set<Integer> dlianSet = new HashSet<>();
    private Set<Integer> youkuSet = new HashSet<>();


    @Resource
    private JdbcTemplate jdbcTemplate;


    /**
     * only dilian ,no youku
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {

        String now = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date());

        String ac_video_source = "ac_video_source_7";
//        List<Map<String, Object>> youku = jdbcTemplate.queryForList("SELECT video_id FROM " + ac_video_source + " WHERE source='youku'");
        List<Map<String, Object>> youku = jdbcTemplate.queryForList("SELECT DISTINCT(video_id) FROM " + ac_video_source + " WHERE source='D_LIAN' and create_time >'2016-04-19 12:20:10' ");
        StringJoiner sj = new StringJoiner(",", "(", ")");

        for (Map<String, Object> map : youku) {
            Object video_id = map.get("video_id");
            sj.add(String.valueOf(video_id));
            dlianSet.add((Integer) video_id);
        }


//        String sql = "SELECT DISTINCT(video_id) FROM " + ac_video_source + " WHERE source='D_LIAN' and video_id in " + sj.toString();
        String sql = "SELECT video_id FROM " + ac_video_source + " WHERE source='youku' and video_id in " + sj.toString();
//        System.out.println(sql);
        List<Map<String, Object>> dilian = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> map : dilian) {
            Object video_id = map.get("video_id");
            youkuSet.add((Integer) video_id);
        }

        dlianSet.removeAll(youkuSet);
        List<String> insertResult_actrans = new ArrayList<>();
//=========================
        Set<Integer> haveYkresult = new HashSet<>();
        List<Map<String, Object>> tInfo = jdbcTemplate.queryForList("select distinct(vid),youku_key from actrans.t_file_info where provider='ACFUN_SELF' and youku_bak >=1 and youku_key is not null and youku_key !=''  and vid in " + getVidStr(dlianSet).toString());
        for (Map<String, Object> stringObjectMap : tInfo) {
            Integer vid = (Integer) stringObjectMap.get("vid");
            String youku_key = (String) stringObjectMap.get("youku_key");

            String insert = getvidesourceSql(vid, youku_key);
            haveYkresult.add(vid);
            insertResult_actrans.add(insert);
        }
        dlianSet.removeAll(haveYkresult);
//================================

        List<String> insertResult_av = new ArrayList<>();
        Set<Integer> haveYkresult_av = new HashSet<>();
        List<Map<String, Object>> av_tinfo = jdbcTemplate.queryForList("select distinct(vid),youku_video_id from Avideo.t_file_info where supplier='A' and youku_bak >=1  and youku_video_id is not null  and youku_video_id !='' and  vid in " + getVidStr(dlianSet).toString());
        for (Map<String, Object> stringObjectMap : av_tinfo) {
            Integer vid = (Integer) stringObjectMap.get("vid");
            String youku_key = (String) stringObjectMap.get("youku_video_id");

            String insert = getvidesourceSql(vid, youku_key);
            haveYkresult_av.add(vid);
            insertResult_av.add(insert);
        }
        dlianSet.removeAll(haveYkresult_av);
        FileUtils.writeLines(new File(("/Users/user/program/tmp/insertNoyouku_actrans"+now+".sql")), insertResult_actrans);
        FileUtils.writeLines(new File(("/Users/user/program/tmp/insertNoyouku_av"+now+".sql")), insertResult_av);
        FileUtils.writeLines(new File("/Users/user/program/tmp/onlyDilian_" + ac_video_source + "_"+now+".txt"), dlianSet);
    }

    private String getVidStr(Set<Integer> set) {
        StringJoiner sjDilian = new StringJoiner(",", "(", ")");

        for (Integer tmp : set) {
            sjDilian.add(String.valueOf(tmp));
        }

        return sjDilian.toString();
    }

    private String getvidesourceSql(Integer vid, String youku_key) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String sql = "INSERT INTO ac_video_source_7 (video_id, url, source, bit_rate, creater, create_time, updater, update_time)value(" + vid + ",'" + youku_key + "','youku',99,1,'" + now + "',1,'" + now + "');";
        return sql;
    }
}
