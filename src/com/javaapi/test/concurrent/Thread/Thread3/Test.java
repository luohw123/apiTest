package com.javaapi.test.concurrent.Thread.Thread3;

/**
 * 1 线程状态
 *
 */
public class Test {
public static void main(String[] args) {
	Thread thread=new Thread(new Runnable() {
		@Override
		public void run() {
		System.out.println(Thread.currentThread().getName());	
		}
	});
	//下面俩个方法是用于挂起,恢复线程的.但是不要调用suspend,容易导致似锁(该方法已经在jdk7中被废弃)
//	thread.suspend();
//	thread.resume();
	//调用stop会让线程进入丝网状态,也就是永远不会再调用了,容易导致似锁(该方法已经在jdk7中被废弃)
//	thread.stop();
}
}
