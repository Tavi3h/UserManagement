package tavish.bit.interceptors;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import tavish.bit.utils.CookieUtils;
import tavish.bit.utils.UserInfoUtils;

@Component("permission")
public class PermissionInterceptor extends AbstractInterceptor {

	@Autowired
	private UserInfoUtils userInfoUtils;

	@Autowired
	private CookieUtils cookieUtils;

	private static final long serialVersionUID = 7158683266269042617L;

	public void setUserInfoUtils(UserInfoUtils userInfoUtils) {
		this.userInfoUtils = userInfoUtils;
	}

	public void setCookieUtils(CookieUtils cookieUtils) {
		this.cookieUtils = cookieUtils;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// 获取用户访问的action的名称
		String actionName = invocation.getInvocationContext().getName();

		// 设置标记，判断要访问的action是否为login.action
		boolean isloginAction = actionName.equals("login");

		// 判断session中是否存在属性“username”
		String sessionUsername = (String) ActionContext.getContext().getSession().get("username");
		if (sessionUsername != null) {
			// 如果session中存在username属性，说明用户已经登录
			// 如果用户访问的是login.action，则跳转到main.jsp，否则执行invocation.invoke();
			return isloginAction ? "success" : invocation.invoke();
		} else {
			// 如果session中不存在username属性，说明用户没有登录
			// 此时需先判断是否为带参数访问login.action（登录）
			if (isloginAction && !invocation.getInvocationContext().getParameters().isEmpty()) {
				// 如果是带参数访问login.action，则判断为登录，跳转到login.action去验证
				return invocation.invoke();
			} else {
				// 如果是不带参数访问login.action或访问其他action
				// 此时，上述两种情况逻辑相同，即为访问任意action，进行cookie验证
				List<String> cookieList = cookieUtils.getCookie(ServletActionContext.getRequest());
				if (cookieList == null) {
					// 如果cookieList为null
					// 说明此时session、cookie中均无用户信息，且不是登录操作，跳转到login视图
					return "login";
				} else {
					// 如果cookie中有用户信息，取出信息进行验证
					String name = cookieList.get(0);
					String psd = cookieList.get(1);
					String check = userInfoUtils.checkInfo(name, psd);
					if (check.equals("Pass")) {
						// 如果验证通过，说明cookie中用户信息合法，将用户信息写入session
						ActionContext.getContext().getSession().put("username", name);
						ActionContext.getContext().getSession().put("usergrade", userInfoUtils.getUserGrade(name));
						// 如果此时访问的是login.action则跳转到main.jsp，如果不是，执行invocation.invoke()
						return isloginAction ? "success" : invocation.invoke();
					} else {
						// 如果cookie中的信息没有验证通过，则说明cookie中用户信息不合法，返回login视图
						return "login";
					}
				}
			}
		}
	}
}
