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
}
