package com.javaapi.test.buisness.dataTrans.json.fastjson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;


public class FastJsonUtil {
    
    public static String json(Object object){
        if(object == null){
            return "{}";
        }
        
        String jsonString = "";
        try {
            jsonString = JSON.toJSONString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
    

    public static <T> T getObject(String jsonString, Class<T> temp) {
        T parseObject = null;
        try {
            parseObject = JSON.parseObject(jsonString, temp);
        } catch (Exception e) {
            T newInstance = null;
            try {
                newInstance = temp.newInstance();
            } catch (Exception e1) {
                e1.printStackTrace();
            } 
            return newInstance;
        }
        return parseObject;
    }
    
    /**
     *
     *传入符合规范的json
     */
    public static  <K, V> Map<K, V>  getMap(String jsonString){
        if(jsonString == null || jsonString.trim() == ""){
            return new HashMap<K,V>();
        }
        Map<K, V>  parseObject;
        try {
            parseObject =  JSON.parseObject(jsonString, new TypeReference<Map<K, V>>(){},Feature.AllowUnQuotedFieldNames,Feature.AllowSingleQuotes);
        } catch (Exception e) {
            parseObject = new HashMap<K,V>();
        }
        return parseObject;
    }
    /**
     *
     *传入符合规范的json
     */
    public static  <T> List<T>  getList(String jsonString){
        if(jsonString == null || jsonString.trim() == ""){
            return new ArrayList<T>();
        }
        List<T> parseObject;
        try {
            parseObject =  JSON.parseObject(jsonString, new TypeReference<List<T>>(){},Feature.AllowUnQuotedFieldNames,Feature.AllowSingleQuotes);
        } catch (Exception e) {
            parseObject = new ArrayList<T>();
        }
        return parseObject;
    }


    public static void main(String[] args) throws IOException {
        String jsonString = "{\"nihao\":123}";
        String jsonString2 = "{\"nihao\":[123,123]}";
        String jsonString3 = "{\"nihao\":{\"nihao2\":223}}";
        Map<String, String> map = FastJsonUtil.getMap(jsonString);
        Map<String, List<String>> mapList = FastJsonUtil.getMap(jsonString2);
        Map<String, Map<String,Object>> mapMap = FastJsonUtil.getMap(jsonString3);
        System.out.println(map);
        System.out.println(mapList);
        System.out.println(mapMap);
        Map<String, Object> map2 = mapMap.get("nihao");
        System.out.println(map2);
        System.out.println(map2.get("nihao2"));
        System.out.println("------------------------");
        String jsonArr= "[1,2,3,4,5]";
        List<String> list = FastJsonUtil.getList(jsonArr);
        System.out.println(list);
        System.out.println("------------------------");
        String objectString = "{\"nihao\":123}" ;
        @SuppressWarnings("unchecked")
        Map<String,Object> object = FastJsonUtil.getObject(objectString, Map.class);
        System.out.println(object);
        System.out.println(object.get("nihao"));
        @SuppressWarnings("unchecked")
        Map<String, Map<String,Object>> mapMap2 = FastJsonUtil.getObject("sdf", LinkedHashMap.class);
        System.out.println(mapMap2);
        System.out.println("-------------");
        String json = FastJsonUtil.json(mapMap);
        System.out.println(json);
    }

}