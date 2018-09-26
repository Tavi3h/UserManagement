package com.bit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 用户验证

public class LoginCl extends HttpServlet {

	private static final long serialVersionUID = 1697078291514090557L;
	@Override
	public void init() {
		BufferedReader bufr = null;
		// 读取原次数 
		try {
			bufr = new BufferedReader(
					new FileReader("D:\\Program Files\\apache-tomcat-9.0.0.M26\\webapps\\UserManagement_Servlet\\files\\Counter.txt"));
			String numval = bufr.readLine();
			// 将times值放入ServletContext中
			this.getServletContext().setAttribute("visitTimes", Integer.parseInt(numval));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void destroy() {
		// 再将新的次数写回
		BufferedWriter bufw = null;
		try {
			bufw = new BufferedWriter(
					new FileWriter("D:\\Program Files\\apache-tomcat-9.0.0.M26\\webapps\\UserManagement_Servlet\\files\\Counter.txt"));
			bufw.write(this.getServletContext().getAttribute("visitTimes").toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	// 处理get请求
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		/*
		 *  接收浏览器提交的信息：用户名和密码
		 *  html对应元素中的name属性
		 *  之后连接数据库进行验证 
		 */			 
		try {
			String u = req.getParameter("username");
			String p = req.getParameter("password");

			// 调用UserBeanCl
			UserBeanCl ubc = new UserBeanCl();

			// 使用UserBeanCl的checkUser()方法进行验证
			if (ubc.checkUser(u, p)) {
				String grd = ubc.getGrade(u, p);
				/*
				 * 使用cookie将用户名和密码保存在客户端
				 */
				String keep = req.getParameter("keep");
				// 如果用户勾选了保存cookie的复选框，则保存cookie
				if (keep != null) {
					// 创建cookie
					Cookie name = new Cookie("username", u);
					Cookie psw = new Cookie("password", p);
					// 设置cookie存在时间
					name.setMaxAge(14 * 24 * 3600);
					psw.setMaxAge(14 * 24 * 3600);
					// 回写到客户端
					res.addCookie(name);
					res.addCookie(psw);
				}

				HttpSession hs = req.getSession(true);
				// 将验证成功的信息写入session
				hs.setAttribute("uname", u);
				hs.setAttribute("ugrd", grd);
				// 设置session存在时间为10分钟。
				hs.setMaxInactiveInterval(600);
				
				// 将ServletContext中的visitTime所对应的值取出并自增
				int times = (int) this.getServletContext().getAttribute("visitTimes");
				this.getServletContext().setAttribute("visitTimes", ++times);
				
				// 跳转到欢迎页面
				res.sendRedirect("main");
			} else {
				// 不合法，跳转回登录页面
				// 这里的名字是该页面的url名
				res.sendRedirect("login?info=IdentityError");
			}

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
