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
		String urlString ="jdbc:sqlserver://localhost:1433;databasename=QLCAFE";
		con = DriverManager.getConnection(urlString,"sa", "123");
	}
	public static Connection getConnection() {
		return con;
	}
	public static void main(String[] args) {
		try {
			ConnectDB.getInstance().connect();
			System.out.println("Kết nối thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Kết nối thất bại");
		}
	}
}
