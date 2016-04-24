package com.javaapi.test.testUtil.Time.javajdk;

import org.junit.Test;

import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Java 8中新的时间与日期API中的所有类都是不可变且线程安全的，
 * 这与之前的Date与Calendar API中的恰好相反，
 * 那里面像java.util.Date以及SimpleDateFormat这些关键的类都不是线程安全的。
 *
 *
        Instant——它代表的是时间戳
        LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
        LocalTime——它代表的是不含日期的时间
        LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。
        ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。
*/

public class TestJdk8 {

    @Test
    public void testInstant() throws Exception {
        Instant now = Instant.now();
        java.util.Date from = Date.from(now);
        System.out.println("from = " + from);
        Instant instant = from.toInstant();
        boolean equals = instant.equals(now);
        System.out.println("equals = " + equals);
    }

    @Test
    public void testLocaleDate() throws Exception {
        LocalDate today = LocalDate.now();
        LocalDateTime localDateTime = today.atStartOfDay();
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("format = " + format);
    }

    public static void main(String[] args) {
        long l = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.of("Asia/Shanghai")).toEpochSecond();
        System.out.println("l = " + l);
        System.out.println("l = " + System.currentTimeMillis());
        String s = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.of("Asia/Shanghai")).toLocalDateTime().toString();
        System.out.println("s = " + s);
    }

    @Test
    public void testJdk8() {
        long l = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.of("Asia/Shanghai")).toEpochSecond();
        System.out.println("l = " + l);
        System.out.println("l = " + System.currentTimeMillis());
        String s = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.of("Asia/Shanghai")).toLocalDateTime().toString();
        System.out.println("s = " + s);

    }

    @Test
    public void testTime() {
        long l = System.currentTimeMillis();
        System.out.println("l = " + l);
    }


}
