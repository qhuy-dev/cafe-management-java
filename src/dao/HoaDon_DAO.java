package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

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
						new KhachHang_DAO().timKiemTheoMa(rs.getString("maKhachHang")),
						new NhanVien_DAO().timTheoMa(rs.getString("maNhanVien")),
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
			String sql = "SELECT * FROM HoaDon WHERE CAST(ngayTao AS DATE) = ?";
			PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
			stmt.setObject(1, ngay);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(
						rs.getString("maHoaDon"),
						new KhachHang_DAO().timKiemTheoMa(rs.getString("maKhachHang")),
						new NhanVien_DAO().timTheoMa(rs.getString("maNhanVien")),
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
						new KhachHang_DAO().timKiemTheoMa(rs.getString("maKhachHang")),
						new NhanVien_DAO().timTheoMa(rs.getString("maNhanVien")),
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
	                rs.getString("tenKH") == null ? "Khách lạ" : rs.getString("tenKH"),
	                rs.getDouble("tongTien")
	            };
	            dsHoaDon.add(row);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsHoaDon;
	}
	// Hàm 1: Phát sinh mã hóa đơn tự động (HD + 3 số)
	public String getNextMaHoaDon() {
        String nextMa = "HD001";
        String sql = "SELECT TOP 1 maHoaDon FROM HoaDon ORDER BY LEN(maHoaDon) DESC, maHoaDon DESC";
        
        // Dùng try-with-resources cho lệnh SELECT là an toàn
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
             
            if (rs.next()) {
                String lastMa = rs.getString("maHoaDon"); 
                int so = Integer.parseInt(lastMa.substring(2)); 
                so++; 
                nextMa = String.format("HD%03d", so); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextMa;
    }

    // Hàm 2: Xử lý lưu hóa đơn xuống Database bằng Transaction
	// Thêm tham số String maKhachHang vào cuối
	public boolean thanhToan(String maHoaDon, String maNhanVien, String maBan, double tongTien, DefaultTableModel modelCart, String maKhachHang) {
	    Connection con = null;
	    try {
	        con = ConnectDB.getConnection();
	        con.setAutoCommit(false); 

	        // 1. Thêm Hóa Đơn (Đã sửa lại câu SQL nhận 5 tham số dấu ?)
	        String sqlHD = "INSERT INTO HoaDon (maHoaDon, maNhanVien, maBan, tongTien, maKhachHang) VALUES (?, ?, ?, ?, ?)";
	        try (PreparedStatement pstHD = con.prepareStatement(sqlHD)) {
	            pstHD.setString(1, maHoaDon);
	            pstHD.setString(2, maNhanVien);
	            pstHD.setString(3, maBan);
	            pstHD.setDouble(4, tongTien);
	            
	            // Nếu có mã KH thì truyền vào, nếu không có (khách lẻ) thì set NULL
	            if (maKhachHang != null && !maKhachHang.trim().isEmpty()) {
	                pstHD.setString(5, maKhachHang);
	            } else {
	                pstHD.setNull(5, Types.NVARCHAR);
	            }
	            pstHD.executeUpdate();
	        }

	        // 2. Thêm Chi Tiết Hóa Đơn (Giữ nguyên)
	        String sqlCT = "INSERT INTO ChiTietHoaDon (maHoaDon, maSanPham, soLuong, giaTien) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement pstCT = con.prepareStatement(sqlCT)) {
	            for (int i = 0; i < modelCart.getRowCount(); i++) {
	                pstCT.setString(1, maHoaDon);
	                pstCT.setString(2, modelCart.getValueAt(i, 0).toString()); 
	                pstCT.setInt(3, Integer.parseInt(modelCart.getValueAt(i, 2).toString())); 
	                pstCT.setDouble(4, Double.parseDouble(modelCart.getValueAt(i, 3).toString())); 
	                pstCT.addBatch(); 
	            }
	            pstCT.executeBatch(); 
	        }

	        // 3. Cập nhật trạng thái bàn về 0 (Giữ nguyên)
	        String sqlBan = "UPDATE TableCafe SET trangThai = 0 WHERE maBan = ?";
	        try (PreparedStatement pstBan = con.prepareStatement(sqlBan)) {
	            pstBan.setString(1, maBan);
	            pstBan.executeUpdate();
	        }

	        con.commit(); 
	        return true;
	        
	    } catch (Exception e) {
	        if (con != null) {
	            try { con.rollback(); } catch(Exception ex) {}
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (con != null) {
	            try { con.setAutoCommit(true); con.close(); } catch(Exception ex) {}
	        }
	    }
	}
}
