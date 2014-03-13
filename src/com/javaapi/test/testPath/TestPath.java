package com.javaapi.test.testPath;

import java.io.File;

import org.junit.Test;

public class TestPath {

	static {
		// 静态代码块中如何获得,Object.class更通用一些,但是好像有些问题
		// String path = Object.class.getResource("").getPath();
		// 写自身得类不会报错，在junit下不会报错
		String path = TestPath.class.getResource("").getPath();
		System.out.println("static ==>" + path);
	}

	@Test
	public void getPath() {
		ClassLoader cls = ClassLoader.getSystemClassLoader();
		ClassLoader classCls = TestPath.class.getClassLoader();
		ClassLoader threadCls = Thread.currentThread().getContextClassLoader();
		System.out.println(ClassLoader.getSystemResource("").getPath());
		System.out.println(TestPath.class.getClassLoader().getResource("")
				.getPath());
		System.out.println(Thread.currentThread().getContextClassLoader()
				.getResource("").getPath());
		System.out.println(cls == classCls);
		System.out.println(cls == threadCls);
	}

	@Test
	public void getDiff() {
		// 输出编译文件夹 根目录
		File f = new File(this.getClass().getResource("/").getPath());
		System.out.println(f);
		// 输出编译文件夹 根目录+包路径
		File f2 = new File(this.getClass().getResource("").getPath());
		System.out.println(f2);
	}

	// public void getPath2() throws IOException{
	// File directory = new File("");//参数为空
	// String courseFile = directory.getCanonicalPath() ;
	// System.out.println(courseFile);
	// }
	public static void main(String[] args) {
		String path = Object.class.getResource("").getPath();
		// System.out.println(path);
	}
}
