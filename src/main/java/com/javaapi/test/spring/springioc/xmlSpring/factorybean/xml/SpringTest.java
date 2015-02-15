package com.javaapi.test.spring.springioc.xmlSpring.factorybean.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.javaapi.test.spring.springioc.xmlSpring.factorybean.School;

/**
 *
 */
public class SpringTest {
	@Test
	public void test() {
		String path=SpringTest.class.getResource("").getPath();
		String filename = path+"applicationContext.xml";
		ApplicationContext app=new FileSystemXmlApplicationContext("file:"+filename);
		System.out.println(app);
		School school=(School)app.getBean("school");
		school.haveClass();//上课
	}
}
