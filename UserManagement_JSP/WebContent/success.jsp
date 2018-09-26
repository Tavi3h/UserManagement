<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/theme.css">
<title>Operation Success</title>
</head>
<body>
	<%
		int userGrade = (int) session.getAttribute("grade");
	%>
	<img src=imgs/logo.png id="logo" />
	<hr>

	<h1>Operation Success</h1>
	<%
		if (userGrade == 10) {
	%>

	<a href="UserClServlet?flag=manage&toPage=1">Return to Management Page</a>
	<%
		}
		if (userGrade >= 5) {
	%>
	<br>
	<br>
	<a href="main.jsp">Return to Main Page</a>
	<%
		}
	%>
	<br>
	<br>
	<img src="imgs/flower.png" id="flower" />
</body>
</html>