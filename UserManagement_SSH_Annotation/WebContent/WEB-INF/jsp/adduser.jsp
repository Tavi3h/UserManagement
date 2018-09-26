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
<title>AddUser Page</title>
</head>
<body>
	<img src=images/logo.png id="logo" />
	<hr>

	<h1>Add User</h1>
	<br>
	<s:fielderror />
	<br>
	<form action="service/add_addUser.action" method="post" onsubmit="return addConfirm();">
		<table border="1" id="table">
			<tr>
				<td>
					<label for="username">用户名</label>
				</td>
				<td>
					<input type="text" name="user.username" id="username" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="passwd">用户密码</label>
				</td>
				<td>
					<input type="password" name="user.passwd" id="passwd" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="passwd2">密码确认</label>
				</td>
				<td>
					<input type="password" name="passwdconfirm" id="passwd2" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="email">电子邮件</label>
				</td>
				<td>
					<input type="text" name="user.email" id="email" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="grade">用户级别</label>
				</td>
				<td>
					<input type="number" name="user.grade" id="grade" min="1" max="10" />
				</td>
			</tr>

		</table>
		<br>
		<input type="submit" value="ADD" />
		<input type="reset" value="RESET" />
	</form>
	<br>
	<a href="service/return.action">Return to Main Page</a>
	<br>
	<br>
	<img src="images/flower.png" id="flower" />
</body>
</html>