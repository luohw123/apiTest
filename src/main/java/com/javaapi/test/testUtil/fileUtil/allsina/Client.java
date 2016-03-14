package com.javaapi.test.testUtil.fileUtil.allsina;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 16/3/8.
 */
public class Client {
    public static final Pattern PATTERN = Pattern.compile("(\\d+)_flv/.*_?\\1_(.*)\\.");

    @Test
    public void test() {
        Set<Integer> nihao = new HashSet<>();
        String path = "/Users/user/program/shell/transdata/20160308-sina-all/all.txt";
        LineIterator lineIterator = null;
        try {
            lineIterator = FileUtils.lineIterator(new File(path), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lineIterator == null) {
            System.out.println("wrong");
            return;
        }
        int i =0;
        while (lineIterator.hasNext()) {
            String url = lineIterator.nextLine();
            int videoId = getVideoId(url);
            nihao.add(videoId);
            i++;
            if (i % 100 == 0) {
                System.out.println("progress="+i);
            }
        }
        System.out.println("videoId="+nihao.size());
    }


    private static int getVideoId(String url) {
        Matcher matcher = PATTERN.matcher(url);
        if (!matcher.find()) {
            return 0;
        }
        try {
            String videoId = matcher.group(1);
            return Integer.parseInt(videoId);
        } catch (Exception e) {
            return 0;
        }
    }

    @Test
    public void testSina(){
        Set<Integer> nihao = new HashSet<>();
        String path = "/Users/user/Downloads/ac-video.txt";
        LineIterator lineIterator = null;
        try {
            lineIterator = FileUtils.lineIterator(new File(path), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lineIterator == null) {
            System.out.println("wrong");
            return;
        }
        int i =0;
        while (lineIterator.hasNext()) {
            String url = lineIterator.nextLine();
            String[] split = url.split("===");
            nihao.add(Integer.parseInt(split[1]));
            i++;
            if (i % 100 == 0) {
                System.out.println("progress="+i);
            }
        }
        System.out.println("videoId="+nihao.size());
    }
}
