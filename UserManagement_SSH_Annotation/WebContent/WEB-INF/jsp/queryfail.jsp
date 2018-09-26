<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Query Fail</title>
</head>
<body>
	<img src=images/logo.png id="logo" />
	<hr>
	<h1>Sorry, We Found Nothing...</h1>
	<br>
	<br>
	<br>
	<a href="service/query_queryInfo.action">Back to Query Page</a>
	<br>
	<br>
	<br>
	<img src="images/flower.png" id="flower" />
</body>
</html>