package com.javaapi.test.testUtil.fileUtil2.msg;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 发送视频失效信息
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
    @Autowired
    NamedParameterJdbcTemplate nameJdbcTemplate;

    @Test
    public void testName() throws Exception {
        String videoIds = null;
        getVideoNameMap(null);

    }


    @Test
    public void test() throws IOException {
        String rejectOpinionPattern = "神仙UP主大人您好,您的宝贝稿件ac%s中,%s已失效（分P标题后的数字为视频源id），劳驾大人前往施法修复补档。";
        String vidPattern = "“%s” %s";
        List<String> result = new ArrayList<>();

        LinkedHashSet<Integer> contentIds = new LinkedHashSet<>();
        LinkedHashSet<Integer> videoIds = new LinkedHashSet<>();

        Map<Integer, List<ContentVideo>> contentVideo = getContentVideo(contentIds,videoIds);
        Map<Integer, String> videoNameMap = getVideoNameMap(new ArrayList<>(videoIds));
        Map<Integer, Integer> contentUserId = getContentUp(new ArrayList<>(contentIds));


        for (Integer contentId : contentIds) {
            List<ContentVideo> videos = contentVideo.get(contentId);
            StringJoiner sj = new StringJoiner("、");
            for (ContentVideo video : videos) {
                String sid = video.getSid();
                String videoTitle = videoNameMap.get(video.getVid());
                String videoInfo = String.format(vidPattern, videoTitle, sid);
                videoInfo = videoInfo.replaceAll("'", "\\\\'");
                videoInfo = videoInfo.replaceAll("\\[", "【");
                videoInfo = videoInfo.replaceAll("\\]", "】");
                sj.add(videoInfo);
            }
            String rejectOpinionString = String.format(rejectOpinionPattern, contentId, sj.toString());

            result.add(getMsgSql(contentUserId.get(contentId), rejectOpinionString));

        }

        FileUtils.writeLines(new File("/Users/user/Downloads/404notfound2/result部分ac.txt"),result);


    }

    private String getMsgSql(Integer integer, String rejectOpinionString) {
        return String.format("insert into jc_broad_msg (`user_id` ,`msg`) values(%s,'%s');",integer,rejectOpinionString);
    }

    private Map<Integer, List<ContentVideo>> getContentVideo(LinkedHashSet<Integer> contentIds, LinkedHashSet<Integer> videoIds) throws IOException {
        String s = "/Users/user/Downloads/404notfound2/部分视频tab2.txt";
        List<String> strings  = Files.readAllLines(Paths.get(s));

        List<ContentVideo> videos = new ArrayList<>();
        int i = 0;
        for (String string : strings) {
            if (i == 0) {
                i++;
                continue;

            }

            String[] split = string.split("\t");
            if (split.length != 4) {
                continue;
            }
            ContentVideo contentVideo = new ContentVideo();
            int contentId = Integer.parseInt(split[0]);
            contentVideo.setContentId(contentId);
            contentVideo.setContentTitle(split[1]);
            int vid = Integer.parseInt(split[2]);
            contentVideo.setVid(vid);
            contentVideo.setSid(split[2]);
            contentIds.add(contentId);
            videoIds.add(vid);
            videos.add(contentVideo);
        }
        Map<Integer, List<ContentVideo>> collect = videos.stream().collect(Collectors.groupingBy(ContentVideo::getContentId));
        return collect;
    }

    private Map<Integer, String> getVideoNameMap(List<Integer> videoList) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("videoIds", videoList);
        List<Map<String, Object>> maps = nameJdbcTemplate.queryForList("SELECT video_id,title FROM ac_new_video WHERE video_id in (:videoIds)", paramMap);
        Map<Integer, String> collect = maps.stream().collect(Collectors.toMap((k) -> (Integer) k.get("video_id"), (v) -> (String) v.get("title"), (oldVal, newVal) -> newVal));
        return collect;
    }

    private Map<Integer, Integer> getContentUp(List<Integer> contentIds) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("contentIds", contentIds);
        List<Map<String, Object>> maps = nameJdbcTemplate.queryForList("SELECT content_id,user_id FROM jc_content WHERE content_id in (:contentIds)", paramMap);
        Map<Integer, Integer> collect = maps.stream().collect(Collectors.toMap((k) -> (Integer) k.get("content_id"), (v) -> (Integer) v.get("user_id"), (oldVal, newVal) -> newVal));
        return collect;
    }
}
