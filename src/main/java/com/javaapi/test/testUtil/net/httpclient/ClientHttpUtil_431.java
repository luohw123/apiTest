package com.javaapi.test.testUtil.net.httpclient;

import com.javaapi.test.testUtil.fileUtil4.TestYoukuUtil;
import org.junit.Test;

/**
 * Created by user on 16/12/31.
 */
public class ClientHttpUtil_431 {
    @Test
    public void test() {
        String s = HttpUtil_431.get(TestYoukuUtil.YKUrl, null);
        System.out.println("s = " + s);

    }
}
