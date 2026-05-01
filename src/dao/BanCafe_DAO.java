package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectDB.ConnectDB;
import enity.BanCafe;

public class BanCafe_DAO {
	public BanCafe   getBanCafe(String tenBan) {
	    BanCafe maBan = null;
	    new ConnectDB();
	    try {
	        ConnectDB.getInstance().connect();
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
}
