package com.javaapi.test.concurrent.Thread.Thread4;

import org.junit.Test;

public class Client {

	@Test
	public void synchronze(){
		String lock = new String("lock");
		for (int i = 1; i < 10; i++) {
			new SynchronizeThread(i, lock).start();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	@Test
	public void synchronzeMethod(){
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
}
