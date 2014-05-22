package com.javaapi.test.spring.aop.springaop3;


public class UserClient {

	public Greeting getGreeting() {
		return greeting;
	}

	public void setGreeting(Greeting greeting) {
		this.greeting = greeting;
	}

	Greeting	greeting;

	public void say() {
		greeting.sayHello("hello kk");
	}

}
