package bus;

import java.sql.ResultSet;
import java.sql.Statement;

import connectDB.ConnectDB;

public class HoaDon_BUS {
	public double getAllDoanhThu() {
		double doanhThu = 0;
		try {
			String sql = "SELECT * FROM HoaDon";
			Statement stmt = ConnectDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				doanhThu += rs.getDouble("tongTien");
			}
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		return doanhThu;
	}
}
