package com.javaapi.test.testPath;

public class Test {
public static void main(String[] args) {
	ClassLoader cls=ClassLoader.getSystemClassLoader();
	ClassLoader classCls=Test.class.getClassLoader();
	ClassLoader threadCls=Thread.currentThread().getContextClassLoader();
	System.out.println(ClassLoader.getSystemResource("").getPath());
	System.out.println(Test.class.getClassLoader().getResource("").getPath());
	System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
	System.out.println(cls==classCls);
	System.out.println(cls==threadCls);
}
}
