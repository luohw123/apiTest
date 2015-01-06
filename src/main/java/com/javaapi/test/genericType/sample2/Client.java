package com.javaapi.test.genericType.sample2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class Client {
	
	@Test
	public void testGeneric() {
		Map<String, ? extends Race> map;
		boolean b = true;
		// 可以直接赋值 将 Map<String, DcRace>直接赋值给map变量
		// 但是map不能加入DcRace
		if(b) {
			map = getDcRace();
		}else {
			map = getZcRace();
		}
//		dealRace(map);
		preHand(map);
	}
	 public <T extends Race> void preHand(Map<String,T> map) {
		 // 获得这个泛型T 能干嘛?
//		 Iterator<Entry<String, T>> iterator = map.entrySet().iterator();
		 // 可以直接当做 父类型使用. 
		 T t = null;
		 t.getName();
		 // 怎么在需要用子类型得时候得时候转为字类型 ?
		 // 如下?
		 if(t instanceof ZcRace) {
			 ZcRace zc = (ZcRace)t;
		 }
		 	// 取出可以直接按照父类型取出
		 Race race = map.get("");
	 }
	
	private void dealRace(Map<String, ? extends Race> map) {
		// 取出可以直接按照父类型取出
		Race race = map.get("");
		// 为什么迭代不可以 ??
		map.entrySet().iterator();
		
	}

	public Map<String,DcRace> getDcRace(){
		
		HashMap<String, DcRace> hashMap = new HashMap<>();
		return hashMap;
	}
	
	public Map<String,ZcRace> getZcRace(){
		
		return new HashMap<>();
	}

}
