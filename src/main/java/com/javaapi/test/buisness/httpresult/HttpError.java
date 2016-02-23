package com.javaapi.test.buisness.httpresult;

import java.io.Serializable;

/**
 * Created by user on 16/2/21.
 */
public class HttpError implements Serializable{
    private static final long serialVersionUID = -1;

    public static final String G_USERNAME_ERROR = "username.error";
    public static final String G_PASSWORD_ERROR = "password.error";
    public static final String G_MAIL_ERROR = "mail.error";


    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpError() {
    }

    public HttpError(String i, String msg) {
        this.code = i;
        this.msg =  msg;
    }
}
