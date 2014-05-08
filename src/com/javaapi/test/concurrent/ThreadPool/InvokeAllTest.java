package com.javaapi.test.concurrent.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokeAllTest {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        ArrayList<Callable<Integer>> callers = new ArrayList<Callable<Integer>>();
        int len = 10;
        for (int i = 0; i < len; i++) {
            callers.add(new Callable<Integer>() {
                public Integer call() throws Exception {
                    String name = Thread.currentThread().getName();
                    System.out.println(name);
                    return Float.valueOf(Thread.currentThread().getId()).intValue();
                }
            });
        }
        //这种方式是需要所有的线程都执行完毕才可以往下执行
        List<Future<Integer>> futrues=   pool.invokeAll(callers);
        System.out.println("done!");
        for(Future<Integer> futrue:futrues){
        	System.out.println(futrue.get());
        }
        pool.shutdown();
    }
}
