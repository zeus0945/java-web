<%@ page language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>
		<%= new Date() %>
		<hr/>
		<%= "Hello JSP" %>				
		Hello SJP
		
		<% String name = "Hello"; %>
		<%= name %>
		
		<%= 100+234 %>
		
		<hr/>
		
		<% 	//声明一个脚本片段,输出5行"Hello JSP"
			for(int i = 0;i<5;i++){
				out.write("Hello JSP<br/>");
			}
		%>
		
		<hr/>
		
		<%	for(int i = 0;i<5;i++){ %>
				Hello JSP<br/>
		<%	} %>
	</h1>
</body>
</html>
