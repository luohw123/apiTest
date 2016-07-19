package com.javaapi.test.testUtil.fileUtil.Util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
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
public class DbInUtil4 {

    @Resource
    private JdbcTemplate jdbcTemplate;


    @Test
    public void test2() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("/Users/user/Downloads/videomapping.txt"));
        List<String> urlList = strings.stream().filter((s -> {
            String[] split = s.split("\\|");
            if (split.length == 4) {
                return true;
            }
            return false;
        })).map((s1 -> {
            String[] split = s1.split("\\|");
            String url = split[0];
            return url;
        })).collect(Collectors.toList());
        System.out.println("urlList = " + urlList);
        StringJoiner sj = new StringJoiner(",", "(", ")");
        urlList.forEach((s -> sj.add("'" + s + "'")));
        List<Map<String, Object>> allSidByTypeForSid = getAllSidInfo(sj.toString());
        Map<String, Object> collect = allSidByTypeForSid.stream().collect(Collectors.toMap((k) -> ((String) k.get("url")), (v) -> v.get("video_id")));

        List<String> result = strings.stream().filter((s -> {
            String[] split = s.split("\\|");
            if (split.length == 4) {
                return true;
            }
            return false;
        })).map((s1) -> {
            String[] split = s1.split("\\|");
            String url = split[0];
            Integer sid = (Integer) collect.get(url);
            return sid + "," + s1;
        }).collect(Collectors.toList());
        result.forEach(s -> System.out.println(s));


    }


    @Test
    public void test() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("/Users/user/Downloads/videomapping.txt"));
        List<String> urlList = strings.stream().filter((s -> {
            String[] split = s.split("\\|");
            if (split.length == 4) {
                return true;
            }
            return false;
        })).map((s1 -> {
            String[] split = s1.split("\\|");
            String url = split[0];
            return url;
        })).collect(Collectors.toList());
        System.out.println("urlList = " + urlList);
        StringJoiner sj = new StringJoiner(",", "(", ")");
        urlList.forEach((s -> sj.add("'"+s+"'")));
        List<Map<String, Object>> allSidByTypeForSid = getAllSidInfo(sj.toString());

        Map<Integer, Object> video_id_url = allSidByTypeForSid.stream().collect(Collectors.toMap((s) ->((Integer) s.get("video_id")), (s2) -> s2.get("url")));


        List<String> sids = allSidByTypeForSid.stream().map((s) -> {
            return String.valueOf(s.get("video_id"));
        }).collect(Collectors.toList());

        StringJoiner sj2 = new StringJoiner(",", "(", ")");
        sids.forEach((s -> sj2.add(s)));


        List<Map<String, Object>> allAc = getAllAc(sj2.toString());
        List<String> result = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : allAc) {
            String source_id = (String) stringObjectMap.get("source_id");
            Integer content_id = (Integer) stringObjectMap.get("content_id");
            Integer user_id = (Integer) stringObjectMap.get("user_id");
            result.add(source_id + "," + content_id + "," + video_id_url.get(Integer.parseInt(source_id))+","+user_id);

        }
        result.stream().forEach(s -> {
            System.out.println(s);
        });


    }

    private List<Map<String, Object>> getAllAc(String sids) {
        String s = "select acv.content_id,av.source_id,av.user_id from ac_new_video av inner join ac_content_video acv on acv.video_id = av.video_id  where av.source_id in " + sids;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(s);
        return maps;

    }

    private List<Map<String, Object>> getAllSidInfo(String sids) {
        String sids1 = sids;
        String allLetvSid = "SELECT special_id as video_id,they_unique as url from ac_source_info where they_unique in " + sids1 + " ;\n";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(allLetvSid);
        return maps;
    }

    private List<Map<String, Object>> getAllSidByTypeForSid(String sids) {
        String allLetvSid = "select video_id,url from ac_video_source where source='youku' and url in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_2 where source='youku' and url in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_3 where source='youku' and url in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_4 where source='youku' and url in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_5 where source='youku' and url in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_6 where source='youku' and url in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_7 where source='youku' and url in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_8 where source='youku' and url in " + sids + ";\n";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(allLetvSid);
        return maps;
    }

}
