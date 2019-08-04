package com.tedu.req;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 	request功能一:
 * 	获取请求参数
 * 	getParameter()
 * 	getParameterValues()
 */
public class RequestDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理post提交的参数乱码问题,这行代码需要放在任何获取参数代码之前执行,否则不会执行
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		//获取用户名
		String user = request.getParameter("user");
		System.out.println("user:"+user);
		//获取爱好
		String[] likes = request.getParameterValues("like");
		for (String like : likes) {
			System.out.println(like);
		}
	}
	/*
	 * 	通过一行代码通知服务器使用utf-8接受请求实体内容中的数据即可!!!
	 * 	request
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
