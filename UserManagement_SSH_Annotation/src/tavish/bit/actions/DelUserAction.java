package tavish.bit.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tavish.bit.beans.Users;
import tavish.bit.model.services.UsersService;
@Controller("delUserAction")
@Scope("prototype")
@Namespace("/service")
@ParentPackage("interceptors")
public class DelUserAction {
	
	private int userId;
	
	@Autowired
	private UsersService service;
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setService(UsersService service) {
		this.service = service;
	}

	@Action(value="delUser", results= {@Result(name="success",location="/WEB-INF/jsp/opsuccess.jsp")})
	public String execute() {
		Users user = new Users();
		user.setUserId(userId);
		service.removeUser(user);
		return "success";
	}
}
