package com.javaapi.test.testUtil.fileUtil.letvBack;

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
public class ClientGenLetvSql {

    @Test
    public void test() {
        String letvDir = "/Users/user/program/shell/allletvWithNoYouku_old/";
        String nihao1 = letvDir + "letvNoNoyouku.txt";
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
                map((l) -> {
                    String[] split = l.split(",");
                    return getSql(split[0], split[1]);
                }).collect(Collectors.toList());
        try {
            Files.write(Paths.get(letvDir + "letvNoNoyouku.insert.sql"), result, StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
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
