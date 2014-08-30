package com.javaapi.test.exception.systemerr;

/**
 * http://jackycheng2007.iteye.com/blog/1473625
 * 
 */
public class TestSystemError {
	public static void main(String[] args) {
		System.out.println("这个标准输出,this is stdout");
		System.err.println("这是标准错误,this is stderror");
		throw new RuntimeException("异常");
	}
}
