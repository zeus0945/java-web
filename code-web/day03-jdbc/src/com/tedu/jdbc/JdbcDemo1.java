package com.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC的快速入门程序
  * 查询account表中的所有记录, 并输出到控制台
 */
public class JdbcDemo1 {
	public static void main(String[] args){
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//1.注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取数据库连接
			conn = DriverManager.getConnection(
				"jdbc:mysql:///jt_db", 
				"root", "root");
			//3.获取传输器
			stat = conn.createStatement();
			//4.发送SQL到数据库执行,并返回执行结果
			String sql = "select * from account";
			rs = stat.executeQuery(sql);
			//5.处理结果(输出到控制台)
			while( rs.next() ) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double money = rs.getDouble("money");
				System.out.println(
					id+":"+name+":"+money);
			}
			
			System.out.println("执行完成!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("执行失败!!");
		} finally {//这里的代码一定会被执行!!
			//6.释放资源
			if(rs != null) {
				try {
					rs.close();
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
	
}











