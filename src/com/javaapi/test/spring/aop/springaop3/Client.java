package com.javaapi.test.spring.aop.springaop3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring这种可以对指定类,或者指定接口的所有方法进行拦截.</br>
 * <strong>但是如果我想拦截指定的类得指定方法应该怎么样那?</strong></br>
 * 
 * 应该想 interceptorNames中添加切面(包含advice和pointcut)
 * 就可以对指定方法进行拦截
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
	@Autowired
	Greeting greeting;

	@Test
	public void test() {
		greeting.sayHello("Jack");
		greeting.goodMorning("kk");
		greeting.goodNight("kk");
	}
}