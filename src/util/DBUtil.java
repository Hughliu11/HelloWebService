package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=test";
	public static final String USER = "imosadmin";
	public static final String PWD = "imos";
	
	public static Connection  getConnection() {
		Connection connection=null;
		try {
			Class.forName(DRIVER);
			connection=DriverManager.getConnection(URL,USER,PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return connection;
	}
	
	
	public static void clossConnection(Connection conn) {
		
		if (conn !=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void clossStatement(Statement sta) {
		
		if (sta !=null) {
			
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
