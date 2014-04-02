package com.javaapi.test.genericType;

/**
 * 泛型的应用包括 泛型类和泛型方法; 这是泛型方法得应用
 * 
 */
public class TestGeneric1_0 {
	public <T> void test(T t) {
		System.out.println(t.getClass().getName());
	}

	public static void main(String[] args) {
		String t = "aa";
		Integer i = 0;
		Object o = new Object();
		new TestGeneric1_0().test(t);
		new TestGeneric1_0().test(i);
		new TestGeneric1_0().test(o);
	}
}
