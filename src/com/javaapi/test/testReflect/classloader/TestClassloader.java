package com.javaapi.test.testReflect.classloader;

import org.junit.Test;

public class TestClassloader {
	static {
		System.out.println(TestClassloader.class.getName());
	}

	/**
	 * 获取classloader得方式
	 */
	@Test
	public void gClassloader() {
		// 获取当前线程得类加载器,当前线程得类加载器可以通过程序员设置,从而摆脱类加载机制得限制
		Thread.currentThread().getContextClassLoader();
		// 通过当前类获取类加载器
		TestClassloader.class.getClassLoader();
		// 使用类调用类得类加载器
		// callingClass.getClassLoader().loadClass(className);
	}

	/**
	 * 获取Class得方式
	 */
	@Test
	public void gClass() throws ClassNotFoundException {
		String loader1 = TestClassloader.class.getClassLoader().getClass()
				.toString();
		String loader2 = new TestClassloader().getClass().getClassLoader()
				.toString();
		String loader3 = Class
				.forName(
						"com.javaapi.test.testReflect.classloader.TestClassloader")
				.getClass().getClassLoader().toString();
		System.out.println(loader1);
		System.out.println(loader2);
		System.out.println(loader3);
	}

	/**
	 * 这俩种方式都会将类初始化
	 */
	@Test
	public void testClassloader() {
		try {
			System.out.println("start");
			Class.forName("com.javaapi.test.testReflect.classloader.Dummy");
			// 用当前类加载器加载当前类,true
			System.out.println("-----------");
			// 不会重复类初始化
			Class.forName("com.javaapi.test.testReflect.classloader.Dummy",
					true, TestClassloader.class.getClassLoader());
			System.out.println("end");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 不会进行类初始化
	 */
	@Test
	public void testClassloader2() {
		try {
			// 用当前类加载器加载当前类,false
			System.out.println("start");
			Class.forName("com.javaapi.test.testReflect.classloader.Dummy",
					false, TestClassloader.class.getClassLoader());
			TestClassloader.class.getClassLoader().loadClass(
					"com.javaapi.test.testReflect.classloader.Dummy");
			System.out.println("-----------");
			System.out.println("end");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
