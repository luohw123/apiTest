package com.javaapi.test.testUtil.fileUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javaapi.test.testUtil.net.httpclient.HttpUtil_412;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 15/11/6.
 */
public class GetUtil {
    private Set set = new HashSet();

    private ExecutorService es = newBlockingExecutorsUseCallerRun(300);

    public static ExecutorService newBlockingExecutorsUseCallerRun(int size) {
        return new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
    @Test
    public void  testb() {
        try {
            InputStream in = new FileInputStream(new File("/Users/user/program/shell/sina/sina/bid.txt"));
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedInputStream = new BufferedReader(reader);
            String temp = null;

            FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/sina/sina/bdownload.txt"));
            OutputStreamWriter writer = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(writer);


            int i=0;
            while ( (temp = bufferedInputStream.readLine())!=null ) {
//                String url = getAcUrl(temp);
//                if (StringUtils.isNotBlank(url)) {
//                    bw.write(url);
//                }
                es.execute(new UrlThread(temp, bw));
                i++;
                if (i % 10 == 0) {
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void  test() {
        try {
            InputStream in = new FileInputStream(new File("/Users/user/program/shell/sina/sina/bid.txt"));
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedInputStream = new BufferedReader(reader);
            String temp = null;

            FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/sina/sina/bdownload.txt"));
            OutputStreamWriter writer = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(writer);


            int i=0;
            while ( (temp = bufferedInputStream.readLine())!=null ) {
//                String url = getAcUrl(temp);
//                if (StringUtils.isNotBlank(url)) {
//                    bw.write(url);
//                }
                es.execute(new UrlThread(temp, bw));
                i++;
                if (i % 10 == 0) {
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testGetAc() {
        System.out.println("ac = " + getAcUrl("779144"));
        System.out.println("getAcUrl(\"7791441\") = " + getAcUrl("7791441"));
    }

    private String getAcUrl(String ac) {
        String url = "http://vpac.5233game.com/parse_pc.php?url=http://www.acfun.tv/v/ac";
        url += ac;
        String s = HttpUtil_412.get(url, null);
        JSONObject jsonObject = JSON.parseObject(s);
        if(s.contains("error")){
            return "";
        }
        String downloadUrl = jsonObject.getString("url");
        if (StringUtils.isBlank(downloadUrl)) {
            return "";
        }
        return downloadUrl;
    }
    private void createUpdateSqlRollback(BufferedWriter bw4) {
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            int videoId = (int) iterator.next();
            String sql = "update ac_new_video set source_type = 'letv' where video_id = " + videoId+";\r\n";
            try {
                bw4.write(sql);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void createUpdateSql(BufferedWriter bw3) {
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            int videoId = (int) iterator.next();
            String sql = "update ac_new_video set source_type = 'zhuzhan' where video_id = " + videoId+";\r\n";
            try {
                bw3.write(sql);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    private static String getUrl(String url) {
        Pattern pattern = Pattern.compile("^http.+sina.+");
        Matcher matcher = pattern.matcher(url);
        if(!matcher.find()){
            return "";
        }
        return url+"\r\n";
    }


    private static int getVideoId(String url){
        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if(!matcher.find()){
            return 0;
        }
        try {
            String videoId = matcher.group(1);
            return Integer.parseInt(videoId);
        } catch (Exception e) {
            return 0;
        }
    }

    private static String getSqlUpdateRollBack(String url) {
        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if(!matcher.find()){
            return "#"+url+"\r\n";
        }
        String videoId = matcher.group(1);
        String sql = "update ac_new_video set source_type = 'letv' where video_id = " + videoId+";\r\n";
        return sql;
    }

    private static String getSqlUpdate(String url) {
        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if(!matcher.find()){
            return "#"+url+"\r\n";
        }
        String videoId = matcher.group(1);
        String sql = "update ac_new_video set source_type = 'zhuzhan' where video_id = " + videoId+";\r\n";
        return sql;
    }

    private static String getSqlDelete(String url) {
        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if(!matcher.find()){
            return "#"+url+"\r\n";
        }
        String videoId = matcher.group(1);
        String rate = mapDilianProfile(matcher.group(2));
        String tablename = getTablename(videoId);
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE FROM ");
        sb.append(tablename);
        sb.append(" WHERE ");
        sb.append(" video_id = " + videoId);
        sb.append(" AND bit_rate= " + rate+";\r\n");
        return sb.toString();
    }

    @Test
    public void testPattern() {
        String url = "http://vplay.aixifan.com/des/acf-2/1523110_mp4/1523110_360p_43.mp4";
//        String url = "http://vplay.aixifan.com/des/acf-2/1663357_mp4/1663357_lvbr.mp4";
//        String url = "http://vplay.aixifan.com/des/20150902/test_2285615_mp4/test_2285615_360p.mp4";
        System.out.println("url = " + getSqlByUrl(url));
    }
    public static  String getSqlByUrl(String url){
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if(!matcher.find()){
            return "#"+url+"\r\n";
        }
        String videoId = matcher.group(1);
        String rate = mapDilianProfile(matcher.group(2));
        String tablename = getTablename(videoId);

        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO ");
        sb.append(tablename);
        sb.append("(video_id,url,source,bit_rate,creater,create_time,updater,update_time) VALUES (");
        sb.append(videoId + ",");
        sb.append("'"+url + "',");
        sb.append("'D_LIAN' ,");
        sb.append(rate + ",");
        sb.append("1,'" + now + "',1,'" + now + "');\r\n");
        return sb.toString();
    }

    @Test
    public void testVideoRate() {
        System.out.println(mapDilianProfile("360"));
        System.out.println(mapDilianProfile("480"));
        System.out.println(mapDilianProfile("720"));
        System.out.println(mapDilianProfile("1080"));
//        System.out.println(mapDilianProfile("222"));
        System.out.println(mapDilianProfile("lvbr"));
    }
    @Test
    public void testTableName() {
        System.out.println(getTablename("500000"));
        System.out.println(getTablename("499999"));
        System.out.println(getTablename("600000"));
        System.out.println(getTablename("1100000"));
        System.out.println(getTablename("1600000"));
        System.out.println(getTablename("2100000"));
        System.out.println(getTablename("2600000"));
        System.out.println(getTablename("3100000"));
        System.out.println(getTablename("3600000"));
        System.out.println(getTablename("4100000"));
        System.out.println(getTablename("4600000"));
    }



    /**
     * 根据转码率获取标示数字
     * @param profileName
     * @return
     */
    public static String mapDilianProfile(String profileName) {
        if (profileName.startsWith("360"))
            return "1";
        if (profileName.startsWith("480"))
            return "2";
        if (profileName.startsWith("720"))
            return "3";
        if (profileName.startsWith("1080"))
            return "4";
        if (profileName.contains("lvbr"))
            return "4";
        return "-2";
    }

    public static String getTablename(String subColumnValue){
        Integer value = Integer.valueOf(Integer.parseInt(subColumnValue.toString()));
        String tName = "ac_video_source";
        int position = value.intValue() / 500000 + 1;
        String tableName = null;
        if(position == 1) {
            tableName = tName;
        } else {
            StringBuilder sb = new StringBuilder();
            tableName = sb.append(tName).append("_").append(position).toString();
        }
        return tableName;
    }

    @Test
    public void testUpdate() {
        Set set = new HashSet();
        set.add(1);
        set.add(1);
        System.out.println("set = " + set);
    }
}
