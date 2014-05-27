package com.javaapi.test.concurrent.Thread.Thread3.condition;

import java.util.concurrent.TimeUnit;


/**http://blog.csdn.net/ghsau/article/details/7481142
 * condition ,用于帮助lock实现同步监听机制</br>
 *   在Condition中，用await()替换wait()，用signal()替换notify()，用signalAll()替换notifyAll()，传统线程的通信方式，Condition都可以实现，</br>
 *   这里注意，Condition是被绑定到Lock上的，要创建一个Lock的Condition必须用newCondition()方法。
 *   要调用同一把锁的await和signal才能生效
 *
 */
public class ThreadTest2 {
	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				threadExecute(business, "sub");
			}
		}).start();
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadExecute(business, "main");
	}	
	public static void threadExecute(Business business, String threadType) {
		for(int i = 0; i < 10; i++) {
			try {
				if("main".equals(threadType)) {
					business.main(i);
				} else {
					business.sub(i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
