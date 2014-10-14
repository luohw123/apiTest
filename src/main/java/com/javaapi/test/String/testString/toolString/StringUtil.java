package com.javaapi.test.String.testString.toolString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

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

}
