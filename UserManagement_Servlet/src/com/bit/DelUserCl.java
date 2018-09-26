package com.bit;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 处理“删除某个用户”

public class DelUserCl extends HttpServlet {

	private static final long serialVersionUID = 4825369027386139678L;
	
	private static final int pageLevel = 10;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		if (!VerifyUser.verify(req, res, pageLevel)) {
			return;
		}
		
		try {
			// 调用UserBeanCl的删除用户方法
			UserBeanCl ubc = new UserBeanCl();
			if(ubc.delUser(req.getParameter("userId"))) {
				// 删除成功
				res.sendRedirect("ok?operation=Delete");
			} else {
				// 删除失败
				res.sendRedirect("err?operation=Delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		doGet(req, res);
	}
}
