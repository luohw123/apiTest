package com.javaapi.test.testUtil.fileUtil;

import jodd.io.FileUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by user on 16/1/22.
 */
public class ClientToYouku2 {

    @Test
    public void test() throws IOException {
        long start = System.currentTimeMillis();
        String[] strings = FileUtil.readLines("/Users/user/program/shell/youkutransdata/acfun_30T_part1.txt");
        long end = System.currentTimeMillis();
        System.out.println(end -start);
//        List<String> strings1 = Arrays.asList(strings);
//        System.out.println("strings1 = " + strings1);
    }
}
