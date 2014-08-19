package com.javaapi.test.spring.aop.aspectj.xml2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javaapi.test.spring.aop.aspectj.CustomerBo;

/**
 * aspectj +spring ,xml
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
	
	@Autowired
	CustomerBo customerBo;
	@Test
	public void test(){
		customerBo.addCustomer();
	}
}
