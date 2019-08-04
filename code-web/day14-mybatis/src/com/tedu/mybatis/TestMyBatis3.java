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
 *mybatis动态
 */

public class TestMyBatis3 {
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
	/*
	 * 9.查询员工信息,如果name不为null,则根据name模糊查询
	 * 如果name为null,则查询所有员工信息
	 */
	@Test
	public void testFindAll4() {
		//0.将sql参数封装到map中
		Map map = new HashMap();
		map.put("name", "");
		//1.执行sql语句,添加员工信息
		List<Emp> list = session.selectList("com.tedu.pojo.EmpMapper.findAll4",map);
		//2.输出list集合
		for (Emp e : list) {
			System.out.println(e);
		}
	}
	/*
	 * 10.查询emp表中所有员工信息,另:
	 * 如果传递了minSal(最低薪资)和maxSal(最高工资)
	 * 则查询薪资大于minSal和小于maxSal的员工信息
	 * where sal>minSal and sal<maxSal 
	 * 如果只传递了minSal,则查询薪资大于minSal的所有员工信息
	 * 如果只传递了maxSal,则查询薪资小于maxSal的所有员工信息
	 * 如果两个都不传,则查询所有员工信息
	 */
	@Test
	public void testFindAll5() {
		//0. 如果两个都不传,则查询所有员工信息
		//0.如果只传递了minSal,则查询薪资大于minSal的所有员工信息
		Map map = new HashMap();
		//map.put("minSal", 3000);
		//0.如果只传递了maxSal,则查询薪资小于maxSal的所有员工信息
		//map.put("maxSal", 4000);
		//0.如果传递了minSal(最低薪资)和maxSal(最高工资), 则查询薪资大于minSal和小于maxSal的员工信息
		//map.put("minSal", 3000);
		map.put("maxSal", 4000);
		//1.执行sql语句,返回执行结果
		List<Emp> list = session.selectList("com.tedu.pojo.EmpMapper.findAll5",map);
		//2.输出list集合
		for (Emp e : list) {
			System.out.println(e);
		}
	}
	//11.修改员工信息,根据传入的值对列进行修改
	@Test
	public void testUpdateById() {
		//将sql参数封装到Emp对象中
		Emp emp = new Emp(3,"涛哥","架构师",20000.0);
		//1.执行sql语句,返回执行结果
		int rows = session.update("com.tedu.pojo.EmpMapper.updateById3",emp);
		session.commit();
		//2.打印结果
		System.out.println("影响的行数"+rows);
	}
	/*
	 * 12.批量删除操作
	 * 删除id为2.4,6,8的员工
	 */
	@Test
	public void testDelete() {
		//0.将要删除的员工的id封装到数组中
		Integer[] ids = {2,4,6,8};
		//1.执行sql语句
		int rows = session.update("com.tedu.pojo.EmpMapper.delete",ids);
		//提交事务
		session.commit();
		System.out.println(rows);
	}
}
