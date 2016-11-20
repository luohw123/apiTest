package com.javaapi.test.testUtil.fileUtil2.rejectContent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cookie 辅助类
 *
 */
public class CookieUtils {
    private final static Logger log = LoggerFactory.getLogger(CookieUtils.class);
    /**
     * 每页条数cookie名称
     */
    public static final String COOKIE_PAGE_SIZE = "_cookie_page_size";
    /**
     * 默认每页条数
     */
    public static final int DEFAULT_SIZE = 20;
    /**
     * 最大每页条数
     */
    public static final int MAX_SIZE = 200;

    public static final String COOKIE_ENCODE = "Walle doesn't have penis";


    /**
     * 旧的cookie加密方式
     * @param str
     * @return
     */
    public static int getHash(String str) {
        int len = str.length();
        int hash = 0;
        for (int i = 0; i < len; i++)  // (hash << 5) + hash 相当于 hash * 33
            hash = (hash << 5) + hash + (int) str.charAt(i);
        return hash;
    }
}
