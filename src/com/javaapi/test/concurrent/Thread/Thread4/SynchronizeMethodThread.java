package com.javaapi.test.concurrent.Thread.Thread4;

/**
 * 1、对于同步的方法或者代码块来说，必须获得对象锁才能够进入同步方法或者代码块进行操作；
 * 
 * 
 * 2、如果采用method级别的同步，则对象锁即为method所在的对象，如果是静态方法，对象锁即指method所在的 Class对象(唯一)；
 * 
 * 
 * 3、对于代码块，对象锁即指synchronized(abc)中的abc；
 * @see http://enetor.iteye.com/blog/986623
 */
/**
 * 2 同步方法
 *
 */
public class SynchronizeMethodThread extends Thread {
	private int threadNo;
	private String lock;

	public SynchronizeMethodThread(int threadNo, String lock) {
		this.threadNo = threadNo;
		this.lock = lock;
	}

	public void run() {
		// 这样会串行执行,这代码
		synchronizedMethod();
	}

	private synchronized void synchronizedMethod() {
			for (int i = 1; i < 10; i++) {
				System.out.println("No." + threadNo + ":" + i);
			}
	}
}
