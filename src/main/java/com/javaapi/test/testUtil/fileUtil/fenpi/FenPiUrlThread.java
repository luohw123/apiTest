package com.javaapi.test.testUtil.fileUtil.fenpi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javaapi.test.testUtil.net.httpclient.HttpUtil_412;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 15/11/6.
 */
public class FenPiUrlThread implements Runnable {
    private String temp;
    private BufferedWriter bufferedWriter;

    public FenPiUrlThread(String temp, BufferedWriter bufferedWriter) {
        this.temp = temp;
        this.bufferedWriter = bufferedWriter;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public void run() {
        AcContentVideo suffix = getAcContentVideo(temp);
        String ac;
        Integer priority = Integer.valueOf(suffix.getPriority());

        if (priority == 0) {
            ac = suffix.getContent_id();

        } else {
            priority += 1;
            ac = suffix.getContent_id() + "_" + priority;

        }
        String url = getAcUrl(ac);
        if (StringUtils.isNotBlank(url)) {
            try {
                synchronized (bufferedWriter) {
                    getBufferedWriter().write(suffix.getVideo_id() + "," + url);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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


    private String getAcUrl(String ac) {
        String url = "http://vpac.5233game.com/parse_pc.php?url=http://www.acfun.tv/v/ac";
        url += ac;
        String s = HttpUtil_412.get(url, null);
        JSONObject jsonObject = JSON.parseObject(s);
        if (s.contains("error")) {
            return "";
        }
        JSONArray downloadUrl = jsonObject.getJSONArray("V");
        if (downloadUrl == null) {
            return "";
        }
        JSONObject videoUrl = downloadUrl.getJSONObject(0);
        if (videoUrl == null) {
            return "";
        }
        String u = videoUrl.getString("U");
        return u + "\r\n";
    }
}
