package com.javaapi.test.testRuntime;

import java.io.Console;

/**
 * this class is avaiable
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
