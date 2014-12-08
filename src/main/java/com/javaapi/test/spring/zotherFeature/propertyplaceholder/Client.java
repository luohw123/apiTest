package com.javaapi.test.spring.zotherFeature.propertyplaceholder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
	@Autowired
	@Qualifier("taskJobXml")
	TaskJobXml jobXml;
	@Test
	public void test() {
		System.out.println(jobXml.getTaskName());
	}
}