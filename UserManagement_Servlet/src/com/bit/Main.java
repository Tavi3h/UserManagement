package com.bit;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 主界面
public class Main extends HttpServlet {

	private static final long serialVersionUID = 6143442858998120479L;
	
	private static final int pageLevel = 1;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		if (!VerifyUser.verify(req, res, pageLevel)) {
			return;
		}
		
		try {
			
			HttpSession hs = req.getSession(true);
			String ugrd = (String) hs.getAttribute("ugrd");
			int grd = Integer.parseInt(ugrd);
			String uname = (String) hs.getAttribute("uname");
			
			res.setContentType("text/html;charset=utf-8");
			
			PrintWriter pw = res.getWriter();
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<link rel='stylesheet' type='text/css' href='css/theme.css'>");
			pw.println("</style>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<img src=imgs/logo.png id='logo'><hr>");
			
			pw.println("<h1>Main Interface</h1>");
			pw.println("<h2>Welcome,");
			if (grd == 10) {
				pw.println(" Super Administrator <em>" + uname + "</em>!");
			} else if (grd >= 5) {
				pw.println(" Administrator <em>" + uname + "</em>!");
			} else {
				pw.println(" User <em>" + uname + "</em>!");
			}
			pw.println("</h2>");
			if (grd == 10) {
				pw.println("<a href=management>MANAGE USERS</a><br><br>");
			}
			if (grd >= 5) {
				pw.println("<a href=addusertemp>ADD USERS</a><br><br>");
			}
			pw.println("<a href=queryusertemp>QUERY USERS</a><br><br>");
			pw.println("<a href=login?info=logoff>LOG OFF</a><br>");
			
			/* **********		读取网页计数	    ********** */
			int times = (int) this.getServletContext().getAttribute("visitTimes");
			pw.println("<br>访问次数为" + times + "次<br><br>");
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
