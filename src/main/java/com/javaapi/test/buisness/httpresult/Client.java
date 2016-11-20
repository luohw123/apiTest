package com.javaapi.test.buisness.httpresult;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 */
public class Client {
    @Test
    public void testRealBusiness_one_right() throws Exception {
        HttpResult<Object> result = HttpResult.ok();
        String username = null;
        String password = null;
        int age = 0;
        HttpResult<Object> httpResult = validParamRight(username, password, age, result);
        if (httpResult.isFail()) {
            System.out.println("响应错误"+JSON.toJSONString(httpResult));
            return;
        }
        HashMap<Object, Object> data = new HashMap<>();
        data.put("nihao", "222");
        data.put("nihao3", "333");

        httpResult.setData(data);

        System.out.println(JSON.toJSONString(httpResult));
    }





    @Test
    public void testRealBusiness_one_error() throws Exception {
        HttpResult<Object> result = HttpResult.ok();
        String username = null;
        String password = null;
        int age = 0;
        HttpResult<Object> httpResult = validParam(username, password, age, result);
        if (httpResult.isFail()) {
            System.out.println("响应错误"+JSON.toJSONString(httpResult));
            return;
        }
        HashMap<Object, Object> data = new HashMap<>();
        data.put("nihao", "222");
        data.put("nihao3", "333");

        httpResult.setData(data);

        System.out.println(JSON.toJSONString(httpResult));
    }

    @Test
    public void testRealBusiness_error_list() throws Exception {
        HttpResult<Object> httpResult = HttpResult.ok();
        String username = "username_2";
        String password = "password_2";
        int age = 0;
        httpResult = validParamForErrorList(username, password, age, httpResult);
        System.out.println("httpResult = " + JSON.toJSONString(httpResult));
    }


    @Test
    public void testSuccess() {
        System.out.println(JSON.toJSONString(HttpResult.ok()));
        System.out.println(JSON.toJSONString(HttpResult.ok("这是返回的结果")));
    }

    // code 为common ,则直接提示错误的msg
    @Test
    public void testOk() {
        HttpResult<String> nihao = HttpResult.ok("nihao");
        System.out.println(JSON.toJSONString(nihao));
    }

    @Test
    public void testOk_v2() {
        List<String> objects = new ArrayList<>();
        objects.add("nihao");
        objects.add("nihao");
        objects.add("nihao");
        HttpResult<List<String>> nihao = HttpResult.ok(objects);
        System.out.println(JSON.toJSONString(nihao));
        List<String> data = nihao.getData();
        System.out.println(data);
    }

    @Test
    public void testError() {
        HttpResult<Object> nihao = HttpResult.displayError("请先登录");
        System.out.println(JSON.toJSONString(nihao));
    }

    @Test
    public void testErrorCode() {
        HttpResult<Object> nihao = HttpResult.error("need.login", "请先登录");
        System.out.println(JSON.toJSONString(nihao));
    }

    @Test
    public void testErrorList() {
        HttpResult<Object> nihao = HttpResult.errorList(new HttpError("need.login", "请先登录")).addError("need.item.2", "条件2不符合");
        System.out.println(JSON.toJSONString(nihao));
    }

    @Test
    public void testErrorList_v2() {
        HttpResult<Object> nihao = HttpResult.errorList("need.item.1", "条件1不符合")
                .addError("need.item.2", "条件2不符合");
        System.out.println(JSON.toJSONString(nihao));
    }

    @Test
    public void testErrorForValid() {
        String username = null;
        String password = null;
        int age = 0;
        HttpResult<Object> httpResult = validParam(username, password, age);
        if (httpResult.isFail()) {
            System.out.println(JSON.toJSONString(httpResult));
        }
    }

    @Test
    public void testErrorForValid_v2() {

        String username = "usernamea";
        String password = "password";
        int age = 0;
        HttpResult<Object> httpResult = validParamForErrorList(username, password, age);
        if (httpResult.isFail()) {
            System.out.println(JSON.toJSONString(httpResult));
        } else {
            System.out.println("chenggong");
            System.out.println(JSON.toJSONString(httpResult));
        }
    }

    private <T> HttpResult<T> validParam(String username, String password, int age) {
        if (!"a".equals(username)) {
            return HttpResult.displayError("username 不正确");
        }
        if (!"p".equals(password)) {
            return HttpResult.displayError("password 不正确");
        }
        return HttpResult.ok();
    }

    private <T> HttpResult<T> validParamForErrorList(String username, String password, int age) {
        HttpResult<T> result = HttpResult.ok();
        if (!"username".equals(username)) {
            result.addError("username is wrong", "username不正确");
        }
        if (!"password".equals(password)) {
            result.addError("password is wrong", "password不正确");
        }
        return result;
    }

    private <T> HttpResult<T> validParamForErrorList(String username, String password, int age, HttpResult httpResult) {
        if (!"username".equals(username)) {
            httpResult.addError("username is wrong", "username不正确");
        }
        if (!"password".equals(password)) {
            httpResult.addError("password is wrong", "password不正确");
        }
        return httpResult;
    }
    private <T> HttpResult<T> validParamRight(String username, String password, int age, HttpResult<T> httpResult) {
        return httpResult;
    }

    private <T> HttpResult<T> validParam(String username, String password, int age, HttpResult<T> httpResult) {
        if (!"a".equals(username)) {
            return httpResult.displayError("username 不正确");
        }
        if (!"p".equals(password)) {
            return httpResult.displayError("password 不正确");
        }
        return httpResult;
    }


}
