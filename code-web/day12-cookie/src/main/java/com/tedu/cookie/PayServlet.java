package com.tedu.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *	负责对购物车中的商品进行结算 
 */
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//1.获取请求中的所有cookie组成的数组
		Cookie[] cs = request.getCookies();
		//2.遍历cookie数组,找到名称为prod的cookie
		//用一个确定没有空的值调用equals方法来比较调用的方法的返回值,以免返回的是空值null,这样的话返回是false
		String prod = null;
		if ( cs!=null ) {
			for(Cookie c : cs) {
				if("prod".equals( c.getName() )) {
					//3.从cookie获取之前添加的商品
					prod = c.getValue();
				}
			}
		}
		//2.1从cookie获取之前添加的商品信息
		
		//2.2(响应)模拟支付,为商品进行支付
		if ( prod == null) {
			out.write("<h1>您还没有将商品加入购物车</h1>");
		}else {
			out.write("<h1>成功为"+prod+"支付了1000.0元..</h1>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
