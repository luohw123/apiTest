package com.javaapi.test.testRuntime;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
            // String catlinahome = "F:\\Program Files\\apache-tomcat-6.0.37";
            // String file = catlinahome + "\\bin\\startup.bat";
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

    public static void uploadFile(File file, String toFilePath)
            throws IOException {
        InputStream is = null;
        BufferedOutputStream os = null;
        try {
            is = new FileInputStream(file);
            os = new BufferedOutputStream(new FileOutputStream(toFilePath));
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException fnfe) {
            throw fnfe;
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            if (os != null)
                os.close();
            if (is != null)
                is.close();
        }
    }
}