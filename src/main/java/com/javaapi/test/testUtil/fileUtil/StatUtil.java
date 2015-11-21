package com.javaapi.test.testUtil.fileUtil;

import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class StatUtil {
    private Set set = new HashSet();

    Map<Integer, Integer> map = new HashMap<>();

    @Test
    public void test() throws IOException {
        InputStream in = new FileInputStream(new File("/Users/user/program/shell/transdata/20151116/music-20151113.txt"));
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedInputStream = new BufferedReader(reader);
        String temp = null;
        while ((temp = bufferedInputStream.readLine()) != null) {
            int videoId = getVideoId(temp);
            if (map.get(videoId) == null) {
                map.put(videoId, 1);
            }else {
                Integer time = map.get(videoId);
                time++;
                map.put(videoId, time);
            }
        }
        createSql();
    }

    private void createSql() throws FileNotFoundException {
        try {
            InputStream in = new FileInputStream(new File("/Users/user/program/shell/transdata/20151116/music-20151113.txt"));
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedInputStream = new BufferedReader(reader);
            String temp = null;

            FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/transdata/20151116/insertSql.list.sql"));
            OutputStreamWriter writer = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(writer);

            FileOutputStream out2 = new FileOutputStream(new File("/Users/user/program/shell/transdata/20151116/insertSql_RollBack.list.sql"));
            OutputStreamWriter writer2 = new OutputStreamWriter(out2);
            BufferedWriter bw2 = new BufferedWriter(writer2);


            FileOutputStream out3 = new FileOutputStream(new File("/Users/user/program/shell/transdata/20151116/ac_new_video_update.list.sql"));
            OutputStreamWriter writer3 = new OutputStreamWriter(out3);
            BufferedWriter bw3 = new BufferedWriter(writer3);

            FileOutputStream out4 = new FileOutputStream(new File("/Users/user/program/shell/transdata/20151116/ac_new_video_update_RollBack.list.sql"));
            OutputStreamWriter writer4 = new OutputStreamWriter(out4);
            BufferedWriter bw4 = new BufferedWriter(writer4);


            while ((temp = bufferedInputStream.readLine()) != null) {
                int videoId = getVideoId(temp);
                if(map.get(videoId) == null || map.get(videoId) <4) {
                    continue;
                }
                String sqlByUrl = getSqlByUrl(temp);
                String deleteSql = getSqlDelete(temp);
//                String updateSql = getSqlUpdate(temp);
//                String updateSqlRollback = getSqlUpdateRollBack(temp);
                set.add(videoId);
                bw.write(sqlByUrl);
                bw2.write(deleteSql);
//                bw3.write(updateSql);
//                bw4.write(updateSqlRollback);
            }
            // 更新sql
            createUpdateSql(bw3);
            // 更新sql
            createUpdateSqlRollback(bw4);
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

    private static int getVideoId(String url) {
        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
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
        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if (!matcher.find()) {
            return "#" + url + "\r\n";
        }
        String videoId = matcher.group(1);
        String sql = "update ac_new_video set source_type = 'letv' where video_id = " + videoId + ";\r\n";
        return sql;
    }

    private static String getSqlUpdate(String url) {
        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if (!matcher.find()) {
            return "#" + url + "\r\n";
        }
        String videoId = matcher.group(1);
        String sql = "update ac_new_video set source_type = 'zhuzhan' where video_id = " + videoId + ";\r\n";
        return sql;
    }

    private static String getSqlDelete(String url) {
        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
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

    public static String getSqlByUrl(String url) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
        Matcher matcher = pattern.matcher(url);
        if (!matcher.find()) {
            return "#" + url + "\r\n";
        }
        String videoId = matcher.group(1);
        String rate = mapDilianProfile(matcher.group(2));
        String tablename = getTablename(videoId);

        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO ");
        sb.append(tablename);
        sb.append("(video_id,url,source,bit_rate,creater,create_time,updater,update_time) VALUES (");
        sb.append(videoId + ",");
        sb.append("'" + url + "',");
        sb.append("'D_LIAN' ,");
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

    private void createUpdateSql(BufferedWriter bw3) {
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            int videoId = (int) iterator.next();
            String sql = "update ac_new_video set source_type = 'zhuzhan' where video_id = " + videoId + ";\r\n";
            try {
                bw3.write(sql);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
