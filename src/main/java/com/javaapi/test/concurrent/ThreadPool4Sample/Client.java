package com.javaapi.test.concurrent.ThreadPool4Sample;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by user on 16/12/12.
 */
public class Client {

    private ExecutorService es = Executors.newFixedThreadPool(5);

    @Test
    public void test(){

    }
}
