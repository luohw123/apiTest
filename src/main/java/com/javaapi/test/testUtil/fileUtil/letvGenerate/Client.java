package com.javaapi.test.testUtil.fileUtil.letvGenerate;

import com.javaapi.test.testUtil.fileUtil.common.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user on 16/5/16.
 */
public class Client {

    @Test
    public void test() {
        String letvDir = "/Users/user/program/shell/letv_20160516/";
        String nihao1 = letvDir + "a站-5.3_vid_vname_vu.txt";
        List<String> nihao = null;
        try {
            nihao = Files.readAllLines(Paths.get(nihao1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(nihao)) {
            return;
        }
        List<String> result = nihao.stream().
                skip(1).
                filter((l) -> {
                    String[] split = l.split(",");
                    if (split.length != 3) {
                        return false;
                    }
                    String video_name = split[1];
                    String[] videoNameArr = video_name.split("\\.");
                    String source_id = videoNameArr[0];
                    if (!NumberUtils.isDigits(source_id)) {
                        return false;
                    }
                    return true;
                }).
                map((l) -> {
                    String[] split = l.split(",");
                    String video_name = split[1];
                    String[] videoNameArr = video_name.split("\\.");
                    String source_id = videoNameArr[0];
                    return getSql(source_id, split[2]);
                }).collect(Collectors.toList());
        try {
            Files.write(Paths.get(letvDir + "a站-5.3_vid_vname_vu.insert.sql"), result, StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSql(String source_id, String url) {
        LocalDateTime now = LocalDateTime.now();
        String nowString = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String result = CommonUtil.getSourceSql(source_id, url, "letv", "99", nowString);
        return result;
    }


}
