package com.javaapi.test.exception.bussiness;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javaapi.test.exception.bussiness.exception.BusinessException;
import com.javaapi.test.exception.bussiness.exception.ErrorCode;

public class BusinessExceptionUtil {
	static Logger logger = LoggerFactory.getLogger(ActionClientBackUp.class);

	
	public static void dealBusinessException(Exception e) {
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
			return;
		}else if(bussiness.getError().getIndex() == ErrorCode.doNotRetry.getIndex()) {
			logger.info("乐观锁或其他异常",e);
			return;
		}else if (bussiness.getError().getIndex() == ErrorCode.commonError.getIndex()) {
			String msg = bussiness.getError().getMsg();
			logger.info("一般错误,错误信息={}",msg,e);
			return;
		}
		
	}
}
