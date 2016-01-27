package com.javaapi.test.testUtil.fileUtil;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/1/21.
 */
public class Client {
    @Test
    public void test() throws IOException {
        List<Integer> dilian = getList("/Users/user/Desktop/dilianSql.csv");
        List<Integer> youku = getList("/Users/user/Desktop/youkuSql.csv");
        youku.removeAll(dilian);
        System.out.println("youku = " + youku);
    }

    private List<Integer> getList(String s) throws IOException {
        InputStream in = new FileInputStream(new File(s));
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedInputStream = new BufferedReader(reader);
        String temp = null;
        List<Integer> dilian = new ArrayList<>();
        while ((temp = bufferedInputStream.readLine()) != null) {
            if (!NumberUtils.isNumber(temp)) {
                continue;
            }
            dilian.add(Integer.parseInt(temp));
        }
        return dilian;
    }

}
