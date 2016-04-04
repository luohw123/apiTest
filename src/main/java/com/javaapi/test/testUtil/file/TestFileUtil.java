package com.javaapi.test.testUtil.file;

import org.junit.Test;

import java.io.File;

/**
 * Created by user on 16/3/28.
 */
public class TestFileUtil {
    @Test
    public void test() {
        File file = new File("/Users/user/Downloads/项目整理new.xlsx");
        System.out.println(file.getName());


    }
}
