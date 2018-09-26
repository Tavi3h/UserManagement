package tavish.bit.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller("logoffAction")
@Scope("prototype")
@Namespace("/service")
@ParentPackage("interceptors")
public class LogoffAction {

	@Action(value = "logoff", results = { @Result(name = "success", location = "/index.jsp", type = "redirect") })
	public String execute() {
		ActionContext.getContext().getSession().remove("username");
		ActionContext.getContext().getSession().remove("usergrade");
		return "success";
	}
}
