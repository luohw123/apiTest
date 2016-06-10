package com.javaapi.test.spring.aop.springaop2AutoProxy1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *   只能匹配目标类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
	@Autowired
    @Qualifier("greetingImpl")
    Greeting greeting;

    @Autowired
    @Qualifier("greeting2Impl")
    Greeting greeting2;

	@Test
	public void test() {
		greeting.sayHello("Jack");
		greeting.goodMorning("kk");
		greeting.goodNight("kk");
        System.out.println("========");


        greeting2.sayHello("Jack");
        greeting2.goodMorning("kk");
        greeting2.goodNight("kk");
    }
}