package com.bit;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserTemp extends HttpServlet {

	private static final long serialVersionUID = 5124093158397863875L;
	
	
	private static final int pageLevel = 5;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		if (!VerifyUser.verify(req, res, pageLevel)) {
			return;
		} 
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = res.getWriter();
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<link rel='stylesheet' type='text/css' href='css/theme.css'>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<img src=imgs/logo.png id='logo'><br>");
			pw.println("<h1>Add User</h1>");
			
			pw.println("<form action=AddUserCl method=post>");
			pw.println("<label for='userId'>userId&nbsp;&nbsp;:&nbsp;<input type=text id='userId' name=userId></label><br>");
			pw.println("<label for='username'>username:&nbsp;<input type=text id='username' name=username></label><br>");
			pw.println("<label for='passwd'>passwd&nbsp;&nbsp;:&nbsp;<input type=text id='passwd' name=passwd></label><br>");
			pw.println("<label for='email'>email&nbsp;&nbsp;&nbsp;:&nbsp;<input type=text id='email' name=email></label><br>");
			pw.println("<label for='grade'>grade&nbsp;&nbsp;&nbsp;:&nbsp;<input type=text id='grade' name=grade></label><br>");
			pw.println("<br><br><input type=submit value=ADD><br>");
			pw.println("</form>");
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
