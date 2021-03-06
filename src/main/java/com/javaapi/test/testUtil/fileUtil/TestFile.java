package com.javaapi.test.testUtil.fileUtil;

import com.javaapi.test.testUtil.fileUtil.common.CommonUtil;
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
 根据帝联url生成sql
 */
public class TestFile {
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
        String tablename = CommonUtil.getTablename(videoId);
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE FROM ");
        sb.append(tablename);
        sb.append(" WHERE ");
        sb.append(" video_id = " + videoId);
        sb.append(" AND bit_rate= " + rate + ";\r\n");
        return sb.toString();
    }

    public static String getSqlByUrl(String url) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Pattern pattern = Pattern.compile("(\\d+)_flv/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if (!matcher.find()) {
            return "#" + url + "\r\n";
        }
        String videoId = matcher.group(1);
        String rate = mapDilianProfile(matcher.group(2));

        String sb = CommonUtil.getSourceSql(videoId, url,"D_LIAN", rate, now);
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

    @Test
    public void test() {
        try {
            InputStream in = new FileInputStream(new File("/Users/user/program/shell/transdata/20160229-sina/acfun-10w-20160229.txt"));
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedInputStream = new BufferedReader(reader);
            String temp = null;

            FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/transdata/20160229-sina/insertSql.list.sql"));
            OutputStreamWriter writer = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(writer);

            FileOutputStream out2 = new FileOutputStream(new File("/Users/user/program/shell/transdata/20160229-sina/insertSql_RollBack.list.sql"));
            OutputStreamWriter writer2 = new OutputStreamWriter(out2);
            BufferedWriter bw2 = new BufferedWriter(writer2);


            FileOutputStream out3 = new FileOutputStream(new File("/Users/user/program/shell/transdata/20160229-sina/ac_new_video_update.list.sql"));
            OutputStreamWriter writer3 = new OutputStreamWriter(out3);
            BufferedWriter bw3 = new BufferedWriter(writer3);

            FileOutputStream out4 = new FileOutputStream(new File("/Users/user/program/shell/transdata/20160229-sina/ac_new_video_update_RollBack.list.sql"));
            OutputStreamWriter writer4 = new OutputStreamWriter(out4);
            BufferedWriter bw4 = new BufferedWriter(writer4);


            while ((temp = bufferedInputStream.readLine()) != null) {
                String sqlByUrl = getSqlByUrl(temp);
                String deleteSql = getSqlDelete(temp);
//                String updateSql = getSqlUpdate(temp);
//                String updateSqlRollback = getSqlUpdateRollBack(temp);
                int videoId = getVideoId(temp);
                set.add(videoId);
                bw.write(sqlByUrl);
                bw2.write(deleteSql);
//                bw3.write(updateSql);
//                bw4.write(updateSqlRollback);
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
        String url = "http://vplay.aixifan.com/des/acf-2/1523110_flv/1523110_360p_43.mp4";
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
        System.out.println(CommonUtil.getTablename("500000"));
        System.out.println(CommonUtil.getTablename("499999"));
        System.out.println(CommonUtil.getTablename("600000"));
        System.out.println(CommonUtil.getTablename("1100000"));
        System.out.println(CommonUtil.getTablename("1600000"));
        System.out.println(CommonUtil.getTablename("2100000"));
        System.out.println(CommonUtil.getTablename("2600000"));
        System.out.println(CommonUtil.getTablename("3100000"));
        System.out.println(CommonUtil.getTablename("3600000"));
        System.out.println(CommonUtil.getTablename("4100000"));
        System.out.println(CommonUtil.getTablename("4600000"));
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
