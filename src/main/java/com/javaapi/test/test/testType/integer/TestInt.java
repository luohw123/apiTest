package com.javaapi.test.test.testType.integer;

import org.junit.Test;

import java.io.Serializable;

/**
 * Created by user on 16/3/24.
 */
public class TestInt {

    private  int a = 111_222;

    @Test
    public void testXiaHuaXian(){
        System.out.println("a = " + a);
    }


    @Test
    public void test(){
        int a = 0;
//        if (a == null) {
//
//        }

    }


    @Test
    public void testSeri() {
        int a = 11;
        ser(a);
    }
    public void ser(Serializable a ){
        System.out.println("a = " + a);
    }
}
