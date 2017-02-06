package com.javaapi.test.testUtil.fileUtil.common;

import org.junit.Test;

import java.util.List;

/**
 * Created by user on 17/2/3.
 */
public class TestCommonUtil {
    @Test
    public void test() {
        List<String> allVideoSourceTableNames =CommonUtil.getAllVideoSourceTableNames(10);
        allVideoSourceTableNames.stream().forEach((s -> {
            System.out.println("s = " + s);
        }));
    }
}
