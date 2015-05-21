package com.javaapi.test.test.testPath;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.unitils.thirdparty.org.apache.commons.io.IOUtils;

public class PathUtil {

	public static void readLog4j() {
		//不写/, 也是相对于类路径下
		InputStream resourceAsStream = PathUtil.class.getClassLoader().getResourceAsStream("log4j.properties");

//		InputStream resourceAsStream = PathUtil.class.getClassLoader().getResourceAsStream("\\log4j.properties");
		String string = null;
		try {
			string = IOUtils.toString(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(string);
	}
	@Test
	public void testName() throws Exception {
		readLog4j();
	}
}
