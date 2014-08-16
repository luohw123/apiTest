package com.javaapi.test.concurrent.ThreadLocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadParamUtil {
    private static ThreadLocal<Map<String, Object>> local = new ThreadLocal<>();

    public static Map<String, Object> getLocal() {
        return local.get();
    }

    public static void setLocal(HashMap<String, Object> map) {
        local.set(map);
    }

}
