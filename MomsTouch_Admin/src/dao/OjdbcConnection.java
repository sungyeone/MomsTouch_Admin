package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OjdbcConnection {

	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@192.168.0.119:1521:XE";
	private static String id = "hr";
	private static String password = "	1234";

	static {

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, id, password);
	}
}
