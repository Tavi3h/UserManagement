package tavish.bit.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import tavish.bit.beans.Users;
import tavish.bit.model.services.UsersService;

@Controller("updateUserAction")
@Scope("prototype")
@Namespace("/service")
@ParentPackage("interceptors")
public class UpdateUserAction {

	private Users user;

	@Autowired
	private UsersService service;

	public void setUser(Users user) {
		this.user = user;
	}

	public Users getUser() {
		return user;
	}

	public void setService(UsersService service) {
		this.service = service;
	}

	// 获取管理页面传来的userId对应的User
	@Action(value = "update_acquireUser", results = { @Result(name = "updatePage", location = "/WEB-INF/jsp/updateuser.jsp") })
	public String acquireUser() {
		System.out.println("acquire");
		Users thisUser = service.getUserById(user.getUserId());
		ActionContext.getContext().put("thisUser", thisUser);
		return "updatePage";
	}

	// 更新指定id的User
	@Action(value = "update_updateUser", results = { @Result(name = "success", location = "/WEB-INF/jsp/opsuccess.jsp") })
	public String updateUser() {
		System.out.println("update");
		Users thisUser = new Users();
		thisUser.setUserId(user.getUserId());
		thisUser.setUsername(user.getUsername());
		thisUser.setPasswd(user.getPasswd());
		thisUser.setEmail(user.getEmail());
		thisUser.setGrade(user.getGrade());
		service.modifyUser(thisUser);
		return "success";
	}
}
