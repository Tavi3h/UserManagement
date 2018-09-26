package com.tavish.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ValCodeMaker {

	private StringBuilder sb = null;

	public BufferedImage makeValCode() {
		// 1、创建图片对象
		int width = 120;
		int height = 30;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 2、编辑图片
		Graphics g = image.getGraphics();
		// 设置背景色
		g.setColor(Color.GRAY);
		// 填充
		g.fillRect(0, 0, width, height);
		// 增加边框
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
		// 写入验证码
		g.setColor(Color.PINK);
		g.setFont(new Font("Arial", Font.BOLD, 22));
		String str = "ABCDEFGHIZKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";
		Random rand = new Random(System.currentTimeMillis());
		int x = 15;
		sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int index = rand.nextInt(str.length() - 1);
			char ch = str.charAt(index);
			g.drawString(ch + "", x, height / 2 + 5);
			x += 20;
			sb.append(ch);
		}
		// 增加干扰线
		g.setColor(Color.BLACK);
		for (int i = 0; i < 8; i++) {
			int x1 = rand.nextInt(width);
			int x2 = rand.nextInt(width);
			int y1 = rand.nextInt(height);
			int y2 = rand.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		return image;
	}

	public String getValCode() {
		return sb.toString();
	}

}
