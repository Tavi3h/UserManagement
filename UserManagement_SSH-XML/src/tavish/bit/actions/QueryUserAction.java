package tavish.bit.actions;

import java.util.Arrays;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import tavish.bit.beans.Users;
import tavish.bit.model.services.UsersService;

public class QueryUserAction extends ActionSupport {
	
	private static final long serialVersionUID = -3890290198504208019L;

	private String username;
	
	private int pageNumber;

	private String querytype;

	private UsersService service;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getQuerytype() {
		return querytype;
	}

	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}

	public void setService(UsersService service) {
		this.service = service;
	}

	public String queryInfo() {
		return "queryPage";
	}

	public String queryUser() {
		
		if (querytype.equals("fuz")) {
			if (pageNumber == 0) {
				pageNumber = 1;
			}
			int pageSize = 10;
			List<Users> list = service.getUsersByPageFuz(username, pageNumber, pageSize);
			int pageCount = service.getPageCountFuz(username, pageSize);
			if (list == null || list.size() == 0) {
				return "fail";
			}
			ActionContext.getContext().put("queryResult", list);
			ActionContext.getContext().put("pageCount", pageCount);
			return "success";
		} else {
			Users user = service.getUserByNamePre(username);
			if (user == null) {
				return "fail";
			}
			ActionContext.getContext().put("queryResult", Arrays.asList(new Users[] {user}));
			return "success";
		}
	}
	
	public void validateQueryUser() {
		if (username.trim().equals("") || username == null) {
			this.addFieldError("nullusername", "请输入用户名");
		}
	}

}
