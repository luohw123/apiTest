package com.javaapi.test.test.testRuntime;

/**
 * @see http://blog.csdn.net/zolalad/article/details/11519327
 *  jvm的-D参数要在主类名之前,程序参数要在主类之后
 *  java -Dsss=6666 -Dkkvalue=333 com.javaapi.test.test.testRuntime.TestProgramArgsAndJvmArgs  
 */
public class TestProgramArgsAndJvmArgs {

	public static void main(String[] args) {
		for(String string: args){
			System.out.println(string);
		}
		// -Dsss=sssvalue

//		 设置系统属性名/值对，运行在此jvm之上的应用程序可用System.getProperty("property")得到value的值。
		System.out.println(System.getenv("kkvalue")); //不能这样获取
		System.out.println(System.getenv("sss"));
		System.out.println(System.getProperty("sss"));// 要这样获取
	}
}
