package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import enity.KhachHang;
import enity.SanPham;

public class KhachHang_DAO {
	public List<KhachHang> danhSachKhachHang(){
		 List<KhachHang> list = new ArrayList<>();
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT * FROM KhachHang ORDER BY maKhachHang";
			PreparedStatement stmt=con.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				list.add(new KhachHang(
						rs.getString("maKhachHang"),
						rs.getString("hoTen"),
						rs.getString("soDienThoai")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<KhachHang> timKiemTheoTen(String kw){
		 List<KhachHang> list = new ArrayList<>();
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT * FROM KhachHang WHERE hoTen LIKE ?";
			PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setString(1, kw);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				list.add(new KhachHang(
						rs.getString("maKhachHang"),
						rs.getString("hoTen"),
						rs.getString("soDienThoai")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<KhachHang> timKiemTheoSDT(String kw){
		 List<KhachHang> list = new ArrayList<>();
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT * FROM KhachHang WHERE soDienThoai LIKE ?";
			PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setString(1, kw);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				list.add(new KhachHang(
						rs.getString("maKhachHang"),
						rs.getString("hoTen"),
						rs.getString("soDienThoai")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean themKhachHang(KhachHang kh) {
		try {
			Connection con=ConnectDB.getConnection();
			String sql="INSERT INTO KhachHang(maKhachHang, hoTen, soDienThoai) VALUES (?,?,?)";
			PreparedStatement stmt =con.prepareStatement(sql);
			stmt.setString(1, kh.getMaKhachHang());
			stmt.setString(2, kh.getHoTen());
			stmt.setString(3, kh.getSoDienThoai());
			return stmt.executeUpdate()>0;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean chinhSuaKhachHang(KhachHang kh) {
		try {
			Connection con=ConnectDB.getConnection();
			String sql="UPDATE KhachHang SET hoTen=? , soDienThoai =? WHERE maKhachHang=?";
			PreparedStatement stmt =con.prepareStatement(sql);
			stmt.setString(3, kh.getMaKhachHang());
			stmt.setString(1, kh.getHoTen());
			stmt.setString(2, kh.getSoDienThoai());
			return stmt.executeUpdate()>0;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean xoaKhachHang(String maKhachHang) {
		try {
			Connection con=ConnectDB.getConnection();
			String sql="DELETE FROM KhachHang WHERE maKhachHang=?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, maKhachHang);
			return stmt.executeUpdate()>0;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public KhachHang TimKiemTheoMa(String maKhachHang) {
		KhachHang a=null;
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT * FROM KhachHang WHERE maKhachHang = ?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,maKhachHang);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				a=new KhachHang(
						rs.getString("maKhachHang"),
						rs.getString("hoTen"),
						rs.getString("soDienThoai")
						);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}return a;
		
	}
}
