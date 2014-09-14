package com.javaapi.test.String.testString;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestRegulerExpression {

	@Test
	public void test() {
		String specialHint = "未开售:胜平负单关,胜平负过关,半全场胜平负单关,半全场胜平负过关";
		String[] forbiddenLotteryTypes = null;
		if (specialHint.trim().contains("未开")) {
			specialHint = specialHint.trim().replaceAll("未开.*:", "").trim();
			specialHint = specialHint.replaceAll("让球胜平负过关", "4071")
					.replaceAll("比分过关", "4073").replaceAll("总进球数过关", "4072")
					.replaceAll("半全场胜平负过关", "4074").replaceAll("胜平负过关", "4076");
			forbiddenLotteryTypes = specialHint.split(",");
		}
		if (forbiddenLotteryTypes == null) {
			return;
		}
		for (String string : forbiddenLotteryTypes) {
			System.out.println(string);
		}
		List<String> asList = Arrays.asList(forbiddenLotteryTypes);
		System.out.println("-----------");
		System.out.println(asList.contains("4074"));
	}
}
