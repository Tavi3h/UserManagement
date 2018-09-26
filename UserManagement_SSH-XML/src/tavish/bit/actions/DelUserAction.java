package tavish.bit.actions;

import tavish.bit.beans.Users;
import tavish.bit.model.services.UsersService;

public class DelUserAction {
	
	private int userId;
	
	private UsersService service;
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setService(UsersService service) {
		this.service = service;
	}

	public String execute() {
		Users user = new Users();
		user.setUserId(userId);
		service.removeUser(user);
		return "success";
	}
}
