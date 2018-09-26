<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/theme.css">
<script type="text/javascript" src="jscript/script.js" charset="utf-8"></script>
<title>Update User</title>
</head>
<body>
	<%
		int userGrade = (int) session.getAttribute("grade");
	%>
	<img src=imgs/logo.png id="logo" />
	<hr>

	<h1>Update User</h1>
	<% 
		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");
		if (userId == null) {
			session.setAttribute("err", "DataErr");
			response.sendRedirect("ErrInfoServlet");
			return;
		}
	%>

	<form action="UserClServlet?flag=updateUser" method="post" onsubmit="return updateConfirm();">
		<table border="1" id="table">
			<tr>
				<td>
					<label for="userId">用户编号</label>
				</td>
				<td>
					<input type="number" min="1" name="userId" id="userId" value=<%=userId%> readonly />
				</td>
			</tr>
			<tr>
				<td>
					<label for="username">用户名</label>
				</td>
				<td>
					<input type="text" name="username" id="username" value=<%=username%> />
				</td>
			</tr>
			<tr>
				<td>
					<label for="passwd">用户密码</label>
				</td>
				<td>
					<input type="text" name="passwd" id="passwd" value=<%=passwd%> />
				</td>
			</tr>
			<tr>
				<td>
					<label for="email">电子邮件</label>
				</td>
				<td>
					<input type="text" name="email" id="email" value=<%=email%> />
				</td>
			</tr>
			<tr>
				<td>
					<label for="grade">用户级别</label>
				</td>
				<td>
					<input type="text" name="grade" id="grade" value=<%=grade%> />
				</td>
			</tr>

		</table>
		<br>
		<input type="submit" value="UPDATE" />
		<input type="reset" value="RESET" />
	</form>
	<br>
	<a href="main.jsp">Return to Main Page</a>
	<br>
	<br>
	<img src="imgs/flower.png" id="flower" />
</body>
</html>