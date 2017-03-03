package com.javaapi.test.concurrent.ThreadPool4Sample;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 16/12/12.
 */
public class ClientNoLimitWorkQueue {


    @Test
    public void test() {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(5, 10, 5000, TimeUnit.DAYS, new LinkedBlockingQueue<>());
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            tpe.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.DAYS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        System.out.println("done = ");

    }


}
