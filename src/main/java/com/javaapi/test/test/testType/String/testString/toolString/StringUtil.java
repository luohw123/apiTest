package com.javaapi.test.test.testType.String.testString.toolString;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;

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
        String plan = "79=3,80=0,81=3|3*1:1^79=3,80=0,81=3|3*1:1^79=3,80=0,81=3|3*1:1^79=3,80=0,81=3|3*1:1";
        try {
            String encode = URLEncoder.encode(plan, "UTF-8");
            System.out.println(encode);
        } catch (UnsupportedEncodingException e) {
        }

    }

    @Test
    public void testEncodeJczq() {
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
        String plan = "SPF|140926025[-1]=1,140926026=0,140926027=0|3*1:1^SPF|140926025[-1]=1,140926026=0,140926027=0|3*1:1^SPF|140926025[-1]=1,140926026=0,140926027=0|3*1:1^SPF|140926025[-1]=1,140926026=0,140926027=0|3*1:1";
        try {
            String encode = URLEncoder.encode(plan, "UTF-8");
            System.out.println(encode);
        } catch (UnsupportedEncodingException e) {
        }

    }

    @Test
    public void testDecode() {
        String plan = "ddd^=";
        try {
            String decode = URLDecoder.decode(plan, "UTF-8");
            System.out.println(decode);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void testDecodeChineseLang() {
        String plan = "你好";
        try {
            String decode = URLDecoder.decode(plan, "UTF-8");
            System.out.println(decode);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


  

    @Test
    public void testRegx() {
        String content = "sss^sss";
        content = content.replaceAll("\\^", "\r\n");
        System.out.println(content);
    }
    
    @Test
    public void testContains() {
        String string = "FloatL kong";
        String string2 = "FloatL dan";
        String string3 = "FloatL closed";
        String cssClass = "sss FloatL kong";
        String cssClass2 = "sss FloatL dan";
        String cssClass3 = "sss FloatL closed";
        System.out.println(cssClass.contains(string));
        System.out.println(cssClass2.contains(string2));
        System.out.println(cssClass3.contains(string3));

    }

    @Test
    public void testSplit() {
        String string = "200.0-8.00-18.00-12.00-55.00-38.00-42.00-150.0-120.0-150.0-500.0-400.0-600.0-500.0-6.50-6.20-17.00-80.00-80.00-5.30-6.75-7.50-14.00-15.00-27.00-34.00-36.00-70.00-100.0-100.0-200.0";
        String[] split = string.split("-");
        for (String string2 : split) {
            System.out.println(string2);
        }
    }
    @Test
    public void testAntMatcher(){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String pattern = "/*/*pigeon=1";
        String pattern2 = "/*?*pigeon=1";
        String pattern3 = "/*?**pigeon=1";
        String pattern4 = "/**pigeon=1";
        String pattern5 = "/**/*.do*pigeon=1";
        String path = "/pigeon/manager.do?version=1.0&pigeon=1";
        boolean match = antPathMatcher.match(pattern, path);
        boolean match2 = antPathMatcher.match(pattern2, path);
        boolean match3 = antPathMatcher.match(pattern3, path);
        boolean match4 = antPathMatcher.match(pattern4, path);
        boolean match5 = antPathMatcher.match(pattern5, path);
        System.out.println(match);
        System.out.println(match2);
        System.out.println(match3);
        System.out.println(match4);
        System.out.println(match5);
    }
    @Test
    public void test() {
    	String string = ",123,123";
    	string = string.substring(1, string.length());
    	System.out.println(string);
    }

}
