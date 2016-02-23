package com.javaapi.test.buisness.httpresult;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by user on 16/2/21.
 */
public class Client {
    @Test
    public void testSuccess() {
        HttpResult hr = new HttpResult();
        hr.setCode("success");
        hr.setMsg("成功");
        ArrayList<String> objects = new ArrayList<>();
        objects.add("nihao");
        objects.add("nihao2");
        hr.setResult(objects);
        String s = JSON.toJSONString(hr);
        System.out.println(s);
    }
    // code 为common ,则直接提示错误的msg
    @Test
    public void testError() {
        HttpResult hr = new HttpResult();
        hr.setCode("common");
        hr.setMsg("未有权限");
        String s = JSON.toJSONString(hr);
        System.out.println(s);
    }
    // code为 errorlist 则使用 errorList成员变量
    @Test
    public void testErrorList(){
        HttpResult hr = new HttpResult();
        hr.setCode("errorlist");
        hr.setMsg("批量错误,请查看errorlist字段");
        hr.addError(new HttpError(HttpError.G_USERNAME_ERROR, "用户名错误"));
        hr.addError(new HttpError(HttpError.G_MAIL_ERROR, "邮箱格式错误"));
        String s = JSON.toJSONString(hr);
        System.out.println(s);
    }
}
