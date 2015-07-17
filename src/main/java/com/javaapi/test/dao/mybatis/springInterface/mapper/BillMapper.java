package com.javaapi.test.dao.mybatis.springInterface.mapper;

import java.util.List;
import java.util.Map;

public interface BillMapper {
	List<Map<String, String>> selectAll();
}