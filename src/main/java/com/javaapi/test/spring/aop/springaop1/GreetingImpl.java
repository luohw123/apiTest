package com.javaapi.test.spring.aop.springaop1;

import org.springframework.stereotype.Component;

@Component
public class GreetingImpl implements Greeting {

	@Override
	public void sayHello(String name) {
		System.out.println("hello");
	}

	@Override
	public void sayGoodBy(String name) {
		System.out.println("sayGoodBy");
	}

}