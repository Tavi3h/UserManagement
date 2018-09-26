package tavish.bit.interceptors;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Component("grade")
public class GradeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 4826791636647126621L;

	private static Map<String, Integer> gradeMap = new HashMap<>();

	static {
		gradeMap.put("logoff", 1);
		gradeMap.put("manage", 10);
		gradeMap.put("delUser", 10);
		gradeMap.put("return", 1);
		gradeMap.put("update_acquireUser", 10);
		gradeMap.put("update_updateUser", 10);
		gradeMap.put("add_setUserInfo", 5);
		gradeMap.put("add_addUser", 5);
		gradeMap.put("query_queryInfo", 1);
		gradeMap.put("query_queryUser", 1);
		gradeMap.put("delCookie", 1);
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		int thisUserGrade = (int) ActionContext.getContext().getSession().get("usergrade");
		int pageGrade = gradeMap.get(invocation.getInvocationContext().getName());

		if (thisUserGrade >= pageGrade) {
			return invocation.invoke();
		} else {
			return "forbidden";
		}
	}
}
