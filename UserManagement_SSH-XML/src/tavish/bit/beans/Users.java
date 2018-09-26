package tavish.bit.beans;

public class Users {
	
	private Integer userId;
	
	private String username;
	
	private String passwd;
	
	private String email;
	
	private int grade;

	public Users(String username, String passwd, String email, int grade) {
		super();
		this.username = username;
		this.passwd = passwd;
		this.email = email;
		this.grade = grade;
	}

	public Users() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", passwd=" + passwd + ", email=" + email
				+ ", grade=" + grade + "]";
	}
}
