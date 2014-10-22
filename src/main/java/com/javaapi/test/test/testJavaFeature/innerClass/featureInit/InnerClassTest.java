package com.javaapi.test.test.testJavaFeature.innerClass.featureInit;

public class InnerClassTest {

	/**
	 * 参考 http://blog.csdn.net/songdexv/article/details/6758972
	 */
	public static void main(String[] args) {
		InnerClassTest.Inner inner = new InnerClassTest().new Inner();
		inner.show();
		InnerClassTest.StaticInner staticInner = new InnerClassTest.StaticInner();
		staticInner.show();
	}
	private class Inner {
		public void show() {
			System.out.println("inner class show");
		}
	}

	private static class StaticInner {
		public void show() {
			System.out.println("static inner class show");
		}
	}
}
