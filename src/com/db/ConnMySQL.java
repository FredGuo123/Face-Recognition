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
	private final String dbDriver = "com.mysql.jdbc.Driver";// ����MySQL���ݿ������
	// ����MySQL���ݿ��·��
	private static final String URL = "jdbc:mysql://localhost:3306/db_lottery";
	private static final String USERNAME = "root";// ����MySQL���ݿ���û���
	private static final String PASSWORD = "123456";// ����MySQL���ݿ������
	private static Connection con = null;// ��ʼ������MySQL���ݿ�Ķ���
	public ConnMySQL() {// ����MySQL���ݿ�Ĺ��췽��
		try {
			Class.forName(dbDriver);// ����MySQL���ݿ������
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("���ݿ����ʧ��");
		}
	}
	public static boolean creatConnection() {// ����MySQL���ݿ������
		try {
			// ��������MySQL���ݿ��·�����û�������������MySQL���ݿ�
			con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public void closeConnection() {// �ر�MySQL���ݿ������
		if (con != null) {// �ж�Connection�����Ƿ�Ϊ��
			try {
				con.close();// �ر�MySQL���ݿ�����
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				con = null;// ����Connection����Ϊ��
			}
		}
	}
}

