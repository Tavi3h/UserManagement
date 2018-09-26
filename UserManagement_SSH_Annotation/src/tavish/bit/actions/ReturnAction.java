package tavish.bit.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("returnAction")
@Scope("prototype")
@Namespace("/service")
@ParentPackage("interceptors")
public class ReturnAction {

	@Action(value = "return", results = { @Result(name = "success", location = "/WEB-INF/jsp/main.jsp") })
	public String execute() {
		return "success";
	}
}
