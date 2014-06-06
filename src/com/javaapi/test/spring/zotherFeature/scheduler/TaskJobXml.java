package com.javaapi.test.spring.zotherFeature.scheduler;

import org.springframework.stereotype.Service;

@Service
public class TaskJobXml {

	public void job1() {
		System.out.println("任务执行xml");
    }

	public void job_init() {
		System.out.println("init xml");
	}
}