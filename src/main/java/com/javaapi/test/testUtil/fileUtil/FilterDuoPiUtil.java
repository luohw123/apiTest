package com.javaapi.test.testUtil.fileUtil;

import org.junit.Test;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by user on 15/11/7.
 */
public class FilterDuoPiUtil {

    private Map<String, String> map = new LinkedHashMap<>();

    @Test

    public void testb() {
        try {
            InputStream in = new FileInputStream(new File("/Users/user/program/shell/sina/sina/多批.txt"));
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedInputStream = new BufferedReader(reader);
            String temp = null;


            int i = 0;
            while ((temp = bufferedInputStream.readLine()) != null) {
                map.put(temp, temp);
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
        InputStream in = new FileInputStream(new File("/Users/user/program/shell/sina/sina/ac_download_video_url.txt"));
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedInputStream = new BufferedReader(reader);
        String temp = null;

        FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/sina/sina/单一video.txt"));
        OutputStreamWriter writer = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(writer);


        int i = 0;
        while ((temp = bufferedInputStream.readLine()) != null) {
            int i1 = temp.indexOf(",");
            String video_id = temp.substring(0, i1);

            if(map.get(video_id) !=null){
                continue;
            }
            bw.write(temp+"\r\n");

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



}
