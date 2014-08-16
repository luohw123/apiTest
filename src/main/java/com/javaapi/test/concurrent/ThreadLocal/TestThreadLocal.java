package com.javaapi.test.concurrent.ThreadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestThreadLocal extends Thread {
    private String ThreadId;

    // 如果世junit得test ，构造器只能有一个无参数的构造器
    public static void main(String[] args) {
        TestThreadLocal test1 = new TestThreadLocal("1");
        TestThreadLocal test2 = new TestThreadLocal("2");
        test1.start();
        test2.start();

    }
    @Override
    public void run() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("term", "140606==>" + getThreadId());
        ThreadParamUtil.setLocal(map);
        if ("1".equals(getThreadId())) {
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        getparam();
    }

    private void getparam() {
        Map<String, Object> resultmap = ThreadParamUtil.getLocal();
        System.out.println(resultmap.get("term"));
    }
    public String getThreadId() {
        return ThreadId;
    }

    public void setThreadId(String threadId) {
        ThreadId = threadId;
    }

    public TestThreadLocal() {
    }
    public TestThreadLocal(String threadId) {
        super();
        ThreadId = threadId;
    }

}
