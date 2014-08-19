package com.javaapi.test.testMath;

import org.junit.Test;

public class TestInteger {
    /**
     * 注意Integer
     * @create_time 2014年7月31日 下午3:39:27 
     */
    @Test
    public void test() {
        Integer a = new Integer(2);
        Integer b = new Integer(2);
        System.out.println(a == b);
        Integer aa = 2;
        Integer bb = 2;
        System.out.println(aa == bb);
        Integer aaa = 128;
        Integer bbb = 128;
        System.out.println(aaa == bbb);
    }
}
