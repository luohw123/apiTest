package com.javaapi.test.spring.zotherFeature.scheduler.quartz.dynamicSpringAdd;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SelfJob  implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
	 	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sd.format(new Date());
			System.out.println("任务执行selfJob == >" +format);
	}

}
