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
<title>Update User</title>
</head>
<body>
	<img src=images/logo.png id="logo" />
	<hr>
	<h1>Update User</h1>

	<form action="service/update_updateUser.action" method="post" onsubmit="return updateConfirm();">
		<table border="1" id="table">
			<tr>
				<td>
					<label for="userId">用户编号</label>
				</td>
				<td>
					<input type="number" name="user.userId" id="userId" value="${thisUser.userId}" readonly />
				</td>
			</tr>
			<tr>
				<td>
					<label for="username">用户名</label>
				</td>
				<td>
					<input type="text" name="user.username" id="username" value="${thisUser.username}" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="passwd">用户密码</label>
				</td>
				<td>
					<input type="text" name="user.passwd" id="passwd" value="${thisUser.passwd}" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="email">电子邮件</label>
				</td>
				<td>
					<input type="text" name="user.email" id="email" value="${thisUser.email}" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="grade">用户级别</label>
				</td>
				<td>
					<input type="text" name="user.grade" id="grade" value="${thisUser.grade}" />
				</td>
			</tr>

		</table>
		<br>
		<input type="submit" value="UPDATE" />
		<input type="reset" value="RESET" />
	</form>
	<br>
	<a href="service/manage.action">Return to Manage Page</a>
	<br>
	<br>
	<a href="service/return.action">Return to Main Page</a>
	<br>
	<br>
	<img src="images/flower.png" id="flower" />
</body>
</html>