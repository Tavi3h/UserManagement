package tavish.bit.actions;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import tavish.bit.utils.CookieUtils;
import tavish.bit.utils.UserInfoUtils;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 970651567715034339L;

	private String username;
	private String passwd;
	private String vcode;
	private String cookie;

	private CookieUtils cookieUtils;
	private UserInfoUtils userInfoUtils;

	private String msg;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public void setCookieUtils(CookieUtils cookieUtils) {
		this.cookieUtils = cookieUtils;
	}

	public void setUserInfoUtils(UserInfoUtils userInfoUtils) {
		this.userInfoUtils = userInfoUtils;
	}

	public String getMsg() {
		return msg;
	}

	public String execute() {

		String info = userInfoUtils.checkInfo(username, passwd);

		if (info.equals("WrongUserName")) {
			msg = "无效的用户名";
			return "input";
		} else if (info.equals("WrongPassWd")) {
			msg = "错误的登录密码";
			return "input";
		} else {
			cookieUtils.delCookie(ServletActionContext.getRequest(), ServletActionContext.getResponse());
			if (cookie != null) {
				cookieUtils.addCookie(ServletActionContext.getResponse(), username, passwd);
			}
			ActionContext.getContext().getSession().put("username", username);
			ActionContext.getContext().getSession().put("usergrade", userInfoUtils.getUserGrade(username));
			return "success";
		}
	}

	@Override
	public void validate() {
		
		// 校验用户名是否为空
		if (username == null || "".equals(username.trim())) {
			this.addFieldError("usernamenull", "请填写用户名");
			return;
		}
		// 校验密码是否为空
		if (passwd == null || "".equals(passwd.trim())) {
			this.addFieldError("passwdnull", "请填写密码");
			return;
		}
		// 校验验证码是否为空，并校验验证码
		if (vcode == null || "".equals(vcode.trim())) {
			this.addFieldError("vcodenull", "请填写验证码");
			return;
		} else if (!vcode.equalsIgnoreCase(ActionContext.getContext().getSession().get("valCode").toString())) {
			this.addFieldError("wrongvcode", "验证码不正确");
			return;
		}
	}
}
