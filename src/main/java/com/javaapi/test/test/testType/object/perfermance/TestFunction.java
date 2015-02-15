package com.javaapi.test.test.testType.object.perfermance;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.javaapi.test.test.testType.object.FromBean;

public class TestFunction {
    @Test
    public void testBeanToMap() {
        FromBean fb = new FromBean();
        fb.setAddress("北京市朝阳区大屯路");
        fb.setAge(20);
        fb.setMoney(30000.111);
        fb.setIdno("110330219879208733");
        fb.setName("测试");
        Map<String,String> map =new HashMap<>();
        BeanUtils.copyProperties(fb, map);
        // 不可以copy到map
        System.out.println(map);
    }
}
