package com.javaapi.test.testUtil.fileUtil;

/**
 *
 */
public class TestFile3 {
//    private Set set = new HashSet();
//
//    private Map<Integer, List<Integer>> map = new HashMap<>();
//
//    private static String getUrl(String url) {
//        Pattern pattern = Pattern.compile("^http.+sina.+");
//        Matcher matcher = pattern.matcher(url);
//        if (!matcher.find()) {
//            return "";
//        }
//        return url + "\r\n";
//    }
//
//    private static int getVideoId(String url) {
//        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
//        Matcher matcher = pattern.matcher(url);
//        if (!matcher.find()) {
//            return 0;
//        }
//        try {
//            String videoId = matcher.group(1);
//            return Integer.parseInt(videoId);
//        } catch (Exception e) {
//            return 0;
//        }
//    }
//
//    private static String getSqlUpdateRollBack(String url) {
//        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
//        Matcher matcher = pattern.matcher(url);
//        if (!matcher.find()) {
//            return "#" + url + "\r\n";
//        }
//        String videoId = matcher.group(1);
//        String sql = "update ac_new_video set source_type = 'letv' where video_id = " + videoId + ";\r\n";
//        return sql;
//    }
//
//    private static String getSqlUpdate(String url) {
//        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
//        Matcher matcher = pattern.matcher(url);
//        if (!matcher.find()) {
//            return "#" + url + "\r\n";
//        }
//        String videoId = matcher.group(1);
//        String sql = "update ac_new_video set source_type = 'zhuzhan' where video_id = " + videoId + ";\r\n";
//        return sql;
//    }
//
//    private static String getSqlDelete(String url) {
//        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
//        Matcher matcher = pattern.matcher(url);
//        if (!matcher.find()) {
//            return "#" + url + "\r\n";
//        }
//        String videoId = matcher.group(1);
//        String rate = mapDilianProfile(matcher.group(2));
//        String tablename = getTablename(videoId);
//        StringBuilder sb = new StringBuilder();
//        sb.append(" DELETE FROM ");
//        sb.append(tablename);
//        sb.append(" WHERE ");
//        sb.append(" video_id = " + videoId);
//        sb.append(" AND bit_rate= " + rate + ";\r\n");
//        return sb.toString();
//    }
//
//    public static String getSqlByUrl(String url) {
//        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        Pattern pattern = Pattern.compile("(\\d+)_mp4/.*_?\\1_(.*)\\.");
//        Matcher matcher = pattern.matcher(url);
//        if (!matcher.find()) {
//            return "#" + url + "\r\n";
//        }
//        String videoId = matcher.group(1);
//        String rate = mapDilianProfile(matcher.group(2));
//        String tablename = getTablename(videoId);
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(" INSERT INTO ");
//        sb.append(tablename);
//        sb.append("(video_id,url,source,bit_rate,creater,create_time,updater,update_time) VALUES (");
//        sb.append(videoId + ",");
//        sb.append("'" + url + "',");
//        sb.append("'D_LIAN' ,");
//        sb.append(rate + ",");
//        sb.append("1,'" + now + "',1,'" + now + "');\r\n");
//        return sb.toString();
//    }
//
//    /**
//     * 根据转码率获取标示数字
//     *
//     * @param profileName
//     * @return
//     */
//    public static String mapDilianProfile(String profileName) {
//        if (profileName.startsWith("360"))
//            return "1";
//        if (profileName.startsWith("480"))
//            return "2";
//        if (profileName.startsWith("720"))
//            return "3";
//        if (profileName.startsWith("1080"))
//            return "4";
//        if (profileName.contains("lvbr"))
//            return "4";
//        return "-2";
//    }
//
//    public static String getTablename(String subColumnValue) {
//        Integer value = Integer.valueOf(Integer.parseInt(subColumnValue.toString()));
//        String tName = "ac_video_source";
//        int position = getPosition(value);
//        String tableName = null;
//        if (position == 1) {
//            tableName = tName;
//        } else {
//            StringBuilder sb = new StringBuilder();
//            tableName = sb.append(tName).append("_").append(position).toString();
//        }
//        return tableName;
//    }
//
//    private static int getPosition(Integer value) {
//        return value.intValue() / 500000 + 1;
//    }
//
//    @Test
//    public void test() {
//        try {
//            InputStream in = new FileInputStream(new File("/Users/user/program/shell/acfun_youku/acfunId.txt"));
//            InputStreamReader reader = new InputStreamReader(in);
//            BufferedReader bufferedInputStream = new BufferedReader(reader);
//            String temp = null;
//
//            FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/acfun_youku/url.txt"));
//            OutputStreamWriter writer = new OutputStreamWriter(out);
//            BufferedWriter bw = new BufferedWriter(writer);
//
//
//            while ((temp = bufferedInputStream.readLine()) != null) {
//                if (StringUtils.isBlank(temp)) {
//                    continue;
//                }
//                Integer value = Integer.valueOf(temp);
//                int position = getPosition(value);
//                List<Integer> integers = map.get(position);
//                if (integers == null) {
//                    integers = new ArrayList<>();
//                    map.put(position, integers);
//                }
//                integers.add(value);
//            }
//            getSql();
//            if (true) {
//                return;
//            }
//
//            bw.write(getSelectSql(temp));
//
//            in.close();
//            reader.close();
//            bufferedInputStream.close();
//
//            bw.close();
//            writer.close();
//            out.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private String getSelectSql(String temp) {
//        String sql = " SELECT video_id ,bit_rate url FROM " + getTablename(temp) + " WHERE video_id = " + temp;
//
//        return sql;
//    }
//    private String getSql(){
//
//        Iterator<Map.Entry<Integer, List<Integer>>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<Integer, List<Integer>> next = iterator.next();
//            Integer key = next.getKey();
//            List<Integer> value = next.getValue();
//            StringJoiner sj = new StringJoiner(",","(",")");
//            value.forEach((v)->sj.add(String.valueOf(v)));
//
//            String sql = "SELECT video_id ,bit_rate ,url FROM ac_video_source_"+key+" WHERE video_id  IN  "+sj.toString();
//            System.out.println(sql);
//        }
//
//        return  null;
//    }
//
//    private void createUpdateSqlRollback(BufferedWriter bw4) {
//        Iterator iterator = set.iterator();
//        while (iterator.hasNext()) {
//            int videoId = (int) iterator.next();
//            String sql = "update ac_new_video set source_type = 'letv' where video_id = " + videoId + ";\r\n";
//            try {
//                bw4.write(sql);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
//
//    private void createUpdateSql(BufferedWriter bw3) {
//        Iterator iterator = set.iterator();
//        while (iterator.hasNext()) {
//            int videoId = (int) iterator.next();
//            String sql = "update ac_new_video set source_type = 'zhuzhan' where video_id = " + videoId + ";\r\n";
//            try {
//                bw3.write(sql);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
//
//    @Test
//    public void testPattern() {
//        String url = "http://vplay.aixifan.com/des/acf-2/1523110_mp4/1523110_360p_43.mp4";
////        String url = "http://vplay.aixifan.com/des/acf-2/1663357_mp4/1663357_lvbr.mp4";
////        String url = "http://vplay.aixifan.com/des/20150902/test_2285615_mp4/test_2285615_360p.mp4";
//        System.out.println("url = " + getSqlByUrl(url));
//    }
//
//    @Test
//    public void testVideoRate() {
//        System.out.println(mapDilianProfile("360"));
//        System.out.println(mapDilianProfile("480"));
//        System.out.println(mapDilianProfile("720"));
//        System.out.println(mapDilianProfile("1080"));
////        System.out.println(mapDilianProfile("222"));
//        System.out.println(mapDilianProfile("lvbr"));
//    }
//
//    @Test
//    public void testTableName() {
//        System.out.println(getTablename("500000"));
//        System.out.println(getTablename("499999"));
//        System.out.println(getTablename("600000"));
//        System.out.println(getTablename("1100000"));
//        System.out.println(getTablename("1600000"));
//        System.out.println(getTablename("2100000"));
//        System.out.println(getTablename("2600000"));
//        System.out.println(getTablename("3100000"));
//        System.out.println(getTablename("3600000"));
//        System.out.println(getTablename("4100000"));
//        System.out.println(getTablename("4600000"));
//    }
//
//    @Test
//    public void testUpdate() {
//        Set set = new HashSet();
//        set.add(1);
//        set.add(1);
//        System.out.println("set = " + set);
//    }
}
