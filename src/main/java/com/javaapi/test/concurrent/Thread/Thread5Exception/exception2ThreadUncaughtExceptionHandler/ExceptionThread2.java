package com.javaapi.test.concurrent.Thread.Thread5Exception.exception2ThreadUncaughtExceptionHandler;

public class ExceptionThread2 implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by" + t);
        System.out.println("eh=" + t.getUncaughtExceptionHandler());
        throw new RuntimeException("抛出运行时异常");
    }
}