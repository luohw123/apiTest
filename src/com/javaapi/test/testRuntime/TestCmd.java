package com.javaapi.test.testRuntime;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestCmd {
    public TestCmd() {
    }

    public static void main(String args[]) {
        try {
            // 使用用Ping命令
            String catlinahome = "F:\\Program Files\\apache-tomcat-6.0.37";
            String file = catlinahome + "\\bin\\startup.bat";
            // String command2 = "cmd /c start " + file.replaceAll(" ",
            // "\" \"");
            // String command1 = "cmd  /c set 'CATALINA_HOME=" + catlinahome +
            // "'";

            String command1 = "cmd /c dir";

            List<String> list = new ArrayList<String>();
            list.add(command1);

            Process pe = Runtime.getRuntime().exec(command1, null);
            InputStream is = pe.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,
                    "GBK"));
            String temp = "";
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}