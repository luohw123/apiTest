package com.javaapi.test.buisness.httpresult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/2/21.
 */
public class HttpResult<T> implements Serializable {
    private static final long serialVersionUID = -1;
    // code 为success时候是业务处理成功,此时取result 为具体需要得数据,msg固定为"成功"
    // 其他数字则为有问题,此时取msg是单个错误说明
    // 前端根据不同数字,制定行为标准,比如 common 为直接显示错误信息即可
    // 其他code码需要自己定制,ps:建议  code码可以使用英语单词,则此时code应该使用string类型

    private String code;
    private T result;
    private String msg;
    // 需要批量错误的场景 再用errorList
    private List<HttpError> errorList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<HttpError> getErrorList() {
        return errorList;
    }

    public void addError(HttpError error) {
        if(this.getErrorList() == null) {
            this.errorList = new ArrayList<>();
        }
        this.getErrorList().add(error);
    }
}
