package com.javaapi.test.dao.jdbc.transactionjta;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

import org.junit.Test;

public class TestJta {

	@Test
	public void test() throws NamingException {
		// UserTransaction
		InitialContext ctx = new InitialContext();
		Object txObj = ctx.lookup("java:comp/UserTransaction");
		UserTransaction utx = (UserTransaction) txObj;
	}

}
