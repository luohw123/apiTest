package com.javaapi.test.test.testType.object.use;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.javaapi.test.test.testType.object.DiffBean;
import com.javaapi.test.test.testType.object.FromBean;
import com.javaapi.test.test.testType.object.ToBean;

public class TestSpringBeanCopy {

	@Test
	public void testSameBeanCopy() {
		FromBean fb = new FromBean();
		fb.setAddress("北京市朝阳区大屯路");
		fb.setAge(20);
		fb.setMoney(30000.111);
		fb.setIdno("110330219879208733");
		fb.setName("测试");
		FromBean to = new FromBean();
		copy(fb, to);
		System.out.println(to.toString());
	}
	private void copy(FromBean fb, FromBean to) {
		BeanUtils.copyProperties(fb, to);
	}
	@Test
	public void testToBeanCopy() {
		FromBean fb = new FromBean();
		fb.setAddress("北京市朝阳区大屯路");
		fb.setAge(20);
		fb.setMoney(30000.111);
		fb.setIdno("110330219879208733");
		fb.setName("测试");
		ToBean to = new ToBean();
		BeanUtils.copyProperties(fb, to);
		System.out.println(to.toString());
	}
	/**
	 * 不同类型得bean,要属性名字相同
	 */
	@Test
	public void testDiffBeanCopy() {
		FromBean fb = new FromBean();
		fb.setAddress("北京市朝阳区大屯路");
		fb.setAge(20);
		fb.setMoney(30000.111);
		fb.setIdno("110330219879208733");
		fb.setName("测试");
		DiffBean to = new DiffBean();
		BeanUtils.copyProperties(fb, to);
		System.out.println(to.toString());
	}
}
