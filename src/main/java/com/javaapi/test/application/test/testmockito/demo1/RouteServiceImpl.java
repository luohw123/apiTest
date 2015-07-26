package com.javaapi.test.application.test.testmockito.demo1;

import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements IRouteService {

	@Override
	public String getAirlineCode(String string, String string2, Object brand,
			Object cInfo, boolean b) {
		System.err.println("getAirlineCode method");
		return "nihao";
	}

}
