package com.tedu.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.tedu.util.JdbcUtils;

/**
 * PreparedStatement对象的使用
 */
public class PSCRUD {
	/** 1.查询user表中的所有用户信息 */
	@Test
	public void findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConn();
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
			JdbcUtils.close(conn, ps, rs);
		}
	}
	/** 2.修改user表中id为2的记录,密码改为123456 */
	@Test
	public void updateUser() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConn();
			//声明SQL语句并获取传输器
			String sql = "update user set "
					+ "password=? where id=?";
			ps = conn.prepareStatement(sql);
			//设置SQL参数值
			ps.setString(1, "123456");
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
