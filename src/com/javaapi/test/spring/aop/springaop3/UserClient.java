package com.javaapi.test.spring.aop.springaop3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserClient {

	@Autowired
	Greeting	greeting;

	public void say() {
		greeting.sayHello("hello kk");
	}
}
