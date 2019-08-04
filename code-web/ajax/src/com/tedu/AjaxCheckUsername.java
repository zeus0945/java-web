package com.tedu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tedu.util.JdbcUtils;

public class AjaxCheckUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理请求参数(POST)和响应正文乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//1.接收请求中携带过来的用户名
		String username = request
				.getParameter("username");
		//2.通过Jdbc检查用户名是否存在
		boolean isExist = 
				checkUsernameIsExist(username);
		if(isExist) {
			out.write(username+"用户名已存在!!");
		}else {
			out.write(username+"用户名不存在,可以使用!!!");
			out.write("");
		}
	}
	/**
	 * 根据用户名查询用户是否存在
	 * @param username
	 * @return true表示存在, false表示不存在
	 */
	private boolean checkUsernameIsExist(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.注册驱动、获取连接对象
			conn = JdbcUtils.getConn();
			//2.声明查询SQL,获取传输器对象
			String sql = "select * from user where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			//3.执行SQL语句,获取结果集
			rs = ps.executeQuery();
			//4.处理结果集，返回true:表示用户名存在,反之则不存在!
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询失败!");
		} finally {
			JdbcUtils.close(conn, ps, rs);
		}
		return false;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
