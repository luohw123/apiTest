package com.javaapi.test.String.testString.toolString;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;

public class StringUtil {

    @Test
    public void testEncode() {
        // 上下单双 ,标准形式
        //        String plan = "79=3,80=1,81=0|3*1:1^79=3,80=1,81=0|3*1:1";
        // 上下单双 ,非标准形式
        //        String plan = "79→1,80→1,81→2 1 2 3_1^79→1,80→1,81→2 1 2 3_1";
        // 半全场 ,标准形式
        //        String plan = "79=33,80=31,81=30|3*1:1^79=33,80=31,81=30|3*1:1";
        // 半全场 ,非标准形式
        //        String plan = "79→33,80→11,81→31 1 2 3_1^79→33,80→11,81→31 1 2 3_1";
        // 总进球 ,非标准形式
        //        String plan = "79→0,80→1,81→2 1 2 3_1^79→0,80→1,81→2 1 2 3_1";
        //总进球数，标准形式
        //        String plan = "79=1,80=2,81=3|3*1:1^79=1,80=2,81=3|3*1:1";
        // 比分 ,非标准形式
        //        String plan = "79→10,80→11,81→20 1 2 3_1^79→10,80→11,81→20 1 2 3_1^79→10,80→11,81→20 1 2 3_1";
        // 比分 ，标准形式
        //        String plan = "79=33,80=31,81=30|3*1:1^79=33,80=31,81=30|3*1:1^79=33,80=31,81=30|3*1:1";
        // 胜平负，非标准形式
        //                String plan = "79→3,80→1,81→0 1 2 3_1^79→3,80→1,81→0 1 2 3_1";
        // 胜平负，标准形式
        String plan = "79=3,80=0,81=3|3*1:1^79=3,80=0,81=3|3*1:1";
        try {
            String encode = URLEncoder.encode(plan, "UTF-8");
            System.out.println(encode);
        } catch (UnsupportedEncodingException e) {
        }

    }

    @Test
    public void testDecode() {
        String plan = "79%3D3%2C80%3D1%2C81%3D0%7C3%2A1%3A1%5E79%3D3%2C80%3D1%2C81%3D0%7C3%2A1%3A1";
        try {
            String decode = URLDecoder.decode(plan, "UTF-8");
            System.out.println(decode);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
