package com.javaapi.test.testUtil.fileUtil.Util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 16/1/23.
 */
public class FileUtilClient {
    @Test
    public void test() {
        List<String> strings = readLines("/Users/user/program/shell/acfun_youku/acfunId.txt");
        for (String string : strings) {
            System.out.println(string);
        }

    }

    private List<String> readLines(String pathname) {
        List<String> strings = null;
        try {
            strings = FileUtils.readLines(new File(pathname));
        } catch (IOException e) {
            throw new RuntimeException("传入路径未找到 path = "+pathname);
        }
        return strings;
    }
}
