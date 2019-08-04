package com.tedu.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.tedu.util.JdbcUtils;

/** 模拟用户登录案例
 */
public class LoginUser {
	/* 思路: 提示用户在控制台输入用户名
	  * 和密码, 并接收用户输入的用户名和密码
	  * 根据用户名和密码查询User表, 如果查到
	  * 了记录, 则用户名密码正确, 允许用户登录
	  * 否则就提示"用户名或密码错误"
	 */
	public static void main(String[] args) {
		//0.声明一个Scanner对象
		Scanner in = new Scanner(System.in);
		//1.提示用户登录
		System.out.println("请登录:");
		//2.提示用户输入用户名、密码，并接收
		System.out.println("请输入用户名:");
		String user = in.nextLine();
		System.out.println("请输入密码:");
		String pwd = in.nextLine();
		
		//3.调用login方法,传入用户名密码, 进行登录
		loginByPS(user, pwd);
	}
	/**
	  * 根据用户名和密码查询用户表
	 */
	private static void loginByPS(
			String user, String pwd) {
		Connection conn = null;
		PreparedStatement ps = null; 
		ResultSet rs = null;
		try {
			//1.注册驱动并获取连接
			conn = JdbcUtils.getConn();
			//2.获取传输器
			String sql = "select * from user"
					+ " where username=? and"
					+ " password=?";
			ps = conn.prepareStatement(sql);
			//>>设置参数
			ps.setString(1, user);
			ps.setString(2, pwd);
			//3.执行SQL语句(千万不要传两次SQL语句)
			rs = ps.executeQuery();
			//4.处理结果
			if(rs.next()) {
				System.out.println("恭喜您登录成功!");
			}else {
				System.out.println(
						"登录失败,用户名或密码不正确!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, ps, rs);
		}
	}
	
	private static void login(
			String user, String pwd) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//1.注册驱动并获取连接
			conn = JdbcUtils.getConn();
			//2.获取传输器
			stat = conn.createStatement();
			//3.执行SQL语句
			String sql = "select * from user "
					+ "where username='"+user+"' "
					+ "and password='"+pwd+"'";
			System.out.println(sql);
			rs = stat.executeQuery(sql);
			//4.处理结果
			if(rs.next()) {
				System.out.println("恭喜您登录成功!");
			}else {
				System.out.println(
						"登录失败,用户名或密码不正确!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, stat, rs);
		}
	}
	
	
	
	
	
	
	
	
	
	
}
