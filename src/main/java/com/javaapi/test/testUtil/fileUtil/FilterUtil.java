package com.javaapi.test.testUtil.fileUtil;

import org.junit.Test;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 15/11/7.
 */
public class FilterUtil {

    private Map<String, String> map = new LinkedHashMap<>();

    @Test

    public void testb() {
        try {
            InputStream in = new FileInputStream(new File("/Users/user/program/shell/sina/sina/ac_content_video.csv"));
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedInputStream = new BufferedReader(reader);
            String temp = null;


            int i = 0;
            while ((temp = bufferedInputStream.readLine()) != null) {
                setContentIdMap(temp);
                i++;
                if (i % 10000 == 0) {
                    System.out.println(" 进度 " + i);
                }
            }

            writeContentId();
            in.close();
            reader.close();
            bufferedInputStream.close();




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeContentId() throws IOException {
        InputStream in = new FileInputStream(new File("/Users/user/program/shell/sina/sina/bdownloadYouxiao.txt"));
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedInputStream = new BufferedReader(reader);
        String temp = null;

        FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/sina/sina/ac_download_video_url.txt"));
        OutputStreamWriter writer = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(writer);


        int i = 0;
        while ((temp = bufferedInputStream.readLine()) != null) {
            int i1 = temp.indexOf(",");
            String[] split = temp.split(",");
            if (split == null || split[0] == null || split[1] == null ) {
                continue;
            }
//            String content_id = split[0];
            String content_id = temp.substring(0, i1);

            String url = temp.substring(i1+1);
//            String url = split[1];
//            String url2 = split[2];
            String video_id = map.get(content_id);
            bw.write(video_id + "," + url  + "\r\n");
            i++;
            if (i % 10000 == 0) {
                System.out.println(" 进度 " + i);
            }
        }


        in.close();
        reader.close();
        bufferedInputStream.close();

        bw.flush();
        bw.close();
        writer.close();
        out.close();


    }

    private void setContentIdMap(String temp) {
        Pattern p = Pattern.compile("(\\d+)\\s+(\\d+)\\s+\\d+\\s+\\d+");
        Matcher matcher = p.matcher(temp);
        if (!matcher.find()) {
            return;
        }
        String content_id = matcher.group(1);
        String video_id = matcher.group(2);
        map.put(content_id, video_id);
    }


}
