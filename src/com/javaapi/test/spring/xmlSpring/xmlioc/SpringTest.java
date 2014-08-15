package com.javaapi.test.spring.xmlSpring.xmlioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *测试spring注入默认对象 
 * @project apiTest
 * @author kk
 * @date 2014年8月15日
 */
public class SpringTest {
	@Test
	public void test() {
		String path=SpringTest.class.getResource("").getPath();
		String filename = path+"applicationContext.xml";
		ApplicationContext app=new FileSystemXmlApplicationContext("file:"+filename);
        HtmlPage htmlPage = (HtmlPage) app.getBean("htmlPage");
        System.out.println(htmlPage.toString());
        System.out.println(htmlPage.getList().get(0).getType());
        System.out.println(htmlPage.getList().get(1).getType());
        System.out.println(htmlPage.getPeoplelist().get(0).getName());
        System.out.println(htmlPage.getPeoplelist().get(1).getName());
	}
}
