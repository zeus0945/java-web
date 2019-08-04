package com.tedu.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 	负责将商品加入购物车
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//0.处理响应正文乱码
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//1.获取要添加到购物车的商品信息
		String prod = request.getParameter("prod");
		
		//2.将商品信息添加到cookie中
		Cookie cookie = new Cookie("prod",prod);
		//>>设置Cookie的最大生存时间
		cookie.setMaxAge( 60*60*24*30 );
		//3.将cookie发送给浏览器保存
		//底层通过set-cookie响应头将cookie发送给浏览器
		response.addCookie( cookie );
		
		//4.响应
		out.write("<h1>成功将"+prod+"商品加入购物车</h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
