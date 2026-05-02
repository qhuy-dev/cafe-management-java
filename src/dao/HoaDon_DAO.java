package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import enity.HoaDon;

public class HoaDon_DAO {
	public ArrayList<HoaDon> getAllHoaDon() {
		ArrayList<HoaDon> list = new ArrayList<>();
		new ConnectDB();
		try {
			ConnectDB.getInstance().connect();
			String sql = "SELECT * FROM HoaDon";
			Statement stmt = ConnectDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				HoaDon hd = new HoaDon(
						rs.getString("maHoaDon"),
						new KhachHang_DAO().TimTheoMa(rs.getString("maKhachHang")),
						new NhanVien_DAO().TimTheoMa(rs.getString("maNhanVien")),
						new BanCafe_DAO().getBanCafe(rs.getString("maBan")),
						rs.getDate("ngayTao").toLocalDate(),
						rs.getDouble("tongTien")
				);
				list.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		return list;
	}
	public double getAllDoanhThu() {
		new ConnectDB();
		double doanhThu = 0;
		try {
			ConnectDB.getInstance().connect();
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
	public ArrayList<HoaDon> getHoaDon(LocalDate ngay) {
		ArrayList<HoaDon> list = new ArrayList<>();
		new ConnectDB();
		try {
			ConnectDB.getInstance().connect();
			String sql = "SELECT * FROM HoaDon WHERE ngayTao = ? ";
			PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
			stmt.setObject(1, ngay);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(
						rs.getString("maHoaDon"),
						new KhachHang_DAO().TimTheoMa(rs.getString("maKhachHang")),
						new NhanVien_DAO().TimTheoMa(rs.getString("maNhanVien")),
						new BanCafe_DAO().getBanCafe(rs.getString("maBan")),
						rs.getDate("ngayTao").toLocalDate(),
						rs.getDouble("tongTien")
						);
				list.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		return list;
	}
}
