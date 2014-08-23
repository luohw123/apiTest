package com.javaapi.test.spring.springtest.JunitSpringMock;

import org.junit.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 这个例子是生效的
 * 
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public class TestMockServletContext2 {

    //    @Autowired
    //    TestMock testMock;
    @Test
    public void test() {
        MockServletContext msc = new MockServletContext("/home/kk/www/webclient/ROOT");
        String[] config = { "file:" + TestMockServletContext2.class.getResource("").getPath() + "applicationContext.xml" };
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocations(config);
        context.setServletContext(msc);
        context.refresh();
        msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
        System.out.println(context.getBean("testMock"));
        context.getBean("testMock");
    }

}
