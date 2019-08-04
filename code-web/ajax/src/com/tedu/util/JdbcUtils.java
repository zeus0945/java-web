package com.tedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** JDBC工具类 */
public class JdbcUtils {
	/* 私有构造方法(目的是不让别人创建该
	  *  类的对象, 因为都是静态方法,直接调用即可) */
	private JdbcUtils() {}
	
	/** 注册驱动并获取连接对象 */
	public static Connection getConn() 
			throws Exception {
		//1.注册驱动并获取连接
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql:///jt_db?characterEncoding=utf-8", 
				"root", "root");
		return conn;
	}
	
	/** 释放资源的工具方法 */
	public static void close(Connection conn, 
			Statement stat, ResultSet rs) {
		//6.释放资源
		if(rs != null) {
			try {
				rs.close();
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if(stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				stat = null;
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
}



