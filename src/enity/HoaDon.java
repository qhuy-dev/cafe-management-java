package enity;

import java.time.LocalDateTime;

public class HoaDon {
	private String maHoaDon;
    private LocalDateTime ngayTao;
    
    // Quan hệ: Mỗi hóa đơn thuộc về 1 khách hàng, 1 nhân viên, 1 bàn
    private KhachHang khachHang; 
    private NhanVien nhanVien;
    private BanCafe ban;
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public LocalDateTime getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(LocalDateTime ngayTao) {
		this.ngayTao = ngayTao;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public BanCafe getBan() {
		return ban;
	}
	public void setBan(BanCafe ban) {
		this.ban = ban;
	}
}
