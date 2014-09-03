package com.javaapi.test.test.testJavaFeature.Version_1_7;

import java.io.IOException;

import org.junit.Test;

/**
 * 1.7中支持的新语法
 *
 */
public class Client {

	/**
	 * 在数字中使用下划线</br>
	 * 注意：在把字符串传进Switch case之前，别忘了检查字符串是否为Null。
	 */
	@Test
	public void test(){
		int billion = 1_000_000_000;
		System.out.println(billion);
	}
	/**
	 * 你可用作二进制字符前加上 0b 来创建一个二进制类型。
	 */
	@Test
	public void testBinary(){
		int binary = 0b1001_1001;
		System.out.println(binary);
	}
	/**
	 * 对字符串进行switch case
	 */
	@Test
	public void testSwitchString() {
		String availability = "available";
		switch (availability) {
		case "available":
			// code
			break;

		case "unavailable":
			// code
			break;

		case "merged":
			// code

		default:
			// code
			break;
		}
	}
	/**
	 * 一个catch 多个异常
	 */
	@Test
	public void testMultiplyException() {
		try {
//			Here comes your code….
			throw new IOException();
			}
			catch(IOException  | NullPointerException | IllegalArgumentException e) {
				e.printStackTrace();
			}
	}
	/**
	 * FileSystem的API支持

Java7对文件系统支持较为广泛，无论是copy， move，delete等操作，还是文件系统的监视，递归，获取文件的元数据都有了大大的提高。
	 */
	@Test
	public void testFileApi() {
		//TODO
	}
}
