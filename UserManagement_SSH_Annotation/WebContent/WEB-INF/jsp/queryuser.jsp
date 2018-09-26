<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=path%>/css/theme.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/script.js" charset="utf-8"></script>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>Query User</title>
</head>
<body>
	<img src=images/logo.png id="logo" />
	<hr>
	<h1>Query User</h1>
	<s:fielderror />
	<br>
	<form action="service/query_queryUser.action" method="post" name="query" onSubmit="return queryCheck(this);">
		<label for="username">用户名:</label>
		<input type="text" name="username" id="username" />
		<br>
		<br>
		<label for="fuzzy">fuzzy</label>
		<input type="radio" name="querytype" id="fuzzy" value="fuz" checked />
		<label for="precise">precise</label>
		<input type="radio" name="querytype" id="precise" value="pre" />
		<br>
		<br>
		<input type="submit" value="QUERY" />
	</form>

	<br>
	<a href="service/return.action">Return to Main Page</a>
	<br>
	<br>
	<img src="images/flower.png" id="flower" />
</body>
</html>