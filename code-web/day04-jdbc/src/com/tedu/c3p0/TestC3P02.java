package com.tedu.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tedu.util.JdbcUtils;

/**
 * PreparedStatement对象的使用
 */
public class TestC3P02 {
	/** 1.查询user表中的所有用户信息 */
	@Test
	public void findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//创建一个连接池对象
		ComboPooledDataSource pool = 
			new ComboPooledDataSource();
		try {
			//从连接池中获取一个连接对象
			conn = pool.getConnection();
			
			//声明SQL,并获取传输器
			String sql = "select * from user";
			ps = conn.prepareStatement(sql);
			//执行SQL语句
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String user = rs
						.getString("username");
				String pwd = rs
						.getString("password");
				System.out.println(
						id+":"+user+":"+pwd);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/* 用完连接对象后要将连接对象还回
			 * 连接池中
			 * 如果是自己创建的连接对象, 调用
			 * conn.close()方法是将连接关闭释放
			 * 如果是从连接池中获取的连接对象, 
			 * 调用conn.close()方法是将连接还回
			 * 连接池中, 因为连接池在将连接对象返回
			 * 之前,对连接对象的close方法进行了改造
			 * 改成了将连接还回池中, 而不是关闭!! */
			JdbcUtils.close(conn, ps, rs);
			//conn.close()
		}
	}
	/** 2.修改user表中id为2的记录,密码改为123456 */
	@Test
	public void updateUser() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//创建一个连接池对象
		ComboPooledDataSource pool = 
				new ComboPooledDataSource();
		try {
			//从连接池中获取一个连接对象
			conn = pool.getConnection();
			
			//声明SQL语句并获取传输器
			String sql = "update user set "
					+ "password=? where id=?";
			ps = conn.prepareStatement(sql);
			//设置SQL参数值
			ps.setString(1, "333");
			ps.setInt(2, 2);
			//执行SQL语句
			int rows = ps.executeUpdate();
			System.out.println("影响的行数:"+rows);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, ps, rs);
		}
	}
	
	
	
	
	
}
