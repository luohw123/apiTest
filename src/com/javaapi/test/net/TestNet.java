package com.javaapi.test.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

/**
 * 网络编程常用类,URL,UrlConnection,URLDecoder,URLEncoder,InetAddress,
 * 
 */
public class TestNet {
	/**
	 * 测试普通得连接 post
	 * 
	 * @throws IOException
	 */
	@Test
	public void post() throws IOException {
		String path = "http://www.baidu.com";
		// 新建一个URL对象
		URL url = new URL(path);
		// 打开一个HttpURLConnection连接
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		// 设置连接超时时间
		urlConn.setConnectTimeout(5 * 1000);
		urlConn.setDoOutput(true);
		// Post请求不能使用缓存
		urlConn.setUseCaches(false);
		// 设置为Post请求
		urlConn.setRequestMethod("POST");
		urlConn.setInstanceFollowRedirects(true);
		// 配置请求Content-Type
		urlConn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencode");
		// 开始连接
		urlConn.connect();
		// 判断请求是否成功
		if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			// 获取返回的数据
			BufferedReader br = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream(), "GBK"));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				System.out.println(temp + System.lineSeparator());
			}
		} else {
			System.out.println("失败");
		}
		// 关闭连接
		urlConn.disconnect();
	}

	/**
	 * 测试普通得连接 get
	 * 
	 * @throws IOException
	 */
	@Test
	public void get() throws IOException {
		String path = "http://www.baidu.com";
		URL url = new URL(path);
		url.getProtocol();
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setRequestMethod("GET");
		urlConn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencode");
		urlConn.connect();
		if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream(), "GBK"));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				System.out.println(temp + System.lineSeparator());
			}
		} else {
			System.out.println("失败");
		}
		urlConn.disconnect();
	}
}
