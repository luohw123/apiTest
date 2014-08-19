package com.javaapi.test.Time.apache;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.junit.Test;

public class TestTime {

    /**
     * 
     * 格式化间隔时间  ，使之可读
     */
    @Test
    public void formatDuration() {
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
}
