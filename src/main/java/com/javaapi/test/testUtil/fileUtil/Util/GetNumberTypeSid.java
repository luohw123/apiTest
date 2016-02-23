package com.javaapi.test.testUtil.fileUtil.Util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/2/14.
 */
public class GetNumberTypeSid {
    @Test
    public void test() throws IOException {
        List<String> result = new ArrayList<>();
        List<String> strings = FileUtils.readLines(new File("/Users/user/program/shell/all-letv-vid_sid.csv"));
        for (String string : strings) {
            if (StringUtils.isBlank(string)) {
                continue;
            }
            String[] split = string.split(",");
            if (split.length < 2) {
                continue;
            }
            String sid = split[1];
            if (NumberUtils.isDigits(sid)) {
                if (Long.parseLong(sid) > 3100000) {
                    result.add(split[0]);
                }
            }
        }
        System.out.println(result);
    }
}
