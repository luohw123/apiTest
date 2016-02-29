package com.javaapi.test.buisness.exception.bussiness.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorCode error;
	
	public BusinessException(ErrorCode error) {
		super();
		this.error = error;
	}

	public ErrorCode getError() {
		return error;
	}

	public void setError(ErrorCode error) {
		this.error = error;
	}

    /**
     * 重写异常提高性能
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
