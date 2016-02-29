package com.javaapi.test.buisness.exception.bussiness.exception_v2;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ErrorCode errorCode;

    public BusinessException() {
        super();
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMsg(), cause);
        this.errorCode = errorCode;
    }

    public BusinessException(String message) {
        super(message);
        this.errorCode = new ErrorCode(ErrorCode.BUSINESS_COMMON_ERROR, message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = new ErrorCode(ErrorCode.BUSINESS_COMMON_ERROR, message);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
