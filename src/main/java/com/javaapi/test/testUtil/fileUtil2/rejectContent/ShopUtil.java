package com.javaapi.test.testUtil.fileUtil2.rejectContent;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 */
public class ShopUtil {

   static Logger logger = org.slf4j.LoggerFactory.getLogger(ShopUtil.class);

    public static boolean sendReject(String url, List<Map<String, Object>> params, String cookieString){
        URIBuilder uri = new URIBuilder();
        uri.setPath(url);
        setParams(uri,params);
        List<Header> list = new ArrayList<>();
        Header cookie = new BasicHeader("cookie", cookieString);
        list.add(cookie);
        CloseableHttpClient client = HttpClientBuilder.create().
                setDefaultHeaders(list).build();
        String result = "";
        try {
            HttpGet post = new HttpGet(uri.build());
            CloseableHttpResponse execute = client.execute(post);
            HttpEntity entity = execute.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if(StringUtils.isBlank(result)){
            logger.error("接口响应失败 param={}",params );
            return  false;
        }

        return true;
    }
    private static void setParams(URIBuilder uri, List<Map<String, Object>> param) {
        if (param == null) {
            return;
        }
        for (Map<String, Object> map : param) {
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                uri.addParameter(entry.getKey(),String.valueOf(entry.getValue()));
            }

        }

    }
}
