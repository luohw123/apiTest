package com.javaapi.test.testUtil.fileUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javaapi.test.testUtil.net.httpclient.HttpUtil_412;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by user on 15/11/6.
 */
public class UrlThread implements Runnable {
    private String acNo;
    private BufferedWriter bufferedWriter;

    public UrlThread(String acNo, BufferedWriter bufferedWriter) {
        this.acNo = acNo;
        this.bufferedWriter = bufferedWriter;
    }

    public String getAcNo() {
        return acNo;
    }

    public void setAcNo(String acNo) {
        this.acNo = acNo;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public void run() {
        String url = getAcUrl(acNo);
        if (StringUtils.isNotBlank(url)) {
            try {
                synchronized (bufferedWriter){
                    getBufferedWriter().write(acNo+","+url);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String getAcUrl(String ac) {
        String url = "http://vpac.5233game.com/parse_pc.php?url=http://www.acfun.tv/v/ac";
        url += ac;
        String s = HttpUtil_412.get(url, null);
        JSONObject jsonObject = JSON.parseObject(s);
        if(s.contains("error")){
            return "";
        }
        JSONArray downloadUrl = jsonObject.getJSONArray("V");
        if(downloadUrl == null){
            return "";
        }
        JSONObject videoUrl = downloadUrl.getJSONObject(0);
        if (videoUrl==null) {
            return "";
        }
        String u = videoUrl.getString("U");
        return u+"\r\n";
    }
}
