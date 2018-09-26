package tavish.bit.actions;

import com.opensymphony.xwork2.ActionSupport;

import tavish.bit.beans.Users;
import tavish.bit.model.services.UsersService;

public class AddUserAction extends ActionSupport {
	
	private static final long serialVersionUID = -471374965799914474L;

	private Users user;
	
	private String passwdconfirm;
	
	private UsersService service;
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public void setPasswdconfirm(String passwdconfirm) {
		this.passwdconfirm = passwdconfirm;
	}

	public void setService(UsersService service) {
		this.service = service;
	}

	public String setUserInfo() {
		return "setUserPage";
	}
	
	public String addUser() {
		Users thisUser = new Users();
		thisUser.setUsername(user.getUsername());
		thisUser.setPasswd(user.getPasswd());
		thisUser.setEmail(user.getEmail());
		thisUser.setGrade(user.getGrade());
		
		service.addUser(thisUser);
		return "success";
	}
	
	public void validateAddUser() {
		
		String username = user.getUsername();
		String passwd = user.getPasswd();
		String email = user.getEmail();
		int grade = user.getGrade();
		
 		if (username.trim().equals("") || username == null) {
 			this.addFieldError("nullusername", "用户名不允许为空");
 		} else if (passwd.equals("") || passwd == null) {
 			this.addFieldError("nullpasswd", "密码不允许为空");
 		} else if (email.trim().equals("") || email == null) {
 			this.addFieldError("nullemail", "email不允许为空");
 		} else if (grade == 0) {
 			this.addFieldError("nullgrade", "grade不允许为0");
 		} else if (!passwdconfirm.equals(passwd)) {
			this.addFieldError("wrongpassword", "两次输入的密码不一致");
		} else if(service.getUserByNamePre(username) != null) {
			this.addFieldError("duplicatedUsername", "该用户名已被使用");
		} 
	}
}
