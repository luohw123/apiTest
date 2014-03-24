package com.javaapi.test.testFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import jodd.util.MathUtil;

import org.junit.Test;

public class TestFile {
	private static final String S = File.separator;
	// 编译之后得类路径
	private static final String txtPath = TestFile.class.getResource("")
			.getPath() + "TestCmd.txt";;
	// 明显是源码路径
	private static final String tempString = "D:" + S + "workspace2012-11-12"
			+ S + "JavaApiSample" + S + "src" + S + "com" + S + "javaapi" + S
			+ "test" + S + "testRuntime" + S + "TestCmd.txt";
	private static final String STRING = txtPath;
	private static final String imagePath = "C:" + S + "Users" + S + "hncw" + S
			+ "Desktop" + S;

	private static final String IMAGEPATH = imagePath;

	private static final int random = MathUtil.randomInt(0, 1000);

	/**
	 * 输出文本文件到硬盘
	 * 
	 * @throws IOException
	 */
	@Test
	public void testWriteText() throws IOException {
		System.out.println(txtPath);

		OutputStream is = null;
		Writer w = null;
		BufferedWriter bw = null;
		try {
			is = new FileOutputStream(new File(STRING));
			w = new OutputStreamWriter(is);
			bw = new BufferedWriter(w);
			bw.write("hello world");
			bw.newLine();
			bw.write("hello world");
			bw.newLine();
			bw.write("hello world");
			bw.write("hello world2");
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

	/**
	 * 读取文本文件到内存
	 * 
	 * @throws IOException
	 */
	@Test
	public void testReadText() throws IOException {
		InputStream is = null;
		Reader r = null;
		BufferedReader br = null;
		try {
			is = new FileInputStream(STRING);
			r = new InputStreamReader(is);
			br = new BufferedReader(r);
			StringBuilder sb = new StringBuilder();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
				sb.append(System.lineSeparator());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (br != null)
				br.close();
			if (r != null)
				r.close();
			if (is != null)
				is.close();

		}
	}

	/**
	 * 读取,输出二进制文件到内存,比如说图片
	 * 
	 * @throws IOException
	 */
	@Test
	public void testReadFile() throws IOException {
		InputStream is = null;
		OutputStream os = null;
		BufferedInputStream br = null;
		BufferedOutputStream bo = null;
		try {
			is = new FileInputStream(new File(imagePath + "136.jpg"));

			br = new BufferedInputStream(is);

			os = new FileOutputStream(new File(imagePath + random + ".jpg"));

			bo = new BufferedOutputStream(os);
			int length = 0;
			byte[] buf = new byte[1024];
			// while ((length = br.read(buf)) > 0) {
			// System.out.println(length);
			// os.write(buf, 0, length);
			// }
			while ((length = br.read(buf)) > 0) {
				System.out.println(length);
				os.write(buf);
			}

		} catch (IOException io) {
			throw io;
		} finally {
			if (br != null)
				br.close();
			if (is != null)
				is.close();
			if (bo != null)
				bo.close();
			if (os != null)
				os.close();
		}
		File file = null;
		file.delete();
	}
}
