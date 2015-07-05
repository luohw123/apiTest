package com.javaapi.test.spring.zotherFeature.scheduler.quartz;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("applicationContext.xml")
@ContextConfiguration("applicationContext-simple.xml")
public class Client {

	@Test
	public void test() {
		try {
			// 休眠十小时
			TimeUnit.HOURS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
