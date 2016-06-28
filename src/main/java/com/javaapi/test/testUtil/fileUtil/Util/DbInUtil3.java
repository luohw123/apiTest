package com.javaapi.test.testUtil.fileUtil.Util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
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
public class DbInUtil3 {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testName() throws Exception {
        List<String> letvAll = Files.readAllLines(Paths.get("/Users/user/Downloads/letv_vid_unique.csv"));


        List<String> strings = Files.readAllLines(Paths.get("/Users/user/program/shellTmp/过审的只有letv没有youku.txt"));


        StringJoiner sj = new StringJoiner(",", "(", ")");
        strings.forEach((s -> sj.add(s)));


        List<Map<String, Object>> letv = getAllSidByTypeForSid(sj.toString());






        Map<String, String> collect = letvAll.stream().skip(1).collect(Collectors.toMap((s) -> {
                    String[] split = s.split(",");
                    String video_unique = split[1];
                    video_unique = StringUtils.remove(video_unique, "\"");
                    return video_unique;
                }, (s) -> s
        ));

        List<String> result = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : letv) {
            String url = (String) stringObjectMap.get("url");
            if(collect.containsKey(url)) {
                String val = collect.get(url);
                String[] split = val.split(",");
                String letv_vid = split[0];
                String size = split[3];

                Integer source_id = (Integer) stringObjectMap.get("video_id");
                letv_vid = StringUtils.remove(letv_vid, "\"");
                size = StringUtils.remove(size, "\"");
                result.add(String.format("%s,%s", source_id, letv_vid));
            }
        }

//        List<String> finalResult = result.stream().sorted((s1, s2) -> {
//            String size1 = s1.split(",")[2];
//            String size2 = s2.split(",")[2];
//            return new BigDecimal(size1).compareTo(new BigDecimal(size2));
//        }).collect(Collectors.toList());


        FileUtils.writeLines(new File("/Users/user/program/shellTmp/过审的只有letv没有youku_sid_letvVid.txt"),result);
    }


    private List<Map<String, Object>> getAllSidByTypeForSid(String sids) {
        String allLetvSid = "select video_id,url from ac_video_source where source='letv' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_2 where source='letv' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_3 where source='letv' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_4 where source='letv' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_5 where source='letv' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_6 where source='letv' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_7 where source='letv' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_8 where source='letv' and video_id in " + sids + ";\n";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(allLetvSid);
        return maps;
    }
}
