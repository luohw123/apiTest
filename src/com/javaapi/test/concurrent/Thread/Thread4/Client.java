package com.javaapi.test.concurrent.Thread.Thread4;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * 关于线程同步的测试用例
 * 
 */
public class Client {

	/**
	 * 同步代码块
	 */
	@Test
	public void synchronzeBlock() {
		String lock = new String("lock");
		for (int i = 1; i < 10; i++) {
			new SynchronizeBlockThread(i, lock).start();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 同步方法
	 */
	@Test
	public void synchronizeMethod() {
		String lock = new String("lock");
		for (int i = 1; i < 10; i++) {
			new SynchronizeMethodThread(i, lock).start();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 同步锁
	 * http://blog.csdn.net/fw0124/article/details/6672522
	 * http://tenyears.iteye.com/blog/48750
	 * ReadWriteLock
	 */
	@Test
	public void synchronizeLock() {
		ReentrantLock lock = new ReentrantLock();
		for (int i = 1; i < 10; i++) {
			new SynchronizeLockThread(i, lock).start();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
