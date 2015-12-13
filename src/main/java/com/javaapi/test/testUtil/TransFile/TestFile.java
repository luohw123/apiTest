package com.javaapi.test.testUtil.TransFile;

/**
 * Created by user on 15/12/7.
 */

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 根据帝联url生成sql
 */
public class TestFile {
    private Map<String, String> map = new HashMap<>();


    @Test
    public void test() {
        loadData();
        try {
            InputStream in = new FileInputStream(new File("/Users/user/program/shell/todilian/20151211/vid.txt"));
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedInputStream = new BufferedReader(reader);
            FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/todilian/20151211/afterTransVideoId.csv"));
            OutputStreamWriter writer = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(writer);

            String temp = null;
            while ((temp = bufferedInputStream.readLine()) != null) {
                if("video_id".equals(temp)) {
                    continue;
                }
                String s = map.get(temp);
                if (s!=null) {
                    temp = s;
                }
//                System.out.println("s = " + temp);
                bw.write(temp + "\r\n");
            }

            in.close();
            reader.close();
            bufferedInputStream.close();

            bw.flush();
            bw.close();
            writer.close();
            out.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void loadData(){
        try {
            InputStream in = new FileInputStream(new File("/Users/user/program/shell/todilian/20151211/ac_video_map.csv"));
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedInputStream = new BufferedReader(reader);
            String temp = null;
            while ((temp = bufferedInputStream.readLine()) != null) {
                getAcContentVideo(temp);
            }

            in.close();
            reader.close();
            bufferedInputStream.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAcContentVideo(String temp) {
        Pattern p = Pattern.compile("(\\d+)\\,(\\d+)\\,(\\d+)");
        Matcher matcher = p.matcher(temp);
        if (!matcher.find()) {
            return ;
        }
        String id = matcher.group(1);
        String video_id = matcher.group(2);
        String source_id = matcher.group(3);
        map.put(video_id,source_id);
    }

}
