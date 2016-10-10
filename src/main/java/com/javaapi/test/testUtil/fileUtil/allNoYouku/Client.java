package com.javaapi.test.testUtil.fileUtil.allNoYouku;

import com.javaapi.test.testUtil.fileUtil.common.CommonUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
 * 查询所有没有youku的数据
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public static  int INDEX = 0;

    @Test
    public void test() {
        String table = "ac_video_source_%s";

        List<Integer> all = new ArrayList<>();
        for (int i = 8; i < 10; i++) {
            if (i == 0) {
                continue;
            }
            String tableSource = String.format(table, i);
            if (i == 1) {
                tableSource = "ac_video_source";
            }
            List<Integer> noYouKuSid = findNoYouKuSid(tableSource);
//            all.addAll(noYouKuSid);
            System.out.println(i + " done");
            try {
                FileUtils.writeLines(new File("/Users/user/Downloads/allNoYoukuSids20160919"+tableSource+".txt"), noYouKuSid);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            FileUtils.writeLines(new File("/Users/user/Downloads/allNoYoukuSids20160919.txt"),all);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private List<Integer> findNoYouKuSid(String table) {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from " + table + " where source='youku' group by video_id ;");

        List<Integer> youkuSids = maps.stream().map((map) -> {
            return (Integer) map.get("video_id");
        }).collect(Collectors.toList());

        List<Map<String, Object>> mapAll = jdbcTemplate.queryForList("select * from " + table + " group by video_id ;");
        List<Integer> allSids = mapAll.stream().map((map) -> {
            return (Integer) map.get("video_id");
        }).collect(Collectors.toList());

        List<Integer> sid = CommonUtil.removeAll(allSids, youkuSids);
//        System.out.println("没有youku的sid" + sid.stream().findFirst().get());
        return sid;
    }

    @Test
    public void testFindAllAc() {
        List<String> strings = null;
        try {
            strings = Files.readAllLines(Paths.get("/Users/user/Downloads/allNoYoukuSids20160919.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(strings == null){
            return;
        }
        int i = 0;
        List<String> sid = new ArrayList<>();
        for (String string : strings) {

            sid.add(string);
            if(i>2 && i%10000==0) {
                List<Map<String, Object>> info = findAllInfo(sid);
                sid.clear();
            }
            i++;
        }
         List<Map<String, Object>> allInfo = findAllInfo(sid);

    }

    private List<Map<String, Object>> findAllInfo(List<String> sid) {
        String[] strings = sid.toArray(new String[sid.size()]);


        StringJoiner sj = new StringJoiner(",", "(", ")");
        for (String s : sid) {
            sj.add("?");
        }

        String ins = sj.toString();


        String sql = "select jc.content_id ,av.video_id,av.title,user.username ,jc.sort_date,jc.views,ext.channel_name,jc.comments from jc_content jc\n" +
                "inner join ac_content_video acv  on acv.content_id = jc.content_id\n" +
                "inner join ac_new_video  av on av.video_id=acv.video_id\n" +
                "inner join ac_user user on user.user_id=av.user_id\n" +
                "inner join jc_channel_ext ext on ext.channel_id=jc.channel_id\n" +
                "where av.source_type='zhuzhan' and  av.source_id  in"+ins+" order by jc.content_id ;";






        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, strings);


        INDEX++;


        List<String> collect = maps.stream().map((map) -> {

            return map.get("content_id") + "," + map.get("video_id") + "," + map.get("title") + "," + map.get("username") + "," + map.get("sort_date")+","+ map.get("views")+","+ map.get("channel_name")+","+ map.get("comments");
        }).collect(Collectors.toList());


        try {
            FileUtils.writeLines(new File("/Users/user/Downloads/allNoYoukuSidInfo"+INDEX+".txt"),collect);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return maps;
    }
}
