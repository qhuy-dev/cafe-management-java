package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dao.NhanVien_DAO;
import enity.NhanVien;

public class CustomerUI extends JFrame implements ActionListener, MouseListener {
	Dimension kichThuocMan = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) (kichThuocMan.getWidth() * 0.85);
	int height = (int) (kichThuocMan.getHeight() * 0.9);
	JPanel pnlNorth, pnlWest;
	ImageIcon logo;
	Image imgLogo;
	JLabel lblLogo, lblTieuDe;
	JButton btnTrangChu, btnBanHang, btnSanPham, btnKhachHang, btnNhanVien, btnThongKe;
	JTextField txtUsername, txtPassword;
	static NhanVien nhanVien;
	NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();

	public CustomerUI() {
		setTitle("Quản lý khách hàng");
		setLayout(new BorderLayout());

		pnlNorth = new JPanel();
		pnlNorth.setLayout(new BorderLayout());
		pnlNorth.setBackground(new Color(253, 243, 227));
		pnlNorth.setPreferredSize(new Dimension(width, 160));

		pnlWest = new JPanel();
		pnlWest.setLayout(new FlowLayout(FlowLayout.LEFT, 10, (int) (height * 0.06)));
		pnlWest.setBackground(new Color(224, 192, 151));
		pnlWest.setPreferredSize(new Dimension(200, height));

		JPanel pnlLogo = new JPanel();
		pnlLogo.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 7));
		pnlLogo.setPreferredSize(new Dimension(200, height));
		pnlLogo.setOpaque(false);

		JPanel pnlTen = new JPanel();
		pnlTen.setLayout(new BorderLayout());

		logo = new ImageIcon("imgs/logo2.png");
		imgLogo = logo.getImage().getScaledInstance(160, 145, Image.SCALE_SMOOTH);
		lblLogo = new JLabel(new ImageIcon(imgLogo));
		pnlLogo.add(lblLogo);

		ImageIcon khung = new ImageIcon("imgs/cloud.png");
		Image imgKhung = khung.getImage().getScaledInstance(320, 150, Image.SCALE_SMOOTH);
		lblTieuDe = new JLabel("Khách Hàng", new ImageIcon(imgKhung), JLabel.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTieuDe.setForeground(Color.white);
		lblTieuDe.setHorizontalTextPosition(JLabel.CENTER);
		lblTieuDe.setVerticalTextPosition(JLabel.CENTER);

		ImageIcon logoTen = new ImageIcon("imgs/plant.png");
		Image imgTen = logoTen.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
		String tenNV = nhanVien != null ? nhanVien.getHoTen() : "";
		JLabel lblTen;
		lblTen = new JLabel(tenNV, new ImageIcon(imgTen), JLabel.CENTER);
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTen.setForeground(new Color(8, 69, 126));
		lblTen.setHorizontalTextPosition(JLabel.CENTER);
		lblTen.setVerticalTextPosition(JLabel.NORTH);

		ImageIcon logoRole = new ImageIcon("imgs/star.png");
		Image imgRole = logoRole.getImage().getScaledInstance(80, 40, Image.SCALE_SMOOTH);
		String chucVu = nhanVien != null ? nhanVien.getRole() : "";
		JLabel lblChucVu;
		lblChucVu = new JLabel(chucVu, new ImageIcon(imgRole), JLabel.LEFT);
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChucVu.setHorizontalTextPosition(JLabel.RIGHT);
		lblChucVu.setVerticalTextPosition(JLabel.CENTER);

		ImageIcon iconTC = new ImageIcon("imgs/home.png");
		Image imgTC = iconTC.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnTrangChu = new JButton("Trang Chủ", new ImageIcon(imgTC));
		btnTrangChu.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnTrangChu.setForeground(Color.WHITE);
		btnTrangChu.setContentAreaFilled(false);
		btnTrangChu.setBorderPainted(false);
		btnTrangChu.setFocusPainted(false);
		btnTrangChu.addActionListener(this);
		pnlWest.add(btnTrangChu);

		ImageIcon iconBH = new ImageIcon("imgs/banHang.png");
		Image imgBH = iconBH.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnBanHang = new JButton("Bán Hàng", new ImageIcon(imgBH));
		btnBanHang.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnBanHang.setForeground(Color.WHITE);
		btnBanHang.setContentAreaFilled(false);
		btnBanHang.setBorderPainted(false);
		btnBanHang.setFocusPainted(false);
		btnBanHang.addActionListener(this);
		pnlWest.add(btnBanHang);

		ImageIcon iconSP = new ImageIcon("imgs/sanPham.png");
		Image imgSP = iconSP.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnSanPham = new JButton("Sản Phẩm", new ImageIcon(imgSP));
		btnSanPham.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSanPham.setForeground(Color.WHITE);
		btnSanPham.setContentAreaFilled(false);
		btnSanPham.setBorderPainted(false);
		btnSanPham.setFocusPainted(false);
		btnSanPham.addActionListener(this);
		pnlWest.add(btnSanPham);

		ImageIcon iconKH = new ImageIcon("imgs/khachHang.png");
		Image imgKH = iconKH.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnKhachHang = new JButton("Khách Hàng", new ImageIcon(imgKH));
		btnKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnKhachHang.setForeground(Color.WHITE);
		btnKhachHang.setContentAreaFilled(false);
		btnKhachHang.setBorderPainted(false);
		btnKhachHang.setFocusPainted(false);
		btnKhachHang.addActionListener(this);
		pnlWest.add(btnKhachHang);

		ImageIcon iconNV = new ImageIcon("imgs/nhanVien.png");
		Image imgNV = iconNV.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnNhanVien = new JButton("Nhân Viên", new ImageIcon(imgNV));
		btnNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNhanVien.setForeground(Color.WHITE);
		btnNhanVien.setContentAreaFilled(false);
		btnNhanVien.setBorderPainted(false);
		btnNhanVien.setFocusPainted(false);
		btnNhanVien.addActionListener(this);
		pnlWest.add(btnNhanVien);

		ImageIcon iconTK = new ImageIcon("imgs/thongKe.png");
		Image imgTK = iconTK.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnThongKe = new JButton("Thống Kê", new ImageIcon(imgTK));
		btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setContentAreaFilled(false);
		btnThongKe.setBorderPainted(false);
		btnThongKe.setFocusPainted(false);
		btnThongKe.addActionListener(this);
		pnlWest.add(btnThongKe);

		ImageIcon iconTrangTri = new ImageIcon("imgs/trangTri.png");
		Image imgTT = iconTrangTri.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
		pnlWest.add(new JLabel(new ImageIcon(imgTT)));

		pnlTen.add(lblChucVu, BorderLayout.NORTH);
		pnlTen.add(lblTen);
		pnlTen.setOpaque(false);

		pnlNorth.add(pnlLogo, BorderLayout.WEST);
		pnlNorth.add(pnlTen, BorderLayout.EAST);
		pnlNorth.add(lblTieuDe);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlWest, BorderLayout.WEST);

		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		PanelCustomer panelCustomer = new PanelCustomer();
		add(panelCustomer.KhachHang(), BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnTrangChu) {
			lblTieuDe.setText(btnTrangChu.getText().toUpperCase());
			PanelTrangChu pnlTrangChu = new PanelTrangChu();
			pnlTrangChu.setMaximumSize(new Dimension(pnlTrangChu.getWidth(), 100));
			add(pnlTrangChu.TrangChu(), BorderLayout.CENTER);
		} else if (o == btnBanHang) {
			lblTieuDe.setText(btnBanHang.getText().toUpperCase());
		} else if (o == btnKhachHang) {
			lblTieuDe.setText("Khách Hàng");
			new CustomerUI().setVisible(true); dispose();
		} else if (o == btnSanPham) {
			lblTieuDe.setText("Sản Phẩm");
			new ProductUI().setVisible(true); dispose();
		}
	}

	@Override public void mouseClicked(MouseEvent e)  {}
	@Override public void mousePressed(MouseEvent e)  {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e)  { ((JButton) e.getSource()).setForeground(new Color(255, 220, 100)); }
	@Override public void mouseExited(MouseEvent e)   { ((JButton) e.getSource()).setForeground(Color.WHITE); }

	public static void main(String[] args) {
		new CustomerUI().setVisible(true);
	}
}
