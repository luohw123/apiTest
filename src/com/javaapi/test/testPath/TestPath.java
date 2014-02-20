package com.javaapi.test.testPath;

import java.io.File;

import org.junit.Test;


public class TestPath {
	@Test
	public void getPath() {
		ClassLoader cls=ClassLoader.getSystemClassLoader();
		ClassLoader classCls=TestPath.class.getClassLoader();
		ClassLoader threadCls=Thread.currentThread().getContextClassLoader();
		System.out.println(ClassLoader.getSystemResource("").getPath());
		System.out.println(TestPath.class.getClassLoader().getResource("").getPath());
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
		System.out.println(cls==classCls);
		System.out.println(cls==threadCls);
	}
	@Test
	public void getDiff() {
		//输出编译文件夹根目录
		File f = new File(this.getClass().getResource("/").getPath()); 
		System.out.println(f); 
		//输出编译文件夹根路径+包路径
		File f2 = new File(this.getClass().getResource("").getPath()); 
		System.out.println(f2); 
	}
}
