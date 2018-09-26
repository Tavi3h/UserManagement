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
<title>Operation Success</title>
</head>
<body>
	<img src=images/logo.png id="logo" />
	<hr>

	<h1>Operation Success</h1>
	<c:if test="${usergrade == 10}">
		<a href="service/manage.action">Return to Management Page</a>
	</c:if>
	<br>
	<br>
	<a href="service/return.action">Return to Main Page</a>
	<br>
	<br>
	<br>
	<img src="images/flower.png" id="flower" />
</body>
</html>