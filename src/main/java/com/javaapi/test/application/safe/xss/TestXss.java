package com.javaapi.test.application.safe.xss;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

public class TestXss {
	@Test
	public void test() {
		String name = null;
		name="名字<script>alert('a')</script>";
		String after=StringEscapeUtils.escapeXml(name);
		System.out.println(after);
	}
}
