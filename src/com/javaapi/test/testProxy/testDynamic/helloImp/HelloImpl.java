package com.javaapi.test.testProxy.testDynamic.helloImp;

public class HelloImpl implements HelloI {

    @Override
    public void say(String name) {
        System.out.println("Hello! " + name);
    }
}