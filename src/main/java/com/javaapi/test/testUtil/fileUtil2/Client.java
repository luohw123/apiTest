package com.javaapi.test.testUtil.fileUtil2;

import com.javaapi.test.testUtil.fileUtil.common.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {

    @Autowired
    NamedParameterJdbcTemplate nameJdbcTemplate;


    @Test
    public void test() {
        getAllSid();
    }


    public void getAllContentPassedZhuzhan(){
        String sql = String.format("SELECT content_id from jc_content jc WHERE jc.status= 2 ;");
        List<Map<String, Object>> all = nameJdbcTemplate.queryForList(sql, new HashMap<String, Object>());
    }







    private List<Map<String, Object>> getAllSid() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            String tablenameByIndex = CommonUtil.getTablenameByIndex(i);
            String sql = String.format("SELECT distinct video_id FROM %s ", tablenameByIndex);
            List<Map<String, Object>> all = nameJdbcTemplate.queryForList(sql, new HashMap<String, Object>());
            result.addAll(all);
        }
        return result;
    }


}
