package com.javaapi.test.testUtil.fileUtil.letvBack;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by user on 16/4/29.
 */
public class ClientDiff {
    public Map<String,String> loadAllOld(){
        List<String> strings = null;
        try {
            strings = FileUtils.readLines(new File("/tmp/ac_new_video_20160908.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> result = new HashMap<>();
        for (String string : strings) {
            String[] split = string.split(",");
            String key = split[0];
            result.put(key,key);
        }
        return result;
    }
    @Test
    public void test() throws IOException {
        List<String> strings = null;
        try {
            strings = FileUtils.readLines(new File("/Users/user/program/shell/all-letv-vid_sid.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> extra = new ArrayList<>();

        Map<String, String> old = loadAllOld();
        Map<String, String> map = new LinkedHashMap<>();
        for (String string : strings) {
            String[] split = string.split(",");
            if (split.length != 2) {
                continue;
            }
            String newVid = split[0];
            if (!old.containsKey(newVid)) {
                extra.add(string);
            }
        }
        FileUtils.writeLines(new File("/Users/user/program/shell/all-letv-vid_sid_extra.csv"),extra);

    }

}
