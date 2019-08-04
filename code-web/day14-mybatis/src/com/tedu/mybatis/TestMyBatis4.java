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

import com.tedu.dao.EmpMapper;
import com.tedu.pojo.Emp;
/*
 *mybatis的Mapper接口开发
 *(1)创建一个接口,接口的全路径名和mapper文件的namespace值要相同
 *(2)SQL标签的ID值要和接口中的方法名要保持一致
 *(3)SQL语句的参数类型和接口中方法的参数类型要保持一致
 *(4)SQL标签上的resultType的返回值类型和接口中方法的返回类型相同(如果返回值是集合,resultType只需要指定集合中的泛型)
 *
 */

public class TestMyBatis4 {
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
	//13.查询员工信息
	@Test
	public void testFindAll() {
		//0.获取EmpMapper接口的(匿名实现类的)实例
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		//1.执行sql语句,添加员工信息
		List<Emp> list = mapper.findAll();
		//2.输出list集合
		for (Emp e : list) {
			System.out.println(e);
		}
	}
	//14.添加员工信息
	@Test
	public void testAddEmp() {
		//0.将sql参数封装到Emp对象中
		Emp emp = new Emp(null,"张飞","辅助",3000.0);
		//1.获取mapper接口的实例
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		//2.调用addEmp方法新增员工
		int rows = mapper.addEmp(emp);
		//提交事务
		session.commit();
		System.out.println(rows);
	}
	//15.修改员工信息:
	//修改id为1的员工信息,将name改为马云云,job="程序员鼓励师",salary=35000(第4条sql)
	@Test
	public void testUpdate() {
		//0.将sql参数封装到Emp对象中
		Emp emp = new Emp(1,"马云云","程序员鼓励师",35000.0);
		//1.获取mapper接口的实例
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		int rows = mapper.updateById2(emp);
		//提交事务
		session.commit();
		System.out.println(rows);
	}
	
	//16.删除id为3的员工信息(第6条sql)
	@Test
	public void testDelete() {
		//1.获取mapper接口的实例
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		int rows = mapper.deleteById(3);
		//提交事务
		session.commit();
		System.out.println(rows);
	}
	
	
}