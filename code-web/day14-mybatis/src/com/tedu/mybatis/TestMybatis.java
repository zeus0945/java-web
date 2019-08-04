package com.tedu.mybatis;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.tedu.pojo.Emp;

/**
 *	通过Mybadis查询yonghedb库中emp表中的所有数据, 
 *	将每一条记录封装到一个Emp对象中
 *	将多条记录的多个Emp对象封装到一个
 *	List<Emp>集合并返回
 */
public class TestMybatis {
	public static void main(String[] args) throws Exception {
		//1.读取mybatis的核心配置文件(sqlMapConfig.xml)
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.通过配置信息创建SqlSession工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//3.通过工厂获取SqlSession对象
		SqlSession session = factory.openSession();
		//4.执行sql语句,返回执行结果(EmpMapper.xml)
		List<Emp> list = session.selectList("com.tedu.pojo.EmpMapper.findAll");
		//5.打印结果
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	/* 2.修改员工信息 */
	@Test
	public void testUpdate() throws Exception {
		//1.读取mybadis核心配置文件
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获取工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//3.获取SqlSession对象
		SqlSession session = factory.openSession();
		//4.执行sql语句,返回执行结果
		int rows = session.update("com.tedu.pojo.EmpMapper.updateById");
		//提交事务
		session.commit();
		//5.处理结果
		System.out.println("影响的行数:"+rows);
	}
}
