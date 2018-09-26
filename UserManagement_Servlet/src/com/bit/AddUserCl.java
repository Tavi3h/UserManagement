package com.bit;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserCl extends HttpServlet {

	private static final long serialVersionUID = -7664029178425622076L;

	private static final int pageLevel = 5;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		if (!VerifyUser.verify(req, res, pageLevel)) {
			return;
		}
		 
		
		UserBean ub = new UserBean();
		String userId = req.getParameter("userId");
		String username = req.getParameter("username");
		String passwd = req.getParameter("passwd");
		String email = req.getParameter("email");
		String grade = req.getParameter("grade");
		
		ub.setUserId(Integer.parseInt(userId));
		ub.setUsername(username);
		ub.setPasswd(passwd);
		ub.setEmail(email);
		ub.setGrade(Integer.parseInt(grade));
		
		UserBeanCl ubc = new UserBeanCl();
		try {
			if (ubc.addUser(ub)) {
				res.sendRedirect("ok?operation=Add");
			} else {
				res.sendRedirect("err?operation=Add");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		doGet(req, res);
	}
}
