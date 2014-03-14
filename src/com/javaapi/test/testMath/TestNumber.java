package com.javaapi.test.testMath;

import java.text.NumberFormat;

import org.junit.Test;

public class TestNumber {

	@Test
	public void testXiaoShu(){
		NumberFormat nf=NumberFormat.getInstance();
		float a=0;
		float b=1.5f;
		System.out.println(nf.getNumberInstance().format(a));
		System.out.println(nf.getNumberInstance().format(b));
	}
}
