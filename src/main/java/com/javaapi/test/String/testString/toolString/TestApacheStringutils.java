package com.javaapi.test.String.testString.toolString;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestApacheStringutils {

	@Test
	public void test() {
		String str = "helloworld";
		String center = StringUtils.center(str, 10+str.length(), "#");
		System.out.println(center);
	}
}
