package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import enity.NhanVien;

public class NhanVien_DAO {
	
	public NhanVien TimTheoUser(String tenDangNhap) {
		new ConnectDB();
		NhanVien a=null ;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NhanVien WHERE username = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, tenDangNhap);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        // Vì username là UNIQUE, chỉ cần check 1 lần
	        if (rs.next()) {
	            a = new NhanVien(
	                rs.getString("maNhanVien"), 
	                rs.getString("hoTen"), 
	                rs.getString("username"), 
	                rs.getString("password"), 
	                rs.getString("role"), 
	                rs.getString("soDienThoai"), 
	                rs.getString("diaChi"), 
	                rs.getString("email"), 
	                rs.getDouble("tienLuong"), 
	                rs.getDate("ngaySinh").toLocalDate(), 
	                rs.getString("gioiTinh")
	            );
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
		
	}
	public NhanVien TimTheoUser(String tenDangNhap,String matKhau) {
		new ConnectDB();
		NhanVien a=null ;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NhanVien WHERE username = ? AND password = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, tenDangNhap);
			stmt.setString(2, matKhau);
			
			ResultSet rs = stmt.executeQuery();
			
			// Vì username là UNIQUE, chỉ cần check 1 lần
			if (rs.next()) {
				a = new NhanVien(
						rs.getString("maNhanVien"), 
						rs.getString("hoTen"), 
						rs.getString("username"), 
						rs.getString("password"), 
						rs.getString("role"), 
						rs.getString("soDienThoai"), 
						rs.getString("diaChi"), 
						rs.getString("email"), 
						rs.getDouble("tienLuong"), 
						rs.getDate("ngaySinh").toLocalDate(), 
						rs.getString("gioiTinh")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
		
	}
	public static void main(String[] args) {
		NhanVien_DAO dao = new NhanVien_DAO();
		NhanVien nv = dao.TimTheoUser("admin");
		if (nv != null) {
			System.out.println("Tìm thấy nhân viên: " + nv.getHoTen());
		} else {
			System.out.println("Không tìm thấy nhân viên với username đã cho.");
		}
	}
}
