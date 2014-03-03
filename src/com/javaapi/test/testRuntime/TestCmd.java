package com.javaapi.test.testRuntime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestCmd {
	public TestCmd() {
    }

    @Test
    public  void testWin() {
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
    @Test
    public  void testLinux() {
    	String base="/home/kk/program/shellscript";
    	File file=new File(base);
    	Writer w;
    	if(file.exists()){
    		File newFile = new File(base+"test.txt");
    	     OutputStream oss;
			try {
				oss = new FileOutputStream(newFile);
				w = new BufferedWriter(new OutputStreamWriter(oss));
				w.write("hello wrold");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	
    	}
        try {
            Process pe = Runtime.getRuntime().exec(base,null,null);
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
    public static void main(String[] args) {
		new TestCmd().testLinux();
	}
}