<%@page import="com.tavish.model.ErrInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/theme.css">
<title>main</title>
</head>
<body>
	<%
		int userGrade = (int) session.getAttribute("grade");
		String u = (String) session.getAttribute("username");
	%>
	<img src=imgs/logo.png id="logo" />
	<hr>

	<h1>Main Page</h1>
	<%
		if (userGrade == 10) {
	%>
	<h2>
		Hello, Super Administrator
		<em><%=u%></em>
	</h2>
	<%
		} else if (userGrade >= 5) {
	%>
	<h2>
		Hello, Administrator
		<em><%=u%></em>
	</h2>
	<%
		} else {
	%>
	<h2>
		Hello, User
		<em><%=u%></em>
	</h2>
	<%
		}
	%>

	<%
		String errInfo = (String) session.getAttribute("errInfo");
		if (errInfo != null) {
			out.println("<h3 class='errorInfo'>" + errInfo + "</h3>");
			session.removeAttribute("errInfo");
		}
	%>

	<%
		if (userGrade == 10) {
	%>
	<a href="UserClServlet?flag=manage&toPage=1">管理用户</a>
	<br>
	<br>
	<%
		}
	%>
	<%
		if (userGrade >= 5) {
	%>
	<a href="adduser.jsp">添加用户</a>
	<br>
	<br>
	<%
		}
	%>
	<a href="queryuser.jsp">查询用户</a>
	<br>
	<br>
	<a href="LogoffClServlet">退出登录</a>
	<br>

	<br>
	<br>
	<img src="imgs/flower.png" id="flower" />
</body>
</html>