package com.javaapi.test.buisness.exception.bussiness.exception_v2;

import java.io.Serializable;

public class ErrorCode implements Serializable {

    public static final String BUSINESS_COMMON_ERROR = "business.common.error";
    public static final ErrorCode DB_ERROR = new ErrorCode("business.db.error", "数据库异常");
    public static final ErrorCode MEMBER_ERROR = new ErrorCode("business.member.error", "会员异常");

    private static final long serialVersionUID = 1L;
    private String key;
    private String msg;

	public static ErrorCode commonError(String msg) {
		return new ErrorCode(BUSINESS_COMMON_ERROR, msg);
	}

    public String getId() {
        return key;
    }

    public void setId(String id) {
        this.key = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String info) {
        this.msg = info;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ErrorCode(String key, String info) {
        super();
        this.key = key;
        this.msg = info;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErrorCode other = (ErrorCode) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        return true;
    }

}
