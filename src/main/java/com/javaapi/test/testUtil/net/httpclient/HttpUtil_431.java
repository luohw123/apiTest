package com.javaapi.test.testUtil.net.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * httpclient 4.3.1 代码
 *
 */
public class HttpUtil_431 {
	private static Logger  logger = LoggerFactory.getLogger(HttpUtil_431.class);
	
	public static <T  extends Object> String getUrlParamsByMap(Map<String,  T> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, T> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}
	public static String post(String url, Map<String, String> param) {
		URIBuilder uri = new URIBuilder();
		uri.setPath(url);
		setParams(uri, param);
		CloseableHttpClient client = HttpClientBuilder.create().build();
		String result = "";
		try {
			HttpPost post = new HttpPost(uri.build());
			CloseableHttpResponse execute = client.execute(post);
			HttpEntity entity = execute.getEntity();
			if(execute.getStatusLine().getStatusCode() == 200){
				result = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String get(String url, Map<String, String> param) {
		URIBuilder uri = new URIBuilder();
		uri.setPath(url);
		setParams(uri, param);
		CloseableHttpClient client = HttpClientBuilder.create().build();
		String result = "";
		try {
			HttpGet post = new HttpGet(uri.build());
			CloseableHttpResponse execute = client.execute(post);
			HttpEntity entity = execute.getEntity();
			if(execute.getStatusLine().getStatusCode() == 200){
				result = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static void setParams(URIBuilder uri, Map<String, String> param) {
		if (param == null) {
			return;
		}
		Iterator<Entry<String, String>> iterator = param.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			uri.setParameter(entry.getKey(), entry.getValue());
		}
	}

	public static void main(String[] args) {
		String post = get("http://www.baidu.com",
				new HashMap<String, String>());
		logger.info(post);
	}
}
