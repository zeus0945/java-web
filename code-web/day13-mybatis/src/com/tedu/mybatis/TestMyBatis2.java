package com.tedu.mybatis;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.tedu.pojo.Emp;
/*
 * 使用#{}占位符对数据进行增删改操作
 * 使用${}占位符通常对没有引号的占位符进行占位
 */

public class TestMyBatis2 {
	private static SqlSessionFactory factory;
	private static SqlSession session;

	static {/*在类被记载时执行*/
		try {
			//1.读取mybatis的核心配置文件(sqlMapConfig.xml)
			InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
			//2.通过配置信息创建SqlSession工厂对象
			factory = new SqlSessionFactoryBuilder().build(in);
			//3.通过工厂获取SqlSession对象
			session = factory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testAddEmp() {
		//0.将sql参数封装到emp对象中
		Emp emp = new Emp(null,"韩少云","ceo",25000.0);
		//1.执行sql语句,添加员工信息
		int rows = session.update("com.tedu.pojo.EmpMapper.addEmp",emp);
		session.commit();
		//2.打印结果
		System.out.println("影响的行数"+rows);
	}
	//修改emp表中指定id的员工信息
	@Test
	public void testUpdateById() {
		//0.将sql参数封装到emp对象中
		Emp emp = new Emp(8,"马云","阿里ceo",35000.0);
		//1.执行sql语句,添加员工信息
		int rows = session.update("com.tedu.pojo.EmpMapper.updateById2",emp);
		//提交事务
		session.commit();
		//2.打印结果
		System.out.println("影响的行数"+rows);
	}
	//根据name查询员工信息
	@Test
	public void testfindByName() {
		List<Emp> list =session.selectList("com.tedu.pojo.EmpMapper.findByName","马云"); 
		for (Emp emp :list) {
			System.out.println(emp);
		}
	}

	/*
	 * Emp emp = session.selectOne("com.tedu.pojo.EmpMapper.findByName","马云");
	 * System.out.println(emp); }
	 */
	@Test
	public void testDeleteById() {
		//执行sql语句
		int rows = session.update("com.tedu.pojo.EmpMapper.deleteById",1);
		//提交事务
		session.commit();
		//2.打印结果
		System.out.println("影响的行数"+rows);
	}
	/*查询所有员工信息,显示的列通过参数传递过去
	 * select ? from emp
	 * select name,job from emp;
	 * select * from emp where name = #{name}
	 */
	@Test
	public void findAll2() {
		//将sql语句参数封装到Map集合中
		Map map = new HashMap();
		map.put("colStr","id,name,job");
		//执行sql语句
		List<Emp> list = session.selectList("com.tedu.pojo.EmpMapper.findAll2",map);
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	@Test
	public void findAll3() {
		//将sql语句参数封装到Map集合中
		Map map = new HashMap();
		map.put("name","刘");
		//1.执行sql语句,模糊查询员工信息
		List<Emp> list = session.selectList("com.tedu.pojo.EmpMapper.findAll3",map);
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
}
