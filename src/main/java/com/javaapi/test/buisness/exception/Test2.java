package com.javaapi.test.buisness.exception;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        try {
            List<String> list = null;
            System.out.println(list.isEmpty());
            // doSomething();
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            // e.printStackTrace();// 打印出3个异常栈信息
            // System.out.println(e.getCause().getMessage());
            // e.getCause().printStackTrace();// 打印出2个异常栈信息
            System.out.println(e.getCause().getCause().getMessage());
            e.getCause().getCause().printStackTrace(); // 打印1个异常栈信息

            // TODO LOG
            // TODO some deal to repair the mistake
        }
    }

    private static void doSomething() throws SQLException {
        try {
            try {
                throw new NullPointerException("空指针异常");
            } catch (NullPointerException e) {
                throw new IOException("读取文件异常", e);
            }
        } catch (IOException e) {
            throw new SQLException("获取sql异常", e);

        }
    }
}
