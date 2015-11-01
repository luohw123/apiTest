package com.javaapi.test.concurrent.Thread.Thread5Exception.exception2ThreadUncaughtExceptionHandler;

import java.lang.Thread.UncaughtExceptionHandler;

public class MyUnchecckedExceptionhandler implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("捕获到异常：" + e);
    }

}