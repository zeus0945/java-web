package com.tedu.res;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ResponseDemo2.doGet()");
		//将请求重定向到index.jsp
		//http://localhost/day09/ResponseDemo2
		//http://localhost/day09/index.jsp
		//response.sendRedirect("index.jsp");
		
		//定时刷新
		//模拟注册成功后
		//提示用户注册成功后,10秒后进行跳转到首页
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("恭喜您注册成功,10秒后将会跳转到首页.......");
		
		//实现10秒后跳转到首页
		response.setHeader("Refresh", "10;url=index.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
