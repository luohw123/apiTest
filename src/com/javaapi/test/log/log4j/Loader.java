package com.javaapi.test.log.log4j;

import java.io.InputStream;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;

/**
 * https://github.com/liweinan/java-snippets.git
 */
public class Loader {
	public static void main(String args[]) throws Exception {
		// load by code
		{
			Logger myLogger = Logger.getLogger("net.bluedash.log4j");
			SimpleLayout simpleLayout = new SimpleLayout();
			myLogger.setLevel(Level.DEBUG);
			// FileAppender fileAppender = new FileAppender(simpleLayout,
			// "play.out");
			// myLogger.addAppender(fileAppender);

			ConsoleAppender consoleAppender = new ConsoleAppender(simpleLayout);
			myLogger.addAppender(consoleAppender);

			myLogger.debug("eating apple...");
			myLogger.fatal("bad apple");
		}

		// load by configuration file
		{
			InputStream config = Loader.class.getClassLoader()
					.getResourceAsStream("META-INF/log4j.properties");
			PropertyConfigurator.configure(config);
			Logger propLogger = Logger.getLogger("propLogger");
			propLogger.info("I'm dancing!");
		}
	}
}
