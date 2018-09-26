package tavish.bit.actions;

import com.opensymphony.xwork2.ActionContext;
import tavish.bit.beans.Users;
import tavish.bit.model.services.UsersService;

public class UpdateUserAction {

	private Users user;

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
	public String acquireUser() {
		Users thisUser = service.getUserById(user.getUserId());
		ActionContext.getContext().put("thisUser", thisUser);
		return "updatePage";
	}

	// 更新指定id的User
	public String updateUser() {

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
