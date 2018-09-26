package tavish.bit.actions;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tavish.bit.utils.CookieUtils;
@Controller("delCookieAction")
@Scope("prototype")
@Namespace("/service")
@ParentPackage("interceptors")
public class DelCookieAction {
	@Autowired
	private CookieUtils cookieUtils;

	public void setCookieUtils(CookieUtils cookieUtils) {
		this.cookieUtils = cookieUtils;
	}
	@Action(value = "delCookie", results = {
			@Result(name = "success", location = "/WEB-INF/jsp/opsuccess.jsp") })
	public String execute() {
		cookieUtils.delCookie(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		return "success";
	}
}
