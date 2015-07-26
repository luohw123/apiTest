package com.javaapi.test.application.test.testmockito.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RouteMatrixDataProviderImpl  implements IRouteMatrixDataProvider{

	@Autowired
	private IRouteMatrixDataProvider provider;
	@Override
	public Object getRevenueRoute(String string, String string2, boolean b) {
		System.err.println("getRevenueRoute method");
		return new RouteMatrix();
	}

}
