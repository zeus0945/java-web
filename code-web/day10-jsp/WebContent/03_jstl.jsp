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
	<h1>1.c: set标签</h1>
	<h3>(1)往指定域中添加属性</h3>
	<% 	String name1 = "张三";
		request.setAttribute("name1", name1);
	%>
	<c:set var="name2" value="张三丰" scope="request"/>
	${ name2 }
	<h3>域的从小到大排序pageContext < request < session < application</h3>
	<h3>(2)修改(覆盖)域中已有的属性值,域不一样就不是修改但是取值时按最小的取值</h3>
	<c:set var="name2" value="张无忌" scope="request"/>
	${ name2 }
	<c:set var="name2" value="张三丰" />
	${ requestScope.name2 }
	${ name2 }
	
	
	<h1>2.c: if标签
		-- 构造简单的if...else语句
	</h1>
	<!-- 如果test的值为true,就会输出标签中的内容,否则就不会输出 -->
	<c:if test="${ 354+567 > 999 }">
		yes, 你最大!
	</c:if>
	<c:if test="${ 354+567 <= 999 }">
		no, 你不行!
	</c:if>
</body>
</html>
