package com.javaapi.test.spring.zotherFeature.scheduler.springscheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

//@Service
public class TaskJobXml {

	public void job1() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sd.format(new Date());
		System.out.println("任务执行xml == >" +format+"==instance==>"+this);
    }

	public void job_init() {
		System.out.println("init xml");
	}
}