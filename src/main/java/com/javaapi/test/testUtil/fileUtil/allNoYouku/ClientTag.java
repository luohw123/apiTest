package com.javaapi.test.testUtil.fileUtil.allNoYouku;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 查询所有没有youku的数据
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class ClientTag {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public static  int INDEX = 0;

    @Test
    public void test() {
        List<String> keyword = null;
        try {
            keyword = FileUtils.readLines(new File("/Users/user/Downloads/keyword.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (String key : keyword) {


            List<String> collect = getTagAcInfo(key);
            try {
                FileUtils.writeLines(new File(String.format("/Users/user/Downloads/keyword_%s.txt",key)),collect);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("done-------");




    }

    private List<String> getTagAcInfo(String keyWord) {
        List<String> collect;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select DISTINCT(jc.content_id) as content_id,jc.title,user.username,jc.sort_date,jc.views,jcc.channel_name ,jc.comments ,tag.tag_name from   jc_content jc \n" +
                "\n" +
                "inner join jc_channel_ext jcc on jcc.channel_id=jc.channel_id\n" +
                "\n" +
                "inner join ac_user user on jc.user_id=user.user_id\n" +
                "\n" +
                "inner join jc_contenttag jct on jct.content_id= jc.content_id\n" +
                "\n" +
                "inner join jc_content_tag  tag on tag.tag_id = jct.tag_id\n" +
                "\n" +
                "\n" +
                "where jc.status=2 and  tag.tag_name like '%" + keyWord + "%' ");
        collect = maps.stream().map((map -> {
            return String.format("%s,%s,%s,%s,%s,%s,%s,%s", map.get("content_id"), map.get("title"), map.get("username"), map.get("sort_date"), map.get("views"), map.get("channel_name"), map.get("comments"), map.get("tag_name"));
        })).collect(Collectors.toList());
        return collect;
    }

}
