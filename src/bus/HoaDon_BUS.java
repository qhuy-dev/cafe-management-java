package bus;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.HoaDon_DAO;
import enity.HoaDon;

public class HoaDon_BUS {
	private static ArrayList<HoaDon> listHoaDon;
	public double getDoanhThu(LocalDate ngay) {
		double doanhThu = 0;
		listHoaDon = new HoaDon_DAO().getHoaDon(ngay);
		for (HoaDon hd : listHoaDon) {
			doanhThu += hd.getTongTien();
		}
		return doanhThu;
	}
//	public double getDoanhThuTheoThang(int tu,int den, int nam) {
//		double doanhThu = 0;
//		listHoaDon = new HoaDon_DAO().getAllHoaDon();
//		if(den - tu < 0) {
//			for (HoaDon hd : listHoaDon) {
//				if((hd.getNgayTao().getMonthValue() >= tu && hd.getNgayTao().getYear() == nam-1) || (hd.getNgayTao().getMonthValue() <= den && hd.getNgayTao().getYear() == nam)) {
//					doanhThu += hd.getTongTien();
//				}
//			}
//		}
//		else {
//			for (HoaDon hd : listHoaDon) {
//				if(hd.getNgayTao().getMonthValue() >= tu && hd.getNgayTao().getMonthValue() <= den && hd.getNgayTao().getYear() == nam) {
//					doanhThu += hd.getTongTien();
//				}
//			}
//		}
//		return doanhThu;
//	}
//	public double getDoanhThuTheoThang() {
//		double doanhThu = 0;
//		listHoaDon = new HoaDon_DAO().getAllHoaDon();
//		for (HoaDon hd : listHoaDon) {
//			doanhThu += hd.getTongTien();
//		}
//		return doanhThu;
//	}
	public int getSoLuongHoaDon(int tu,int den, int nam) {
		int soLuong = 0;
		listHoaDon = new HoaDon_DAO().getAllHoaDon();
		if(den - tu < 0) {
			for (HoaDon hd : listHoaDon) {
				if((hd.getNgayTao().getMonthValue() >= tu && hd.getNgayTao().getYear() == nam-1) || (hd.getNgayTao().getMonthValue() <= den && hd.getNgayTao().getYear() == nam)) {
					soLuong++;
				}
			}
		}
		else {
			for (HoaDon hd : listHoaDon) {
				if(hd.getNgayTao().getMonthValue() >= tu && hd.getNgayTao().getMonthValue() <= den && hd.getNgayTao().getYear() == nam) {
					soLuong++;
				}
			}
		}
		return soLuong;
	}
	public int getSoLuongHoaDon() {
		return new HoaDon_DAO().getAllHoaDon().size();
	}
	public String getDoanhThu(int tu,int den, int nam) {
		DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");
		Double doanhThuSo = new HoaDon_DAO().getDoanhThu(tu, den, nam);
		return decimalFormat.format(doanhThuSo);
	}
	public String getDoanhThu() {
		DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");
		Double doanhThuSo = new HoaDon_DAO().getDoanhThu();
		return decimalFormat.format(doanhThuSo);
	}
	
	
}
