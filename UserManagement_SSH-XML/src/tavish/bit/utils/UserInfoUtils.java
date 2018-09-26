package tavish.bit.utils;

import tavish.bit.beans.Users;
import tavish.bit.model.services.UsersService;

public class UserInfoUtils {
	
	private UsersService service;
	
	public void setService(UsersService service) {
		this.service = service;
	}

	public String checkInfo(String username, String passwd) {
		
		Users user = service.getUserByNamePre(username);
		if (user == null) {
			return "WrongUserName";
		} 
		if (!user.getPasswd().equals(passwd)) {
			return "WrongPassWd";
		}
		return "Pass";
	}
	
	public int getUserGrade(String username) {
		Users user = service.getUserByNamePre(username);
		return user.getGrade();
	}
}
