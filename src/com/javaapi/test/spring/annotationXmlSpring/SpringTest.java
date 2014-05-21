package com.javaapi.test.spring.annotationXmlSpring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * http://pingping0524.iteye.com/blog/262482 取名字小写也可以
 */
public class SpringTest {
	@Test
	public void test() {
		String path=SpringTest.class.getResource("").getPath();
		String filename = path+"applicationContext.xml";
		ApplicationContext app=new FileSystemXmlApplicationContext(filename);
		School school=(School)app.getBean("school");
		school.haveClass();//上课
	}
}
