package com.bit;
// 暂不使用的Servlet
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuerUserCl extends HttpServlet {

	private static final long serialVersionUID = -2374116999751401035L;
	
	private static final int pageLevel = 1;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		if (!VerifyUser.verify(req, res, pageLevel)) {
			return;
		}
		
		String username = req.getParameter("username");
		String queryType = req.getParameter("qType");
		UserBeanCl ubc = null;
		PrintWriter pw = null;
		ArrayList<UserBean> al = null;
		if (username.equals("")) {
			try {
				res.sendRedirect("err?operation=Query");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		res.setContentType("text/html;charset=utf-8");
		
		int pageNow = 1;
		try {
			String toPage = req.getParameter("toPage");
			if (toPage != null) {
				pageNow = Integer.parseInt(toPage);
			} 
		} catch (Exception e) {
			
		}
		
		try {
			pw = res.getWriter();
			String ugrd = (String) req.getSession(true).getAttribute("ugrd");
			int grd = Integer.parseInt(ugrd);
			ubc = new UserBeanCl();
			al = ubc.queryUserByName(username, queryType, pageNow);
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<link rel='stylesheet' type='text/css' href='css/theme.css'>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<img src=imgs/logo.png id='logo'><br>");
			pw.println("<h1>Query Result</h1>");
			
			if (al.size() == 0) {
				
				pw.println("<h2><em>No Result.</em></h2>");
				
			} else {
				
				pw.println("<table border=1 id='table'>");
				pw.println("<tr bgcolor='pink'><th>id</th><th>name</th>");
				if (grd == 10) {
					pw.println("<th>pwd</th>");
				}				
				pw.println("<th>email</th><th>grade</th></tr>");
				Iterator<UserBean> it = al.iterator();
				while (it.hasNext()) {
					UserBean ub = it.next();
					pw.println("<tr>");
					pw.println("<td>" + ub.getUserId() + "</td>");
					pw.println("<td>" + ub.getUsername() + "</td>");
					if (grd == 10) { 
						pw.println("<td>" + ub.getPasswd() + "</td>");
					}
					pw.println("<td>" + ub.getEmail() + "</td>");
					pw.println("<td>" + ub.getGrade() + "</td>");
					pw.println("</tr>");
				}
				pw.println("</table><br>");
				pw.println("Current page is " + pageNow + "<br><br>");
				
				
				int pageCount = ubc.getPageCount();

				if (pageNow != 1)
					pw.println("<a href=QuerUserCl?toPage=" + (pageNow - 1) + "&username="+username+"&qType="+queryType+">" + "Previous" + "</a>");
				int maxPage = pageNow + 4;
				if (maxPage <= pageCount) {
					for (int i = pageNow; i <= maxPage; i++) {
						pw.println("<a href=QuerUserCl?toPage=" + i + "&username="+username+"&qType="+queryType+">" + i + "</a>");
					}
				} else {
					for (int i = pageNow; i <= pageCount; i++) {
						pw.println("<a href=QuerUserCl?toPage=" + i + "&username="+username+"&qType="+queryType+">" + i + "</a>");
					}
				}			
				if (pageNow != pageCount)
					pw.println("<a href=QuerUserCl?toPage=" + (pageNow + 1) + "&username="+username+"&qType="+queryType+">" + "Next" + "</a>");
				
				pw.println("<br><form action=QuerUserCl method=post>");
				pw.println("<br><label for='goPage'>Skip to:<input type=number name=toPage id='goPage' min='1' max="+pageCount+"></label>");
				pw.println("<input type=hidden name=username value="+username+">");
				pw.println("<input type=hidden name=qType value="+queryType+">");
				pw.println("<input type=submit value=GO>");
				pw.println("</form>");
				
			}
			pw.println("<br><br><a href=queryusertemp>Return to Query Page</a>");
			pw.println("<br><br><a href=main>Return to Main Page</a>");
			pw.println("<br><br><img id='flower' src=imgs/flower.png>");
			pw.println("</body>");
			pw.println("</html>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		doGet(req, res);
	}

}
