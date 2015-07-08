package com.javaapi.test.spring.zotherFeature.scheduler.quartz.dynamicSpringAdd;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SelfJob implements Job {

	SelfService SelfService;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("nihao");
//		SelfJob object = (SelfJob) context.getMergedJobDataMap().get("selfJob");
//		System.out.println("SelfJob回调SelfJob==>"+this+"========getSelfService==>"+object.getSelfService()+"===spring selfJob==>"+object);
//		extracted();
	}

	private void extracted() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sd.format(new Date());
		System.out.println("任务执行selfJob == >" + format + this);
	}
	
	public String getService() {
		return SelfService.getBussiness();
	}

	public SelfService getSelfService() {
		return SelfService;
	}

	public void setSelfService(SelfService selfService) {
		SelfService = selfService;
	}

	
	
}
