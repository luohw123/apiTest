package com.javaapi.test.testUtil.fileUtil2.changeUrl;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送视频失效信息
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
    @Autowired
    NamedParameterJdbcTemplate nameJdbcTemplate;

    @Test
    public void testName() throws Exception {
        String tableName = "ac_web_module";
        String urlProperty = "link";
        String idProperties = "id";
        String sql = "SELECT "+ idProperties +" , "+urlProperty +" FROM "+tableName +" WHERE "+urlProperty+"  like "+" '%acfun.tv%' ";
        List<Map<String, Object>> result = nameJdbcTemplate.queryForList(sql, new HashMap<String, Object>());
        List<String> resultFile = new ArrayList<>();
        List<String> beforeResultFile = new ArrayList<>();
        for (Map<String, Object> s : result) {
            String url = (String) s.get(urlProperty);
            Integer id = (Integer) s.get(idProperties);
            if(!url.contains("acfun.tv")){
                continue;
            }
            String afterUrl = clean(url);

            String updateString = String.format("update %s set %s = '%s' WHERE %s =%s ;", tableName, urlProperty, afterUrl, idProperties, id);
            resultFile.add(updateString);
            beforeResultFile.add(id+"====="+url);

        }


        FileUtils.writeLines(new File("/Users/user/Downloads/changeurl/"+tableName+"_update.sql"),resultFile);
        FileUtils.writeLines(new File("/Users/user/Downloads/changeurl/"+tableName+"_before_update.sql"),beforeResultFile);
    }

    private static  String clean(String url) {
        String[] split = url.split("acfun.tv");
        int lastIndex = split.length - 1;
        String afterClear = split[lastIndex];

        return afterClear;
    }


    @Test
    public void test() {
        System.out.println("clean = " + clean("http://www.acfun.tv/v/ac123"));
        System.out.println("clean = " + clean("http://qdldq.acfun.tv/v/ac123"));
        System.out.println("clean = " + clean("www.acfun.tv/v/ac123"));
    }


}
