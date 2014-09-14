package com.javaapi.test.spring.aop.springaop3;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * http://my.oschina.net/huangyong/blog/161402
 * 
 */
public class ClientPlain {

	@Test
	public void test() {
		String path = ClientPlain.class.getResource("")
				.getPath();
		System.out.println(path);
		String apppath = "file:" + path + "applicationContext2.xml";
		ApplicationContext app = new FileSystemXmlApplicationContext(apppath);
		GreetingImpl bean = (GreetingImpl) app.getBean("greetingImpl");
		bean.goodMorning("kk");
	}
}