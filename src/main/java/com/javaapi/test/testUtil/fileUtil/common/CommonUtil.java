package com.javaapi.test.testUtil.fileUtil.common;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by user on 16/4/28.
 */
public class CommonUtil {


    public static String getTablenameByIndex(Integer tableIndex) {
        if(tableIndex == 0) {
            return "ac_video_source";
        }
        else {
            String format = String.format("ac_video_source_%s", tableIndex);
            return format;
        }
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

    public static <T> List<T> removeAll(List<T> collection, List<T> toBeRemove) {
        Map<T, T> collect = toBeRemove.stream().collect(Collectors.toMap((s) -> s, (s1 -> s1)));

        List<T> result = collection.stream().filter((s -> !collect.containsKey(s))).collect(Collectors.toList());


        return result;
    }

}
