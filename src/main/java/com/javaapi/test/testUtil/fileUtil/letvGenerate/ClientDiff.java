package com.javaapi.test.testUtil.fileUtil.letvGenerate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user on 16/6/12.
 */
public class ClientDiff {
    @Test
    public void test() throws IOException {
        Path path = Paths.get("/Users/user/Downloads/0317-0516-status_2_sourceId.csv");
        Path path2 = Paths.get("/Users/user/program/shell/letv_20160612/a站-6.12_vid_vu_1.txt");
        List<String> strings = Files.readAllLines(path);
        List<String> nihao = Files.readAllLines(path2);

        List<String> result = nihao.stream().
                skip(1).
                filter((l) -> {
                    String[] split = l.split(",");
                    if (split.length != 3) {
                        return false;
                    }
                    // video name
                    String video_name = split[2];


                    if (video_name.indexOf("AcFun_") > 0) {
                        return false;
                    }


                    String[] videoNameArr = video_name.split("\\.");
                    String source_id = videoNameArr[0];
                    if (!NumberUtils.isDigits(source_id)) {
                        return false;
                    }
                    return true;
                }).
                map((l) -> {
                    String[] split = l.split(",");
                    String video_name = split[2];
                    String[] videoNameArr = video_name.split("\\.");
                    String source_id = videoNameArr[0];
                    return source_id;
                }).collect(Collectors.toList());

        System.out.println("result = " + result.size());
        Collection<String> intersection = CollectionUtils.intersection(strings, result);
        System.out.println("intersection = " + intersection.size());

//        System.out.println("intersection = " + intersection.stream().limit(100).collect(Collectors.toList()));
//        System.out.println("intersection = " + intersection.size());
//        Collection<String> disjunction = CollectionUtils.disjunction(strings, result);
//        List<String> collect = disjunction.stream().limit(100).collect(Collectors.toList());
//        System.out.println("disjunction = " + collect);
//        System.out.println("disjunction = " + disjunction.size());
    }
}
