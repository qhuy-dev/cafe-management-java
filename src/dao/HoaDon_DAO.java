package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.HoaDon;

public class HoaDon_DAO {
	public ArrayList<HoaDon> getAllHoaDon() {
		ArrayList<HoaDon> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM HoaDon";
			Statement stmt = ConnectDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				HoaDon hd = new HoaDon(
						rs.getString("maHoaDon"),
						new KhachHang_DAO().TimKiemTheoMa(rs.getString("maKhachHang")),
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
	
	public ArrayList<HoaDon> getHoaDon(LocalDate ngay) {
		ArrayList<HoaDon> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM HoaDon WHERE ngayTao = ? ";
			PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
			stmt.setObject(1, ngay);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(
						rs.getString("maHoaDon"),
						new KhachHang_DAO().TimKiemTheoMa(rs.getString("maKhachHang")),
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
	public ArrayList<HoaDon> getHoaDon(int thang, int nam) {
		ArrayList<HoaDon> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM HoaDon WHERE MONTH(ngayTao) = ? AND YEAR(ngayTao) = ?";
			PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(
						rs.getString("maHoaDon"),
						new KhachHang_DAO().TimKiemTheoMa(rs.getString("maKhachHang")),
						new NhanVien_DAO().TimTheoMa(rs.getString("maNhanVien")),
						new BanCafe_DAO().getBanCafe(rs.getString("maBan")),
						rs.getDate("ngayTao").toLocalDate(),
						rs.getDouble("tongTien")
						);
				list.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public double getDoanhThu(int tu, int den, int nam) {
		double doanhThu = 0;
		String sql;
		PreparedStatement pstmt;
		try {
			if(den - tu < 0) {
				sql = "SELECT SUM(tongTien) AS doanhThu FROM HoaDon WHERE MONTH(ngayTao) BETWEEN ? AND ? AND YEAR(ngayTao) BETWEEN ? AND ?";
				pstmt = ConnectDB.getConnection().prepareStatement(sql);
				pstmt.setInt(1, tu);
				pstmt.setInt(2, den);
				pstmt.setInt(3, nam-1);
				pstmt.setInt(4, nam);
			}
			else {
				sql = "SELECT SUM(tongTien) AS doanhThu FROM HoaDon WHERE MONTH(ngayTao) BETWEEN ? AND ? AND YEAR(ngayTao) = ?";
				pstmt = ConnectDB.getConnection().prepareStatement(sql);
				pstmt.setInt(1, tu);
				pstmt.setInt(2, den);
				pstmt.setInt(3, nam);
				}
			
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			doanhThu = rs.getDouble("doanhThu");
		}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return doanhThu;
	}
	public double getDoanhThu() {
		double doanhThu = 0;
		try {
			String sql = "SELECT SUM(tongTien) AS doanhThu FROM HoaDon";
			Statement stmt = ConnectDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				doanhThu = rs.getDouble("doanhThu");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doanhThu;
	}
	public List<Object[]> timKiemHoaDon(Date tuNgay, Date denNgay, String maNhanVien) {
	    List<Object[]> dsHoaDon = new ArrayList<>();
	    
	    // Nối bảng HoaDon, NhanVien và KhachHang (dùng LEFT JOIN cho KhachHang phòng trường hợp khách lẻ không có tên)
	    StringBuilder sql = new StringBuilder(
	        "SELECT hd.maHoaDon, hd.ngayTao, nv.hoTen as tenNV, kh.hoTen as tenKH, hd.tongTien " +
	        "FROM HoaDon hd " +
	        "JOIN NhanVien nv ON hd.maNhanVien = nv.maNhanVien " +
	        "LEFT JOIN KhachHang kh ON hd.maKhachHang = kh.maKhachHang WHERE 1=1 "
	    );

	    if (tuNgay != null) {
	        sql.append(" AND CAST(hd.ngayTao AS DATE) >= ?");
	    }
	    if (denNgay != null) {
	        sql.append(" AND CAST(hd.ngayTao AS DATE) <= ?");
	    }
	    if (maNhanVien != null && !maNhanVien.equals("ALL")) {
	        sql.append(" AND hd.maNhanVien = ?");
	    }
	    
	    sql.append(" ORDER BY hd.ngayTao DESC");

	    try (Connection con = connectDB.ConnectDB.getConnection();
	         PreparedStatement pst = con.prepareStatement(sql.toString())) {
	        
	        int paramIndex = 1;
	        if (tuNgay != null) { pst.setDate(paramIndex++, tuNgay); }
	        if (denNgay != null) { pst.setDate(paramIndex++, denNgay); }
	        if (maNhanVien != null && !maNhanVien.equals("ALL")) { pst.setString(paramIndex++, maNhanVien); }
	        
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            Object[] row = {
	                rs.getString("maHoaDon"),
	                rs.getTimestamp("ngayTao"), 
	                rs.getString("tenNV"),
	                rs.getString("tenKH") == null ? "Khách lẻ" : rs.getString("tenKH"),
	                rs.getDouble("tongTien")
	            };
	            dsHoaDon.add(row);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsHoaDon;
	}
}
