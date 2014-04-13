package com.javaapi.test.testProxy.cglibEg2;

/**
 * http://my.oschina.net/bairrfhoinn/blog/144812
 *
 */
public class Client {

	public static void main(String[] args) {
		TableDAO tableDao = TableDAOFactory.getInstance();
		doMethod(tableDao);
	}
	public static void doMethod(TableDAO dao){
		dao.create();
		dao.query();
		dao.update();
		dao.delete();
	}
}