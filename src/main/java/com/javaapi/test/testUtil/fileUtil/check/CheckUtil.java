package com.javaapi.test.testUtil.fileUtil.check;

import com.javaapi.test.testUtil.net.httpclient.HttpUtil_412;
import org.junit.Test;

import java.io.*;

/**
 * Created by user on 15/11/13.
 */
public class CheckUtil {
    @Test
    public void test() throws IOException {
        InputStream in = new FileInputStream(new File("/Users/user/program/shell/transdata/checkWrong/download_failedAfter.txt"));
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedInputStream = new BufferedReader(reader);
        String temp = null;

        FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/transdata/checkWrong/resultAfter.txt"));
        OutputStreamWriter writer = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(writer);
        int i = 0;
        while ((temp = bufferedInputStream.readLine()) != null) {

            String s = HttpUtil_412.get("http://hengyang.acfun.tv/video/getH5DownloadByVid.aspx?vid=" + temp, null);
            bw.write(temp+"==="+s+"\r\n");
            if (i % 10 == 0) {
                System.out.println("i进度 = " + i);

            }
            i++;
        }
        in.close();
        reader.close();
        bufferedInputStream.close();

    }
}
