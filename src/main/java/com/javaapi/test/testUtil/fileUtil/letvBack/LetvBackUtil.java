package com.javaapi.test.testUtil.fileUtil.letvBack;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/4/29.
 */
public class LetvBackUtil {

    public static Map<String,String> loadOldVidSid(String pathname) {
        List<String> strings = null;
        try {
            strings = FileUtils.readLines(new File(pathname));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> result = new HashMap<>();

        for (String string : strings) {
            result.put(string, string);
        }
        return result;
    }


    public static Map<String, String> loadAllNoYoukuSid() {
        List<String> result = new ArrayList<>();
        List<String> strings = null;
        List<String> strings2 = null;
        List<String> strings3 = null;
        List<String> strings4 = null;
        List<String> strings5 = null;
        List<String> strings6 = null;
        List<String> strings7 = null;
        try {
            String pathname = "/Users/user/program/tmp/onlyDilian_405217_20160408-写入100Tyouku数据后剩下的只有帝联没有优酷的数据/";
            strings = FileUtils.readLines(new File(pathname + "onlyDilian_ac_video_source.txt"));
            strings2 = FileUtils.readLines(new File(pathname + "onlyDilian_ac_video_source_2.txt"));
            strings3 = FileUtils.readLines(new File(pathname + "onlyDilian_ac_video_source_3.txt"));
            strings4 = FileUtils.readLines(new File(pathname + "onlyDilian_ac_video_source_4.txt"));
            strings5 = FileUtils.readLines(new File(pathname + "onlyDilian_ac_video_source_5.txt"));
            strings6 = FileUtils.readLines(new File(pathname + "onlyDilian_ac_video_source_6.txt"));
            strings7 = FileUtils.readLines(new File(pathname + "onlyDilian_ac_video_source_7.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.addAll(strings);
        result.addAll(strings2);
        result.addAll(strings3);
        result.addAll(strings4);
        result.addAll(strings5);
        result.addAll(strings6);
        result.addAll(strings7);
        Map<String, String> map = new HashMap<>();
        for (String s : result) {
            map.put(s, s);
        }
        return map;
    }
}
