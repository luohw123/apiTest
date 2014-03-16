package com.javaapi.test.Thread.Thread2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(11, 100,
                1, TimeUnit.MINUTES, //
                new ArrayBlockingQueue<Runnable>(10000)//
        ) {

            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                printException(r, t);
            }
        };
        threadPoolExecutor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("执行");
                    throw new RuntimeException("错误1");
                } catch (Exception e) {
                    throw new RuntimeException("错误1");
                }

            }
        });
    }

    private static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone())
                    future.get();
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // ignore/reset
            }
        }
        if (t != null)
            System.out.println("error occur");
    }
}
