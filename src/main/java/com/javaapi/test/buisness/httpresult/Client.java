package com.javaapi.test.buisness.httpresult;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @TODO  raw type
 */
public class Client {
    @Test
    public void testSuccess() {
        System.out.println(JSON.toJSONString(HttpResult.ok()));
        System.out.println(JSON.toJSONString(HttpResult.ok("这是返回的结果")));
    }

    // code 为common ,则直接提示错误的msg
    @Test
    public void testOk() {
        HttpResult nihao = HttpResult.ok("nihao");
        System.out.println(JSON.toJSONString(nihao));
    }

    @Test
    public void testError() {
        HttpResult nihao = HttpResult.displayError("请先登录");
        System.out.println(JSON.toJSONString(nihao));
    }

    @Test
    public void testErrorCode() {
        HttpResult nihao = HttpResult.error("need.login", "请先登录");
        System.out.println(JSON.toJSONString(nihao));
    }

    @Test
    public void testErrorList() {
        HttpResult nihao = HttpResult.errorList(new HttpError("need.login", "请先登录")).addError("need.item.2", "条件2不符合");
        System.out.println(JSON.toJSONString(nihao));
    }

    @Test
    public void testErrorList_v2() {
        HttpResult nihao = HttpResult.errorList("need.item.1", "条件1不符合")
                .addError("need.item.2", "条件2不符合");
        System.out.println(JSON.toJSONString(nihao));
    }
}
