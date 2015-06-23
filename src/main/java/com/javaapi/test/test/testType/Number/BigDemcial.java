package com.javaapi.test.test.testType.Number;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDemcial {
	@Test
	public void testName() throws Exception {
		BigDecimal bigDecimal = new BigDecimal(-5);
		BigDecimal bigDecimal2 = new BigDecimal(10);
		BigDecimal subtract = bigDecimal2.subtract(bigDecimal);
		System.out.println(subtract);
	}

	@Test
	public void test2() throws Exception {
		BigDecimal bigDecimal = new BigDecimal(10000);
		BigDecimal bigDecimal2 = new BigDecimal(10);
		System.out.println(bigDecimal.compareTo(bigDecimal2));
	}
	
	/**
	 * 字符串格式不符合，会报错
	 * java.lang.NumberFormatException
	 */
	@Test
	public void testRegular() throws Exception {
		BigDecimal bigDecimal = new BigDecimal("100.22..00");
		System.out.println(bigDecimal);
	}
	@Test
	public void RightDoubleToBigdecimal() throws Exception {
		double val = 10.08;
		BigDecimal value = new BigDecimal(String.valueOf(val));
		System.out.println(value);
	}
	@Test
	public void RightDoubleToBigdecimal2() throws Exception {
		double val = 10.08;
		BigDecimal value = BigDecimal.valueOf(val);
		System.out.println(value);
	}
	@Test
	public void RightBigdecimalCompare() throws Exception {
		double val = 10.08;
		BigDecimal value = new BigDecimal(String.valueOf(val));
		BigDecimal value2 = BigDecimal.valueOf(val);
		System.out.println(value.equals(value2) );
		System.out.println(value.compareTo(value2));
	}
	@Test
	public void WrongToBigdecimal() throws Exception {
		double val = 10.08;
		BigDecimal value = new BigDecimal(val);
		System.out.println(value);
	}
}
