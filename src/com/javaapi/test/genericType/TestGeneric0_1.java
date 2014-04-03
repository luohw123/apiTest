package com.javaapi.test.genericType;

/**
 * 泛型方法
 * 
 */
public class TestGeneric0_1 {
	public <T> void test(T t) {
		System.out.println(t.getClass().getName());
	}

	public static void main(String[] args) {
		String t = "aa";
		Integer i = 0;
		Object o = new Object();
		new TestGeneric0_1().test(t);
		new TestGeneric0_1().test(i);
		new TestGeneric0_1().test(o);
	}
}
