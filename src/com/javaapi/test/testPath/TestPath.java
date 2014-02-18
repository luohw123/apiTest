package com.javaapi.test.testPath;


public class TestPath {
public static void main(String[] args) {
	ClassLoader cls=ClassLoader.getSystemClassLoader();
	ClassLoader classCls=TestPath.class.getClassLoader();
	ClassLoader threadCls=Thread.currentThread().getContextClassLoader();
	System.out.println(ClassLoader.getSystemResource("").getPath());
	System.out.println(TestPath.class.getClassLoader().getResource("").getPath());
	System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
	System.out.println(cls==classCls);
	System.out.println(cls==threadCls);
}
}
