package com.javaapi.test.spring.zotherFeature.scheduler.quartz.dynamicSpringAdd;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 还未整完
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class ClientDynamic {
	
	@Autowired
	SchedulerFactoryBean SchedulerFactoryBean;
	@Test
	public void testDynamic() throws Exception {
		Scheduler scheduler = SchedulerFactoryBean.getScheduler();
        CronTrigger trigger = new CronTrigger("cronTrigger", "triggerGroup");// 触发器名,触发器组  
        String cronExp = "10,15,20,25,30,35,40,45,50,55 * * * * ?";
		trigger.setCronExpression(cronExp);// 触发器时间设定  
		JobDetail jobDetail = new JobDetail();
		jobDetail.setJobClass(SelfJob.class);
		jobDetail.setName("tmp");
		scheduler.scheduleJob(jobDetail, trigger );
	      // 启动  
        if (!scheduler.isShutdown()) {  
        	scheduler.start();  
        }  
		try {
			// 休眠十小时
			TimeUnit.HOURS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
