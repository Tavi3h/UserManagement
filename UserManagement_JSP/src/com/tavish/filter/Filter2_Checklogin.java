package com.tavish.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Filter2_Checklogin
 */
@WebFilter(filterName = "Filter2_Checklogin", description = "Check if any users logged_Order2", urlPatterns = { "*.jsp" })
public class Filter2_Checklogin implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String url = req.getRequestURL().toString();
		String loginUrl = "http://localhost:8080/UserManagement_JSP/login.jsp";
		if (url.equals(loginUrl)) {
			chain.doFilter(request, response);
		} else {
			HttpSession hs = req.getSession(true);
			String u = (String) hs.getAttribute("username");
			if (u == null) {
				// session中没有username，检查cookie
				String name = null;
				String passwd = null;
				Cookie[] allCookie = req.getCookies();
				// 如果cookie中有信息，则取出
				if (allCookie != null) {
					for (int i = 0; i < allCookie.length; i++) {
						Cookie temp = allCookie[i];
						if (temp.getName().equals("username")) {
							name = temp.getValue();
						} else if (temp.getName().equals("password")) {
							passwd = temp.getValue();
						}
					}
					if (name != null && passwd != null) {
						// 将从cookie中取得信息拿去验证
						res.sendRedirect("LoginClServlet?&type=cookiecheck&username=" + name + "&password=" + passwd);
						return;
					} else {
						// 如果session和cookie中都没有信息，跳转到登录页面
						hs.setAttribute("err", "IllegallyAccess");
						res.sendRedirect("ErrInfoServlet");
						return;
					}
				}
			} else {
				// 如果session中有信息，则放行
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
