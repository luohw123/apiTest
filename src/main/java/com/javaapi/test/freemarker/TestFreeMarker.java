package com.javaapi.test.freemarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;


public class TestFreeMarker {
	@Test
	public void testOutPut() {

	}

     public static void main(String[] args) {
     String freemarkerName="test.ftl";
     String freemarkerHtml="test.shtml";
//     String path=Thread.currentThread().getContextClassLoader().getResource("").getPath()+"com/javaapi/test/freemarker";
		String path2 = TestFreeMarker.class.getResource("").getPath();
     System.out.println(path2);
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
     map.put("number3", 4);
     
     map.put("list", list);
     map.put("map", map2);
     //  输出到path变量
     FreeMarkerUtil.geneHtmlFile(freemarkerName, map, path2,
     freemarkerHtml);
     }

}
