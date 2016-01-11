package com.javaapi.test.testUtil.TransFile;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class MapToNew {
    @Test
    public void test() throws IOException {
        InputStream is = new FileInputStream("/Users/user/program/shell/ppp/old_ac_video_map/ac_video_map.csv");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);


        FileOutputStream out3 = new FileOutputStream(new File("/Users/user/program/shell/ppp/old_ac_video_map/updateSql.txt"));
        OutputStreamWriter writer3 = new OutputStreamWriter(out3);
        BufferedWriter bw3 = new BufferedWriter(writer3);

        FileOutputStream out4 = new FileOutputStream(new File("/Users/user/program/shell/ppp/old_ac_video_map/selectBackUp.txt"));
        OutputStreamWriter writer4 = new OutputStreamWriter(out4);
        BufferedWriter bw4 = new BufferedWriter(writer4);



        String temp = null;
        int progress = 0;
        int errProgress = 0;
        List<String> backUpSelect = new ArrayList<>();
        while ((temp = br.readLine()) != null) {
            progress++;
            if (StringUtils.isBlank(temp)) {
                continue;
            }
            String sid;
            String vid = null;
            try {
                String[] split = temp.split(",");
                vid = split[1];
                if ("video_id".equals(vid)) {
                    continue;
                }
                sid = split[2];
                String update = getUpdate(vid, sid);
                bw3.write(update);
                backUpSelect.add(vid);

            } catch (Exception e) {
                errProgress++;
                System.out.println(e.getMessage() + "==>" + vid);
            }
            if (progress++ % 1000 == 0) {
                System.out.println("进度->" + progress);

            }

        }

        System.out.println("backUpSelect = " + backUpSelect.size());
        getSelectBackUp(backUpSelect, bw4);

        bw3.flush();
        bw3.close();
        writer3.close();
        out3.close();

        bw4.flush();
        bw4.close();
        writer4.close();
        out4.close();


        br.close();
        isr.close();
        is.close();
        System.out.println("错误次数:" + errProgress);
    }

    private void getSelectBackUp(List<String> backUpSelect, BufferedWriter bw4) {
        StringJoiner sj = new StringJoiner(",", "(", ")");
        for (String s : backUpSelect) {
            sj.add(s);
        }
        String select = "select * from ac_new_video where video_id in " + sj.toString();
        try {
            bw4.write(select+";\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUpdate(String vid, String sid) {
        String updateSql = "update ac_new_video set source_id=" + sid + " where video_id = " + vid + ";\r\n";
        return updateSql;
    }



    @Test
    public void testVid() {
        String line = "1004,779536,112237636,绽放的火花,506b829069,\\N,\\N,33500895,zhuzhan,2,193,2013-08-14 03:01:06,2013-08-14 03:01:06,2,112237636,sina,0,149119,610788,0,-1,-1,0,http://video.sina.com.cn/v/b/112237636-1053397524.html,0,\\N";
        String[] split = line.split(",");
        System.out.println("split = " + split[0]);
        System.out.println("split = " + split[1]);
    }

    @Test
    public void testRegex() {
        String input = "-1";
        Pattern compile = Pattern.compile("^[0-9]*$");
        Matcher matcher = compile.matcher(input);
        if (matcher.find()) {
            System.out.println("matcher = " + true);
        } else {
            System.out.println("matcher = " + false);
        }


    }

    @Test
    public void testNumber() {
        System.out.println(NumberUtils.isNumber("-22ss"));
        System.out.println(NumberUtils.isDigits("2ss2"));
    }
}
