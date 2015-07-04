package com.javaapi.test.application.dataTrans.json.fastjson;

public class WithdrawVo<T> {

	private String RESULT;
	private T DATA;
	
	public String getRESULT() {
		return RESULT;
	}
	public void setRESULT(String RESULT) {
		this.RESULT = RESULT;
	}
	public T getDATA() {
		return DATA;
	}
	public void setDATA(T DATA) {
		this.DATA = DATA;
	}
	
	
}
