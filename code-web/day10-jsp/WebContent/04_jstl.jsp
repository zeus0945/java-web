<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page pageEncoding="UTF-8"%>
<%@	taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>3.c: forEach标签(重要!!)</h1>
	<h3>(1)遍历域中的数组或集合中的元素</h3>
	<% 	//声明一个数组并将数组存入域中
		String[] names = {"刘德华","郭富城","张学友","黎明"};
		request.setAttribute("names", names);
		
		for(String name:names){  }
	%>
	<c:forEach items="${ names }" var="name">
		${ name } <br/>
	</c:forEach>
	
	<h3>(2)遍历域中的map集合中的元素</h3>
	<% 	//声明一个map集合,并存入域中
		Map map = new HashMap();
		map.put("name","阿凡达");
		map.put("age","18");
		map.put("nickname","小达达");
		request.setAttribute("map1", map);
	%>
	<c:forEach items="${ map1 }" var="entry">
		${entry.getKey()} : ${entry.getValue()}<br/>
		${entry.key} : ${entry.value}<br/>
	</c:forEach>
	
	<h3>(3)遍历0~100之间的所有整数,
		将是7的倍数的数值输出到网页上</h3>
		
	<c:forEach begin="0" end="100" var="i">
		<c:if test="${ i%7==0 }">
			${ i },
		</c:if>
	</c:forEach><br/>
	
	<%-- 
	<c:forEach begin="0" end="100" var="i">
		${ i%7==0 ? i : "" }
	</c:forEach>
	--%>
	
	<h3>(4)使用逗号分隔上面的练习中的数值,
		并去除最后一个逗号</h3>
	<!-- varStatus表示当前循环遍历信息的对象,
	该对象上常见的属性有:
	(1)status.first -- 如果返回true,表示当前遍历的是一个元素
	(2)status.last  -- 如果返回true,表示当前遍历的是最后一个元素
	(3)status.count -- 统计当前遍历的元素是第几个元素
	(4)status.count -- 可以表示下标,不要用index因为当begin=0时,index下标是从0开始
		当begin不是等于0时,index下标就不是从0开始
	 -->
	 
	<c:forEach begin="0" end="100" var="i" varStatus="status">
		<c:if test="${ i%7==0 }">
			${ status.first ? "" : "," }
			${ i }
		</c:if>
	</c:forEach><br/><hr/>
		
	
	<c:forEach begin="0" end="100" var="i" step="2" varStatus="status">
		${ i }
		${ status.last ? "" : "," }
	</c:forEach>
</body>
</html>
