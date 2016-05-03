package com.javaapi.test.testUtil.fileUtil.common;

/**
 * Created by user on 16/4/28.
 */
public class CommonUtil {

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

    public static String getSourceSql(String videoId, String url, String source,String rate, String now) {
        String tablename = CommonUtil.getTablename(videoId);
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO ");
        sb.append(tablename);
        sb.append("(video_id,url,source,bit_rate,creater,create_time,updater,update_time) VALUES (");
        sb.append(videoId + ",");
        sb.append("'" + url + "',");
        sb.append("'"+ source +"' ,");
        sb.append(rate + ",");
        sb.append("1,'" + now + "',1,'" + now + "');");
        return sb.toString();
    }
}
