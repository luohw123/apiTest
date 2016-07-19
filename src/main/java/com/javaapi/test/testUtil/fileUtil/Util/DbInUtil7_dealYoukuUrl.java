package com.javaapi.test.testUtil.fileUtil.Util;

import com.alibaba.fastjson.JSONObject;
import com.javaapi.test.testUtil.fileUtil.common.CommonUtil;
import com.javaapi.test.testUtil.net.httpclient.HttpUtil_431;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * sid,vid,size
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class DbInUtil7_dealYoukuUrl {

    @Resource
    private JdbcTemplate jdbcTemplate;


    @Test
    public void testGetDelete() throws Exception {
        List<String> sids = Files.readAllLines(Paths.get("/Users/user/Downloads/sid.txt"));
        List<String> collect = sids.stream().map((s) -> {
            String[] split = s.split(",");
            String s1 = split[0];
            return s1;
        }).collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        for (String sid : collect) {
            String deleteSql = getDeleteSql(sid);
            result.add(deleteSql);

        }
        FileUtils.writeLines(new File("/Users/user/Downloads/需要删除的youku_sid.txt"),result);


    }

    private String getDeleteSql(String sid) {
        String table = CommonUtil.getTablename(sid);
        String sql = "DELETE FROM " + table + " where source='youku' and video_id=" + sid+";";
        return sql;
    }

    @Test
    public void testName() throws Exception {
        List<String> sids = Files.readAllLines(Paths.get("/Users/user/Downloads/sid.txt"));
        StringJoiner sj = new StringJoiner(",", "(", ")");
        sids.stream().map((s) -> {
            String[] split = s.split(",");
            String s1 = split[0];
            return s1;
        }).forEach((s -> sj.add(s)));
        System.out.println("sids size = " + sids.size());
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> allSidByTypeForSid = getAllSidByTypeForSid(sj.toString());
        int i = 0;
        for (Map<String, Object> video : allSidByTypeForSid) {
            Integer video_id = (Integer) video.get("video_id");
            String url = (String) video.get("url");
            boolean isError = IsError(url);
            if(isError) {
                String after_url = url + "==";
                boolean b = IsError(after_url);
                if(!b){
                    result.add(video_id+","+url);
                }


            }
            
            if(i%100==0){
                System.out.println("进度="+i);
            }
            i++;
            continue;
        }
        System.out.println("done");
        FileUtils.writeLines(new File("/Users/user/Downloads/需要加等号的url.txt"),result);
    }

    private List<Map<String, Object>> getAllSidByTypeForSid(String sids) {
        String allLetvSid = "select video_id,url from ac_video_source where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_2 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_3 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_4 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_5 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_6 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_7 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_8 where source='youku' and video_id in " + sids + ";\n";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(allLetvSid);
        return maps;
    }

    @Test
    public void test() throws Exception {
        String youku_vid = "CMzc4NjExMg";
        boolean b = IsError(youku_vid);

    }

    private boolean IsError(String youku_vid) {
        String sign = getSign(youku_vid);
        HashMap<String, String> param = new HashMap<>();
        param.put("vid", youku_vid);
        param.put("ct", "85");
        param.put("sign", sign);
        String result = HttpUtil_431.get("http://play.youku.com/partner/get.json", param);
        JSONObject jsonObject = JSONObject.parseObject(result);
        boolean b = jsonObject.getJSONObject("data").containsKey("error");
        return b;
    }

    private String getSign(String url) {
        String client_id = "908a519d032263f8";
        String s = "https://api.youku.com/players/custom.json";
        Map<String, String> map = new HashMap<>();
        map.put("client_id", client_id);
        map.put("video_id", url);
        String result = HttpUtil_431.get(s, map);
        if (StringUtils.isBlank(result)) {
            return result;
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        String playsign = jsonObject.getString("playsign");
        return playsign;
    }
}
