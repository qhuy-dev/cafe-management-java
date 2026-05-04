package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import enity.BanCafe;
import enity.SanPham;

public class BanCafe_DAO {
	public BanCafe   getBanCafe(String tenBan) {
	    BanCafe maBan = null;
	    try {
	        String sql = "SELECT maBan FROM TableCafe WHERE tenBan = ?";
	        PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
	        stmt.setString(1, tenBan);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            maBan = new BanCafe(rs.getString("maBan"), rs.getString("tenBan"), 1);
	            		
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return maBan;
	}
	public List<SanPham> getAllSanPhamBan() {
        List<SanPham> dsSanPham = new ArrayList<SanPham>();
        // Chỉ lấy những sản phẩm có trangThai = 1 (Còn bán)
        String sql = "SELECT maSanPham, tenSanPham, giaTien, trangThai FROM SanPham WHERE trangThai = 1";
        
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
             
            while (rs.next()) {
                String ma = rs.getString("maSanPham");
                String ten = rs.getString("tenSanPham");
                double gia = rs.getDouble("giaTien");
                boolean trangThai = rs.getBoolean("trangThai");
                
                // Giả định constructor của class SanPham là SanPham(ma, ten, gia)
                // Cậu điều chỉnh lại cho khớp với constructor trong class SanPham của nhóm nhé
                SanPham sp = new SanPham(ma, ten, gia, trangThai); 
                dsSanPham.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsSanPham;
    }
	public List<BanCafe> getAllTable() {
	    List<BanCafe> dsBan = new ArrayList<>();
	    String sql = "SELECT maBan, tenBan, trangThai FROM TableCafe";
	    
	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement pst = con.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {
	         
	        while (rs.next()) {
	            String maBan = rs.getString("maBan");
	            String tenBan = rs.getString("tenBan");
	            int trangThai = rs.getInt("trangThai");
	            
	            // Khởi tạo đối tượng BanCafe và thêm vào list
	            dsBan.add(new BanCafe(maBan, tenBan, trangThai));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsBan;
	}
}
