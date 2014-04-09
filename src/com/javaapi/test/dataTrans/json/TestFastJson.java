package com.javaapi.test.dataTrans.json;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 1.数组或对象的每个成员的值，可以是简单值，也可以是复合值。</br>
 * 
 * 2.简单值分为四种：字符串、数值（必须以十进制表示）、布尔值和null。</br>
 * 
 * 3.复合值分为两种：符合JSON格式的对象和符合JSON格式的数组。</br>
 * 
 * 4.数组或对象最后一个成员的后面，不能加逗号。</br>
 * 
 * 5.数组或对象之中的字符串必须使用双引号，不能使用单引号。</br>
 * 
 * 6.对象的成员名称必须使用<span style="color:red">双引号</span>。</br>
 * 
 */
public class TestFastJson {
	@Test
	public void serialize() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "2");
		map.put("2", "2");
		map.put("3", "2");
		System.out.println(JSON.toJSONString(map,
				SerializerFeature.UseSingleQuotes));
		System.out.println(JSON.toJSON(map).toString());
	}

	@Test
	public void deserialize() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "2");
		map.put("2", "2");
		map.put("3", "2");
		String jsonString = JSON.toJSON(map).toString();
		// @SuppressWarnings("unchecked")
		// HashMap<String, String> hashmap = JSON.parseObject(jsonString,
		// HashMap.class);
		// 传入类型参数
		Map<String, String> hashmap = JSON.parseObject(jsonString,
				new TypeReference<Map<String, String>>() {
				}, Feature.AllowUnQuotedFieldNames);
		System.out.println(hashmap);
	}
}