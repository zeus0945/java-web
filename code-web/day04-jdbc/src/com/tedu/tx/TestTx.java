package com.tedu.tx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tedu.util.JdbcUtils;

public class TestTx {
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//1.获取连接
			conn = JdbcUtils.getConn();
			//2.关闭JDBC自动提交事务（默认开启事务）
			conn.setAutoCommit(false);  
			//3.获取传输器
			stat = conn.createStatement();
			
			/* ***** A给B转账100元 ***** */
			//4.A账户减去100元
			String sql = "update acc set money=money-100 where name='A'";
			stat.executeUpdate(sql);
			
			int i = 1/0; // 让程序抛出异常，中断转账操作
			
			//5.B账户加上100元
			sql = "update acc set money=money+100 where name='B'";
			stat.executeUpdate(sql);
			
			//6.手动提交事务
			conn.commit();
			System.out.println("转账成功！提交事务...");
		} catch (Exception e) {
			e.printStackTrace();
			//一旦其中一个操作出错都将回滚，使两个操作都不成功 
			conn.rollback();    
			System.out.println("执行失败！回滚事务...");
		} finally{
			JdbcUtils.close(conn, stat, rs);
		}
	}
}
