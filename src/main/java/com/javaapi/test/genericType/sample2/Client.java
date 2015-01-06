package com.javaapi.test.genericType.sample2;

import java.util.HashMap;
import java.util.Map;

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
		dealRace(map);
		
	}
	
	private void dealRace(Map<String, ? extends Race> map) {
		map.entrySet().iterator();
		Race race = map.get("");
	}

	public Map<String,DcRace> getDcRace(){
		
		return new HashMap<>();
	}
	public Map<String,ZcRace> getZcRace(){
		
		return new HashMap<>();
	}

}
