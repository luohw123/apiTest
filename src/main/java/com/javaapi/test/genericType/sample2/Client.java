package com.javaapi.test.genericType.sample2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class Client {
	@Test
	public void testGeneric() {
		Map<String, ? extends Race> map;
		boolean b = true;
		if(b) {
			map = getDcRace();
		}else {
			map = getZcRace();
		}
		dealRace(map);
		
	}
	
	private void dealRace(Map<String, ? extends Race> map) {
		map.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Object next = iterator.next();
//			
//		}
	}

	public Map<String,DcRace> getDcRace(){
		
		return new HashMap<>();
	}
	public Map<String,ZcRace> getZcRace(){
		
		return new HashMap<>();
	}

}
