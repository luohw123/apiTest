package com.javaapi.test.testUtil.fileUtil.Util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 16/3/14.
 */
public class DealSourceStatus {

    public static final String VIDEO_STATUS_DEF = "0";//草稿
    public static final String VIDEO_STATUS_UNCHECKED = "1";//未审核
    public static final String VIDEO_STATUS_CHECKED = "2";//主站审核通过
    public static final String VIDEO_STATUS_DELETE = "4";//已删除的
    public static final String VIDEO_STATUS_RECYCLE = "3";//投稿进回收站时视频的状态
    public static final String VIDEO_STATUS_ENCODE = "5";//视频转码中
    public static final String VIDEO_STATUS_ENCODE_FAILED = "6";//视频转码失败
    public static final String VIDEO_STATUS_REJECTED = "7";//退稿




    /** 转码失败 */
    public static final String ENCODE_FAIL = "-3";
    /** 上传失败 */
    public static final String UPLOAD_FAIL = "-1";
    /** 上传中 */
    public static final String UPLOADING = "0";
    /** 上传成功 */
    public static final String UPLOAD_OK = "1";
    /** 转码中 */
    public static final String ENCODING = "2";
    /** 转码成功 */
    public static final String ENCOD_OK = "3";


//    public static final String VIDEO_STATUS_UNCHECKED = "1";//未审核
//    public static final String VIDEO_STATUS_CHECKED = "2";//主站审核通过
//    public static final String VIDEO_STATUS_DELETE = "4";//已删除的




