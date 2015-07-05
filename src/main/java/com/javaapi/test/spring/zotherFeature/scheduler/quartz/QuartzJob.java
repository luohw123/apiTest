package com.javaapi.test.spring.zotherFeature.scheduler.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzJob
{

    public void work()
    {
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sd.format(new Date());
		System.out.println("任务执行 == >" +format);
    }
}