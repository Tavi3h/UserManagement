package com.bit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryUserTemp extends HttpServlet {

	private static final long serialVersionUID = -3953175627432572010L;

	private static final int pageLevel = 1;

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		if (!VerifyUser.verify(req, res, pageLevel)) {
			return;
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
		
		String username = req.getParameter("username");
		String queryType = req.getParameter("qType");
		
		int grd = 0;
		PrintWriter pw = null;
		UserBeanCl ubc = new UserBeanCl();
		ArrayList<UserBean> al = new ArrayList<>();

		try {
			pw = res.getWriter();
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<link rel='stylesheet' type='text/css' href='css/theme.css'>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<img src=imgs/logo.png id='logo'><br>");
			pw.println("<h1>Query User</h1>");
			
			pw.println("<form action=queryusertemp method=post id='query'>");
			pw.println("<label for='username'>username:&nbsp;<input type=text id='username' name=username></label><br><br><br>");
			pw.println("<label for='fuzzy'>Fuzzy&nbsp;<input type=radio id='fuzzy' name=qType value=fuz checked></label>");
			pw.println("<label for='precise'>Precise&nbsp;<input type=radio id='precise' name=qType value=pre></label><br>");
			pw.println("<br><br><input type=submit value=Query><br><br></form>");

			if (username != null && queryType != null) {
				if (username.equals("")) {
					try {
						res.sendRedirect("err?operation=Query");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					String ugrd = (String) req.getSession(true).getAttribute("ugrd");
					grd = Integer.parseInt(ugrd);

					al = ubc.queryUserByName(username, queryType, pageNow);

					if (al.size() == 0) {

						pw.println("<h2><em>No Result.</em></h2>");

					} else {
						pw.println("<h2>Query Result:</h2>");
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
						pw.println("Current page is " + pageNow + "<br>");

						int pageCount = ubc.getPageCount();
						pw.println("Total Page: " + pageCount + "<br><br>");

						int upper = pageNow + 4 <= pageCount ? pageNow + 4 : pageCount;
						int lower = pageNow - 4 >= 1 ? pageNow - 4 : 1;
						
						pw.println("<a href=queryusertemp?toPage=1&username="+username+"&qType="+queryType+">" + "First" + "</a>");		
						if (pageNow != 1)
							pw.println("<a href=queryusertemp?toPage=" + (pageNow - 1) + "&username=" + username
									+ "&qType=" + queryType + ">" + "Previous" + "</a>");
						for (int i = lower; i <= upper; i++) {
							pw.println("<a href=queryusertemp?toPage=" + i + "&username="+username+"&qType="+queryType+">" + i + "</a>");
						}
						if (pageNow != pageCount)
							pw.println("<a href=queryusertemp?toPage=" + (pageNow + 1) + "&username=" + username
									+ "&qType=" + queryType + ">" + "Next" + "</a>");
						pw.println("<a href=queryusertemp?toPage="+pageCount+"&username="+username+"&qType="+queryType+">" + "Last" + "</a>");	
					
						pw.println("<br><br><form action=queryusertemp id='skipto'>");
						pw.println("<label for='goPage'>Skip to:<input type=number name=toPage id='goPage' min='1' max=" + pageCount + "></label>");
						pw.println("<input type=hidden name=qType value=" + queryType + ">");  
						pw.println("<input type=hidden name=username value=" + username + ">");  
						pw.println("<input type=submit value=GO>");						
						pw.println("</form>");

					}
				}

			}
			pw.println("<br><br><a href=main>Return to Main Page</a>");
			pw.println("<br><br><img id='flower' src=imgs/flower.png>");
			pw.println("</body>");
			pw.println("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		doGet(req, res);
	}
}
