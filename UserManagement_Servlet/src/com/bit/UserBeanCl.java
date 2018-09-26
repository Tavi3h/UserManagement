package com.bit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// 这是一个处理类（对users表进行处理）操作userBean
// 业务逻辑部分

public class UserBeanCl {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ConnDB cd = null;
	private int pageCount = 0;
	
	
	// 通过用户查找该用户的级别
	public String getGrade(String u, String p) {
		int grade = 0;
		if (checkUser(u, p)) {
			try {
				cd = new ConnDB();
				conn = cd.getConnection();
				String sql = "select grade from users where username = ? && passwd = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, u);
				pstmt.setString(2, p);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					grade = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭资源
				cd.closeResource(rs, pstmt, conn);
			}
		}
		return grade + "";
	}
	
	// 通过用户名查找数据
	public ArrayList<UserBean> queryUserByName(String username, String queryType, int pageNow) {
		
		ArrayList<UserBean> al = null;
		
		try {
			
			cd = new ConnDB();
			conn = cd.getConnection();
			String sql = "select COUNT(*) from users ";
			String condition = null;
			if (queryType.equals("pre")) {
				condition = "where username = ?";
			} else {
				condition = "where username LIKE ?";
				username = "%" + username + "%";
			}
			
			pstmt = conn.prepareStatement(sql + condition);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			int rowCount = 0;
			int pageSize = 5;
			
			if (rs.next()) {
				rowCount = rs.getInt(1);
			}
			
			cd.closeResource(rs, pstmt, null);

			if (rowCount % pageSize == 0) {
				pageCount = rowCount / pageSize;
			} else {
				pageCount = rowCount / pageSize + 1;
			}
			
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
	
	// 添加数据
	public boolean addUser(UserBean ub) {
		boolean add = false;
		
		int userId = ub.getUserId();
		String username = ub.getUsername();
		String passwd = ub.getPasswd();
		String email = ub.getEmail();
		int grade = ub.getGrade();
		
		try {
			cd = new ConnDB();
			conn = cd.getConnection();
			String sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, username);
			pstmt.setString(3, passwd);
			pstmt.setString(4, email);
			pstmt.setInt(5, grade);
			int num = pstmt.executeUpdate();
			if (num == 1) {
				add = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.closeResource(null, pstmt, conn);
		}
		
		return add;
	}
	
	// 更新数据
	public boolean updateUser(UserBean ub) {
		boolean update = false;
		int userId = ub.getUserId();
		String passwd = ub.getPasswd();
		String email = ub.getEmail();
		int grade = ub.getGrade();
		
		try {
			cd = new ConnDB();
			conn = cd.getConnection();
			String sql = "update users set passwd = ?, email = ?, grade = ? where userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setString(2, email);
			pstmt.setInt(3, grade);
			pstmt.setInt(4, userId);
			int num = pstmt.executeUpdate();
			if (num == 1) {
				update = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.closeResource(null, pstmt, conn);
		}
		return update;		
	}
	
	// 根据id删除用户
	public boolean delUser(String id) {
		boolean del = false;
		try {
			cd = new ConnDB();
			conn = cd.getConnection();
			String sql = "delete from users where userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int num = pstmt.executeUpdate();
			if (num == 1) {
				del = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.closeResource(null, pstmt, conn);
		}
		return del;
	}

	// 验证用户是否合法
	public boolean checkUser(String u, String p) {

		boolean b = false;

		try {
			// 得到连接
			cd = new ConnDB();
			conn = cd.getConnection();
			// 组织sql语句
			String sql = "select passwd from users where username = ? limit 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u);
			// 执行查询
			rs = pstmt.executeQuery();
			// 对用户名及密码进行验证
			if (rs.next()) {
				String dbPasswd = rs.getString(1);
				if (dbPasswd.equals(p)) {
					b = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			cd.closeResource(rs, pstmt, conn);
		}
		return b;
	}

	// 分页显示数据库
	public ArrayList<UserBean> getResultByPage(int pageNow, int pageSize) {

		ArrayList<UserBean> al = null;

		try {

			int rowCount = 0;
			
			// 创建数据库连接
			cd = new ConnDB();
			conn = cd.getConnection();

			// 查询表中记录总数
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM users");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rowCount = rs.getInt(1);
			}

			// 关闭资源
			cd.closeResource(rs, pstmt, null);

			// 分页算法
			if (rowCount % pageSize == 0) {
				pageCount = rowCount / pageSize;
			} else {
				pageCount = rowCount / pageSize + 1;
			}

			// 重新创建pstmt、rs，查询按照分页算法实际应该显示的内容
			pstmt = conn.prepareStatement("SELECT * FROM users LIMIT ?, ?");
			pstmt.setInt(1, (pageNow - 1) * pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();

			// 初始化arraylist
			al = new ArrayList<>();

			// 将rs中的每一条记录封装为一个UserBean
			while (rs.next()) {
				UserBean ub = new UserBean();
				ub.setUserId(rs.getInt(1));
				ub.setUsername(rs.getString(2));
				ub.setPasswd(rs.getString(3));
				ub.setEmail(rs.getString(4));
				ub.setGrade(rs.getInt(5));

				// 将ub放入ArrayList中
				al.add(ub);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.closeResource(rs, pstmt, conn);
		}
		
		return al;
	}
	
	// 返回pageCount
	public int getPageCount() {
		return pageCount;
	}
}
