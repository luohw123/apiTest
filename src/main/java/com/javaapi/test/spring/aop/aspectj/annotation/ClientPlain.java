package com.javaapi.test.spring.aop.aspectj.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.javaapi.test.spring.aop.aspectj.CustomerBo;

/**
 * http://pingping0524.iteye.com/blog/262482
 *
 */
public class ClientPlain {
	@Test
	public void test() {
		String path=ClientPlain.class.getResource("").getPath();
		String filename = "file:" +path+"applicationContext.xml";
		ApplicationContext app=new FileSystemXmlApplicationContext(filename);
		CustomerBo customerBo = (CustomerBo) app.getBean("customerBoImp");
		customerBo.addCustomer();
	}
}
