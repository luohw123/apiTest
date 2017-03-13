package com.javaapi.test.testUtil.fileUtil5;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 */
public class Client {
    @Test
    public void test() throws IOException {
        String dir = "/Users/user/Downloads/";
        List<String> strings = FileUtils.readLines(new File(dir + "acfun_vid.txt"));
        StringBuilder sb = new StringBuilder();

        for (String string : strings) {
            String[] split = string.split("\t");
            String s = split[1];
            sb.append("'" + s + "',");
        }
        FileUtils.write(new File(dir + "acfun_vid_sourceId.txt"), sb);

    }
}
