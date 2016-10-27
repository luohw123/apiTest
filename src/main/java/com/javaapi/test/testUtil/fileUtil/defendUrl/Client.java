package com.javaapi.test.testUtil.fileUtil.defendUrl;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 查询所有没有youku的数据
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testName() throws Exception {
        List<String> strings = Files.readAllLines(Paths.get("/Users/user/Downloads/out.txt"));
        Map<String, String> sourceUrl = new HashMap<>();
        List<String> urls = strings.stream().filter((s)-> {
            return s.split(" ")[0].length() >= 5;
        }).map((s -> {
            String source = s.split(" ")[0];

            int length = source.length();
            String substring = source.substring(0, length - 5);
            String substring2 = source.substring(length - 5 + 1, length);
            source = substring + substring2;


            String thirdUrl = s.split(" ")[1];
            sourceUrl.put(source, thirdUrl);
            return source;
        })).collect(Collectors.toList());

        StringJoiner sj = new StringJoiner(",","(",")");
        for (String url : urls) {
            sj.add("'"+url+"'");
        }
        List<Map<String, Object>> allSidByTypeForSid = getAllSidByTypeForSid(sj.toString());
        Map<String, String> sidUrl = new HashMap<>();
        List<Integer> sids = allSidByTypeForSid.stream().map(s -> {

            Integer video_id = (Integer) s.get("video_id");
            sidUrl.put(String.valueOf(video_id), (String)s.get("url"));
            return video_id;
        }).collect(Collectors.toList());


        List<Map<String, Object>> infos = getInfo(sids);
        List<String> result = new ArrayList<>();
        for (Map<String, Object> info : infos) {
            String source_id = (String) info.get("source_id");
            Integer content_id = (Integer) info.get("content_id");
            Integer video_id = (Integer) info.get("video_id");
            String sourceUrlInfo = sidUrl.get(source_id);
            String thirdUrl = sourceUrl.get(sourceUrlInfo);
            result.add(content_id + "," + video_id + "," + source_id + "," + sourceUrlInfo + "," + thirdUrl);
        }
        FileUtils.writeLines(new File("/Users/user/Downloads/盗链网站对应我站的信息2016-10-12.txt"), result);


    }

    private List<Map<String, Object>> getInfo(List<Integer> list) {
        StringJoiner sj = new StringJoiner(",","(",")");
        for (Integer sid : list) {
            sj.add("'"+String.valueOf(sid)+"'");
        }
        String sids = sj.toString();
        String sql = "select acv.content_id,av.video_id ,av.source_id from ac_new_video av\n" +
                "inner join ac_content_video acv on acv.video_id=av.video_id\n" +
                "inner join jc_content jc on jc.content_id = acv.content_id\n" +
                "\n" +
                " where jc.status=2 and  av.source_id in" + sids;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
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
                "select video_id,url from ac_video_source_8 where source='youku' and url in " + sids + "\n"+
                "UNION all \n" +
                "select video_id,url from ac_video_source_9 where source='youku' and url in " + sids + ";\n";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(allLetvSid);
        return maps;
    }
}
