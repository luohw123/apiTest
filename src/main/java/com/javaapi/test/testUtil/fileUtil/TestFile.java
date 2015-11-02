package com.javaapi.test.testUtil.fileUtil;

import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class TestFile {

    @Test
    public void  test() {
        try {
            InputStream in = new FileInputStream(new File("/Users/user/program/shell/transdata/url300.list"));
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedInputStream = new BufferedReader(reader);
            String temp = null;

            FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/transdata/insertSql.list.sql"));
            OutputStreamWriter writer = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(writer);

            FileOutputStream out2 = new FileOutputStream(new File("/Users/user/program/shell/transdata/insertSql_RollBack.list.sql"));
            OutputStreamWriter writer2 = new OutputStreamWriter(out2);
            BufferedWriter bw2 = new BufferedWriter(writer2);


            FileOutputStream out3 = new FileOutputStream(new File("/Users/user/program/shell/transdata/ac_new_video_update.list.sql"));
            OutputStreamWriter writer3 = new OutputStreamWriter(out3);
            BufferedWriter bw3 = new BufferedWriter(writer3);

            FileOutputStream out4 = new FileOutputStream(new File("/Users/user/program/shell/transdata/ac_new_video_update_RollBack.list.sql"));
            OutputStreamWriter writer4 = new OutputStreamWriter(out4);
            BufferedWriter bw4 = new BufferedWriter(writer4);


            while ( (temp = bufferedInputStream.readLine())!=null ) {
                String sqlByUrl = getSqlByUrl(temp);
                String deleteSql = getSqlDelete(temp);
                String updateSql = getSqlUpdate(temp);
                String updateSqlRollback = getSqlUpdateRollBack(temp);
                bw.write(sqlByUrl);
                bw2.write(deleteSql);
                bw3.write(updateSql);
                bw4.write(updateSqlRollback);
            }
            in.close();
            reader.close();
            bufferedInputStream.close();

            bw.close();
            writer.close();
            out.close();

            bw2.close();
            writer2.close();
            out2.close();

            bw3.close();
            writer3.close();
            out3.close();

            bw4.close();
            writer4.close();
            out4.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
        System.out.println(mapDilianProfile("222"));
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
            return "1";
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
}
