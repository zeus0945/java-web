package com.tedu.session;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *	负责将商品加入购物车 
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//1.获取要添加到购物车的商品信息
		String prod = request.getParameter("prod");
		
		//2.获取一个session对象
		HttpSession session = request.getSession();
		//创建一个cookie用于保存session的id
		Cookie c = new Cookie("JSESSIONID",session.getId());
		//设置cookie的最大存活时间,这样即使浏览器关闭,cookie也不会销毁,id也不会丢死
		c.setMaxAge( 3600*24 );
		//将保存session的cookie发送给浏览器
		response.addCookie(c);
		
		//3.将商品信息保存到session对象中
		session.setAttribute("prod", prod);
		
		//4.做出响应
		out.write("<h1>成功将"+prod+"商品加入购物车...</h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
