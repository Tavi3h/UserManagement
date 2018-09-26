<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/theme.css">
<script type="text/javascript" src="jscript/script.js" charset="utf-8"></script>
<title>Query User</title>
</head>
<body>
	<img src=imgs/logo.png id="logo" />
	<hr>
	<h1>Query User</h1>

	<form action="UserClServlet?flag=queryUser" method="post" name="query" onSubmit="return queryCheck(this);">
		<label for="username">用户名:</label>
		<input type="text" name="username" id="username" />
		<br>
		<br>
		<label for="fuzzy">fuzzy</label>
		<input type="radio" name="qType" id="fuzzy" value="fuz" checked />
		<label for="precise">precise</label>
		<input type="radio" name="qType" id="precise" value="pre" />
		<br>
		<br>
		<input type="submit" value="QUERY" />
	</form>

	<br>
	<a href="main.jsp">Return to Main Page</a>
	<br>
	<br>
	<img src="imgs/flower.png" id="flower" />
</body>
</html>