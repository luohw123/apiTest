package com.javaapi.test.test.testlog.log.slf4jApi;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SLF4J 1.6.0　以前的版本 ,如果msg含有变量，一般用String.format方法格式化msg.</br>
 * 记住，别把异常放进占位符号里就可以打印完整堆栈</br>
 */
public class TestSlf4j {
	private static transient Logger	logger	= LoggerFactory
												.getLogger(TestSlf4j.class);

	@Test
	public void testLog4jlogger() {
		logger.info("this is log4j style");
		logger.info("test replace in {}", "log4j");
	}
	/**
	 * 异常不在占位符中,无论多少个占位符号都可以打印完整堆栈
	 */
	@Test
	public void testSlf4jException0() {
		logger.info("test replace in {}","exception", new RuntimeException("sss"));
	}
	/**
	 * 异常在占位符中,1个占位符号可以打印完整堆栈
	 */
	@Test
	public void testSlf4jException1() {
		logger.info("test replace in {}", new RuntimeException("sss"));
	}
	/**
	 * 异常在占位符中,2个占位符号以及以上,不会打印堆栈
	 */
	@Test
	public void testSlf4jException2() {
		logger.info("test replace in {} {}","exception", new RuntimeException("sss"));
	}
	
}
