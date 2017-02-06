package com.javaapi.test.testUtil.fileUtil4;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javaapi.test.testUtil.fileUtil.common.CommonUtil;
import com.javaapi.test.testUtil.net.httpclient.HttpUtil_431;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
    public static String CLIENT_ID = TestYoukuUtil.CLIENT_ID;
    @Autowired
    NamedParameterJdbcTemplate nameJdbcTemplate;
    private transient Logger logger = LoggerFactory.getLogger(this.getClass());
    private int index = 0;
    private ExecutorService es = newBlockingExecutorsUseCallerRun(30);

    public static ExecutorService newBlockingExecutorsUseCallerRun(int size) {
        return new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        if (!executor.isShutdown()) {
                            try {
                                executor.getQueue().put(r);
                            } catch (InterruptedException e) {
                                // should not be interrupted
                            }
                        }
                    }
                });
    }

    @Test
    public void test() throws IOException {
        List<String> allVideoSourceTableNames = CommonUtil.getAllVideoSourceTableNames(10);
        List<String> newTableNames = new ArrayList<>();
        for (int i = 7; i < allVideoSourceTableNames.size(); i++) {
            String s = allVideoSourceTableNames.get(i);
            newTableNames.add(s);

        }
        allVideoSourceTableNames = newTableNames;
        for (String tableName : allVideoSourceTableNames) {
            List<Map<String, Object>> allYoukuNoNormalInfo = getAllYoukuInfo(tableName, (tName) -> {
                String sql = " SELECT video_id,url FROM  " + tName + " WHERE source='youku' ";
                List<Map<String, Object>> maps = nameJdbcTemplate.queryForList(sql, new HashMap<String, Object>());
                return maps;
            }, (paramMap) -> {
                try {
                    String url = (String) paramMap.get("url");
                    HashMap<String, String> param = new HashMap<>();
                    param.put("client_id", CLIENT_ID);
                    param.put("video_id", url);
                    String result = null;
                    result = HttpUtil_431.get(TestYoukuUtil.YKUrl, param);
                    JSONArray json = JSON.parseArray(result);
                    if (json != null && !json.isEmpty()) {
                        JSONObject jsonObject = json.getJSONObject(0);
                        paramMap.put("youkuStatus", jsonObject.getString("state"));
                    }
                } catch (Exception e) {
                    logger.error("处理youku数据异常 ,param = {}", paramMap, e);
                    return paramMap;
                }
                return paramMap;
            });
            FileUtils.writeLines(new File("/Users/user/Downloads/getYoukuIllegalState/allYoukuNoNormalInfo_" + tableName + ".txt"), allYoukuNoNormalInfo);
        }
    }

    public List<Map<String, Object>> getAllYoukuInfo(String tableName, Sampler sampler, Processor processor) {
        List<Map<String, Object>> youkuSource = sampler.getYoukuSource(tableName);
        if (youkuSource == null || youkuSource.isEmpty()) {
            return youkuSource;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (Map<String, Object> ykSource : youkuSource) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    Map<String, Object> infoFromYouku = processor.getInfoFromYouku(ykSource);
                    if (!infoFromYouku.getOrDefault("youkuStatus", "").equals("normal")) {
                        list.add(infoFromYouku);
                    }
                    if (index != 0 && index % 100 == 0) {
                        System.out.println("index = " + index);
                    }
                    index++;
                }
            });

        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String requestYouku(String url) {
        HashMap<String, String> param = new HashMap<>();
        param.put("client_id", CLIENT_ID);
        String s = HttpUtil_431.get(TestYoukuUtil.YKUrl, param);
        return s;
    }

    public static interface Sampler {
        public List<Map<String, Object>> getYoukuSource(String tableName);
    }

    public static interface Processor {
        public Map<String, Object> getInfoFromYouku(Map<String, Object> param);
    }


}
