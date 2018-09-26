package com.tavish.model;

// 这是一个处理类

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserBeanCl {

	private ConnDB cd = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private int pageCount = 0;

	// 根据用户名查询用户级别
	public int getGrade(String username, String passwd) {
		int grade = 0;
		try {
			cd = new ConnDB();
			conn = cd.getConnection();
			String sql = "SELECT grade FROM users where username = ? and passwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				grade = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.closeResource(rs, pstmt, conn);
		}
		return grade;
	}

	// 根据用户名查询用户
	public ArrayList<UserBean> queryUser(String username, String qType, int pageNow) {
		ArrayList<UserBean> al = null;

		String sql = "select COUNT(*) from users ";
		String condition = "";
		if (qType.equals("pre")) {
			condition = "where username = ?";
		} else {
			condition = "where username LIKE ?";
			username = "%" + username + "%";
		}

		try {
			cd = new ConnDB();
			conn = cd.getConnection();
			pstmt = conn.prepareStatement(sql + condition);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			int rowCount = 0;
			int pageSize = 5;

			if (rs.next()) {
				rowCount = rs.getInt(1);
			}

			cd.closeResource(rs, pstmt, null);

			pageCount = (rowCount % pageSize == 0) ? rowCount / pageSize : rowCount / pageSize + 1;

			pstmt = conn.prepareStatement("SELECT * FROM users " + condition + " LIMIT ?, ?");
			pstmt.setString(1, username);
			pstmt.setInt(2, (pageNow - 1) * pageSize);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();

			al = new ArrayList<>();

			while (rs.next()) {

				UserBean ub = new UserBean();
				ub.setUserId(rs.getInt(1));
				ub.setUsername(rs.getString(2));
				ub.setPasswd(rs.getString(3));
				ub.setEmail(rs.getString(4));
				ub.setGrade(rs.getInt(5));

				al.add(ub);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.closeResource(rs, pstmt, conn);
		}
		return al;
	}

	// 修改用户信息的方法
	public boolean updateUser(String userId, String username, String passwd, String email, String grade) {
		boolean update = false;

		username = username.equals("") ? null : username;
		passwd = passwd.equals("") ? null : passwd;
		email = email.equals("") ? null : email;
		grade = grade.equals("") ? null : grade;

		cd = new ConnDB();
		conn = cd.getConnection();

		try {
			String sql = "UPDATE users SET username = ?, passwd = ?, email = ?, grade = ? WHERE userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, passwd);
			pstmt.setString(3, email);
			pstmt.setString(4, grade);
			pstmt.setString(5, userId);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				update = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.closeResource(rs, pstmt, conn);
		}
		return update;
	}

	// 添加用户的方法
	public boolean addUser(String userId, String username, String passwd, String email, String grade) {
		boolean add = false;

		userId = userId.equals("") ? null : userId;
		username = username.equals("") ? null : username;
		passwd = passwd.equals("") ? null : passwd;
		email = email.equals("") ? null : email;
		grade = grade.equals("") ? null : grade;

		try {
			cd = new ConnDB();
			conn = cd.getConnection();
			String sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, username);
			pstmt.setString(3, passwd);
			pstmt.setString(4, email);
			pstmt.setString(5, grade);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				add = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.closeResource(rs, pstmt, conn);
		}
		return add;
	}

	// 根据用户id删除用户
	public boolean deUserById(String id) {

		boolean del = false;
		try {
			cd = new ConnDB();
			conn = cd.getConnection();
			String sql = "delete from users where userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				del = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.closeResource(rs, pstmt, conn);
		}

		return del;
	}

	// 验证用户是否存在
	public boolean checkUser(String u, String p) {

		boolean check = false;

		try {

			cd = new ConnDB();
			conn = cd.getConnection();
			String sql = "select passwd from users where username = ? limit 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPasswd = rs.getString(1);
				if (dbPasswd.equals(p)) {
					check = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.closeResource(rs, pstmt, conn);
		}

		return check;
	}

	// 分页显示用户信息
	public ArrayList<UserBean> getResultByPage(int pageNow, int pageSize) {

		ArrayList<UserBean> al = null;

		cd = new ConnDB();
		conn = cd.getConnection();

		int rowCount = 0;

		try {
			// 查询数据库中的总条目数
			String sql = "select count(*) from users";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rowCount = rs.getInt(1);
			}

			// 关闭资源
			cd.closeResource(rs, pstmt, null);

			// 分页算法
			pageCount = (rowCount % pageSize == 0) ? rowCount / pageSize : rowCount / pageSize + 1;

			// 查询实际应该显示的条目
			sql = "SELECT * FROM users LIMIT ? , ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNow - 1) * pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();

			// 将查询结果封装进一个UserBean对象，并存储在al中
			al = new ArrayList<>();
			while (rs.next()) {
				UserBean ub = new UserBean();
				ub.setUserId(rs.getInt(1));
				ub.setUsername(rs.getString(2));
				ub.setPasswd(rs.getString(3));
				ub.setEmail(rs.getString(4));
				ub.setGrade(rs.getInt(5));
				al.add(ub);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.closeResource(rs, pstmt, conn);
		}

		return al;
	}

	// 获得pageCount
	public int getPageCount() {
		return pageCount;
	}
}
