package com.javaapi.test.spring.aop.springaop2AutoProxy2;

import org.springframework.stereotype.Component;

@Component
public class Greeting2Impl implements Greeting {

	@Override
	public void sayHello(String name) {
		System.out.println("Hello! " + name);
	}

	@Override
	public void goodMorning(String name) {
		System.out.println("Good Morning! " + name);
	}

	@Override
	public void goodNight(String name) {
		System.out.println("Good Night! " + name);
	}
}