package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

public class ConnMySQL
{
	private final String dbDriver = "com.mysql.jdbc.Driver";// 连接MySQL数据库的驱动
	// 连接MySQL数据库的路径
	private static final String URL = "jdbc:mysql://localhost:3306/db_lottery";
	private static final String USERNAME = "root";// 连接MySQL数据库的用户名
	private static final String PASSWORD = "123456";// 连接MySQL数据库的密码
	private static Connection con = null;// 初始化连接MySQL数据库的对象
	public ConnMySQL() {// 连接MySQL数据库的构造方法
		try {
			Class.forName(dbDriver);// 加载MySQL数据库的驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("数据库加载失败");
		}
	}
	public static boolean creatConnection() {// 建立MySQL数据库的连接
		try {
			// 根据连接MySQL数据库的路径、用户名、密码连接MySQL数据库
			con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public void closeConnection() {// 关闭MySQL数据库的连接
		if (con != null) {// 判断Connection对象是否为空
			try {
				con.close();// 关闭MySQL数据库连接
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				con = null;// 重置Connection对象为空
			}
		}
	}
}

