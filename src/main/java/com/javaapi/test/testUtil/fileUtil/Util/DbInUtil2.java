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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 统计到底有多少letv 和 youku
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class DbInUtil2 {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testName() throws Exception {
        List<String> strings = Files.readAllLines(Paths.get("/Users/user/Downloads/letv_vid_unique.csv"));


        List<Map<String, Object>> letv = getAllSidByType("letv");
        Map<String, Object> url_videoId = letv.stream().collect(Collectors.toMap(stringObjectMap -> (String) stringObjectMap.get("url"), val -> val.get("video_id"), (oldVal, newVal) -> newVal));
        Map<Integer, Object> allAcSourceInfo = getAllAcSourceInfo();

        List<String> result = new ArrayList<>();
        List<String> noResult = new ArrayList<>();
        strings.stream().skip(1).forEach((s -> {
            String[] split = s.split(",");
            String letv_vid = split[0];


            String video_unique = split[1];

            letv_vid = StringUtils.remove(letv_vid, "\"");
            if (allAcSourceInfo.containsKey(Integer.parseInt(letv_vid))) {
                return;
            }
            video_unique = StringUtils.remove(video_unique, "\"");

            if (url_videoId.containsKey(video_unique)) {
                Integer sid = (Integer) url_videoId.get(video_unique);
                String sql = getAcSourceInfoSql(sid, letv_vid, video_unique);
                result.add(sql);
            } else {
                String sql = getAcSourceInfoSql(-2, letv_vid, video_unique);
                noResult.add(sql);
            }

        }));

        FileUtils.writeLines(new File("/Users/user/program/shell/letvVidUnique/result.txt"), result);
        FileUtils.writeLines(new File("/Users/user/program/shell/letvVidUnique/noresult.txt"), noResult);


    }

    private String getAcSourceInfoSql(Integer sid, String letv_vid, String video_unique) {
        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        int special_id = sid;
        String they_vid = letv_vid;
        String they_unique = video_unique;
        String insert = "INSERT INTO system32.ac_source_info (`special_id`,`they_vid`,`they_unique`,`they_upload_url`,`they_progress_url`,`they_token`,`they_uploadtype`,`type`,`feature`,`user_id`,`push_01`,`push_02`,`push_03`,`push_04`,`push_05`,`creator`,`create_time`,`last_updator`,`last_update_time`) VALUES (" + special_id + "," + they_vid + ",'" + they_unique + "',NULL,NULL,NULL,NULL,'letv',NULL,NULL,1,1,0,0,0,NULL,'" + now + "',NULL,'" + now + "');";
        return insert;
    }


    private Map<Integer, Object> getAllAcSourceInfo() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT they_vid from system32.ac_source_info ");
        Map<Integer, Object> collect = maps.stream().collect(Collectors.toMap((s) -> (Integer) s.get("they_vid"), (s1) -> s1.get("they_vid")));
        return collect;
    }

    private List<Map<String, Object>> getAllSidByType(String letv) {
        String allLetvSid = "select video_id,url from ac_video_source where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_2 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_3 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_4 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_5 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_6 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_7 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_8 where source='" + letv + "';\n";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(allLetvSid);
        return maps;
    }


}
