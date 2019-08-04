package com.tedu;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tedu.dao.Dao;
import com.tedu.pojo.User;

/*
 * 1.测试spring IOC(控制反转)
 */
public class TestSpring {
	//1.加载applicationContext.xml
	ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	@Test
	public void testDao() {
		//创建MySQLDao的实例,操作mysql数据库
		Dao dao = (Dao) ac.getBean("dao");
		dao.findAll();
	}
	
	@Test
	public void testDI() {
		//获取User类的实例对象
		User user = (User) ac.getBean("user");
		
		System.out.println(user);
	}

	@Test
	public void testIOC2() {
		
		//获取User对象
		User u1 = (User) ac.getBean("user");
		User u2 = (User) ac.getBean("user");
		if (u1 == u2) {
			System.out.println("当前Bean对象是单实例...");
		}else {
			System.out.println("当前Bean对象是多实例...");
		}
	}
	@Test
	public void testIOC() {

		//2.获取User对象
		User user = (User) ac.getBean("user");
		System.out.println(user);
	}
	
	@Test
	public void testFanshe() throws Exception {
		//通过反射创建User类的对象
		Class clz = Class.forName("com.tedu.pojo.User");
		//调用newInstance方法创建类的实例
		User user = (User) clz.newInstance();
		System.out.println(user);
	}
}
