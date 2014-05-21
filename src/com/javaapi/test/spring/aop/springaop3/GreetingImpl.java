package com.javaapi.test.spring.aop.springaop3;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingImpl implements Greeting {

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