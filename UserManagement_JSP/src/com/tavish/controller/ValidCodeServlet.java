package com.tavish.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tavish.model.ValCodeMaker;

/**
 * Servlet implementation class ValidCodeServlet 动态的生成验证码
 */
@WebServlet("/ValidCodeServlet")
public class ValidCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ValCodeMaker vcm = new ValCodeMaker();
		BufferedImage image = vcm.makeValCode();
		String valCode = vcm.getValCode();

		System.out.println(valCode);

		// 将图片写入输出流以便输出
		ImageIO.write(image, "png", response.getOutputStream());

		// 将验证码存入session
		HttpSession hs = request.getSession(true);
		hs.setAttribute("valCode", valCode);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
