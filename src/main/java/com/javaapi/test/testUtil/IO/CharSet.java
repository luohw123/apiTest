package com.javaapi.test.testUtil.IO;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class CharSet {
	@Test
	public void charset() {
		String charset = StandardCharsets.UTF_8.displayName();
		System.out.println(charset);
	}
}
