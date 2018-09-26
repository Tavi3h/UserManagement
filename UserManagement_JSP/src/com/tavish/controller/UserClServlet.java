package com.tavish.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tavish.model.OpSelect;

/**
 * Servlet implementation class UsersClServlet 这个控制器将处理管理界面用户的分页显示、用户的增添、删除、修改。
 */
@WebServlet("/UserClServlet")
public class UserClServlet extends HttpServlet {

	private static final long serialVersionUID = -3669779706385126908L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String flag = request.getParameter("flag");

		if (flag == null) {
			request.getSession(true).setAttribute("err", "IllegallyAccess");
			response.sendRedirect("ErrInfoServlet");
			return;
		}

		switch (flag) {

		case "manage":
			OpSelect.manage(request, response);
			return;
		case "addUser":
			OpSelect.addUser(request, response);
			return;
		case "delUser":
			OpSelect.delUser(request, response);
			return;
		case "updateUser":
			OpSelect.updateUser(request, response);
			return;
		case "queryUser":
			OpSelect.queryUser(request, response);
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
