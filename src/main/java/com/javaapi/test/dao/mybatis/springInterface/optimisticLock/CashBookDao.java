package com.javaapi.test.dao.mybatis.springInterface.optimisticLock;


public interface CashBookDao  {
	CashBook selectOne(CashBook cashBook);
	int updateBalance(CashBook cashbook);
}
