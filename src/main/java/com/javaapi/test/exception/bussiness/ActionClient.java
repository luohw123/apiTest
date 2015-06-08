package com.javaapi.test.exception.bussiness;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javaapi.test.exception.bussiness.exception.BusinessException;
import com.javaapi.test.exception.bussiness.exception.ErrorCode;

public class ActionClient {

	static Logger logger = LoggerFactory.getLogger(ActionClient.class);
	
	@Test
	public void testRetry() throws Exception {
		boolean isSuccess = false;
		try {
			BusinessService.seriviceBusiness();
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				BusinessException bussiness = (BusinessException) e;
				if(bussiness.getError().getIndex() == ErrorCode.needRetry3.getIndex()) {
					logger.info(ErrorCode.needRetry3.getMsg());
					for (int i = 0; i < 3; i++) {
						isSuccess=BusinessService.seriviceBusiness();
						if(isSuccess) {
							break;
						}
					}
				}else if(bussiness.getError().getIndex() == ErrorCode.doNotRetry.getIndex()) {
					logger.info("乐观锁或其他异常",e);
				}else if (bussiness.getError().getIndex() == ErrorCode.commonError.getIndex()) {
					String msg = bussiness.getError().getMsg();
					logger.info("一般错误,错误信息={}",msg,e);
				}
			}else  {
				logger.info("系统发生内部错误",e);
			}
		}
		System.out.println("全部业务执行成功");
	}
}
