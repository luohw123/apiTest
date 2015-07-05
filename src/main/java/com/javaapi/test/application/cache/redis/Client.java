package com.javaapi.test.application.cache.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Test
	public void testSet() {
		redisTemplate.opsForValue().set("nihao", "nihao2");
	}
	@Test
	public void testGet() {
		String string = redisTemplate.opsForValue().get("nihao");
		System.err.println(string);
	}
}
