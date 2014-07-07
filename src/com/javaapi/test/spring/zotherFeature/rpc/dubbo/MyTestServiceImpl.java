package com.javaapi.test.spring.zotherFeature.rpc.dubbo;

public class MyTestServiceImpl implements MyTestService {

    public String sendMessage(String name) {
        return "hello" + name;
    }

}
