package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectDB.ConnectDB;
import enity.KhachHang;

public class KhachHang_DAO {
	public KhachHang TimTheoMa(String maKhachHang) {
		KhachHang khachHang = null;
		new ConnectDB();
		try {
			ConnectDB.getInstance().connect();
			String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
			PreparedStatement stmtPreparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			stmtPreparedStatement.setString(1, maKhachHang);
			ResultSet rs = stmtPreparedStatement.executeQuery();
			if (rs.next()) {
				khachHang = new KhachHang(
						rs.getString("maKhachHang"),
						rs.getString("hoTen"),
						rs.getString("soDienThoai")
				);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return khachHang;
	}
}
