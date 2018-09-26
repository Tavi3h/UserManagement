package tavish.bit.actions;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import tavish.bit.utils.ValCodeUtils;

@Controller("validCodeAction")
@Scope("prototype")
@Namespace("/util")
@ParentPackage("struts-default")
public class ValidCodeAction {
	
	@Autowired
	private ValCodeUtils vcm;
	
	public void setVcm(ValCodeUtils vcm) {
		this.vcm = vcm;
	}
	
	@Action("valcode")
	public void execute() throws IOException {
		
		BufferedImage vcode = vcm.makeValCode();
		String vcodeStr = vcm.getValCode();
		
		System.out.println(vcodeStr);

		HttpServletResponse response = ServletActionContext.getResponse();
		
		ImageIO.write(vcode, "png", response.getOutputStream());
		
		ActionContext.getContext().getSession().put("valCode", vcodeStr);
	}
}
