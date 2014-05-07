package com.javaapi.test.concurrent.ThreadPool;

import java.util.ArrayList;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class Client {
	 static ExecutorService threadPool1 = Executors.newFixedThreadPool(10);
	 static CompletionService<String> completionService = new ExecutorCompletionService<String>(
             threadPool1);
	 
	 
	 /**
	 * 测试线程池invokeall得使用
	 */
	@Test
	 public void testCase1(){
		
		ArrayList<Thread> list=new ArrayList<Thread>();
		for(int i=0;i<10;i++){
			Thread t=new Thread();
			list.add(t);
		}
		//TODO 未完成
//		threadPool1.invokeAll(tasks);
		
	}
	 
	@Test 
    public  void test() throws InterruptedException,
            ExecutionException {
        for (int i = 0; i < 9; i++) {
            Thread t = new Thread(new UserThread(threadPool1,
                    completionService, i));
            t.start();
        }
    }
}
