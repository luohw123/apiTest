package com.javaapi.test.Time.joda;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.junit.Test;

/**
 * An interval in Joda-Time represents an interval of time from one millisecond instant to another instant
 * duration ,interval 之间的区别，duration的是持续时间，interval是时间间隔，举些例子吧，我被弄糊涂了</br>
 * http://zhidao.baidu.com/link?url=eZtAHGCL3EM3OrpBdzKj6LAWnxZSQkG7PzUqZ7BT347wuyuI_VKCv34Mz7j6XsC0OhmEhi2PP_cJa6dbSag86a</br>
 *隔一分钟打一次雷，每次雷持续响半分钟。</br>
一分钟就是interval，半分钟就是duratioin</br>

 */
public class JodaIntervalsTest {

	@Test
	public void test() {
		// interval from start to end
		DateTime start = new DateTime(2014, 1, 1, 0, 0, 0, 0);
		DateTime end = new DateTime(2015, 1, 1, 0, 0, 0, 0);
		Interval interval = new Interval(start, end);
		//---------
		dealInterval(interval);
	}

	private void dealInterval(Interval interval) {
		DateTime start = interval.getStart();
		DateTime end = interval.getEnd();
		Chronology chrono = interval.getChronology();
		Duration duration = interval.toDuration();
		Period period = interval.toPeriod();
		System.out.println(duration.getStandardDays());
		System.out.println(period.getDays());
	}
}
