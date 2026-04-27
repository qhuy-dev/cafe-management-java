package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private static Connection con = null;
	private static ConnectDB instance = new ConnectDB();
	public static ConnectDB getInstance() {
		return instance;
	}
	public void connect() throws SQLException{
		String urlString ="jdbc:sqlsever://localhost:1433;databasename=QLCAFE";
		con = DriverManager.getConnection(urlString,"sa", "123");
	}
	public static Connection getConnection() {
		return con;
	}
}
