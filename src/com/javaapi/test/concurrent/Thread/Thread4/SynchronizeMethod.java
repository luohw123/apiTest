package com.javaapi.test.concurrent.Thread.Thread4;

public class SynchronizeMethod {
	/**
	 * 1 加了synchronized,这段代码肯定是串行的.
	 * 2 Thread.sleep 是不会释放同步监听器的 
	 * 另外suspend也不会释放,但是最后不要用.
	 */
	public  static synchronized void synchronizedMethod(int threadNo) {
		for (int i = 1; i < 10; i++) {
			try {
//				System.out.println("线程=>"+threadNo+"得到同步监听器");
				System.out.println("No." + threadNo + ":" + i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}
}
