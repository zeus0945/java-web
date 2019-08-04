package com.tedu.req;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *	当访问RequestDemo2,负责查询当前用户的个人信息
 *	将用户的个人信息转向JSP,由JSP负责做响应 
 *
 */
public class RequestDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//1.模拟查询当前用户的个人信息
		String user = "王海涛";
		String addr = "北京";
		
		//2.将个人信息存入request的map集合中
		request.setAttribute("user", user);
		request.setAttribute("addr", addr);
		
		//3.将请求和request对象一起转发到jsp
		request.getRequestDispatcher("show.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
