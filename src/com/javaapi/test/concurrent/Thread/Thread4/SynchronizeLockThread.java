package com.javaapi.test.concurrent.Thread.Thread4;

import java.util.concurrent.locks.Lock;

/**
 * 1 同步锁,思索的例子, 2 线程通信 :wait,condition,阻塞队列
 * 
 */
public class SynchronizeLockThread extends Thread {
	private int threadNo;
	private Lock lock;

	public SynchronizeLockThread(int threadNo, Lock lock) {
		this.threadNo = threadNo;
		this.lock = lock;
	}

	public void run() {
		lock.lock();
		// 这样会串行执行,这代码
		for (int i = 1; i < 10; i++) {
			System.out.println("No." + threadNo + ":" + i);
		}
		lock.unlock();
	}
}