    public static final String INSERT_AC_SOURCE = "INSERT INTO ac_source (`id`,`status`,`user_id`,`creator`,`create_time`,`last_updator`,`last_update_time`) value (%s,%s,%s,%s,'%s',%s,'%s');";
    public static final String checkVideoSource = "SELECT video_id FROM %s WHERE video_id=%s LIMIT 1;\r\n";
    public static final Map<String, String> videoSourceMap = new HashMap<String, String>();
    @Test
    public void loadVideoSource() {
        String s = "/Users/user/program/shell/160314-trans-ac_source/source_all_vid.tmp";
        LineIterator lineIterator = null;
        try {
            lineIterator = FileUtils.lineIterator(new File(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (lineIterator.hasNext()) {
            String s1 = lineIterator.nextLine();
            videoSourceMap.put(s1, s1);
        }
//        long l = System.currentTimeMillis();
//        videoSourceMap.containsKey("1004");
//        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void test() {
        loadVideoSource();


//        Map<String, ArrayList<String>> ac_video_source_map = new HashMap<>();
//        ac_video_source_map.put("ac_video_source", new ArrayList<String>());
//        ac_video_source_map.put("ac_video_source_2", new ArrayList<String>());
//        ac_video_source_map.put("ac_video_source_3", new ArrayList<String>());
//        ac_video_source_map.put("ac_video_source_4", new ArrayList<String>());
//        ac_video_source_map.put("ac_video_source_5", new ArrayList<String>());
//        ac_video_source_map.put("ac_video_source_6", new ArrayList<String>());
//        ac_video_source_map.put("ac_video_source_7", new ArrayList<String>());
        String path = "/Users/user/program/shell/160314-trans-ac_source/all_ac_new_video.txt";
        String ac_source_ok = "/Users/user/program/shell/160314-trans-ac_source/ac_source_ok.txt";
        String ac_new_video_update = "/Users/user/program/shell/160314-trans-ac_source/ac_new_video_update.txt";
        String ac_new_video_update_rollback = "/Users/user/program/shell/160314-trans-ac_source/ac_new_video_update_rollback.txt";


        List<String> listResult = new ArrayList<>();
        List<String> listResultAcNewVideo = new ArrayList<>();
        List<String> listResultAcNewVideoRollback = new ArrayList<>();


        LineIterator lineIterator = null;
        try {
            lineIterator = FileUtils.lineIterator(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            String[] split = next.split(",");
            String source_id = split[0];
            String status = split[1];
            String userId = split[2];
            String createTime = split[3];
            String id = split[4];
//            boolean needVideSource = needVideSource(status);
//            if (needVideSource) {
//                ArrayList<String> strings = ac_video_source_map.get(getTablename(source_id));
//                strings.add(source_id);
//            } else {
            String acSourceSql = getAcSourceSql(source_id, status, userId, createTime);
            listResult.add(acSourceSql);
            String acNewVideoSql = getAcNewVideoSql(status, id);

            listResultAcNewVideo.add(acNewVideoSql);
            String acNewVideoSqlRollback = getAcNewVideoSqlRollback(status, id);
            listResultAcNewVideoRollback.add(acNewVideoSqlRollback);
//            }
        }
//        generateSelectAcVideoSource(ac_video_source_map);
        try {
            FileUtils.writeLines(new File(ac_source_ok), listResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (String acVideo : listResultAcNewVideo) {
                sb.append(acVideo);
            }
            FileUtils.write(new File(ac_new_video_update), sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (String acVideo : listResultAcNewVideoRollback) {
                sb.append(acVideo);
            }
            FileUtils.write(new File(ac_new_video_update_rollback),sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAcNewVideoSqlRollback(String status, String videoId) {
        String afterStatus = dealCheckStatus(status);
        if(afterStatus.equals(status)){
            return "";
        }
        String update = "UPDATE ac_new_video SET status=%s WHERE video_id=%s ; \r\n";
        String format = String.format(update, status, videoId);
        return format;
    }

    private String getAcNewVideoSql(String status, String videoId) {

        String afterStatus = dealCheckStatus(status);
        if(afterStatus.equals(status)){
            return "";
        }
        String update = "UPDATE ac_new_video SET status=%s WHERE video_id=%s ; \r\n";
        String format = String.format(update, afterStatus, videoId);
        return format;
    }

    private String dealCheckStatus(String status) {
//        public static final String VIDEO_STATUS_DEF = "0";//草稿
//        public static final String VIDEO_STATUS_UNCHECKED = "1";//未审核
//        public static final String VIDEO_STATUS_CHECKED = "2";//主站审核通过
//        public static final String VIDEO_STATUS_DELETE = "4";//已删除的
//        public static final String VIDEO_STATUS_RECYCLE = "3";//投稿进回收站时视频的状态
//        public static final String VIDEO_STATUS_ENCODE = "5";//视频转码中
//        public static final String VIDEO_STATUS_ENCODE_FAILED = "6";//视频转码失败
//        public static final String VIDEO_STATUS_REJECTED = "7";//退稿
        if(VIDEO_STATUS_CHECKED.equals(status)){
            return status;
        }
        if(VIDEO_STATUS_DELETE.equals(status)){
            return status;
        }
        if(VIDEO_STATUS_UNCHECKED.equals(status)){
            return status;
        }

        return VIDEO_STATUS_UNCHECKED;
    }


    private void generateSelectAcVideoSource(Map<String, ArrayList<String>> ac_video_source_map) {
        Iterator<Map.Entry<String, ArrayList<String>>> iterator = ac_video_source_map.entrySet().iterator();
        StringJoiner unionAll = new StringJoiner("union all");

        while (iterator.hasNext()) {
            Map.Entry<String, ArrayList<String>> next = iterator.next();
            String key = next.getKey();
            ArrayList<String> value = next.getValue();

            StringJoiner sj = new StringJoiner(",", "(", ")");
            for (String s : value) {
                sj.add(s);
            }
            String format = String.format("SELECT video_id FROM %s WHERE video_id in %s ", key, sj.toString());
            unionAll.add(format);
        }
        System.out.println(unionAll);
    }

    private String getSelectVideoSource(String source_id) {
        String tablename = getTablename(source_id);
        String result = String.format(checkVideoSource, tablename, source_id);
        return result;
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

    private boolean needVideSource(String status) {
        if(VIDEO_STATUS_DEF.equals(status)) {
            return true;
        }
        if(VIDEO_STATUS_RECYCLE.equals(status)) {
            return true;
        }
        if(VIDEO_STATUS_DELETE.equals(status)) {
            return true;
        }
        if(VIDEO_STATUS_REJECTED.equals(status)) {
            return true;
        }
        return false;

    }

    private String getAcSourceSql(String source_id, String status, String userId, String createTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date());
        String dealedStatus = dealStats(status,source_id,createTime);
        String result = String.format(INSERT_AC_SOURCE, source_id, dealedStatus, userId, userId, createTime, userId, format);
        return result;
    }

    private String dealStats(String status, String source_id, String createTime) {
        if (VIDEO_STATUS_UNCHECKED.equals(status)) {
//            System.out.println("other old stats="+VIDEO_STATUS_UNCHECKED);

            return ENCOD_OK;

        }
        if (VIDEO_STATUS_CHECKED.equals(status)) {
//            System.out.println("other old stats="+VIDEO_STATUS_CHECKED);

            return ENCOD_OK;

        }
        if (VIDEO_STATUS_ENCODE.equals(status)) {
//            System.out.println("other old stats="+VIDEO_STATUS_ENCODE);


            return getOtherStatus(source_id,createTime);

        }
        if (VIDEO_STATUS_ENCODE_FAILED.equals(status)) {
//            System.out.println("other old stats="+VIDEO_STATUS_ENCODE_FAILED);
            return ENCODE_FAIL;
        }
        return getOtherStatus(source_id,createTime);
    }
    private String checkEnCode(String createTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date yesterday = DateUtils.addDays(new Date(), -1);
        Date create = null;
        try {
            create = simpleDateFormat.parse(createTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (create.before(yesterday)) {
            return ENCODE_FAIL;
        } else {
            return ENCODING;
        }
    }

    private String getOtherStatus(String source_id, String createTime) {
        boolean b = videoSourceMap.containsKey(source_id);
        if (b) {
            System.out.println("other stats="+ENCOD_OK);
            return ENCOD_OK;
        } else {
            String s = checkEnCode(createTime);
            System.out.println("other stats=" + s);
            return s;
        }
    }

    @Test
    public void testDate() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = DateUtils.addDays(new Date(), -1);
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }
}
