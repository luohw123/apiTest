package com.javaapi.test.Time.joda;

import org.joda.time.Instant;
import org.junit.Test;

public class JodaTimeUtil {

    @SuppressWarnings("static-access")
    @Test
    public void test() {
        Instant in = new Instant();
        System.out.println(in.toString());
        Instant plus = in.plus(1000*60*60);
        System.out.println(plus.toString());
    }

}
