package com.javaapi.test.dao.mybatis.springInterface.mapper;


import java.util.List;
import java.util.Map;

import com.javaapi.test.dao.mybatis.Social;

public interface SocialMapper {
	List<Social> getUserList();
	
	Social getTop1User();

	Social getTop1UserResultMap();

    int setUser(Social user);

    int countUser();
    
    Map<String,String> selectBill(long billid);
    
    
    Map<String,String> selectBillMap(Map<String,Object> map);
}