package com.bit;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 操作成功界面

public class Ok extends HttpServlet {

	private static final long serialVersionUID = -3126904755132734392L;
	
	private static final int pageLevel = 1;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		if (!VerifyUser.verify(req, res, pageLevel)) {
			return;
		}
		
		String operation = req.getParameter("operation");
		
		try {
			
			res.setContentType("text/html;charset=utf-8");
			PrintWriter pw = res.getWriter();
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<link rel='stylesheet' type='text/css' href='css/theme.css'>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<img src=imgs/logo.png id='logo'><hr>");
			
			if (operation == null) {
				pw.println("<h1>Ooops, No Operation has been executed.</h1>");
			} else {
				pw.println("<h1>Congratulations! Operation " + operation + " succeeded.</h1>");
			}
			
			pw.println("<a href=management>Return to Management Page</a><br><br>");
			pw.println("<a href=main>Return to Main Page</a>");
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
