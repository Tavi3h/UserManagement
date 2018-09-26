package tavish.bit.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import tavish.bit.beans.Users;
import tavish.bit.model.services.UsersService;

@Controller("manageusersAction")
@Scope("prototype")
@Namespace("/service")
@ParentPackage("interceptors")
public class ManageusersAction {

	private int pageNumber;

	@Autowired
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

	@Action(value = "manage", results = { @Result(name = "success", location = "/WEB-INF/jsp/manageusers.jsp") })
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
