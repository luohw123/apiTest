package com.javaapi.test.spring.zotherFeature.scheduler.quartz.dynamicSpringAdd;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.MethodInvokingJob;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.MethodInvoker;

/**
 * 还未整完
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class ClientDynamicNew implements BeanFactoryAware{
	
	@Autowired
	SchedulerFactoryBean SchedulerFactoryBean;
	
	
	private BeanFactory	beanFactory;
	
	
	@Test
	public void getGetTrigger() throws Exception {
//		String cronExpression = "0,5,10,15,20,25,30,35,40,45,50,55 * * * * ?";
		addJob("0,5,10,15,20,25,30,35,40,45,50,55 * * * * ?");
		addJob("0,1,2,3,4,6,7,8,9,40,45,50,55 * * * * ?");
		addJob("0,1,2,3,4,6,7,8,9,40,45,50,55 * * * * ?");
		try {
			// 休眠十小时
			TimeUnit.HOURS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	private void addJob(String cronExpression) throws ParseException,
			SchedulerException {
		Scheduler scheduler = SchedulerFactoryBean.getScheduler();
		CronTriggerBean trigger = beanFactory.getBean("doTime", CronTriggerBean.class);
		trigger.setCronExpression(cronExpression);
		JobDetail job  =  beanFactory.getBean("jobtask",JobDetail.class);
		System.err.println(job);
		String name = String.valueOf(System.currentTimeMillis());
		job.setName(name);
		trigger.setName(name);
		scheduler.scheduleJob( job, trigger);
	}


	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		 this.beanFactory = beanFactory;
	}
	
	@Test
	public void testGetJob() throws Exception {
		JobDetail job  =  beanFactory.getBean("jobtask",JobDetail.class);
		System.err.println(job);
		
		JobDetail job_tmp  =  (JobDetail) beanFactory.getBean("jobtask");
		System.err.println(job_tmp);
		
		System.err.println(job == job_tmp);

		
		
	}
	
}
