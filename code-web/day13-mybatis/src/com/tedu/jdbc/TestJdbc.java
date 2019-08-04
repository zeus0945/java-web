package com.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tedu.pojo.Emp;

/**
 *	通过JDBC查询yonghedb库中emp表中的所有数据, 
 *	将每一条记录封装到一个Emp对象中
 *	将多条记录的多个Emp对象封装到一个
 *	List<Emp>集合并返回
 */
public class TestJdbc {
	public static void main(String[] args) {
		List<Emp> list = findAll();
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	/** 查询所有员工的信息 */
	private static List<Emp> findAll() {
		 
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://yonghedb","root","root");
			stat = conn.createStatement();
			String sql = "select * from emp";
			rs = stat.executeQuery(sql);
			//处理结果
			List<Emp> list = new ArrayList();
			while(rs.next()) {
				Emp emp = new Emp(rs.getInt("id"),rs.getString("name"),rs.getString("job"),rs.getDouble("salary"));
				list.add(emp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询失败");
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
		return null;
	}
}
