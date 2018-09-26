package com.tavish.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tavish.model.UserBeanCl;

/*
 * Servlet implementation class LoginClServlet
 * 一个控制器类：完成对用户身份的验证
 * 控制器本身不完成业务逻辑，它主要是调用model来完成对数据的处理。
 */
@WebServlet("/LoginClServlet")
public class LoginClServlet extends HttpServlet {

	private static final long serialVersionUID = -7439485721040340168L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String u = request.getParameter("username");
		String p = request.getParameter("password");
		
		// 获得用户输入的验证码及后台生成的验证码并比对
		HttpSession hs = request.getSession(true);
		
		String type = request.getParameter("type");
		if (type == null) {
			String valCode = (String) hs.getAttribute("valCode");
			String vcode = request.getParameter("vcode");
			hs.removeAttribute("valCode");
			
			if (!valCode.toLowerCase().equals(vcode.toLowerCase())) {
				hs.setAttribute("err", "WrongValCode");
				response.sendRedirect("ErrInfoServlet");
				return;
			}
		}
		
		
		// 完成对用户对用户的验证
		UserBeanCl ubc = new UserBeanCl();
		if (ubc.checkUser(u, p)) {
			int grd = ubc.getGrade(u, p);
			String cookie = request.getParameter("cookie");
			if (cookie != null) {
				// 写入cookie
				Cookie name = new Cookie("username", u);
				Cookie psw = new Cookie("password", p);
				// 设置cookie存在时间
				name.setMaxAge(14 * 24 * 3600);
				psw.setMaxAge(14 * 24 * 3600);
				// 回写到客户端
				response.addCookie(name);
				response.addCookie(psw);
				System.out.println("cookie写入成功");
			}

			// 将用户名和用户级别存入Session
			hs.setAttribute("username", u);
			hs.setAttribute("grade", grd);
			hs.setMaxInactiveInterval(600);
			System.out.println("session写入成功");

			response.sendRedirect("main.jsp");
			return;
		} else {
			hs.setAttribute("err", "WrongUserInfo");
			response.sendRedirect("ErrInfoServlet");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
