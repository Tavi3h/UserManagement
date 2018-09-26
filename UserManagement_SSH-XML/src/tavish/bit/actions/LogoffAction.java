package tavish.bit.actions;

import com.opensymphony.xwork2.ActionContext;

public class LogoffAction {

	public String execute() {
		ActionContext.getContext().getSession().remove("username");
		ActionContext.getContext().getSession().remove("usergrade");
		return "success";
	}
}
