package tavish.bit.actions;

import org.apache.struts2.ServletActionContext;

import tavish.bit.utils.CookieUtils;

public class DelCookieAction {
	private CookieUtils cookieUtils;

	public void setCookieUtils(CookieUtils cookieUtils) {
		this.cookieUtils = cookieUtils;
	}
	
	public String execute() {
		
		cookieUtils.delCookie(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		
		return "success";
	}
}
