package com.javaapi.test.freemarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Test {

     public static void main(String[] args) {
     String freemarkerName="test.ftl";
     String freemarkerHtml="test.shtml";
     Thread.currentThread().getContextClassLoader().getResource("").getPath();
     String path=Thread.currentThread().getContextClassLoader().getResource("").getPath()+"com/javaapi/test/freemarker";
     System.out.println(path);
     Map<String, Object> map = new HashMap<String, Object>();
     Map<String, Object> map2 = new HashMap<String, Object>();
     List<String> list=new ArrayList<String>();
     map2.put("number", 1);
     map2.put("number2", 2);
     map2.put("number3", 3);
     list.add("nihao");
     list.add("nihao2");
    
     map.put("number", 1);
     map.put("number2", 2);
     map.put("number3", 3);
     
     map.put("list", list);
     map.put("map", map2);
     //  输出到path变量
     FreeMarkerUtil.geneHtmlFile(freemarkerName, map, path,"/page"+
     freemarkerHtml);
     }

}
