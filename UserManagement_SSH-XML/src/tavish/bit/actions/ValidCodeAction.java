package tavish.bit.actions;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import tavish.bit.utils.ValCodeUtils;

public class ValidCodeAction {
	
	private ValCodeUtils vcm;
	
	public void setVcm(ValCodeUtils vcm) {
		this.vcm = vcm;
	}

	public void execute() throws IOException {
		
		BufferedImage vcode = vcm.makeValCode();
		String vcodeStr = vcm.getValCode();
		
		System.out.println(vcodeStr);

		HttpServletResponse response = ServletActionContext.getResponse();
		
		ImageIO.write(vcode, "png", response.getOutputStream());
		
		ActionContext.getContext().getSession().put("valCode", vcodeStr);
	}
}
