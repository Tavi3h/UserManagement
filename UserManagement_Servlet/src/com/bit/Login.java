package com.bit;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1988656720460536288L;
	
	// 处理get请求
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			
			res.setContentType("text/html;charset=utf-8");
			
			PrintWriter pw = res.getWriter();
			// 返回登录界面
			pw.println("<html>");
			pw.println("<head>");
			// 设置一个简单的css
			pw.println("<link rel='stylesheet' type='text/css' href='css/theme.css'>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<img src=imgs/logo.png id='logo'><hr>");
			// 得到error信息
			String info = req.getParameter("info");
			if (info != null) {
				if (info.equals("unauthorized")) {
					pw.println("<h1 class='errorInfo'>Unauthorized User ...</h1><br>");
				}
				if (info.equals("IllegallyAccess")) {
					pw.println("<h1 class='errorInfo'>Illegally Access, Login Please ...</h1><br>");
				} 
				if (info.equals("IdentityError")) {
					pw.println("<h1 class='errorInfo'>The user name or password you entered is incorrect ...<h1><br>");
				}
				if (info.equals("logoff")) {
					// 如果点击退出则销毁session
					req.getSession(true).invalidate();
				}				
			}
			pw.println("<h1>Control Panel Login</h1>");
			// 表单提交给url为loginCl的这个Servlet
			pw.println("<form action=loginCl method=post>");
			pw.println("<label for='username'>Username：<input type=text id='username' name=username></label><br><br>");
			pw.println("<label for='passwd'>Password：<input type=password id='passwd' name=password></label><br>");
			pw.println("<br><label for='cookie'>Keep cookie in 2 weeks<input type=checkbox id='cookie' name=keep></label><br><br>");
			pw.println("<input type=submit value=LOGIN><br>");
			pw.println("</form>");
			pw.println("<br><br><img id='flower' src=imgs/flower.png>");
			pw.println("</body>");
			pw.println("</html>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 处理post请求
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		this.doGet(req, res);
	}

}
