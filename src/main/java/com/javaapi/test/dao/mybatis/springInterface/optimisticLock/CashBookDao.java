package com.javaapi.test.dao.mybatis.springInterface.optimisticLock;


public interface CashBookDao  {
	CashBook selectOne(CashBook cashBook);
	
	
	
	int updateBalance(CashBook cashbook);
	
	//以下悲观锁
	CashBook selectOneForUpdate(CashBook cashBook);
	
	int update(CashBook cashBook);
}