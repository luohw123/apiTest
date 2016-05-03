package com.javaapi.test.testUtil.fileUtil.letvBack;

import com.javaapi.test.testUtil.fileUtil.common.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
    public   String USERS_USER_PROGRAM_SHELL_ALL_LETV_VID_SID_CSV = "";
    public   String USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU = "";
    public   String PATHNAME = "";
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    private List<Integer> vidSet = new ArrayList<>();

    private Map<Integer, String> vidSidOld = new LinkedHashMap<>();

    public static final String type = "new";


    @Test
    public void test() throws IOException {
        String type = Client.type;

        if("new".equals(type)){
            USERS_USER_PROGRAM_SHELL_ALL_LETV_VID_SID_CSV = "/Users/user/program/shell/all-letv-vid_sid_extra.csv";
            USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU = "/Users/user/program/shell/allletvWithNoYouku";
        } else if ("old".equals(type)) {
            USERS_USER_PROGRAM_SHELL_ALL_LETV_VID_SID_CSV = "/tmp/ac_new_video_20160908.txt";
            USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU = "/Users/user/program/shell/allletvWithNoYouku_old";
        }else{
            System.out.println("no type choosed");
            return;
        }
        PATHNAME = "/Users/user/program/shell/allletvWithNoYouku_old" + "/oldLetvBefore.txt";


        Map < String, Integer > sidToVid_now = getvidToSidNow();
        if (sidToVid_now == null) return;
        Map<String, String> noyoukuSid = LetvBackUtil.loadAllNoYoukuSid();

        Iterator<Map.Entry<String, Integer>> iterator = sidToVid_now.entrySet().iterator();


        List<String> letvHaveNoyouku = new ArrayList<>();
        List<String> letvNoNoyouku = new ArrayList<>();


        List<String> oldLetvBefore = new ArrayList<>();

        Map<String, String> stringStringMap = null;
        if ("new".equals(Client.type)) {
            stringStringMap = LetvBackUtil.loadOldVidSid(PATHNAME);
        }
        List<String> finalAllResult = new ArrayList<>();
        List<String> thatInold = new ArrayList<>();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            String sid = next.getKey();
            boolean b = noyoukuSid.containsKey(sid);
            Integer vid = next.getValue();

            finalAllResult.add(String.valueOf(vid)+","+vidSidOld.get(vid));

            if ("new".equals(Client.type)) {
                if (stringStringMap.containsKey(String.valueOf(vid))) {
                    thatInold.add(String.valueOf(vid) + "," + vidSidOld.get(vid));
                    continue;
                }
            }


            if("old".equals(Client.type)){
                oldLetvBefore.add(String.valueOf(vid));
            }
            if (b) {
                letvHaveNoyouku.add(getAllSql(vid));
            } else {
                letvNoNoyouku.add(backVidSid(vid));
            }

        }

        FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/letvHaveNoyouku.txt"), letvHaveNoyouku);
        FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/letvNoNoyouku.txt"), letvNoNoyouku);
        FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/finalAllResult.txt"), finalAllResult);
        if("old".equals(Client.type)){
            FileUtils.writeLines(new File(PATHNAME), oldLetvBefore);
        }
        if ("new".equals(Client.type)) {
            FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/thatInold.txt"), thatInold);

        }


    }

    private String backVidSid(Integer vid) {
        String oldSid = vidSidOld.get(vid);
        return vid + "," + oldSid;

    }

    private String getAllSql(Integer vid) {
        String oldSid = vidSidOld.get(vid);
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String sourceSql = CommonUtil.getSourceSql(String.valueOf(vid), oldSid, "letv", "99", now);

        return sourceSql;


    }

    private Map<String, Integer> getvidToSidNow() {
        List<String> strings = cleanData();
        if (CollectionUtils.isEmpty(strings)) {
            System.out.println("string 为空");
            return null;
        }
        for (int i = 0; i < strings.size(); i++) {
            if (i == 0) {
                continue;
            }
            String vid_sid = strings.get(i);

            String[] split = vid_sid.split(",");

            String vid = split[0];
            String sid = split[1];
            vidSet.add(Integer.parseInt(vid));
            vidSidOld.put(Integer.parseInt(vid), sid);
        }
        Collections.sort(vidSet, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println("筛选过后[去除不符合长度的letv 代码]后最大的vid为" + vidSet.get(vidSet.size()-1));



        StringJoiner sj = new StringJoiner(",", "(", ")");
        for (Integer vid : vidSet) {
            sj.add(String.valueOf(vid));
        }
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT video_id ,source_id ,status,source_type FROM system32.ac_new_video WHERE video_id in  " + sj.toString());

        maps = cleanBussinessDate(maps);


        System.out.println(String.format("根据备份letv文件在数据库中发现,类型为[zhuzhan] ,未换过源的 条数为 =%s ", maps.size()));

        Map<String, Integer> sidToVid_now = new LinkedHashMap<>();
        for (Map<String, Object> vidSid : maps) {
            Integer video_id = (Integer) vidSid.get("video_id");
            String now_source_id = (String) vidSid.get("source_id");
            sidToVid_now.put(now_source_id, video_id);
        }
        return sidToVid_now;
    }

    private List<Map<String, Object>> cleanBussinessDate(List<Map<String, Object>> maps) {
        List<Map<String, Object>> wrongDataNoStatus = new ArrayList<>();
        List<Map<String, Object>> wrongDataNosource_other = new ArrayList<>();
        List<Map<String, Object>> wrongDataNosource_letv = new ArrayList<>();
        List<Map<String, Object>> wrongDataChanged = new ArrayList<>();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> map : maps) {
//            Integer status = (Integer) map.get("status");
//            if (status != 2) {
//                wrongDataNoStatus.add(map);
//                continue;
//            }
            if ("letv".equals(map.get("source_type"))) {
                wrongDataNosource_letv.add(map);
                continue;
            }
            if (!"zhuzhan".equals(map.get("source_type"))) {
                wrongDataNosource_other.add(map);
                continue;
            }
            Integer video_id = (Integer) map.get("video_id");
            int source_id = Integer.parseInt(String.valueOf(map.get("source_id")));
            if (video_id != source_id) {
                wrongDataChanged.add(map);
                continue;
            }
            result.add(map);
        }
//        try {
//            FileUtils.writeLines(new File("/Users/user/program/shell/allletvWithNoYouku/all-letv-vid_sid.noStatus.csv"), wrongDataNoStatus);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/all-letv-vid_sid.noSource_other.csv"), wrongDataNosource_other);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/all-letv-vid_sid.noSource_letv.csv"), wrongDataNosource_letv);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/all-letv-vid_sid.noSource_sourceChanged.csv"), wrongDataChanged);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/all-letv-vid_sid.source_zhuzhan_noChanged.csv"), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<String> cleanData() {
        List<String> dataWronglt2 = new ArrayList<>();
        List<String> dataWrongnull = new ArrayList<>();
        List<String> dataWrongGt2 = new ArrayList<>();

        List<String> dataWrong_status_lt7 = new ArrayList<>();
        List<String> dataWrong_status_gt10 = new ArrayList<>();
        List<String> dataWrong_status_gt7_lt10 = new ArrayList<>();

        List<String> resultAfterClean = new ArrayList<>();
        List<String> strings = null;
        try {
            strings = FileUtils.readLines(new File(USERS_USER_PROGRAM_SHELL_ALL_LETV_VID_SID_CSV));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < strings.size(); i++) {
            if (i == 0) {
                continue;
            }
            String vid_sid = strings.get(i);
            String[] split = vid_sid.split(",");
            if (split.length < 2) {
                dataWronglt2.add(vid_sid);
                continue;
            }
            if (split.length > 2) {
                dataWrongGt2.add(vid_sid);
                continue;
            }
            String sid = split[1];
            if (StringUtils.isBlank(sid) || "null".equalsIgnoreCase(sid)) {
                dataWrongnull.add(vid_sid);
                continue;
            }
            int length = sid.length();
            if (length <= 7) {
                dataWrong_status_lt7.add(vid_sid);
                continue;
            }
            if (length > 10) {
                dataWrong_status_gt10.add(vid_sid);
                continue;
            }
            if (length > 7 && length < 10 ) {
                dataWrong_status_gt7_lt10.add(vid_sid);
                continue;
            }



            resultAfterClean.add(vid_sid);
            continue;

        }
        try {
            FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/all-letv-vid_sid.dataWrong_status_lt7.csv"), dataWrong_status_lt7);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/all-letv-vid_sid.dataWrong_status_gt10.csv"), dataWrong_status_gt10);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/all-letv-vid_sid.dataWrong_status_gt7_lt10.csv"), dataWrong_status_gt7_lt10);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/all-letv-vid_sid.dataWrong_lt2.csv"), dataWronglt2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/all-letv-vid_sid.dataWrongGt2.csv"), dataWrongGt2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileUtils.writeLines(new File(USERS_USER_PROGRAM_SHELL_ALLLETV_WITH_NO_YOUKU + "/all-letv-vid_sid.dataWrong_null.csv"), dataWrongnull);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return resultAfterClean;
    }

}
