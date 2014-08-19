package com.javaapi.test.concurrent.Thread.Thread3;

import java.util.concurrent.TimeUnit;

/**
 * 虽然不想说，但是还是忍不住，首先你要明确interrupt方法的意思，它是设置一个标志来告诉线程已中断</br>
 * 其次它会使得正在进行sleep、wait和join的方法抛出InterruptException异常
 * ，你的Example1中只是设置了标志，而没有检查标志，或者满足抛出异常的条件，而且在你进行了中断标志设置之后并没有去检查该标志，线程当然会自动的执行下去
 * 
 */
public class InteruptThread {
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程执行");
				try {
					TimeUnit.SECONDS.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("-----------------------------------------");
		thread.interrupt();
		try {
			TimeUnit.HOURS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
