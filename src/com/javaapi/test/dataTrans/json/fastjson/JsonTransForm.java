package com.javaapi.test.dataTrans.json.fastjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.javaapi.test.dataTrans.json.CompanyInfo;
import com.javaapi.test.dataTrans.json.CompanyName;

public class JsonTransForm {

  /**
   * 将java对象转换成json字符串
   * 
   * @param <T>
   * @param t
   *          java对象
   * @return
   */
  public static <T> String pojoToJsonString(T t) {
    String jsonStr = JSON.toJSONString(t);
    return jsonStr;
  }

  /**
   * 将含有时间属性的java对象转换成json字符串
   * 
   * @param <T>
   * @param t
   *          java对象
   * @param DateFormate
   *          时间 格式
   * @return
   */
  public static <T> String pojoToJsonString(T t, String DateFormate) {
    // TODO UseISO8601DateFormat？
    String jsonStr = JSON.toJSONStringWithDateFormat(t, DateFormate,
        SerializerFeature.UseISO8601DateFormat);
    return jsonStr;
  }

  /**
   * 将Json格式的字符串转换成指定的对象返回
   * 
   * @param jsonStr
   *          要转化的Json格式的字符串
   * @param javaBean
   *          指定转化对象类型
   * @return 转化后的对象
   */
  public static <T> T jsonToPojo(String jsonStr, Class<T> javaBean) {
    return JSON.parseObject(jsonStr, javaBean);
  }

  public static void main(String[] args) {
    CompanyInfo ci = new CompanyInfo();
    CompanyName companyName = new CompanyName();
    companyName.setCompany1("companyName1");
    companyName.setCompany2("companyName2");
    CompanyName companyName2 = new CompanyName();
    companyName2.setCompany1("company2222");
    companyName2.setCompany2("company2222");
    List<String> list = new ArrayList<String>();
    list.add("姓名1");
    list.add("姓名2");
    List<CompanyName> listCompanyName = new ArrayList<CompanyName>();
    listCompanyName.add(companyName2);
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("aa", "aaa");
    map.put("vv", "vv");
    ci.setMap(map);
    ci.setCompany(companyName);
    ci.setList(list);
    ci.setCompanyName("companyName");
    ci.setCompanyType("企业");
    ci.setListCompanyName(listCompanyName);
    String a = JSON.toJSONString(ci);
    System.out.println(a);
    System.out.println("===================");
    CompanyInfo cio = jsonToPojo(a, CompanyInfo.class);
    System.out.println(cio.getListCompanyName().get(0).getCompany2());
  }
}
