package com.javaapi.test.dao.mybatis;

import java.util.List;

public interface SocialMapper {
    Social getTop1User();

    List<Social> getUserList();

    Integer setUser(Social user);
}
