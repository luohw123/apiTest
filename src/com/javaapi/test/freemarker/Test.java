package com.javaapi.test.freemarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		String freemarkerName="Test.ftl";
		String freemarkerHtml="test.shtml";
		String path="C:\\Users\\Administrator\\Desktop\\TestFreemarker";
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
		FreeMarkerUtil.geneHtmlFile(freemarkerName,  map, path,"/page/"+ freemarkerHtml);
	}

}
