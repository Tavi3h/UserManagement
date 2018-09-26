<%@page import="com.tavish.model.ErrInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/theme.css">
<script type="text/javascript" src="jscript/script.js" charset="utf-8"></script>
<title>User Login</title>
</head>
<body>
	<img src=imgs/logo.png id="logo" />
	<hr>
	<h1>Control Panel Login</h1>
	<% 
		String errInfo = (String) session.getAttribute("errInfo");
		if (errInfo != null) {
			out.println("<h2 class='errorInfo'>" + errInfo + "</h2>");
			session.removeAttribute("errInfo");
		}
	%>

	<form action="LoginClServlet" method="post" onSubmit="return loginCheck(this);">
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
					<label for="password">Password</label>
				</td>
				<td>
					<input type="password" class="input" name="password" id="password" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="vcode">ValidCode</label>
				</td>
				<td>
					<input type="text" class="input" name="vcode" id="vcode" maxlength="5" />
					<img title="Click to change" src="ValidCodeServlet" id="validcode" onclick="refresh()" />
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
	<br>
	<br>
	<img src="imgs/flower.png" id="flower" />
</body>
</html>