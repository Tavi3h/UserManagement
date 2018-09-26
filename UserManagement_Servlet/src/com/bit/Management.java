package com.bit;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 欢迎界面

public class Management extends HttpServlet {

	private static final long serialVersionUID = -4103671143322287542L;
	
	private static final int pageLevel = 10;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		if (!VerifyUser.verify(req, res, pageLevel)) {
			return;
		}

		PrintWriter pw = null;
		UserBeanCl ubc = null;
		
		res.setContentType("text/html;charset=utf-8");

		
		HttpSession hs = req.getSession(true);
		String myName = (String) hs.getAttribute("uname");

		try {	
			
			/* **********		 增加分页功能	    ********** */
			
			int pageSize = 10; // 一页显示10条记录
			int pageNow = 1; // 希望显示第几页
			
			
			// 动态的接收pageNow
			String sPageNow = req.getParameter("toPage");
			try {
				if (sPageNow != null) {
					pageNow = Integer.parseInt(sPageNow);
				}
			} catch (Exception e) {
				
			}


			/* **********		 构建HTML页面	    ********** */
			
			pw = res.getWriter();
			pw.println("<html>");
			pw.println("<head>");
			// 设置一个简单的css
			pw.println("<link rel='stylesheet' type='text/css' href='css/theme.css'>");
			pw.println("</head>");
			pw.println("<body>");

			// 在servlet中显示图片
			pw.println("<img src=imgs/logo.png id='logo'><br>");

			// 输出一个welcome
			pw.println("<h1>Manage Users</h1>");
			pw.println("Welcome Administrator " + "<em>" + myName + "</em>" + "<img src=imgs/1.png width='30px' height='30px'><br><br><br>");
			
			// 显示表格
			pw.println("<table border=1 id='table'>");
			pw.println("<tr bgcolor='pink'><th>id</th><th>name</th><th>pwd</th><th>email</th><th>grade</th><th>update user</th><th>delete user</th></tr>");
			// 调用UserBeanCl
			ubc = new UserBeanCl();			
			ArrayList<UserBean> al = ubc.getResultByPage(pageNow, pageSize);
			Iterator<UserBean> it = al.iterator();
			while (it.hasNext()) {
				UserBean ub = it.next();
				pw.println("<tr>");
				pw.println("<td>" + ub.getUserId() + "</td>");
				pw.println("<td>" + ub.getUsername() + "</td>");
				pw.println("<td>" + ub.getPasswd() + "</td>");
				pw.println("<td>" + ub.getEmail() + "</td>");
				pw.println("<td>" + ub.getGrade() + "</td>");
				pw.println("<td><a href=updateusertemp?userId="+ub.getUserId()
						+"&username="+ub.getUsername()+"&passwd="+ub.getPasswd()
						+"&email="+ub.getEmail()+"&grade="+ub.getGrade()+">update</a></td>");
				pw.println("<td><a href=delusercl?userId=" + ub.getUserId() +
						" onclick= \"return window.confirm('Do you really want to delete this user?')\">delete</a></td>");
				pw.println("</tr>");
			}			
			pw.println("</table><br>");
			
			pw.println("Current page is " + pageNow + "<br>");
			
			/* **********		 显示超链接	    ********** */	
			int pageCount = ubc.getPageCount();
			
			pw.println("Total Page: " + pageCount + "<br><br>");

			int upper = pageNow + 4 <= pageCount ? pageNow + 4 : pageCount;
			int lower = pageNow - 4 >= 1 ? pageNow - 4 : 1;
			
			pw.println("<a href=management?toPage=1>" + "First" + "</a>");			
			// 当前页不是第一页
			if (pageNow != 1)
				pw.println("<a href=management?toPage=" + (pageNow - 1) + ">" + "Previous" + "</a>");
			for (int i = lower; i <= upper; i++) {
				pw.println("<a href=management?toPage=" + i + ">" + i + "</a>");
			}	
			if (pageNow != pageCount)
				pw.println("<a href=management?toPage=" + (pageNow + 1) + ">" + "Next" + "</a>");
			pw.println("<a href=management?toPage="+pageCount+">" + "Last" + "</a>");	

			// 添加页数跳转功能
			pw.println("<br><form action=management method=post>");
			pw.println("<br><label for='goPage'>Skip to:<input type=number name=toPage id='goPage' min='1' max="+pageCount+"></label>");
			pw.println("<input type=submit value=GO>");
			pw.println("</form>");
			
			pw.println("<br><br><br><a href=main>Return to Main Page</a>");
			
			pw.println("<br><br><br><a href=login?info=logoff>LOG OFF</a>");
			pw.println("<br><img id='flower' src=imgs/flower.png>");
			pw.println("</body>");
			pw.println("</html>");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		this.doGet(req, res);
	}
}
