package com.javaapi.test.spring.JunitSpringMock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 未完成
 * @project JavaApiSample
 * @author kk
 * @date 2014年7月4日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = MockServletContextWebContextLoader.class, locations = "file:target/classes/com/javaapi/test/spring/JunitSpringMock/applicationContext.xml")
public class TestMockServletContext {

    @Autowired
    TestMock testMock;
    @Test
    public void test() {
        System.out.println(testMock.getMockServlet());
    }

}
