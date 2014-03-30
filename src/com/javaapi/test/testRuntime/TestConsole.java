package com.javaapi.test.testRuntime;

import java.io.Console;

/**
 * only use "java TestConsole" can be avaiable
 * 
 */
public class TestConsole {
	public static void main(String[] args) {
		Console console = System.console();
		if (console == null) {
			System.err.println("Console not available");
			return;
		}
		console.printf("%s, %s", "string", "123");
	}
}
