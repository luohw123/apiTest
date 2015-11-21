package com.javaapi.test.concurrent.Thread.Thread6Sample;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 15/11/2.
 */
public class Client {
    @Test
    public void test() {
        final ClassA a = new ClassA();
        final ClassB b = new ClassB();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.a1(b);
            }
        },"线程A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                b.b1(a);
            }
        },"线程B").start();
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
