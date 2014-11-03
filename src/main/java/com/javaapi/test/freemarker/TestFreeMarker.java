package com.javaapi.test.freemarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;


public class TestFreeMarker {
	@Test
	public void testMap() {
		String freemarkerName = "testmap.ftl";
		String freemarkerHtml = "testmap.shtml";
		String path2 = TestFreeMarker.class.getResource("").getPath();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("number1", 1);
		map.put("number2", 2);
		map.put("number3", 3);
		// 输出到path变量
		FreeMarkerUtil.geneHtmlFile(freemarkerName, map, path2, freemarkerHtml);
	}
	@Test
	public void testList() {
		String freemarkerName = "testlist.ftl";
		String freemarkerHtml = "testlist.shtml";
		String path2 = TestFreeMarker.class.getResource("").getPath();
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list.add("nihao1");
		list.add("nihao2");
		list.add("nihao3");
		map.put("nihaolist", list);
		// 输出到path变量
		FreeMarkerUtil.geneHtmlFile(freemarkerName, map, path2, freemarkerHtml);
	}


	@Test
	public void testToString() {
		String freemarkerName = "testToString.ftl";
		String freemarkerHtml = "testToString.shtml";
		String path2 = TestFreeMarker.class.getResource("").getPath();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("number2", 2);
		// 输出到path变量
		FreeMarkerUtil.geneHtmlFile(freemarkerName, map, path2, freemarkerHtml);
	}

	/**
	 * freemarker里面得判断,只跟实际类型有关.跟声明得泛型物管</br> Map<Object, Object> map = new
	 * HashMap<Object, Object>(); </br> Map<String, Object> map = new
	 * HashMap<String, Object>();
	 */
	@Test
	public void testIf() {
		String freemarkerName = "testif.ftl";
		String freemarkerHtml = "testif.shtml";
		String path2 = TestFreeMarker.class.getResource("").getPath();
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("number2", 2);
		map.put("number3", "3");
		map.put("booleanTrue", true);
		map.put("booleanFalse", false);
		Map<Object, Object> mapInner = new HashMap<>();
		mapInner.put("booleanTrue", true);
		mapInner.put("booleanFalse", false);
		map.put("mapInner", mapInner);
		// 输出到path变量
		FreeMarkerUtil.geneHtmlFile(freemarkerName, map, path2, freemarkerHtml);
	}

	@Test
	public void testBooleanToString() {
		String freemarkerName = "testNumberToString.ftl";
		String freemarkerHtml = "testNumberToString.shtml";
		String path2 = TestFreeMarker.class.getResource("").getPath();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("number2", 2);
		map.put("foo1", true);
		map.put("foo2", false);
		// 输出到path变量
		FreeMarkerUtil.geneHtmlFile(freemarkerName, map, path2, freemarkerHtml);
	}
}
