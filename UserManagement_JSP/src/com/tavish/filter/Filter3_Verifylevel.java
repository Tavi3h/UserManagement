package com.tavish.filter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Filter3_Verifylevel
 */
@WebFilter(filterName = "Filter3_Verifylevel", description = "Verify whether user's grade greater than page level or not_Order3", urlPatterns = {
		"*.jsp" })
public class Filter3_Verifylevel implements Filter {

	private static HashMap<String, Integer> hm;

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();
		String loginUrl = "http://localhost:8080/UserManagement_JSP/login.jsp";

		HttpSession hs = req.getSession(true);

		if (url.equals(loginUrl)) {
			chain.doFilter(request, response);
		} else {
			int userGrade = (int) hs.getAttribute("grade");
			if (userGrade >= hm.get(url)) {
				chain.doFilter(request, response);
			} else {
				hs.setAttribute("err", "Unauthorized");
				res.sendRedirect("ErrInfoServlet");
				return;
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		hm = new HashMap<>();
		hm.put("http://localhost:8080/UserManagement_JSP/main.jsp", 1);
		hm.put("http://localhost:8080/UserManagement_JSP/queryuser.jsp", 1);
		hm.put("http://localhost:8080/UserManagement_JSP/queryresult.jsp", 1);
		hm.put("http://localhost:8080/UserManagement_JSP/success.jsp", 1);
		hm.put("http://localhost:8080/UserManagement_JSP/fail.jsp", 1);
		hm.put("http://localhost:8080/UserManagement_JSP/adduser.jsp", 5);
		hm.put("http://localhost:8080/UserManagement_JSP/management.jsp", 10);
		hm.put("http://localhost:8080/UserManagement_JSP/updateuser.jsp", 10);
	}

}
