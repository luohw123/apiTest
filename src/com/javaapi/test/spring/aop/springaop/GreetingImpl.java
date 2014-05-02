package com.javaapi.test.spring.aop.springaop;

import org.springframework.stereotype.Component;

@Component("greetingImpl")
public class GreetingImpl implements Greeting {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello! " + name);
    }
}