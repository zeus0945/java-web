package com.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.tedu.util.JdbcUtils;

/**
 * JDBC增删改查(CRUD)
 * Create 增加 Retrieve查询 
 * Update 修改 Delete 删除
 */
public class JdbcCRUD {
	/** 1、增加：往account表中插入一条新的记
	  *  录，name为 "john"，money为30000 */
	@Test
	public void addAcc() { 
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null; 
		try {
			//1.注册驱动并获取连接
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql:///jt_db", 
					"root", "root");
			//2.获取传输器
			stat = conn.createStatement();
			//3.发送sql到服务器执行并返回执行结果
			String sql = "insert into account"
					+ " values(null, 'john', 3000)";
			int rows = stat.executeUpdate(sql);
			//4.处理结果
			System.out.println("影响的行数:"+rows);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("执行失败!");
		} finally {
			//释放资源(模版代码)
			JdbcUtils.close(conn, stat, rs);
		}
	}
	
	/** 2、修改：修改account表中name
	  *  为 "john" 的记录，将金额改为2500 */
	@Test
	public void updateAcc() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null; 
		try {
			//1.注册驱动并获取连接
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql:///jt_db", 
					"root", "root");
			//2.获取传输器
			stat = conn.createStatement();
			//3.发送sql到服务器执行并返回执行结果
			String sql = "update account set "
				+ "money=2500 where name='john'";
			int rows = stat.executeUpdate(sql);
			//4.处理结果
			System.out.println("影响的行数:"+rows);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("执行失败!");
		} finally {
			//释放资源(模版代码)
			JdbcUtils.close(conn, stat, rs);
		}
		
	}
	
	/** 3、删除：删除account表中name
	  * 为 "john" 的记录 */
	@Test
	public void deleteAcc() { 
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null; 
		try {
			//1.注册驱动并获取连接
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql:///jt_db", 
					"root", "root");
			//2.获取传输器
			stat = conn.createStatement();
			//3.发送sql到服务器执行并返回执行结果
			String sql = "delete from account where"
					+ " name='john'";
			int rows = stat.executeUpdate(sql);
			//4.处理结果
			System.out.println("影响的行数:"+rows);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("执行失败!");
		} finally {
			//释放资源(模版代码)
			JdbcUtils.close(conn, stat, rs);
		}
	}
	
	/** 4、查询account表中id为1的记录 */
	@Test
	public void findById() { 
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//1.注册驱动并获取连接
			conn = JdbcUtils.getConn();
			//2.获取传输器
			stat = conn.createStatement();
			//3.执行SQL语句并返回执行结果
			String sql = "select * from"
				+ " account where id=1";
			rs = stat.executeQuery(sql);
			//4.处理结果
			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double money = rs.getDouble("money");
				System.out.println(
					id+":"+name+":"+money);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放资源
			JdbcUtils.close(conn, stat, rs);
		}
		
		
	}
	
}







