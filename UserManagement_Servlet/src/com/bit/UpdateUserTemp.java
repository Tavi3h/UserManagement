package com.bit;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserTemp extends HttpServlet {

	private static final long serialVersionUID = 2984159151945921262L;
	
	
	private static final int pageLevel = 10;
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		if (!VerifyUser.verify(req, res, pageLevel)) {
			return;
		}
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			
			pw = res.getWriter();
			
			String id = req.getParameter("userId");
			String username = req.getParameter("username");
			String passwd = req.getParameter("passwd");
			String email = req.getParameter("email");
			String grade = req.getParameter("grade");
			
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<link rel='stylesheet' type='text/css' href='css/theme.css'>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<img src=imgs/logo.png id='logo'><br>");
			pw.println("<h1>Update User</h1>");
			
			pw.println("<form action=UpdateUserCl method=post>");
			pw.println("<label for='userId'>userId&nbsp;&nbsp;:&nbsp;<input readonly type=text id='userId' name=userId value="+id+"></label><br>");
			pw.println("<label for='username'>username:&nbsp;<input readonly type=text id='username' name=username value="+username+"></label><br>");
			pw.println("<label for='passwd'>passwd&nbsp;&nbsp;:&nbsp;<input type=text id='passwd' name=passwd value="+passwd+"></label><br>");
			pw.println("<label for='email'>email&nbsp;&nbsp;&nbsp;:&nbsp;<input type=text id='email' name=email value="+email+"></label><br>");
			pw.println("<label for='grade'>grade&nbsp;&nbsp;&nbsp;:&nbsp;<input type=text id='grade' name=grade value="+grade+"></label><br>");
			pw.println("<br><br><input type=submit value=UPDATE>");
			pw.println("<input type=reset value=RESET><br>");
			pw.println("</form>");
			pw.println("<br><br><br><a href=management>Return to Management Page</a>");
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
