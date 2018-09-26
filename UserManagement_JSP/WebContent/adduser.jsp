<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/theme.css">
<script type="text/javascript" src="jscript/script.js" charset="utf-8"></script>
<title>Add a New User</title>
</head>
<body>
	<%
		int userGrade = (int) session.getAttribute("grade");
	%>
	<img src=imgs/logo.png id="logo" />
	<hr>

	<h1>Add User</h1>

	<form action="UserClServlet?flag=addUser" method="post" onsubmit="return addConfirm();">
		<table border="1" id="table">
			<tr>
				<td>
					<label for="userId">用户编号</label>
				</td>
				<td>
					<input type="number" min="1" name="userId" id="userId" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="username">用户名</label>
				</td>
				<td>
					<input type="text" name="username" id="username" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="passwd">用户密码</label>
				</td>
				<td>
					<input type="text" name="passwd" id="passwd" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="email">电子邮件</label>
				</td>
				<td>
					<input type="text" name="email" id="email" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="grade">用户级别</label>
				</td>
				<td>
					<input type="number" name="grade" id="grade" min="1" max="10" />
				</td>
			</tr>

		</table>
		<br>
		<input type="submit" value="ADD" />
		<input type="reset" value="RESET" />
	</form>
	<br>
	<a href="main.jsp">Return to Main Page</a>
	<br>
	<br>
	<img src="imgs/flower.png" id="flower" />
</body>
</html>