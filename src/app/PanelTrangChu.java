package app;

import java.awt.Panel;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.HoaDon_DAO;
import enity.HoaDon;

public class PanelTrangChu extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
	private JTable tblHoaDon;
	private DefaultTableModel tableModelHoaDon;
	private JScrollPane scrollPaneHoaDon;
//	private static final LocalDate  NGAYHIENTAI = LocalDate.now();
	public void loadDanhSachHoaDon() {
		tableModelHoaDon.setRowCount(0);
		for (HoaDon hd : hoaDon_DAO.getAllHoaDon()) {
			Object[] rowData = {
					hd.getMaHoaDon(),
					hd.getNgayTao(),
					hd.getNhanVien().getHoTen(),
					hd.getTongTien()
			};
			tableModelHoaDon.addRow(rowData);
		}
		if(tableModelHoaDon.getRowCount() <2) {
			tableModelHoaDon.addRow(new Object[] {"Không có hóa đơn nào"});
		}
	}
	public Panel TrangChu() {
		Panel pnlTrangChu = new Panel();
		pnlTrangChu.setLayout(new BoxLayout(pnlTrangChu, BoxLayout.Y_AXIS));
		tableModelHoaDon = new DefaultTableModel();
		tblHoaDon = new JTable(tableModelHoaDon);
		tableModelHoaDon.addColumn("Mã Hóa Đơn");
		tableModelHoaDon.addColumn("Ngày Tạo");
		tableModelHoaDon.addColumn("Nhân Viên");
		tableModelHoaDon.addColumn("Tổng Tiền");
		scrollPaneHoaDon = new JScrollPane(tblHoaDon);
		loadDanhSachHoaDon();
		scrollPaneHoaDon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPaneHoaDon.setPreferredSize(new Dimension(800, 300));
		pnlTrangChu.add(scrollPaneHoaDon);
		return pnlTrangChu;
	}
	public static void main(String[] args) {
		PanelTrangChu panel = new PanelTrangChu();
		JFrame frame = new JFrame("Quản Lý Bán Hàng Coffee");
//		JPanel pnlMain = new JPanel();
//		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
//		pnlMain.add(panel.TrangChu());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.add(panel.TrangChu());
		frame.setVisible(true);
	}
}
