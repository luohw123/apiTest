package com.javaapi.test.exception;

import org.apache.commons.lang3.text.StrBuilder;

public class TestDetailStack {
	public static void main(String[] args) {

		try {
			frame1();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("----------");
			withErrorAndLog("123", "123", "321", e);
		}
	}

	public static void frame1() {
		frame2();
	}

	public static void frame2() {
		frame3();
	}

	public static void frame3() {
		throw new RuntimeException();
	}
	public static void withErrorAndLog(
			String errorCode, String errorMsg, String inputParamWhereCatch,
			Throwable e) {
		StrBuilder sb = new StrBuilder(512);
		sb.append("{errorCode:'").append(errorCode).append("', errorMsg:'")
				.append(errorMsg).append("', inputParamWhereCatch:'")
				.append(inputParamWhereCatch).append("'}");
		// System.out.println(sb.toString());

		sb.clear();
		for (StackTraceElement line : e.getStackTrace()) {
			// sb.append(line);
			sb.append("\tat ").append(line).append("\r\n");
		}
		System.out.println(sb.toString());
	}
}
