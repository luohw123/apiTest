package com.javaapi.test.buisness.exception.bussiness;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javaapi.test.buisness.exception.bussiness.exception.BusinessException;
import com.javaapi.test.buisness.exception.bussiness.exception.ErrorCode;

public class ActionClient {

	static Logger logger = LoggerFactory.getLogger(ActionClient.class);
	
	@Test
	public void testRetry() throws Exception {
		try {
			BusinessService.seriviceBusiness();
		} catch (Exception e) {
			if (!(e instanceof BusinessException)) {
				logger.info("系统发生内部错误",e);
				return;
			} 
			
		}
		System.out.println("全部业务执行成功");
	}
	
	@Test
	public void testRetryOther() throws Exception {
		try {
			BusinessService.seriviceBusiness();
		} 
		catch(BusinessException e) {
			dealBusinessException(e);
		}
		catch (Exception e) {
				logger.info("系统发生内部错误",e);
		}
		System.out.println("全部业务执行成功");
	}

	private void dealBusinessException(BusinessException e) {
		boolean isSuccess = false;
		BusinessException bussiness = (BusinessException) e;
		if(bussiness.getError().getIndex() == ErrorCode.needRetry3.getIndex()) {
			logger.info(ErrorCode.needRetry3.getMsg());
			for (int i = 0; i < 3; i++) {
				isSuccess=BusinessService.seriviceBusiness();
				if(isSuccess) {
					break;
				}
			}
		}
		else if(bussiness.getError().getIndex() == ErrorCode.doNotRetry.getIndex()) {
			logger.info("乐观锁或其他异常",e);
		}
		else if (bussiness.getError().getIndex() == ErrorCode.commonError.getIndex()) {
			String msg = bussiness.getError().getMsg();
			logger.info("一般错误,错误信息={}",msg,e);
		}
	}
}
