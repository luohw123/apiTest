package com.javaapi.test.buisness.businessSkill;

import org.junit.Test;

/**
 * Created by user on 16/5/30.
 */
public class Client {
    @Test
    public void test() {
        Source source = new Source(1);
        if (source.getStatus() == Source.SourceStatus.SUCCESS) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    @Test
    public void testCheck() throws Exception {
        Source source = new Source(1);
        boolean check = source.check(1);
        System.out.println("check = " + check);

    }
}
