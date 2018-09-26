package com.bit;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// 验证Session或Cookie中是否有用户信息

public class VerifyUser {
	
	public static boolean verify(HttpServletRequest req, HttpServletResponse res, int pageLevel) {
		
		boolean verify = false;
		
		// 得到session
		HttpSession hs = req.getSession(true);
		String myName = (String) hs.getAttribute("uname");
		String name = null;
		String passwd = null;
		
		// 判断
		if (myName == null) {
			// 如果session中没有用户信息，再查看cookie
			// 从客户端得到所有cookie信息
			Cookie[] allCookie = req.getCookies();
			// 如果cookie不为空
			if (allCookie != null) {
				// 依次取出
				for (int j = 0; j < allCookie.length; j++) {
					Cookie temp  = allCookie[j];
					if (temp.getName().equals("username")) {
						name = temp.getValue();
					} else if (temp.getName().equals("password")) {
						passwd = temp.getValue();
					}
				}
				if (name != null && passwd != null) {
					// 跳转到loginCl去验证
					try {
						res.sendRedirect("loginCl?username=" + name + "&password=" + passwd);
						return verify;
					} catch (IOException e) {
						e.printStackTrace();
					};
				}
			}			
			try {
				res.sendRedirect("login?info=IllegallyAccess");
				return verify;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String ugrd = (String) hs.getAttribute("ugrd");
			if (Integer.parseInt(ugrd) >= pageLevel) {
				verify = true;
			} else {
				try {
					res.sendRedirect("login?info=unauthorized");
					return verify;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return verify;
	}
}
