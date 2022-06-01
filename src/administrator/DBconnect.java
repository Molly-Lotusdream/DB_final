package administrator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MyDBConnection {
	private String DBDriver;// 声明驱动程序
	private String DBURL;// 声明数据库连接的url地址
	private String DBUser;// 声明数据库用户名
	private String DBPass;// 声明数据库登录密码
	private Connection conn = null;// 声明连接对象

	// 定义构造方法，完成驱动程序加载和连接工作
	public MyDBConnection(String driver, String dburl, String user, String pass) {
		DBDriver = driver;
		DBURL = dburl;
		DBUser = user;
		DBPass = pass;
		try {
			Class.forName(DBDriver);// 加载数据库驱动程序
			// System.out.println("数据库驱动程序加载成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DBURL, DBUser, DBPass);// 连接数据库
			// System.out.println("连接MySQL数据库成功！");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getMyConnection() {// 返回连接对象
		return conn;
	}

	public void closeMyConnection() {// 关闭连接对象
		try {
			conn.close();// 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String toString() {// 返回状态信息
		return "数据库驱动程序" + DBDriver + ",连接地址" + DBURL + ",用户名" + DBUser + ",密码" + DBPass;
	}
}

public class DBconnect {
	public static void main(String[] args) {
		// String DBDriver="com.mysql.jdbc.Driver";// 定义驱动程序名称:用于mysql 5.x
		String DBDriver = "com.mysql.cj.jdbc.Driver";// 定义驱动程序名称:用于mysql 8.x
		String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";// 定义数据库连接地址
		String DBUser = "root";// 声明数据库服务器用户名称
		String DBPass = "123456";// 声明登录数据库服务器的密码
		MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		@SuppressWarnings("unused")
		Connection conn = myDB.getMyConnection();
		System.out.println(myDB);
		myDB.closeMyConnection();// 关闭连接
	}
}
