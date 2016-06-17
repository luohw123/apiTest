package com.javaapi.test.test.testBit;

import org.junit.Test;

public class TestBit2 {
    @Test
    public void test(){
        Integer a = 3^2;
        System.out.println("a = " + Integer.toBinaryString(a));
        Integer b = 4^2;
        System.out.println("b = " + Integer.toBinaryString(b));
    }

}
