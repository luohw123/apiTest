package com.javaapi.test.application.test.testmockito.demo1;

import org.springframework.stereotype.Repository;

import com.javaapi.test.application.test.testmockito.demo1.po.RouteMatrix;

@Repository
public class RouteMatrixDataProviderImpl  implements IRouteMatrixDataProvider{

	
	@Override
	public RouteMatrix getRevenueRoute(String string, String string2, boolean b) {
		System.err.println("getRevenueRoute method");
		return new RouteMatrix();
	}

}
