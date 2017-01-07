package com.javaapi.test.testUtil.net.httpclient;

import org.junit.Test;

/**
 * Created by user on 16/12/31.
 */
public class ClientHttpUtil_431 {
    @Test
    public void test() {
        String s = HttpUtil_431.get("https://aplay-vod.cn-beijing.aliyuncs.com/cloud/videos/show.json", null);
        System.out.println("s = " + s);

    }
}
