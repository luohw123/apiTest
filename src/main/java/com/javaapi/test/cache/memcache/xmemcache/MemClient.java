package com.javaapi.test.cache.memcache.xmemcache;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.junit.Test;


public class MemClient {

	private static final int		port	= 11211;
	private static final String	ip	= "127.0.0.1";
	private static XMemcachedClient	client;
	static {
		try {
			client = new XMemcachedClient(ip, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void add() throws TimeoutException, InterruptedException,
			MemcachedException {
		client.add("hello", 0, "dennis");
	}

	public static void main(String[] args) {
		try {
			// 存储操作
			if (!client .set("hello", 0, "dennis")) {
				System.err.println(" set error ");
			}
			client .add("hello", 0, "dennis");
			client .replace("hello", 0, "dennis");

			// get操作
			String name = (String) client .get("hello");
			System.out.println(name);

			// 批量获取
			List<String> keys = new ArrayList<String>();
			keys.add("hello");
			keys.add("test");
			Map<String, Object> map = client .get(keys);
			System.out.println(" map size: " + map.size());

			// delete操作
			if (!client .delete("hello", 1000)) {
				System.err.println(" delete error ");
			}

			// incr,decr
			client .incr("a", 4);
			client .decr("a", 4);

			// version
			// String version = client.version();
			// System.out.println(version);
			// 增删改查自定义对象
			Name dennis = new Name("dennis", "zhuang", 26, -1);
			System.out.println("dennis: " + dennis);
			client .set("dennis", 0, dennis);

			Name cachedPerson = (Name) client .get("dennis");
			System.out.println("cachedPerson: " + cachedPerson);
			cachedPerson.money = -10000;

			client .replace("dennis", 0, cachedPerson);
			Name cachedPerson2 = (Name) client .get("dennis");
			System.out.println(" cachedPerson2: " + cachedPerson2);

			// delete
			client .delete("dennis");
			System.out.println(" after delete: " + client .get("dennis"));
			client .shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}