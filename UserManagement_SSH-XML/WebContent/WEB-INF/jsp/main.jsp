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
<title>Main</title>
</head>
<body>
	<img src=images/logo.png id="logo" />
	<hr>
	<h1>Main Page</h1>
	<c:choose>
		<c:when test="${usergrade == 10}">
			<h2>
				Hello, Super Administrator
				<em>${username}</em>
			</h2>
		</c:when>
		<c:when test="${usergrade == 5}">
			<h2>
				Hello, Administrator
				<em>${username}</em>
			</h2>
		</c:when>
		<c:otherwise>
			<h2>
				Hello, User
				<em>${username}</em>
			</h2>
		</c:otherwise>
	</c:choose>

	<c:if test="${usergrade == 10}">
		<a href="service/manage.action">管理用户</a>
		<br>
		<br>
	</c:if>

	<c:if test="${usergrade >= 5}">
		<a href="service/add_setUserInfo.action">添加用户</a>
		<br>
		<br>
	</c:if>

	<a href="service/query_queryInfo.action">查询用户</a>
	<br>
	<br>
	<a href="service/delCookie.action">删除Cookie</a>
	<br>
	<br>
	<a href="service/logoff.action">退出登录</a>
	<br>
	<br>
	<img src="images/flower.png" id="flower" />
</body>
</html>