package bus;

import java.util.ArrayList;

import dao.CTHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.SanPham_DAO;
import enity.CTHoaDon;
import enity.HoaDon;
import enity.SanPham;

public class CTHoaDon_BUS {
	private ArrayList<CTHoaDon> listCTHoaDon;
	public int soLuongCTHoaDon() {
		return listCTHoaDon.size();
	}
//	public int soLuongSanPhamDaBan(int tu,int den, int nam) {
//		int soLuong = 0;
//		ArrayList<HoaDon> listHoaDon = new HoaDon_DAO().getAllHoaDon();
//		ArrayList<HoaDon> listHoaDon1 = new ArrayList<>();
//		listCTHoaDon = new CTHoaDon_DAO().getAllCTHoaDon();
//		if(den - tu < 0) {
//			for (HoaDon hd : listHoaDon) {
//				if((hd.getNgayTao().getMonthValue() >= tu && hd.getNgayTao().getYear() == nam-1) || (hd.getNgayTao().getMonthValue() <= den && hd.getNgayTao().getYear() == nam)) {
//					listHoaDon1.add(hd);
//				}
//			}
//		}
//		else {
//			for (HoaDon hd : listHoaDon) {
//				if(hd.getNgayTao().getMonthValue() >= tu && hd.getNgayTao().getMonthValue() <= den && hd.getNgayTao().getYear() == nam) {
//					listHoaDon1.add(hd);
//				}
//			}
//		}
//		for (CTHoaDon cthd : listCTHoaDon) {
//			for (HoaDon hd : listHoaDon1) {
//				if(cthd.getHoaDon().getMaHoaDon().equals(hd.getMaHoaDon())) {
//					soLuong += cthd.getSoLuong();
//				}
//			}
//		}
//		return soLuong;
//	}
//	public int soLuongSanPhamDaBan() {
//		int soLuong = 0;
//		listCTHoaDon = new CTHoaDon_DAO().getAllCTHoaDon();
//		for (CTHoaDon cthd : listCTHoaDon) {
//			soLuong += cthd.getSoLuong();
//		}
//		
//		return soLuong;
//	}
//	public SanPham getSanPhamBanChay(int tu,int den, int nam) {
//		SanPham sanPhamBanChay = null;
//		int maxSoLuong = 0;
//		ArrayList<SanPham> listSanPham = new SanPham_DAO().danhSachSanPham();
//		ArrayList<HoaDon> listHoaDon = new HoaDon_DAO().getAllHoaDon();
//		ArrayList<HoaDon> listHoaDon1 = new ArrayList<>();
//		listCTHoaDon = new CTHoaDon_DAO().getAllCTHoaDon();
//		if(den - tu < 0) {
//			for (HoaDon hd : listHoaDon) {
//				if((hd.getNgayTao().getMonthValue() >= tu && hd.getNgayTao().getYear() == nam-1) || (hd.getNgayTao().getMonthValue() <= den && hd.getNgayTao().getYear() == nam)) {
//					listHoaDon1.add(hd);
//				}
//			}
//		}
//		else {
//			for (HoaDon hd : listHoaDon) {
//				if(hd.getNgayTao().getMonthValue() >= tu && hd.getNgayTao().getMonthValue() <= den && hd.getNgayTao().getYear() == nam) {
//					listHoaDon1.add(hd);
//				}
//			}
//		}
//		for (CTHoaDon cthd : listCTHoaDon) {
//			for (HoaDon hd : listHoaDon1) {
//				if(cthd.getHoaDon().getMaHoaDon().equals(hd.getMaHoaDon())) {
//					if (cthd.getSoLuong() > maxSoLuong) {
//						maxSoLuong = cthd.getSoLuong();
//						sanPhamBanChay = cthd.getSanPham();
//					}
//				}
//			}
//		}
//		for (SanPham sp : listSanPham) {
//			if (sp.getMaSanPham().equals(sanPhamBanChay.getMaSanPham())) {
//				sanPhamBanChay = sp;
//				break;
//			}
//		}
//		
//		return sanPhamBanChay;
//	}
//	public SanPham getSanPhamBanChay() {
//		SanPham sanPhamBanChay = null;
//		listCTHoaDon = new CTHoaDon_DAO().getAllCTHoaDon();
//		ArrayList<SanPham> listSanPham = new SanPham_DAO().danhSachSanPham();
//		int maxSoLuong = 0;
//		
//		for (CTHoaDon cthd : listCTHoaDon) {
//			if (cthd.getSoLuong() > maxSoLuong) {
//				maxSoLuong = cthd.getSoLuong();
//				sanPhamBanChay = cthd.getSanPham();
//			}
//		}
//		for (SanPham sp : listSanPham) {
//			if (sp.getMaSanPham().equals(sanPhamBanChay.getMaSanPham())) {
//				sanPhamBanChay = sp;
//				break;
//			}
//		}
//		
//		return sanPhamBanChay;
//	}
	public ArrayList<CTHoaDon> getAllCTHoaDon() {
		return new CTHoaDon_DAO().getAllCTHoaDon();
	}
	public String getTenSanPhamBanChay(int tu,int den, int nam) {
		SanPham sanPhamBanChay = new CTHoaDon_DAO().getSanPhamBanNhieuNhat(tu, den, nam);
		ArrayList<SanPham> listSanPham = new SanPham_DAO().danhSachSanPham();
		for (SanPham sp : listSanPham) {
			if (sp.getMaSanPham().equals(sanPhamBanChay.getMaSanPham())) {
				sanPhamBanChay = sp;
				break;
			}
		}
		return sanPhamBanChay.getTenSanPham();
	}
	public String getTenSanPhamBanChay() {
		SanPham sanPhamBanChay = new CTHoaDon_DAO().getSanPhamBanNhieuNhat();
		ArrayList<SanPham> listSanPham = new SanPham_DAO().danhSachSanPham();
		for (SanPham sp : listSanPham) {
			if (sp.getMaSanPham().equals(sanPhamBanChay.getMaSanPham())) {
				sanPhamBanChay = sp;
				break;
			}
		}
		return sanPhamBanChay.getTenSanPham();
	}
	public String getSoLuongDaBan(int tu,int den, int nam) {
		int soLuong = new CTHoaDon_DAO().getsoLuongSanPhamDaBan(tu, den, nam);
		return String.valueOf(soLuong);
	}
	public String getSoLuongDaBan() {
		int soLuong = new CTHoaDon_DAO().getsoLuongSanPhamDaBan();
		return String.valueOf(soLuong);
	}
}
