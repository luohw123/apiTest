package com.javaapi.test.spring.zotherFeature.namespaceUtil;

import java.util.Properties;

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
	@Qualifier("selfPro")
	private Properties properties;

	@Test
	public void testName() throws Exception {
		String property = properties.getProperty("nihao");
		System.err.println(property);
	}
}
