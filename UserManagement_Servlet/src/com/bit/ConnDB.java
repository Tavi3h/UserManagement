package com.bit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnDB {
	
	
	// 从数据库中得到连接
	public Connection getConnection() {
		/*
		 * 使用jdbc的方式连接数据库
		 */
		
//		Connection conn = null;
//		String classPath = "com.mysql.jdbc.Driver";
//		String username = "root";
//		String password = "mysql";
//		String url = "jdbc:mysql://localhost:3306/test";
//		
//		try {
//			Class.forName(classPath);
//			conn = DriverManager.getConnection(url, username, password);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return conn;
		
		
		/*
		 * 使用连接池的的方式进行连接数据库
		 */
		Connection conn = null;
		DataSource ds = null;
		Context initContext = null;
		Context envContext = null;
		try {
			initContext = new InitialContext();
			envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/TavishSQL");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return conn;
	}
	
	// 关闭资源
	public void closeResource(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try { 
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			} 
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
