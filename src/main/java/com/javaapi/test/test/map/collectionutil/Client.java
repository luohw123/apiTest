package com.javaapi.test.test.map.collectionutil;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

/**
 * http://www.ylzx8.cn/zonghe/open-source/248952.html</br>
 * 收集属性
 *
 */
public class Client {
	/**
	 *  获取List<Person> 里的 id 得list
	 */
	@Test
	public void testName() throws Exception {
		List<Book> list = new ArrayList<>();
		Book book = new Book();
		book.setCategory("nihao");
		Book book2 = new Book();
		book2.setCategory("nihao2");
		list.add(book);
		list.add(book2);
		List<String> category = new ArrayList<>();
		CollectionUtils.collect(list, new Transformer<Book, String>() {
			@Override
			public String transform(Book input) {
				return input.getCategory();
			}
		}, category);
		System.err.println(category);
	}
}
