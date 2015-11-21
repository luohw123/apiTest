package com.javaapi.test.testUtil.fileUtil.fenpi;

import org.junit.Test;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FenPiUrl {
    private ExecutorService es = newBlockingExecutorsUseCallerRun(300);

    public static ExecutorService newBlockingExecutorsUseCallerRun(int size) {
        return new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
    @Test
    public void test() throws IOException {
        InputStream in = new FileInputStream(new File("/Users/user/program/shell/sina/sina/duopi/fenpContent.txt"));
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedInputStream = new BufferedReader(reader);
        String temp ;

        FileOutputStream out = new FileOutputStream(new File("/Users/user/program/shell/sina/sina/duopi/fenpiVideoUrl.txt"));
        OutputStreamWriter writer = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(writer);

        int i=0;
        while ( (temp = bufferedInputStream.readLine())!=null ) {
            es.execute(new FenPiUrlThread(temp, bw));
            i++;
            if (i % 10 == 0) {
                System.out.println(" 进度 " + i);
            }
        }

        in.close();
        reader.close();
        bufferedInputStream.close();

        bw.flush();
        bw.close();
        writer.close();
        out.close();

    }
}
