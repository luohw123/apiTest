package com.javaapi.test.testUtil.fileUtil.Util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * sid,vid,size
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class DbInUtil5_TestLetv {

    @Resource
    private JdbcTemplate jdbcTemplate;


    @Test
    public void testName() throws Exception {
        String path = "/Users/user/Downloads/404.txt";

        List<String> strings = Files.readAllLines(Paths.get(path));
        System.out.println("strings = " + strings.size());
        StringJoiner sj = new StringJoiner(",", "(", ")");
        strings.forEach((s -> sj.add(s)));

        List<Map<String, Object>> allSidByTypeForSid = getAllSidByTypeForSid(sj.toString());
        System.out.println("allSidByTypeForSid = " + allSidByTypeForSid.size());
        FileUtils.writeLines(new File("/Users/user/Downloads/404_tmp.txt"),allSidByTypeForSid);
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
