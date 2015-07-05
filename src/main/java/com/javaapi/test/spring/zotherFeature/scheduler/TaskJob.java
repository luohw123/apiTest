package com.javaapi.test.spring.zotherFeature.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

//@Service
public class TaskJob {

	public void job1() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sd.format(new Date());
		System.out.println("任务执行xml == >" +format);
    }
}