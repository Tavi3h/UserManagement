<%@page import="java.util.Iterator"%>
<%@page import="com.tavish.model.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/theme.css">
<title>Query Result</title>
</head>
<body>
	<img src=imgs/logo.png id="logo" />
	<hr>
	<h1>Query Result</h1>
	<%
		// 取得信息
		int userGrade = (int) session.getAttribute("grade");
		String username = request.getParameter("username");
		String qType = request.getParameter("qType");
		@SuppressWarnings("unchecked")
		ArrayList<UserBean> al = (ArrayList<UserBean>) request.getAttribute("queryResult");
		if (al == null) {
			session.setAttribute("err", "DataErr");
			response.sendRedirect("ErrInfoServlet");
			return;
		}
		if (al.size() == 0) {
			out.println("<h2>No Result ... </h2>");
		} else {
	%>
	<table border="1" id="table">
		<tr>
			<td>用户id</td>
			<td>用户名</td>
			<td>用户密码</td>
			<td>电子邮件</td>
			<td>用户级别</td>
		</tr>
		<%
			Iterator<UserBean> it = al.iterator();
				while (it.hasNext()) {
					UserBean ub = it.next();
					int userId = ub.getUserId();
					String userName = ub.getUsername();
					String passwd = userGrade == 10 ? ub.getPasswd() : "Encrypted";
					String email = ub.getEmail();
					int grade = ub.getGrade();
		%>
		<tr>
			<td><%=userId%></td>
			<td><%=userName%></td>
			<td><%=passwd%></td>
			<td><%=email%></td>
			<td><%=grade%></td>
		</tr>
		<%
			}
			}
		%>
	</table>
	<%
		int pageNow = (int) request.getAttribute("pageNow");
		int pageCount = (int) request.getAttribute("pageCount");
	%>
	<%
		if (pageCount != 0) {
			int upper = pageNow + 4 <= pageCount ? pageNow + 4 : pageCount;
			int lower = pageNow - 4 >= 1 ? pageNow - 4 : 1;
	%>
	<p>
		当前页:
		<%=pageNow%>&nbsp;&nbsp;总页数:<%=pageCount%>
	<p>
		<a href="UserClServlet?qType=<%=qType%>&username=<%=username%>&flag=queryUser&toPage=1">首页</a>
		<%
			if (pageNow != 1) {
		%>
		<a href="UserClServlet?qType=<%=qType%>&username=<%=username%>&flag=queryUser&toPage=<%=pageNow - 1%>">上一页</a>
		<%
			}
		%>
		<%
			for (int i = lower; i <= upper; i++) {
		%>
		<a href="UserClServlet?qType=<%=qType%>&username=<%=username%>&flag=queryUser&toPage=<%=i%>">
			[<%=i%>]
		</a>
		<%
			}
		%>
		<%
			if (pageNow != pageCount) {
		%>
		<a href="UserClServlet?qType=<%=qType%>&username=<%=username%>&flag=queryUser&toPage=<%=pageNow + 1%>">下一页</a>
		<%
			}
		%>
		<a href="UserClServlet?qType=<%=qType%>&username=<%=username%>&flag=queryUser&toPage=<%=pageCount%>">尾页</a>
		<br>
	<form action="UserClServlet?flag=queryUser" method="post">
		<label for="goPage"> Skip to:</label>
		<input type="number" name="toPage" id="goPage" min="1" max="<%=pageCount%>" />
		<input type="hidden" name="username" value=<%=username%> />
		<input type="hidden" name="qType" value=<%=qType%> />
		<input type="submit" value="Go" />
	</form>
	<%
		}
	%>
	<br>
	<a href="queryuser.jsp">Return to Query Page</a>
	<br>
	<br>
	<img src="imgs/flower.png" id="flower" />
</body>
</html>