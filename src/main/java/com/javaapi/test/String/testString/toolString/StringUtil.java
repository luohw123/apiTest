package com.javaapi.test.String.testString.toolString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
    public void testFile() throws IOException, InterruptedException {
        long tempRunCount = 1200;
        long tempSleep = 200;
        String path = "/mfs/ShareFile/news/0/33/10001/test-ssi-sinanba.shtml";
        File pathFile = new File(path);
        String tmpPath = path + ".tmp";
        File tmp = new File(tmpPath);
        for (long i = 0; i < tempRunCount; i++) {
            FileOutputStream fos = new FileOutputStream(tmp, false);
            fos.write(new String(new Date().getTime() + "aaaaa" + i).getBytes());
            fos.close();
            if (tmp != null) {
                if (!tmp.renameTo(pathFile)) {
                    //we may want to retry if move fails
                    tmp.delete();
                    FileOutputStream ff = new FileOutputStream(pathFile, false);
                    ff.write(new String(new Date().getTime() + "aaaaa" + i).getBytes());
                    ff.close();
                }
            }

            Thread.sleep(tempSleep);
        }

    }

    @Test
    public void testArrayString() {
        List<String> list = new ArrayList<>();
        list.add("你好");
        list.add("世界");
        String join = StringUtils.join(list.toArray(), "，");
        System.out.println(join);
    }

    /**
     * 
     * http://blog.csdn.net/lonely_fireworks/article/details/7962171
     */
    @Test
    public void testStringFormat() {
        String format = String.format("%s:", "nihao");
        System.out.println(format);

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

}
