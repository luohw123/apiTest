package com.javaapi.test.dao.mybatis.springInterface.optimisticLock;

import java.math.BigDecimal;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javaapi.test.exception.bussiness.exception.BusinessException;
import com.javaapi.test.exception.bussiness.exception.ErrorCode;

@Service
@Transactional(propagation = Propagation.REQUIRED ,isolation=Isolation.REPEATABLE_READ)
public class CashBookService {
	static transient Logger logger = LoggerFactory.getLogger(CashBookService.class);

	@Autowired
	SqlSessionTemplate	sqlSessionTemplate;

	public  void addBalance(long accountId, BigDecimal money) {
		try {
			addBalanceSelf(accountId, money);
		} catch (Exception e) {
			if(e instanceof BusinessException) {
				boolean isSuccess = false;
				BusinessException bussiness = (BusinessException) e;
				if(bussiness.getError().getIndex() == ErrorCode.needRetry3.getIndex()) {
					for (int i = 0; i < 5; i++) {
						try {
							addBalanceSelf(accountId, money);
						} catch (Exception e1) {
							continue;
						}
						isSuccess = true;
						if(isSuccess) {
							return;
						}
					}
					logger.info("乐观锁重试3次后，无成功 thread = {}",Thread.currentThread().getId());
					return;
				}
				else if(bussiness.getError().getIndex() == ErrorCode.doNotRetry.getIndex()) {
					logger.info("乐观锁或其他异常",e);
					return;
				}
				else if (bussiness.getError().getIndex() == ErrorCode.commonError.getIndex()) {
					String msg = bussiness.getError().getMsg();
					logger.info("一般错误,错误信息={}",msg,e);
					return;
				}
				
			}else {
				logger.info("内部错误",e);
				return;
			}
		}
	}

	private void addBalanceSelf(long accountId, BigDecimal money) {
		// 模拟业务更新 1 
		CashBook cash  = new CashBook();
		cash.setAccountId(accountId);
		CashBook seletOne = sqlSessionTemplate.getMapper(CashBookDao.class).selectOne(cash);
		seletOne.setBalance(seletOne.getBalance().add(money));
		int result = sqlSessionTemplate.getMapper(CashBookDao.class).updateBalance(seletOne);
		if(result ==0 ) {
			throw new BusinessException(ErrorCode.needRetry3);
		}else {
			logger.info("success thread ={}",Thread.currentThread().getId());
		}
		// 模拟其他业务更新 2 
		CashBook cash_2  = new CashBook();
		cash_2.setAccountId(accountId);
		CashBook seletOne_2 = sqlSessionTemplate.getMapper(CashBookDao.class).selectOne(cash_2);
		seletOne_2.setBalance(seletOne_2.getBalance().add(money));
		int result_2 = sqlSessionTemplate.getMapper(CashBookDao.class).updateBalance(seletOne_2);
		if(result_2 ==0 ) {
			throw new BusinessException(ErrorCode.needRetry3);
		}else {
			logger.info("success thread ={}",Thread.currentThread().getId());
		}
		// 模拟其他业务更新 3 
		CashBook cash_3  = new CashBook();
		cash_3.setAccountId(accountId);
		CashBook seletOne_3 = sqlSessionTemplate.getMapper(CashBookDao.class).selectOne(cash_3);
		seletOne_3.setBalance(seletOne_3.getBalance().add(money));
		int result_3 = sqlSessionTemplate.getMapper(CashBookDao.class).updateBalance(seletOne_3);
		if(result_3 ==0 ) {
			throw new BusinessException(ErrorCode.needRetry3);
		}else {
			logger.info("success thread ={}",Thread.currentThread().getId());
		}
	}

}
