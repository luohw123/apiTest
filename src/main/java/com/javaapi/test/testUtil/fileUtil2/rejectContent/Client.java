package com.javaapi.test.testUtil.fileUtil2.rejectContent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {

    @Autowired
    NamedParameterJdbcTemplate nameJdbcTemplate;

    /**
     *
     * @throws Exception
     */
    @Test
    public void rejectContent() throws Exception {
        Path path = Paths.get("/Users/user/Downloads/404notfound2/全部视频不能播放ac号left_20161118.csv");
        List<String> strings = Files.readAllLines(path);
        List<Integer> contentIds = new ArrayList<>();
        int index = 0;
        for (String string : strings) {
            int contentId = Integer.parseInt(string);
            contentIds.add(contentId);
            if(index !=0 && index % 300==0){
                sendRedirect(contentIds);
                System.out.println("contentId = " + contentIds);
                contentIds.clear();
            }
            index++;
        }
        // 清除不足300的
        if (contentIds != null && !contentIds.isEmpty()) {
            sendRedirect(contentIds);
            System.out.println("contentId = " + contentIds);
            contentIds.clear();
        }



    }
    @Test
    public void rejectContentTmp() throws Exception {
        List<Integer> contentIds = new ArrayList<>();
//        contentIds.add(111112);
        contentIds.add(2756249);
        sendRedirect(contentIds);
    }

    private void sendRedirect(List<Integer> contentIds) {
        String cookieString = "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221586bd4c1ba0-09176da60fa4cf-1d346c53-fa000-1586bd4c1bb3cc%22%7D; JSESSIONID=ba8ecacf5d064059bc3379c92a11f7a8; ac_time=8swquISi2jsrtMY82x; clientlanguage=zh_CN";
        String url = "http://hengyang.aixifan.com/acadmin/nanohasnopenis/content/o_reject.do";
        ArrayList<Map<String, Object>> params = new ArrayList<>();

        for (Integer contentId : contentIds) {
            HashMap<String, Object> contentIdMap = new HashMap<>();
            contentIdMap.put("ids", contentId);
            params.add(contentIdMap);
        }

        String opinion = "该视频稿件猴子这边无法播放，请检查修改视频源，谢谢。";
        HashMap<String, Object> rejectOpinion = new HashMap<>();
        rejectOpinion.put("rejectOpinion", opinion);
        HashMap<String, Object> queryRecommend = new HashMap<>();
        queryRecommend.put("queryRecommend", false);
        HashMap<String, Object> queryOrderBy = new HashMap<>();
        queryOrderBy.put("queryOrderBy", 0);
        HashMap<String, Object> queryTopLevel = new HashMap<>();
        queryTopLevel.put("queryTopLevel", false);
        HashMap<String, Object> rejectStep = new HashMap<>();
        rejectStep.put("rejectStep", 0);
        params.add(rejectOpinion);
        params.add(queryRecommend);
        params.add(queryOrderBy);
        params.add(queryTopLevel);
        params.add(rejectStep);


        boolean result = ShopUtil.sendReject(url, params, cookieString);
        if(!result){
            System.out.println("退稿异常, contentIds = " + contentIds);
        }
    }
}
