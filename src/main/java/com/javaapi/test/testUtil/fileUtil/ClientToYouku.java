package com.javaapi.test.testUtil.fileUtil;

import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 16/1/21.
 */
public class ClientToYouku {

    private Set set = new HashSet();

    private static int getVideoId(String url) {
        Pattern pattern = Pattern.compile("(\\d+)_flv/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if (!matcher.find()) {
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
        Pattern pattern = Pattern.compile("(\\d+)_flv/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if (!matcher.find()) {
            return "#" + url + "\r\n";
        }
        String videoId = matcher.group(1);
        String sql = "update ac_new_video set source_type = 'letv' where video_id = " + videoId + ";\r\n";
        return sql;
    }

    private static String getSqlUpdate(String url) {
        Pattern pattern = Pattern.compile("(\\d+)_flv/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if (!matcher.find()) {
            return "#" + url + "\r\n";
        }
        String videoId = matcher.group(1);
        String sql = "update ac_new_video set source_type = 'zhuzhan' where video_id = " + videoId + ";\r\n";
        return sql;
    }

    private static String getSqlDelete(String url) {
        Pattern pattern = Pattern.compile("(\\d+)_flv/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if (!matcher.find()) {
            return "#" + url + "\r\n";
        }
        String videoId = matcher.group(1);
        String rate = mapDilianProfile(matcher.group(2));
        String tablename = getTablename(videoId);
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE FROM ");
        sb.append(tablename);
        sb.append(" WHERE ");
        sb.append(" video_id = " + videoId);
        sb.append(" AND bit_rate= " + rate + ";\r\n");
        return sb.toString();
    }

    public static String getSqlByUrl(String temp) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //1004 38825 CMTU1MzAw
        String[] split = temp.split(" ");
        String videoId = split[0];
//        if(NumberUtils.isNumber(videoId)){
//            return "" ;
//
//        }
        String url = split[2];

        String rate ="99";
        String tablename = getTablename(videoId);

        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO ");
        sb.append(tablename);
        sb.append("(video_id,url,source,bit_rate,creater,create_time,updater,update_time) VALUES (");
        sb.append(videoId + ",");
        sb.append("'" + url + "',");
        sb.append("'youku' ,");
        sb.append(rate + ",");
        sb.append("1,'" + now + "',1,'" + now + "');\r\n");
        return sb.toString();
    }

    /**
     * 根据转码率获取标示数字
     *
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

    public static String getTablename(String subColumnValue) {
        Integer value = Integer.valueOf(Integer.parseInt(subColumnValue.toString()));
        String tName = "ac_video_source";
        int position = value.intValue() / 500000 + 1;
        String tableName = null;
        if (position == 1) {
            tableName = tName;
        } else {
            StringBuilder sb = new StringBuilder();
            tableName = sb.append(tName).append("_").append(position).toString();
        }
        return tableName;
    }

    @Test
    public void test() {
        try {
            InputStream in = new FileInputStream(new File("/Users/user/program/shell/youkutransdata/20160229-youku/acfun_30T_part2.txt"));
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedInputStream = new BufferedReader(reader);
            String temp = null;

            FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/youkutransdata/20160229-youku/youku.insertSql.list.sql"));
            OutputStreamWriter writer = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(writer);

            FileOutputStream out2 = new FileOutputStream(new File("/Users/user/program/shell/youkutransdata/20160229-youku/youku.insertSql_RollBack.list.sql"));
            OutputStreamWriter writer2 = new OutputStreamWriter(out2);
            BufferedWriter bw2 = new BufferedWriter(writer2);


            FileOutputStream out3 = new FileOutputStream(new File("/Users/user/program/shell/youkutransdata/20160229-youku/youku.ac_new_video_update.list.sql"));
            OutputStreamWriter writer3 = new OutputStreamWriter(out3);
            BufferedWriter bw3 = new BufferedWriter(writer3);

            FileOutputStream out4 = new FileOutputStream(new File("/Users/user/program/shell/youkutransdata/20160229-youku/youku.ac_new_video_update_RollBack.list.sql"));
            OutputStreamWriter writer4 = new OutputStreamWriter(out4);
            BufferedWriter bw4 = new BufferedWriter(writer4);


            while ((temp = bufferedInputStream.readLine()) != null) {

                String sqlByUrl = getSqlByUrl(temp);
                bw.write(sqlByUrl);
            }
            // 更新sql
            createUpdateSql(bw3);
            // 更新sql
            createUpdateSqlRollbackSina(bw4);
            in.close();
            reader.close();
            bufferedInputStream.close();

            bw.flush();
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

    private void createUpdateSqlRollback(BufferedWriter bw4) {
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            int videoId = (int) iterator.next();
            String sql = "update ac_new_video set source_type = 'letv' where video_id = " + videoId + ";\r\n";
            try {
                bw4.write(sql);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void createUpdateSqlRollbackSina(BufferedWriter bw4) {
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            int videoId = (int) iterator.next();
            String sql = "update ac_new_video set source_type = 'sina' where video_id = " + videoId + ";\r\n";
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
            String sql = "update ac_new_video set source_type = 'zhuzhan' ,source_id=video_id  where video_id = " + videoId + ";\r\n";
            try {
                bw3.write(sql);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Test
    public void testPattern() {
        String url = "1004 38825 CMTU1MzAw";
//        String url = "http://vplay.aixifan.com/des/acf-2/1663357_flv/1663357_lvbr.mp4";
//        String url = "http://vplay.aixifan.com/des/20150902/test_2285615_flv/test_2285615_360p.mp4";
        System.out.println("url = " + getSqlByUrl(url));
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

    @Test
    public void testUpdate() {
        Set set = new HashSet();
        set.add(1);
        set.add(1);
        System.out.println("set = " + set);
    }

    @Test
    public void testPlus() {
        int nihao = 1;
        if (++nihao == 2) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
        nihao = 1;
        if (nihao++ == 2) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }


    }
}
