package com.javaapi.test.concurrent.Thread.Thread2;

import java.util.concurrent.TimeUnit;

/**
 * 测试调用run后，到底能不能执行start
 * 
 */
public class Test2 {
	public static void main(String[] args) {
		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("1");
			}
		});
		Thread b = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("2");
			}
		});
	}
}
