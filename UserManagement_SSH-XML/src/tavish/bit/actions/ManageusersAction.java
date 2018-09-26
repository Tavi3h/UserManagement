package tavish.bit.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import tavish.bit.beans.Users;
import tavish.bit.model.services.UsersService;

public class ManageusersAction {
	
	private int pageNumber;
	
	private UsersService service;
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setService(UsersService service) {
		this.service = service;
	}
	
	public String execute() {
		
		if (pageNumber == 0) {
			pageNumber = 1;
		}
		
		int pageSize = 10;
		
		List<Users> list = service.getUsersByPage(pageNumber, pageSize);
		int pageCount = service.getPageCount(pageSize);
		
		ActionContext.getContext().put("queryResult", list);
		ActionContext.getContext().put("pageCount", pageCount);
		return "success";
	}
}
