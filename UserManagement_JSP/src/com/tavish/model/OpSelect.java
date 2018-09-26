package com.tavish.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 工具类

public class OpSelect {

	// 处理进入管理页面的分页请求
	public static void manage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNow = 1;
		try {
			String toPage = request.getParameter("toPage");
			pageNow = Integer.parseInt(toPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 调用UserBeanCl
		UserBeanCl ubc = new UserBeanCl();
		ArrayList<UserBean> queryResult = ubc.getResultByPage(pageNow, 10);
		int pageCount = ubc.getPageCount();
		// 将这queryResult、pageCount、pageNow放入request中
		request.setAttribute("queryResult", queryResult);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNow", pageNow);
		// 跳转回welcome.jsp
		request.getRequestDispatcher("management.jsp").forward(request, response);

	}

	// 处理删除用户的请求
	public static void delUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		UserBeanCl ubc = new UserBeanCl();
		if (ubc.deUserById(userId)) {
			// 删除成功
			response.sendRedirect("success.jsp");
		} else {
			// 删除失败
			response.sendRedirect("fail.jsp");
		}
	}

	// 处理添加用户的请求
	public static void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");

		UserBeanCl ubc = new UserBeanCl();
		if (ubc.addUser(userId, username, passwd, email, grade)) {
			// 添加成功
			response.sendRedirect("success.jsp");
		} else {
			// 添加失败
			response.sendRedirect("fail.jsp");
		}
	}

	// 处理根据用户id修改信息的请求
	public static void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");
		UserBeanCl ubc = new UserBeanCl();
		if (ubc.updateUser(userId, username, passwd, email, grade)) {
			// 修改成功
			response.sendRedirect("success.jsp");
		} else {
			// 修改失败
			response.sendRedirect("fail.jsp");
		}
	}

	// 根据用户名查询用户请求
	public static void queryUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String qType = request.getParameter("qType");

		String toPage = request.getParameter("toPage");
		int pageNow = request.getParameter("toPage") == null || request.getParameter("toPage").equals("") ? 1
				: Integer.parseInt(toPage);

		UserBeanCl ubc = new UserBeanCl();
		ArrayList<UserBean> queryResult = ubc.queryUser(username, qType, pageNow);
		int pageCount = ubc.getPageCount();

		request.setAttribute("queryResult", queryResult);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNow", pageNow);

		request.getRequestDispatcher("queryresult.jsp").forward(request, response);
	}
}
