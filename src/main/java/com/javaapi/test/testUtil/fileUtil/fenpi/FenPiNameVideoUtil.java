package com.javaapi.test.testUtil.fileUtil.fenpi;

import org.junit.Test;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 15/11/9.
 */
public class FenPiNameVideoUtil {
    @Test
    public  void test() throws IOException {
        InputStream in = new FileInputStream(new File("/Users/user/program/shell/transdata/20151119_failed/downloadfailed.csv"));
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedInputStream = new BufferedReader(reader);
        String temp ;
        FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/transdata/20151119_failed/ac-video.txt"));
        OutputStreamWriter writer = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(writer);


        int i=0;
        while ((temp = bufferedInputStream.readLine()) != null) {
            i++;
            if (i == 1) {
                continue;
            }
            if(i%10==0){
                System.out.println("进度 = " + i);
            }
            AcContentVideo suffix = getAcContentVideoBydot(temp);
            if(suffix == null){
                continue;
            }
            String ac;
            Integer priority = Integer.valueOf(suffix.getPriority());

            if (priority == 0) {
                ac = suffix.getContent_id();

            } else {
                priority += 1;
                ac = suffix.getContent_id() + "_" + priority;
            }

            bw.write(ac + "===" + suffix.getVideo_id()+"\r\n");
        }


        bw.flush();
        bw.close();
        writer.close();
        out.close();

    }

    private AcContentVideo getAcContentVideo(String temp) {
        Pattern p = Pattern.compile("(\\d+)\\t+(\\d+)\\t+(\\d+)\\t+\\d+");
        Matcher matcher = p.matcher(temp);
        if (!matcher.find()) {
            return null;
        }
        String content_id = matcher.group(1);
        String video_id = matcher.group(2);
        String priority = matcher.group(3);
        return new AcContentVideo(content_id, video_id, priority);
    }


    private AcContentVideo getAcContentVideoBydot(String temp) {
        Pattern p = Pattern.compile("(\\d+)\\,(\\d+)\\,(\\d+)\\,\\d+");
        Matcher matcher = p.matcher(temp);
        if (!matcher.find()) {
            return null;
        }
        String content_id = matcher.group(1);
        String video_id = matcher.group(2);
        String priority = matcher.group(3);
        return new AcContentVideo(content_id, video_id, priority);
    }
}
