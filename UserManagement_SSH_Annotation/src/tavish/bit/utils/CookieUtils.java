package tavish.bit.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CookieUtils {

	public static final String USER_COOKIE = "user.cookie";

	// 写入cookie
	public boolean addCookie(HttpServletResponse response, String username, String passwd) {
		Cookie userCookie = new Cookie(USER_COOKIE, username + "&" + passwd);
		userCookie.setMaxAge(14 * 24 * 3600);
		response.addCookie(userCookie);
		System.out.println("Cookie写入完成！");
		return true;
	}

	// 获取cookie
	public List<String> getCookie(HttpServletRequest request) {

		List<String> list = null;

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(USER_COOKIE)) {
				String[] userInfo = cookie.getValue().split("&");
				list = new ArrayList<>();
				list.add(userInfo[0]);
				list.add(userInfo[1]);
				break;
			}
		}
		return list;
	}

	// 删除cookie
	public boolean delCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(USER_COOKIE)) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				System.out.println("Cookie已删除");
				break;
			}
			System.out.println("没有Cookie需要被删除");
		}
		return true;
	}
}
