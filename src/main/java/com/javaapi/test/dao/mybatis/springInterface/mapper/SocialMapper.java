package com.javaapi.test.dao.mybatis.springInterface.mapper;


import com.javaapi.test.dao.mybatis.Social;
import java.util.List;

public interface SocialMapper {
	List<Social> getUserList();
	
	Social getTop1User();

	Social getTop1UserResultMap();

    int setUser(Social user);

    int countUser();
}