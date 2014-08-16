package com.javaapi.test.testProxy.cglibEg2;

public class TableDAOFactory {
	private static TableDAO tDao = new TableDAO();
	public static TableDAO getInstance(){
		return tDao;
	}
}