package com.javaapi.test.testProxy.testDynamic.worldImp;

public class WorldImp implements WorldI {

    @Override
    public void speak(String name) {
        System.out.println("world ==>" + name);
    }

}
