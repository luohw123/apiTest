package com.javaapi.test.testUtil.fileUtil.Util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 统计到底有多少letv 和 youku
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class StatUtil {

    @Resource
    private JdbcTemplate jdbcTemplate;


    @Test
    public void testGetAllLetvSid() throws Exception {
        List<String> letv = getAllSidByType("letv");
        List<String> youku = getAllSidByType("youku");
        List<String> haveLetv = removeAll(letv, youku);
        List<String> haveYouku = removeAll(youku, letv);
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/haveLetv.txt"),haveLetv);
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/haveYouku.txt"), haveYouku);
    }


    @Test
    public void test() throws IOException {
        List<String> haveYouku = FileUtils.readLines(new File("/Users/user/program/shellTmp/haveLetv.txt"));
        List<String> statusEqual = getStatusEqual(haveYouku, "2");
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/过审的只有letv没有youku.txt"), statusEqual);
    }

    @Test
    public void test2() throws IOException {
        List<String> haveYouku = FileUtils.readLines(new File("/Users/user/program/shellTmp/haveLetv.txt"));
        List<String> statusEqual = getStatusNotEqual(haveYouku, "2");
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/未过审的只有letv没有youku.txt"), statusEqual);
    }
    @Test
    public void test3() throws IOException {
        List<String> haveYouku = FileUtils.readLines(new File("/Users/user/program/shellTmp/haveYouku.txt"));
        List<String> statusEqual = getStatusNotEqual(haveYouku, "2");
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/未过审的只有Youku没有letv.txt"), statusEqual);
    }

    @Test
    public void testGetSidInfo() throws IOException {
        List<String> strings1 = FileUtils.readLines(new File("/Users/user/program/shellTmp/只有dilian没有youku的并且状态为2的.txt"));

        StringJoiner sj = new StringJoiner(",","(",")");
        strings1.forEach((s -> sj.add(s)));

        String s = "select av.source_id as 'sid',acv.video_id as 'video_id',acv.content_id as 'ac' from ac_new_video av \n" +
                "right join ac_content_video acv \n" +
                "on acv.video_id = av.video_id\n" +
                "\n" +
                "where av.source_id in " + sj.toString();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(s);
        List<String> collect = maps.stream().map((m) -> m.get("sid") + "," + m.get("video_id") + "," + m.get("ac")).collect(Collectors.toList());

        String pathname = "/Users/user/program/shellTmp/只有dilian没有youku的并且状态为2的info.txt";
        FileUtils.write(new File(pathname),"sid,video_id,ac\n");
        FileUtils.writeLines(new File(pathname), collect, true);
    }


    @Test
    public void testGetSid() throws IOException {
        String sid = "/Users/user/program/shellTmp/未过审的只有Youku没有letv.txt";
        List<String> sid1 = Files.readAllLines(Paths.get(sid));

        StringJoiner sj = new StringJoiner(",","(",")");
        sid1.forEach((s -> sj.add(s)));

        List<Map<String, Object>> allSidByTypeForSid = getAllSidByTypeForSid(sj.toString());
        System.out.println("allSidByTypeForSid = " + allSidByTypeForSid.size());
        List<String> collect = allSidByTypeForSid.stream().map((m) -> {
            String s = m.get("video_id") + "," + m.get("url");
            return s;
        }).collect(Collectors.toList());
        FileUtils.writeLines(new File("/Users/user/program/shellTmp/未过审的只有Youku没有letv_sid_url.txt"), collect);
    }

    /**
     * 获取 sid,they_vid,they_unique的对应关系
     * @throws IOException
     */
    @Test
    public void testGetLetvSidVid() throws IOException {
        String sid = "/Users/user/program/shellTmp/过审的只有letv没有youku.txt";
        List<String> strings = Files.readAllLines(Paths.get(sid));
        System.out.println("strings = " + strings.size());
        List<String> collect1 = strings.stream().distinct().collect(Collectors.toList());
        System.out.println("collect1 = " + collect1.size());



        StringJoiner sj = new StringJoiner(",","(",")");
        collect1.forEach((s -> sj.add(s)));

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT special_id,they_vid,they_unique FROM ac_source_info where special_id in "+sj.toString());
        List<String> collect = maps.stream().map((m) -> {
            return m.get("special_id") + "," + m.get("they_vid") + "," + m.get("they_unique");
        }).collect(Collectors.toList());

        FileUtils.writeLines(new File("/Users/user/program/shellTmp/过审的只有letv没有youku_sid_vid_unique.txt"),collect);


    }









    private List<String> getStatusEqual(List<String> strings1, String status) throws IOException {
        StringJoiner sj = new StringJoiner(",","(",")");
        strings1.forEach((s -> sj.add(s)));
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT DISTINCT(source_id) FROM ac_new_video where source_type='zhuzhan' and status =" + status + " and  source_id in " + sj.toString() + ";");

        List<String> result = new ArrayList<>();
        for (Map<String, Object> map : maps) {

            String source_id = (String) map.get("source_id");
            result.add(source_id);

        }
        return result;
    }

    private List<String> getStatusNotEqual(List<String> strings1, String status) throws IOException {
        StringJoiner sj = new StringJoiner(",","(",")");
        strings1.forEach((s -> sj.add(s)));
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT DISTINCT(source_id) FROM ac_new_video where source_type='zhuzhan' and status !=" + status + " and  source_id in " + sj.toString() + ";");

        List<String> result = new ArrayList<>();
        for (Map<String, Object> map : maps) {

            String source_id = (String) map.get("source_id");
            result.add(source_id);

        }
        return result;
    }









    private List<String> removeAll(List<String> collection, List<String> toBeRemove) {
        Map<String, String> collect = toBeRemove.stream().collect(Collectors.toMap((s) -> s, (s1 -> s1)));

        List<String> result = collection.stream().filter((s -> !collect.containsKey(s))).collect(Collectors.toList());


        return result;
    }


    private List<String> getAllSidByType(String letv) {
        String allLetvSid = "select video_id from ac_video_source where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_2 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_3 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_4 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_5 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_6 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_7 where source='" + letv + "'\n" +
                "UNION all \n" +
                "select video_id from ac_video_source_8 where source='" + letv + "';\n";
        List<String> result = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(allLetvSid);
        maps.forEach((sid) -> result.add((sid.get("video_id")).toString()));
        return result;
    }

    private List<Map<String, Object>> getAllSidByTypeForSid(String sids) {
        String allLetvSid = "select video_id,url from ac_video_source where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_2 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_3 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_4 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_5 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_6 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_7 where source='youku' and video_id in " + sids + "\n" +
                "UNION all \n" +
                "select video_id,url from ac_video_source_8 where source='youku' and video_id in " + sids + ";\n";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(allLetvSid);
        return maps;
    }
}
