<%@page import="com.tedu.User"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h3>(1)获取常量,表达式或变量(先存入域)的值,并输出</h3>
	${ "Hello JSP" }
	<%= "Hello JSP" %>
	Hello JSP
	<hr/>
	${ 100+23 }
	<hr/>
	<% String name = "韩少云";
		request.setAttribute("name1", name);
	%>
	${ name1 }
	<%-- 
	在EL中书写变量,在执行时EL会根据变量的名字到域中寻找指定名称的属性值,
	如果找到就将值返回并输出,如果找不到就什么也不输出!
	 --%>
	<h3>(2)获取域中数组或集合中的元素/数据</h3>
	<% 	//声明一个数组并将数组存入域中
		String[] names = {"刘德华","郭富城","张学友","黎明"};
		request.setAttribute("names", names);
	%>
	<!-- EL只能获取域中的数据,但不能遍历 -->
	${ names[0] }
	${ names[1] }
	${ names[2] }
	${ names[3] }
	
	<h3>(3)获取域中的map集合中的元素/数据</h3>
	<% 	//声明一个map集合,并存入域中
		Map map = new HashMap();
		map.put("name","阿凡达");
		map.put("age","18");
		map.put("nickname","小达达");
		request.setAttribute("map1", map);
	%>
	${ map1.get("name") }
	${ map1["name"] }
	${ map1.name }
	${ map1.age }
	${ map1.nickname }
	
	<h3>(4)获取域中的对象(user对象)的属性值</h3>
	<% 	//声明一个User对象并存入域中
		User u = new User("张飞","123","zf@tedu.cn");
		request.setAttribute("user", u);
	%>
	<!-- 下面两个底层调用的都是getUsername方法 -->
	${	user.getUsername() }
	${	user.username }
	<!-- 下面两个底层调用的都是getPwd方法 -->
	${	user.getPwd() }
	${	user.pwd }
	<!-- 下面两个底层调用的都是getEmail方法 -->
	${	user.getEmail() }
	${	user.email }
	
</body>
</html>
