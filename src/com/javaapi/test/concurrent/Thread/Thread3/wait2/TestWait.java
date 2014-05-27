package com.javaapi.test.concurrent.Thread.Thread3.wait2;

public class TestWait {

	// 创建event Object，以使用它的wait(), notify()等方法 
	private Object event = null; 
	public TestWait() { 
	  // 创建event 
	  event = new Object(); 
	} 
	public static void main(String[] args) {

	  TestWait tester = new TestWait(); 
	  tester.test(); 
	}

	public Object getEvent() { 
	  return event; 
	} 
	public void test() { 
	  // 启动3个等候线程 
	  new Thread(new WaitThread(this)).start(); 
	  new Thread(new WaitThread(this)).start(); 
	  new Thread(new WaitThread(this)).start(); 
	  // 启动通知线程 
	  new Thread(new NotifyThread(this)).start(); 
	} 
	}