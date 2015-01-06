package com.javaapi.test.genericType.sample2;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Client2 {
	// 可以这么赋值
	@Test
	public void test() {
		List<Race> list = new ArrayList<>();
		list.add(new ZcRace());
		list.add(new DcRace());
	}

	@Test
	public void test2() {
		List<Race> list = new ArrayList<>();
		list.add(new ZcRace());
		list.add(new DcRace());
		List<DcRace> dcList = new ArrayList<>();
		dcList.add(new DcRace());
		dcList.add(new DcRace());
		List<ZcRace> zcList = new ArrayList<>();
		zcList.add(new ZcRace());
		zcList.add(new ZcRace());
//		但不可以这么赋值
//		list = dcList;
//		list = zcList;
	}
}
