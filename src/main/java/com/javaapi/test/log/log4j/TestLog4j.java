package com.javaapi.test.log.log4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog4j {
	private static transient Logger	logger	= LoggerFactory
												.getLogger(TestLog4j.class);

	@Test
	public void testLog4jlogger() {
		logger.info("this is log4j style");
		logger.info("test replace in {}", "log4j");
	}

}
