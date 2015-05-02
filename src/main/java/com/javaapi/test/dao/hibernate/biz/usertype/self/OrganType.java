package com.javaapi.test.dao.hibernate.biz.usertype.self;

import java.util.Map;
import java.util.TreeMap;
 
 
public enum OrganType implements PersistEnum<OrganType>{
    // 利用构造函数传参
    ORGANTYPE_DEPARTMENT("D"), // 部门
    ORGANTYPE_ORGANMANAGER("M"), // 管理机构
    ORGANTYPE_NONE("N");   //普通机构 无特殊含意的机构，类似企业中的总公司和分支机构，只有上下级关系。
 
    private static final Map<String, OrganType> map = new TreeMap<String, OrganType>();
     
    static {
        map.put(ORGANTYPE_DEPARTMENT.getOrgType(), ORGANTYPE_DEPARTMENT);
        map.put(ORGANTYPE_ORGANMANAGER.getOrgType(), ORGANTYPE_ORGANMANAGER);
        map.put(ORGANTYPE_NONE.getOrgType(), ORGANTYPE_NONE);
    }
     
    // 定义私有变量
    private String orgType ;
 
    // 构造函数，枚举类型只能为私有
     private OrganType(String _orgType) {
         this . orgType = _orgType;
     }
 
    public String getOrgType() {
        return orgType;
    }
 
    @Override
    public String getPersistedValue() {
        return getOrgType();
    }
 
    @Override
    public OrganType returnEnum(String persistedValue) {
        return map.get(persistedValue);
    }
 
    @Override
    public Map<String, OrganType> getAllValueMap() {
        return map;
    }         
}