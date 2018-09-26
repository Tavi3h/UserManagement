package tavish.bit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tavish.bit.beans.Users;
import tavish.bit.model.services.UsersService;

@Component
public class UserInfoUtils {
	
	@Autowired
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
