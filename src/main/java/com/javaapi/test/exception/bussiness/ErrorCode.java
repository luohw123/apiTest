package com.javaapi.test.exception.bussiness;

/**
 * 异常错误,根据不同得错误码进行不同操作
 *
 */
public class ErrorCode {
	
	private ErrorCode commonError = new ErrorCode(10000,"一般错误信息,上层收到,直接响应至客户即可");
	
	private ErrorCode needRetry3 = new ErrorCode(20000,"因乐观锁导致重试3次");
	
	private ErrorCode doNotRetry = new ErrorCode(20001,"因乐观锁或其他异常,不需要重试");

	private int index;
	private String msg;
	
	public ErrorCode(int index, String msg) {
		super();
		this.index = index;
		this.msg = msg;
	}

	public ErrorCode getCommonError() {
		return commonError;
	}

	public void setCommonError(ErrorCode commonError) {
		this.commonError = commonError;
	}

	public ErrorCode getNeedRetry3() {
		return needRetry3;
	}

	public void setNeedRetry3(ErrorCode needRetry3) {
		this.needRetry3 = needRetry3;
	}

	public ErrorCode getDoNotRetry() {
		return doNotRetry;
	}

	public void setDoNotRetry(ErrorCode doNotRetry) {
		this.doNotRetry = doNotRetry;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
