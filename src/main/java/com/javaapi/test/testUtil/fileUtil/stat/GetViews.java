package com.javaapi.test.testUtil.fileUtil.stat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;

/**
 *
 */
public class GetViews {
    private HashMap<String, Integer> map = new HashMap<>();

    @Test

    public void test() throws IOException {
        InputStream in = new FileInputStream(new File("/Users/user/Downloads/chongfu/1234.csv"));
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedInputStream = new BufferedReader(reader);
        String temp = null;
        while ((temp = bufferedInputStream.readLine()) != null) {
            if (StringUtils.isBlank(temp)) {
                continue;
            }
            String[] split = temp.split(",");
            String content_id = split[0];
            if (map.get(content_id) == null) {
                map.put(content_id, 1);
            } else {
                Integer integer = map.get(content_id);
                integer++;
                map.put(content_id, integer);
            }
        }
        checkFenP();
    }

    private void checkFenP() throws IOException {
        InputStream in = new FileInputStream(new File("/Users/user/Downloads/chongfu/1234.csv"));
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedInputStream = new BufferedReader(reader);

        FileOutputStream out = new FileOutputStream(new File("/Users/user/Downloads/chongfu/23.csv"));
        OutputStreamWriter writer = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(writer);

        String temp = null;
        while ((temp = bufferedInputStream.readLine()) != null) {
            String[] split = temp.split(",");
            if (StringUtils.isBlank(temp)) {
                continue;
            }
            String content_id = split[0];
            if (map.get(content_id)  == null) {
                System.out.println("content_id = " + content_id);
                continue;
            }
            if (map.get(content_id) > 2) {
                continue;
            }
            bw.write(content_id + "," + split[1] + "\r\n");
        }
        bw.flush();
        bw.close();
        writer.close();
        out.close();
    }
}
