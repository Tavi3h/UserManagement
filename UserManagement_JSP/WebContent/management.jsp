<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.tavish.model.UserBean"%>
<%@page import="com.tavish.model.UserBeanCl"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/theme.css">
<script type="text/javascript" src="jscript/script.js" charset="utf-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	<img src=imgs/logo.png id="logo">
	<hr>

	<%
		int userGrade = (int) session.getAttribute("grade");
		String u = (String) session.getAttribute("username");
	%>

	<h1>
		Congratulations!<%=u%></h1>

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
		<%
			// 从request中取得信息
			@SuppressWarnings("unchecked")
			ArrayList<UserBean> al = (ArrayList<UserBean>) request.getAttribute("queryResult");
			if (al == null) {
				session.setAttribute("err", "DataErr");
				response.sendRedirect("ErrInfoServlet");
				return;
			}
			Iterator<UserBean> it = al.iterator();
			while (it.hasNext()) {
				UserBean ub = it.next();
				int userId = ub.getUserId();
				String username = ub.getUsername();
				String passwd = ub.getPasswd();
				String email = ub.getEmail();
				int grade = ub.getGrade();
		%>
		<tr>
			<td><%=userId%></td>
			<td><%=username%></td>
			<td><%=passwd%></td>
			<td><%=email%></td>
			<td><%=grade%></td>
			<td>
				<a href="updateuser.jsp?userId=<%=userId%>&username=<%=username%>&passwd=<%=passwd%>&email=<%=email%>&grade=<%=grade%>">修改用户</a>
			</td>
			<td>
				<a href="UserClServlet?flag=delUser&userId=<%=userId%>" onclick="return delConfirm();">删除用户</a>
			</td>
		</tr>
		<%
			}
		%>

	</table>
	<br>

	<!-- 显示超链接 -->
	<%
		int pageNow = (int) request.getAttribute("pageNow");
		int pageCount = (int) request.getAttribute("pageCount");
		out.println("当前页: " + pageNow + " 总页数: " + pageCount + "<br><br>");

		int upper = pageNow + 4 <= pageCount ? pageNow + 4 : pageCount;
		int lower = pageNow - 4 >= 1 ? pageNow - 4 : 1;

		out.println("<a href=UserClServlet?flag=manage&toPage=1>首页</a>");
		if (pageNow != 1) {
			out.println("<a href=UserClServlet?flag=manage&toPage=" + (pageNow - 1) + ">上一页</a>");
		}
		for (int i = lower; i <= upper; i++) {
			out.println("<a href=UserClServlet?flag=manage&toPage=" + i + ">[" + i + "]</a>");
		}
		if (pageNow != pageCount) {
			out.println("<a href=UserClServlet?flag=manage&toPage=" + (pageNow + 1) + ">下一页</a>");
		}
		out.println("<a href=UserClServlet?flag=manage&toPage=" + pageCount + ">尾页</a>");
	%>
	<br>
	<br>
	<form action="UserClServlet">
		<label for="goPage">
			Skip to:
			<input type="number" name="toPage" id="goPage" min="1" max="<%=pageCount%>" />
			<input type="hidden" name="flag" value="manage" />
		</label>
		<input type="submit" value="Go" />
	</form>

	<br>
	<a href="main.jsp">Return to Main Page</a>
	<br>
	<br>
	<a href="LogoffClServlet">Return to Login</a>
	<br>
	<br>
	<img src="imgs/flower.png" id="flower" />

</body>
</html>