package draw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LinkDB {

	public static void main(String[] args) {
		
		List<String> list = connect();
	}
	
	//连接数据库执行语句
	private static List<String> connect() {
		List<String> arr = new ArrayList<String>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://ip:port;database=stu_cour";
			String user = "root";
			String password = "123456";
			Connection con = DriverManager.getConnection(url, user, password);
			
			Statement statement = con.createStatement();
			ResultSet re = statement.executeQuery("insert into man_face(age,beauty,emotion,gender,expression,faceshape,race)values(1,2,3,4,5,6,7);");//执行查询语句，接收结果集
			while (re.next()) {
				arr.add(re.getString("name"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
		
	}

}

