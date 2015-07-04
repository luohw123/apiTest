package com.javaapi.test.testUtil.net.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * httpclient 4.1.2代码
 *
 */
public class HttpUtil_412 {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil_412.class);

	public static <T extends Object> String getUrlParamsByMap(Map<String, T> map) {
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
		DefaultHttpClient httpclient = new DefaultHttpClient();
		// 创建httpget.
		HttpPost httpPost = new HttpPost(url);
		HttpEntity setPostParams = setPostParams( param);
		httpPost.setEntity(setPostParams);
		String result = null;
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}
	private static HttpEntity setPostParams(Map<String, String> param) {
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>(); // Post方式添加参数的方法
		Iterator<Entry<String, String>> iterator = param.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity uefEntity = null;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 防止出现乱码，加上UTF-8
		return uefEntity;
	}
	public static String get(String url, Map<String, String> param) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		// 创建httpget.
		 url = setGetParams(url,param);
		HttpGet httpget = new HttpGet(url);
		String result = null;
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}



	private static String setGetParams(String originUrl,Map<String, String> param) {
		if(MapUtils.isEmpty(param)) {
			return originUrl;
		}
		StringBuilder sb = new StringBuilder(originUrl);
		sb.append("?");
		Iterator<Entry<String, String>> iterator = param.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("&");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Map<String,String> param = new HashMap<>();
		param.put("memberPhone", "15311806620");
		param.put("flag", "1");
		param.put("dataTime", "11");
		param.put("money", "100003");
		param.put("msg", "不提供");
		String post = get("http://192.168.31.204:8089/okdilifeapi/bankAccount/sendSms", param);
		logger.info(post);
	}
}
