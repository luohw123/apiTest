package com.javaapi.test.testUtil.fileUtil4;

import com.javaapi.test.testUtil.net.httpclient.HttpUtil_431;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by user on 17/2/3.
 */
public class TestYoukuUtil {
    public static final String YKUrl = "";
    public static String CLIENT_ID = "";

    @Test
    public void test(){
        HashMap<String, String> param = new HashMap<>();
        param.put("client_id", CLIENT_ID);
        param.put("video_id", "CNTc1OTMwOA==");
        String result = HttpUtil_431.get(YKUrl, param);
        System.out.println("result = " + result);
    }
}
