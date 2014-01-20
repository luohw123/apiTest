package com.javaapi.test.concurrent.ThreadPool;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) throws InterruptedException,
            ExecutionException {
        ExecutorService threadPool1 = Executors.newFixedThreadPool(10);
        CompletionService<String> completionService = new ExecutorCompletionService<String>(
                threadPool1);

        for (int i = 0; i < 9; i++) {
            Thread t = new Thread(new UserThread(threadPool1,
                    completionService, i));
            t.start();
        }

    }
}
