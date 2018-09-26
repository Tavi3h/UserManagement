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
	<img src=images/logo.png id="logo">
	<hr>
	<h1>Congratulations!&nbsp;${sessionScope.username}</h1>
	<h2>用户信息列表</h2>
	<table border="1" id="table">
		<tr>
			<td>用户id</td>
			<td>用户名</td>
			<td>用户密码</td>
			<td>电子邮件</td>
			<td>用户级别</td>
			<td>修改用户</td>
			<td>删除用户</td>
		</tr>
		<c:forEach var="user" items="${requestScope.queryResult}">
			<tr>
				<td>${user.userId}</td>
				<td>${user.username}</td>
				<td>${user.passwd}</td>
				<td>${user.email}</td>
				<td>${user.grade}</td>
				<td>
					<a href="service/update_acquireUser.action?user.userId=${user.userId}">修改用户</a>
				</td>
				<td>
					<a href="service/delUser.action?userId=${user.userId}" onclick="return delConfirm();">删除用户</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<c:set var="upper" value="${pageNumber + 4 <= pageCount ? pageNumber + 4 : pageCount}" scope="page" />
	<c:set var="lower" value="${pageNumber - 4 >= 1 ? pageNumber - 4 : 1}" scope="page" />
	当前页: ${pageNumber}&nbsp;&nbsp;总页数: ${pageCount}
	<br>
	<br>
	<a href="service/manage.action?pageNumber=1">首页</a>
	<c:if test="${pageNumber != 1}">
		<a href="service/manage.action?pageNumber=${pageNumber-1}">上一页</a>
	</c:if>
	<c:forEach var="i" begin="${lower}" end="${upper}" step="1">
		<a href="service/manage.action?pageNumber=${i}">[${i}]</a>
	</c:forEach>

	<c:if test="${pageNumber != pageCount}">
		<a href="service/manage.action?pageNumber=${pageNumber + 1}">下一页</a>
	</c:if>
	<a href="service/manage.action?pageNumber=${pageCount}">尾页</a>
	<br>
	<br>
	<form action="service/manage.action" method="post">
		<label for="goPage">
			Skip to:
			<input type="number" name="pageNumber" id="goPage" min="1" max="${pageCount}" />
			<input type="hidden" name="flag" value="manage" />
		</label>
		<input type="submit" value="Go" />
	</form>
	<br>
	<a href="service/return.action">Return to Main Page</a>
	<br>
	<br>
	<img src="images/flower.png" id="flower" />
</body>
</html>