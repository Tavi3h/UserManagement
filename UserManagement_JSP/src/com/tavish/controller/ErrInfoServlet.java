package com.tavish.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tavish.model.ErrInfo;

/**
 * Servlet implementation class ErrInfoServlet
 */
@WebServlet("/ErrInfoServlet")
public class ErrInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession(true);
		String err = (String) hs.getAttribute("err");
		hs.removeAttribute("err");
		String errInfo = ErrInfo.errProcess(err);
		hs.setAttribute("errInfo", errInfo);
		if (err.equals("DataErr") || err.equals("Unauthorized")) {
			response.sendRedirect("main.jsp");
			return;
		} else {
			response.sendRedirect("login.jsp");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
