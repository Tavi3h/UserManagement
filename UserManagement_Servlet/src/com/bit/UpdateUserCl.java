package com.bit;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserCl extends HttpServlet {

	private static final long serialVersionUID = 2984159151945921262L;
	
	private static final int pageLevel = 10;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		if (!VerifyUser.verify(req, res, pageLevel)) {
			return;
		}
		
		UserBean ub = new UserBean();
		String userId = req.getParameter("userId");
		String passwd = req.getParameter("passwd");
		String email = req.getParameter("email");
		String grade = req.getParameter("grade");
		
		ub.setUserId(Integer.parseInt(userId));
		ub.setPasswd(passwd);
		ub.setEmail(email);
		ub.setGrade(Integer.parseInt(grade));
		
		UserBeanCl ubc = new UserBeanCl();
		try {
			if (ubc.updateUser(ub)) {
				res.sendRedirect("ok?operation=Update");
			} else {
				res.sendRedirect("err?operation=Update");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		doGet(req, res);
	}
}
