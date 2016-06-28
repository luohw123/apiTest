package com.javaapi.test.testUtil.fileUtil.Util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 统计到底有多少letv 和 youku
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class DbInUtil {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testName() throws Exception {
        List<String> letvAll = Files.readAllLines(Paths.get("/Users/user/Downloads/letv_vid_unique.csv"));


        List<String> strings = Files.readAllLines(Paths.get("/Users/user/program/shellTmp/未过审的只有letv没有youku.txt"));


        StringJoiner sj = new StringJoiner(",", "(", ")");
        strings.forEach((s -> sj.add(s)));


        List<Map<String, Object>> letv = getAllSidByTypeForSid(sj.toString());






        Map<String, String> collect = letvAll.stream().skip(1).collect(Collectors.toMap((s) -> {
                    String[] split = s.split(",");
                    String video_unique = split[1];
                    video_unique = StringUtils.remove(video_unique, "\"");
                    return video_unique;
                }, (s) -> {
                    String[] split = s.split(",");
                    String size = split[3];
                    size = StringUtils.remove(size, "\"");
                    return size;
                }
        ));
        BigDecimal total = new BigDecimal(0);

        for (Map<String, Object> stringObjectMap : letv) {
            String url = (String) stringObjectMap.get("url");
            if(collect.containsKey(url)) {
               total= total.add(new BigDecimal(collect.get(url)));
            }
        }
        System.out.println(total.toString());


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
