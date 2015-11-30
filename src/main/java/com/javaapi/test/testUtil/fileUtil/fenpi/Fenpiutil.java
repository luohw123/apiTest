package com.javaapi.test.testUtil.fileUtil.fenpi;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fenpiutil {
    private List<AcContentVideo> list = new ArrayList<AcContentVideo>();
    private Map<String, String> map = new HashMap<>();

    private void readDuoPi() throws IOException {
        InputStream in = new FileInputStream(new File("/Users/user/program/shell/sina/sina/duopi/fenpiachao.txt"));
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedInputStream = new BufferedReader(reader);
        String temp;
        while ((temp = bufferedInputStream.readLine()) != null) {
            map.put(temp, temp);
        }
        in.close();
        reader.close();
        bufferedInputStream.close();
    }

    @Test
    public void test() throws IOException {
        readDuoPi();
        InputStream in = new FileInputStream(new File("/Users/user/program/shell/sina/sina/ac_content_video.csv"));
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedInputStream = new BufferedReader(reader);


        FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/sina/sina/duopi/fenpContent.txt"));
        OutputStreamWriter writer = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(writer);

        String temp = null;
        int i = 0;
        while ((temp = bufferedInputStream.readLine()) != null) {
            i++;
            if (i == 1) {
                continue;
            }
            String contentId = getContentId(temp);
            if (map.get(contentId) != null) {
                bw.write(temp + "\r\n");
            }

        }

        bw.flush();
        bw.close();
        writer.close();
        out.close();


    }
    private void setContentIdMap(String temp) {
        Pattern p = Pattern.compile("(\\d+)\\t+(\\d+)\\t+(\\d+)\\t+\\d+");
        Matcher matcher = p.matcher(temp);
        if (!matcher.find()) {
            return;
        }
        String content_id = matcher.group(1);
        String video_id = matcher.group(2);
        String priority = matcher.group(3);
        list.add(new AcContentVideo(content_id, video_id, priority));
    }
    private String getContentId(String temp){
        Pattern p = Pattern.compile("(\\d+)\\t+(\\d+)\\t+(\\d+)\\t+\\d+");
        Matcher matcher = p.matcher(temp);
        if (!matcher.find()) {
            return "";
        }
        String content_id = matcher.group(1);
        return content_id;
    }

}
