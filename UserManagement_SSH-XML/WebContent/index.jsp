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
<title>Index: Login Page</title>
</head>
<body>
	<img src=images/logo.png id="logo" />
	<h1>Control Panel Login</h1>
	<h2>${msg}${param.errmsg}<s:fielderror /></h2>
	<br>
	<br>
	<form action="service/login.action" method="post" onSubmit="return loginCheck(this);">
		<table border="1" id="table">
			<tr>
				<td>
					<label for="username">Username</label>
				</td>
				<td>
					<input type="text" class="input" name="username" id="username" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="passwd">Password</label>
				</td>
				<td>
					<input type="password" class="input" name="passwd" id="passwd" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="vcode">ValidCode</label>
				</td>
				<td>
					<input type="text" class="input" name="vcode" id="vcode" maxlength="5" />
					<img title="Click to change" src="util/valcode.action" id="validcode" onclick="refresh()" />
				</td>
			</tr>
		</table>
		<br>
		<label for="checkbox">Keep cookie for 2 weeks</label>
		<input type="checkbox" id="checkbox" name="cookie" value="keepCookie" />
		<br>
		<br>
		<input type="submit" value="LOGIN" />
		<input type="reset" value="RESET" />
	</form>
</body>
</html>