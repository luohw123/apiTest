package com.javaapi.test.dataTrans.array;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * http://www.cnblogs.com/chenssy/p/3466092.html 
 *不错
 *  所以在性能要求较高的场景中请优先考虑数组。</br>
 *  java 中把数组也当做对象来用
 */
public class TestArray {

	
	@Test
	public void test() {
		int[] a = new int[5];
		System.out.println(a.toString());
		System.out.println(Arrays.toString(a));
	}
	
	/**
	 * System.arraycopy 不会有任何附加功能.所以有可能产生越界等异常
	 */
	@Test
	public void testSystemArrayCopy() {
		int[] ids = { 1, 2, 3, 4, 5 };
		int[] id2 = { 2, 3, 4, 5, 6, 8 };
		System.arraycopy(ids, 0, id2,0, ids.length);
		System.out.println(Arrays.toString(ids));
		System.out.println(Arrays.toString(id2));
	}
	/**
	 * 注意 目的数组得长度不要越界
	 */
	@Test
	public void testSystemArray() {
		int[] ids = { 1, 2, 3, 4, 5,9 };
		int[] id2 = { 2, 3, 4, 5, 6, 8 };
		System.arraycopy(ids, 0, id2,0, ids.length);
		System.out.println(Arrays.toString(ids));
		System.out.println(Arrays.toString(id2));
	}
	@Test
	public void testArraysCopy() {
		int[] ids = { 1, 2, 3, 4, 5,9 };
		int[] afterCopy = Arrays.copyOf(ids, 20);
		System.out.println(Arrays.toString(afterCopy));
	}
	@Test
	public void testArraysSortStringWrong() {
	    String[] strArr = {"0","4","2","5","3","10","1024"};
	    Arrays.sort(strArr);
	    System.out.println(Arrays.asList(strArr));
	}
	@Test
	public void testArraysSortString() {
	    String[] strArr = {"0","4","2","5","3","10","1024"};
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int parseInt = Integer.parseInt(o1);
                int parseInt2 = Integer.parseInt(o2);
                return parseInt-parseInt2;
            }
        });
        System.out.println(Arrays.asList(strArr));
	}
	@Test
    public void testArraysSortString2() {
        String paramsForUpload = "dc_spf?136,139,142?3,1,0?";
        String[] strArr = paramsForUpload.split("\\?");
        String[] raceNoArr = strArr[1].trim().split(",");
        Arrays.sort(raceNoArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int parseInt = Integer.parseInt(o1);
                int parseInt2 = Integer.parseInt(o2);
                return parseInt - parseInt2;
            }
        });
        System.out.println(StringUtils.join(raceNoArr, ","));
        
    }
}
