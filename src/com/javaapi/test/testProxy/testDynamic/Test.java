package com.javaapi.test.testProxy.testDynamic;

import java.lang.reflect.Proxy;

import com.javaapi.test.testProxy.testDynamic.helloImp.HelloI;
import com.javaapi.test.testProxy.testDynamic.helloImp.HelloImpl;
import com.javaapi.test.testProxy.testDynamic.worldImp.WorldI;
import com.javaapi.test.testProxy.testDynamic.worldImp.WorldImp;

public class Test {
    public static void main(String[] args) {
        HelloI hello = new HelloImpl();
        WorldI world = new WorldImp();
        DynamicProxy helloDynamicProxy = new DynamicProxy(hello);
        DynamicProxy worldDynamicProxy = new DynamicProxy(world);

        HelloI helloProxy = (HelloI) Proxy.newProxyInstance(hello.getClass()
                .getClassLoader(), hello.getClass().getInterfaces(),
                helloDynamicProxy);
        helloProxy.say("Jack");

        WorldI worldProxy = (WorldI) Proxy.newProxyInstance(world.getClass()
                .getClassLoader(), world.getClass().getInterfaces(),
                worldDynamicProxy);
        worldProxy.speak("jack");
    }
}
