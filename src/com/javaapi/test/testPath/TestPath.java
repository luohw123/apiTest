package com.javaapi.test.testPath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.junit.Test;

public class TestPath {
    private static final String L = System.lineSeparator();

    @Test
    public void getPath() {
        ClassLoader cls = ClassLoader.getSystemClassLoader();
        ClassLoader classCls = TestPath.class.getClassLoader();
        ClassLoader threadCls = Thread.currentThread().getContextClassLoader();
        System.out.println(ClassLoader.getSystemResource("").getPath());
        System.out.println(TestPath.class.getClassLoader().getResource("")
                .getPath());
        System.out.println(Thread.currentThread().getContextClassLoader()
                .getResource("").getPath());
        System.out.println(cls == classCls);
        System.out.println(cls == threadCls);
    }

    @Test
    public void getDiff() {
        // 输出编译文件夹根目录
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);
        // 输出编译文件夹根路径+包路径
        File f2 = new File(this.getClass().getResource("").getPath());
        System.out.println(f2);
    }

    @Test
    public void getSymbol() throws IOException {
        System.out.println(File.separator);
        System.out.println(File.separatorChar);

        System.out.println("跨系统的分隔符↓");
        System.out.println(File.pathSeparator);
        System.out.println(File.pathSeparatorChar);

        /*
         * You use separator when you are building a file path. So in unix the
         * separator is /. So if you wanted to build the unix path /var/temp you
         * would do it like this:
         * 
         * String path = File.separator + "var"+ File.separator + "temp"
         * 
         * You use the pathSeparator when you are dealing with a list of files
         * like in a classpath. For example, if your app took a list of jars as
         * argument the standard way to format that list on unix is:
         * /path/to/jar1.jar:/path/to/jar2.jar:/path/to/jar3.jar
         * 
         * So given a list of files you would do something like this:
         * 
         * String listOfFiles = ... String[] filePaths =
         * listOfFiles.split(File.pathSeparator);
         */
        // 获得跨系统的换行符 4种方法,
        System.out.println("跨系统的换行符==>" + System.lineSeparator()); // java7以上才支持
        System.out.println("------------");
        System.out.println("跨系统的换行符==>" + System.getProperty("line.separator"));
        System.out.println("------------");
        System.out.println("跨系统的换行符==>" + String.format("%n"));
        System.out.println("------------");
        // 第4种是BufferedWriter 的newline方法
        OutputStream is = null;
        Writer w = null;
        BufferedWriter bw = null;
        try {
            is = new FileOutputStream(new File("path"));
            w = new OutputStreamWriter(is);
            bw = new BufferedWriter(w);
            bw.write("hello world");
            bw.newLine();
            bw.write("hello world");
            bw.newLine();
            bw.write("hello world");
            bw.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                bw.close();
            }
            if (w != null) {
                w.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }
}
