package com.javaapi.test.concurrent.Thread.Thread3;

/**
 * 2 Thread,join的使用
 *
 */
public class JoinThread implements Runnable {
	public static int a = 0;

	public void run() {
		for (int k = 0; k < 5; k++) {
			a = a + 1;
		}
	}

	public static void main(String[] args) throws Exception {
		Runnable r = new JoinThread();
		Thread t = new Thread(r);
		t.start();
		//1调用t.join()后,t线程执行完成后才会执行下面的输出,始终输出5
		//2 如果注释掉t.join(),则并不是始终输出5
//		t.join();
		System.out.println("end");
		System.out.println(a);
	}
}