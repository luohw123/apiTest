package com.javaapi.test.Time.javajdk;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class TestCalendar {

	@Test
	public void test() {
		SimpleDateFormat sd = new SimpleDateFormat("u");
		Calendar instance = Calendar.getInstance();
		System.out.println(sd.format(instance.getTime()));
		System.out.println("-----------");
		instance.add(Calendar.DAY_OF_WEEK, 1);
		System.out.println(sd.format(instance.getTime()));
	}
	@Test
	public void testWeekDesc() {
		SimpleDateFormat sd = new SimpleDateFormat("E",Locale.CHINESE);
		String format = sd.format(new Date());
		System.out.println(format);
	}
	/**
	 * 原始得 instance.get(Calendar.DAY_OF_WEEK)</br> 星期天是1,星期六是7</br>
	 */
	@Test
	public void testOrigin() {
		Calendar instance = Calendar.getInstance();
		String day = String.valueOf(instance.get(
				Calendar.DAY_OF_WEEK) - 1);
		System.out.println(day);
	}
}
