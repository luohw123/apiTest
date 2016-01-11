package com.javaapi.test.test.testType.Number;

import org.junit.Test;

import java.util.UUID;

/**
 * GUID 在java中为uuid
 */
public class TestRandom {
    @Test
    public void test(){
        UUID uuid = UUID.randomUUID();
        System.out.println("uuid = " + uuid.toString());
    }
}
