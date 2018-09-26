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
	<h1>Query Result</h1>
	<table border="1" id="table">
		<tr>
			<td>用户id</td>
			<td>用户名</td>
			<td>用户密码</td>
			<td>电子邮件</td>
			<td>用户级别</td>
		</tr>
		<c:forEach var="user" items="${queryResult}">
			<tr>
				<td>${user.userId}</td>
				<td>${user.username}</td>
				<td>${usergrade == 10 ? user.passwd : "******"}</td>
				<td>${user.email}</td>
				<td>${user.grade}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${querytype eq 'fuz'}">
		<c:set var="upper" value="${pageNumber + 4 <= pageCount ? pageNumber + 4 : pageCount}" scope="page" />
		<c:set var="lower" value="${pageNumber - 4 >= 1 ? pageNumber - 4 : 1}" scope="page" />
		<br>
	当前页: ${pageNumber}&nbsp;&nbsp;总页数: ${pageCount}
	<br>
		<br>
		<a href="service/query_queryUser.action?pageNumber=1&querytype=fuz&username=${username}">首页</a>
		<c:if test="${pageNumber != 1}">
			<a href="service/query_queryUser.action?pageNumber=${pageNumber-1}&querytype=fuz&username=${username}">上一页</a>
		</c:if>
		<c:forEach var="i" begin="${lower}" end="${upper}" step="1">
			<a href="service/query_queryUser.action?pageNumber=${i}&querytype=fuz&username=${username}">[${i}]</a>
		</c:forEach>
		<c:if test="${pageNumber != pageCount}">
			<a href="service/query_queryUser.action?pageNumber=${pageNumber + 1}&querytype=fuz&username=${username}">下一页</a>
		</c:if>
		<a href="service/query_queryUser.action?pageNumber=${pageCount}&querytype=fuz&username=${username}">尾页</a>
		<br>
		<br>
		<form action="service/query_queryUser.action" method="post">
			<label for="goPage"> Skip to:</label>
			<input type="number" name="pageNumber" id="goPage" min="1" max="${pageCount}" />
			<input type="hidden" name="username" value="${username}" />
			<input type="hidden" name="querytype" value="fuz" />
			<input type="submit" value="Go" />
		</form>
	</c:if>

	<br>
	<a href="service/query_queryInfo.action">Return to Query Page</a>
	<br>
	<br>
	<img src="images/flower.png" id="flower" />
</body>
</html>