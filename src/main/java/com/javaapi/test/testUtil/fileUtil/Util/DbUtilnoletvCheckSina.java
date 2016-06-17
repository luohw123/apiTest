package com.javaapi.test.testUtil.fileUtil.Util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DbUtilnoletvCheckSina {

    public static final Pattern PATTERN = Pattern.compile("(\\d+)_flv/.*_?\\1_(.*)\\.");

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
    public void test() throws IOException {
        Set<Integer> sidSet = getVideoSet();
        Map<Integer, Integer> collect = sidSet.stream().collect(Collectors.toMap(((s) -> s), (s2) -> s2));
        System.out.println("dilian帮转的sid总共有"+sidSet.size());

        System.out.println("抽取前100条:"+sidSet.stream().limit(100).collect(Collectors.toList()));

        List<Integer> status2Sid = getStatus2Sid();
        System.out.println("只有dilian没有youku的并且状态为2的"+status2Sid.size());
        List<Integer> collect1 = status2Sid.stream().filter((s) -> !collect.containsKey(s)).collect(Collectors.toList());
        System.out.println("剩下的不在sina里的"+collect1.size());
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/只有dilian没有youku的并且状态为2的.txt"), collect1);

    }

    public List<Integer> getStatus2Sid() throws IOException {
        Path path = Paths.get("/Users/user/program/tmp2/status2.txt");
        List<Integer> result = new ArrayList<>();
        List<String> strings = Files.readAllLines(path);
        for (String string : strings) {
            String[] split = string.split("-");
            String source_Id = split[1];
            result.add(Integer.parseInt(source_Id));
        }
        return result;
    }

    private Set<Integer> getVideoSet() {
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
            return nihao;
        }
        int i = 0;
        while (lineIterator.hasNext()) {
            String url = lineIterator.nextLine();
            int videoId = getVideoId(url);
            nihao.add(videoId);
            i++;
            if (i % 100 == 0) {
                System.out.println("progress=" + i);
            }
        }
        System.out.println("videoId=" + nihao.size());
        return nihao;
    }

}
