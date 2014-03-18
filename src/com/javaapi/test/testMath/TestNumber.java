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
	 * 具体计算,可以计算之后再进行四舍五入
	 */
	@Test
	public void testJiSuan() {
		System.out.println(new BigDecimal(1332.1222, new MathContext(2,
				RoundingMode.CEILING)));
	}
}
