package com.javaapi.test.testMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;

import org.junit.Test;

public class TestNumber {

	/**
	 * 显示,可以用这个处理
	 */
	@Test
	public void testXiaoShu() {
		NumberFormat nf = NumberFormat.getInstance();
		float a = 0;
		float b = 1.5f;
		System.out.println(nf.format(a));
		System.out.println(nf.format(b));
	}

	/**
	 * 具体计算,可以计算之后再进行四舍五入. precision是指的是整个数字精确后的长度，而非理解的精度（scale）。
	 */
	@Test
	public void testJiSuan() {
		System.out.println(new BigDecimal(1332.1222, new MathContext(7,
				RoundingMode.CEILING)));
		System.out.println(new BigDecimal(1332.1222, new MathContext(8,
				RoundingMode.CEILING)));
		System.out.println(new BigDecimal(1332.1222, new MathContext(9,
				RoundingMode.CEILING)));
		System.out.println(new BigDecimal(1332.1222, new MathContext(10,
				RoundingMode.CEILING)));

	}

	/**
	 * 正确对数字进行四舍五入得方式.对数字转字符串四舍五入参考numberformat
	 */
	@Test
	public void testHalfUp() {
		System.out.println("---------------------");
		System.out.println(new BigDecimal(1332.124).setScale(2,
				RoundingMode.HALF_UP));
		System.out.println(new BigDecimal(1332.125).setScale(2,
				RoundingMode.HALF_UP));
	}
}
