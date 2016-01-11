package com.javaapi.test.testUtil.Time.apache;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestTime {
	
	public static void main(String[] args) throws ParseException {
        long start  = new SimpleDateFormat("yyyyMMdd").parse("20130101").getTime();
        long end = new SimpleDateFormat("yyyyMMdd").parse("20140101").getTime();
        System.out.println(DurationFormatUtils.formatDuration(end-start,"MM" ));
        System.out.println(DurationFormatUtils.formatDuration(1 * 60 * 60 * 1000, "HH-mm-ss"));
    }
    /**
     * 
     * 格式化间隔时间  ，使之可读
     * @throws ParseException 
     */
    @Test
    public void formatDuration() throws ParseException {
    	long start  = new SimpleDateFormat("yyyyMMdd").parse("20130101").getTime();
    	long end = new SimpleDateFormat("yyyyMMdd").parse("20140101").getTime();
    	System.out.println(DurationFormatUtils.formatDuration(end-start,"MM" ));
        System.out.println(DurationFormatUtils.formatDuration(1 * 60 * 60 * 1000, "HH-mm-ss"));
    }

    /**
     * 
     *  格式化间隔时间 ，使之可读
     */
    @Test
    public void formatPeriod() {
        long start = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println(DurationFormatUtils.formatPeriod(start, end, "HH-mm-ss"));
    }
    @Test
    public void test(){
        long unixTime=1450592982;
        Date date = new Date(unixTime * 1000);
        String yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("yyyyMMddHHmmss = " + yyyyMMddHHmmss);
    }
}
