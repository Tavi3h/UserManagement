package com.tavish.model;

// 工具类

public class ErrInfo {

	public static String errProcess(String err) {
		String info = "";
		switch (err) {
		case "WrongUserInfo":
			info = "Username or Password is Wrong ... ";
			break;
		case "WrongValCode":
			info = "ValidCode is Wrong ... ";
			break;
		case "IllegallyAccess":
			info = "Illegally Access, please Login ... ";
			break;
		case "DataErr":
			info = "Data error, return to main page ... ";
			break;
		case "Unauthorized":
			info = "Unauthorized User, Access denied ... ";
			break;
		}
		return info;
	}
}
