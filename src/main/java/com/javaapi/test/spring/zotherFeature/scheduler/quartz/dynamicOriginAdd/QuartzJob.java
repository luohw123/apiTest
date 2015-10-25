package com.javaapi.test.spring.zotherFeature.scheduler.quartz.dynamicOriginAdd;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println("nihao");
//        SelfJob object = (SelfJob) context.getMergedJobDataMap().get("selfJob");
        System.out.println("SelfJob回调SelfJob==>"+this);
//		extracted();
    }
}  