package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import connectDB.ConnectDB;

public class Ban_DAO {

    // Hàm cập nhật trạng thái bàn (0: Trống, 1: Có khách) xuống CSDL
    public boolean updateTrangThaiBan(String maBan, int trangThai) {
        String sql = "UPDATE TableCafe SET trangThai = ? WHERE maBan = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setInt(1, trangThai);
            pst.setString(2, maBan);
            return pst.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}