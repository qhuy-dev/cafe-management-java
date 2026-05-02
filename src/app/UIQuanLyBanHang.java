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

public class UIQuanLyBanHang extends JFrame implements ActionListener,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Dimension kichThuocMan = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) (kichThuocMan.getWidth()*0.85);
	int height = (int) (kichThuocMan.getHeight()*0.9);
	JPanel pnlNorth,pnlWest,pnlTrangChu;
	ImageIcon logo;
	Image imgLogo;
	JLabel lblLogo,lblTieuDe;
	JButton btnTrangChu,btnBanHang,btnSanPham,btnKhachHang,btnNhanVien,btnThongKe;
	JTextField txtUsername,txtPassword;
	static NhanVien nhanVien;
	NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
//	JButton btnTrangChu,btnBanHang,btnSanPham;
//	Color w,n;
	public UIQuanLyBanHang(String txtUsername, String txtPassword) {
		nhanVien = nhanVien_DAO.TimTheoUser(txtUsername);
		new UIQuanLyBanHang().setVisible(true);
	}
	public UIQuanLyBanHang() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());

	    pnlNorth = new JPanel();
	    pnlNorth.setLayout(new BorderLayout());
	    pnlNorth.setBackground(new Color(253, 243, 227));
	    pnlNorth.setPreferredSize(new Dimension(width, 160));

	    pnlWest = new JPanel();
	    pnlWest.setLayout(new FlowLayout(FlowLayout.LEFT, 10, (int)(height*0.06)));
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
	    lblTieuDe = new JLabel("Trang Chủ",new ImageIcon(imgKhung),JLabel.CENTER);
	    lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 22));
	    lblTieuDe.setForeground(Color.white);
	    lblTieuDe.setHorizontalTextPosition(JLabel.CENTER);
	    lblTieuDe.setVerticalTextPosition(JLabel.CENTER);
	    
	    ImageIcon logoTen = new ImageIcon("imgs/plant.png");
	    Image imgTen = logoTen.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
	    String tenNV = nhanVien != null ? nhanVien.getHoTen() : "";
	    JLabel lblTen;
	    lblTen = new JLabel(tenNV,new ImageIcon(imgTen),JLabel.CENTER);
//	    System.out.println(nhanVien.getHoTen());
	    lblTen.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblTen.setForeground(new Color(8, 69, 126));
	    lblTen.setHorizontalTextPosition(JLabel.CENTER);
	    lblTen.setVerticalTextPosition(JLabel.NORTH);
	    
	    ImageIcon logoRole = new ImageIcon("imgs/star.png");
	    Image imgRole = logoRole.getImage().getScaledInstance(80, 40, Image.SCALE_SMOOTH);
	    String chucVu = nhanVien != null ? nhanVien.getRole() : "";
	    JLabel lblChucVu;
	    lblChucVu = new JLabel(chucVu,new ImageIcon(imgRole),JLabel.LEFT);
//	    System.out.println(nhanVien.getHoTen());
	    lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 16));
//	    lblTen.setForeground(Color.white);
	    lblChucVu.setHorizontalTextPosition(JLabel.RIGHT);
	    lblChucVu.setVerticalTextPosition(JLabel.CENTER);
	    
	    ImageIcon iconTC = new ImageIcon("imgs/home.png");
	    Image imgTC = iconTC.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    btnTrangChu = new JButton("Trang Chủ",new ImageIcon(imgTC));
	    btnTrangChu.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnTrangChu.setBackground(new Color(60, 40, 20));
	    btnTrangChu.setForeground(Color.WHITE);
	    btnTrangChu.setContentAreaFilled(false); // Xóa nền mặc định
	    btnTrangChu.setBorderPainted(false);     // Xóa viền nút
	    btnTrangChu.setFocusPainted(false);      // Xóa khung focus khi click
	    btnTrangChu.addActionListener(this);
	    pnlWest.add(btnTrangChu);
	    
	    PanelTrangChu trangChu = new PanelTrangChu();
	    
	    pnlTrangChu = trangChu.TrangChu();
		pnlTrangChu.setMaximumSize(new Dimension(trangChu.getWidth(), 100));
	    
	    ImageIcon iconBH = new ImageIcon("imgs/banHang.png");
	    Image imgBH = iconBH.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    btnBanHang = new JButton("Bán Hàng", new ImageIcon(imgBH));
	    btnBanHang.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnBanHang.setBackground(new Color(60, 40, 20));
	    btnBanHang.setForeground(Color.WHITE);
	    btnBanHang.setContentAreaFilled(false); // Xóa nền mặc định
	    btnBanHang.setBorderPainted(false);     // Xóa viền nút
	    btnBanHang.setFocusPainted(false);      // Xóa khung focus khi click
	    btnBanHang.addActionListener(this);
	    pnlWest.add(btnBanHang);
	    
	    
	    
	    ImageIcon iconSP = new ImageIcon("imgs/sanPham.png");
	    Image imgSP = iconSP.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    btnSanPham = new JButton("Sản Phẩm", new ImageIcon(imgSP));
	    btnSanPham.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnSanPham.setBackground(new Color(60, 40, 20));
	    btnSanPham.setForeground(Color.WHITE);
	    btnSanPham.setContentAreaFilled(false); // Xóa nền mặc định
	    btnSanPham.setBorderPainted(false);     // Xóa viền nút
	    btnSanPham.setFocusPainted(false);      // Xóa khung focus khi click
	    btnSanPham.addActionListener(this);
	    pnlWest.add(btnSanPham);
	    
	    
	    
	    
	    ImageIcon iconKH = new ImageIcon("imgs/khachHang.png");
	    Image imgKH = iconKH.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    btnKhachHang = new JButton("Khách Hàng", new ImageIcon(imgKH));
	    btnKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnKhachHang.setBackground(new Color(60, 40, 20));
	    btnKhachHang.setForeground(Color.WHITE);
	    btnKhachHang.setContentAreaFilled(false); // Xóa nền mặc định
	    btnKhachHang.setBorderPainted(false);     // Xóa viền nút
	    btnKhachHang.setFocusPainted(false);      // Xóa khung focus khi click
	    btnKhachHang.addActionListener(this);
	    pnlWest.add(btnKhachHang);
	    
	    
	    
	    ImageIcon iconNV = new ImageIcon("imgs/nhanVien.png");
	    Image imgNV = iconNV.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    btnNhanVien = new JButton("Nhân Viên", new ImageIcon(imgNV));
	    btnNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnNhanVien.setBackground(new Color(60, 40, 20));
	    btnNhanVien.setForeground(Color.WHITE);
	    btnNhanVien.setContentAreaFilled(false); // Xóa nền mặc định
	    btnNhanVien.setBorderPainted(false);     // Xóa viền nút
	    btnNhanVien.setFocusPainted(false);      // Xóa khung focus khi click
	    btnNhanVien.addActionListener(this);
	    pnlWest.add(btnNhanVien);
	    
	    
	    
	    
	    ImageIcon iconTK = new ImageIcon("imgs/thongKe.png");
	    Image imgTK = iconTK.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    btnThongKe = new JButton("Thống Kê", new ImageIcon(imgTK));
	    btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnThongKe.setBackground(new Color(60, 40, 20));
	    btnThongKe.setForeground(Color.WHITE);
	    btnThongKe.setContentAreaFilled(false); // Xóa nền mặc định
	    btnThongKe.setBorderPainted(false);     // Xóa viền nút
	    btnThongKe.setFocusPainted(false);      // Xóa khung focus khi click
	    btnThongKe.addActionListener(this);
	    pnlWest.add(btnThongKe);
	    
	    
	    ImageIcon iconTrangTri = new ImageIcon("imgs/trangTri.png");
	    Image imgTT = iconTrangTri.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
	    pnlWest.add(new JLabel(new ImageIcon(imgTT)));
	    
	    pnlTen.add(lblChucVu, BorderLayout.NORTH);
	    pnlTen.add(lblTen);
	    pnlTen.setOpaque(false);
	    
	    pnlNorth.add(pnlLogo,BorderLayout.WEST);
	    pnlNorth.add(pnlTen,BorderLayout.EAST);
	    pnlNorth.add(lblTieuDe);
	    
	    
	    add(pnlNorth, BorderLayout.NORTH);

	   
	    
	    
	    
	    
	    add(pnlWest, BorderLayout.WEST);

	    setSize(width, height);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	public static void main(String[] args) {
		new UIQuanLyBanHang().setVisible(true);
//		new UIQuanLyBanHang("admin", "123");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		btnTrangChu.setOpaque(false);
		btnBanHang.setOpaque(false);
		btnSanPham.setOpaque(false);
		btnKhachHang.setOpaque(false);
		btnNhanVien.setOpaque(false);
		btnThongKe.setOpaque(false);
		if(o== btnTrangChu) {
			
			btnTrangChu.setOpaque(true);
			lblTieuDe.setText(btnTrangChu.getText().toUpperCase());
			
			add(pnlTrangChu, BorderLayout.CENTER);
		}
		else if(o== btnBanHang) {
			btnBanHang.setOpaque(true);
			lblTieuDe.setText(btnBanHang.getText().toUpperCase());
		}
		else if(o== btnSanPham) {
			btnSanPham.setOpaque(true);
			lblTieuDe.setText(btnSanPham.getText().toUpperCase());
		}
		else if(o== btnKhachHang) {
			btnKhachHang.setOpaque(true);
			lblTieuDe.setText(btnKhachHang.getText().toUpperCase());
		}
		else if(o== btnNhanVien) {
			btnNhanVien.setOpaque(true);
			lblTieuDe.setText(btnNhanVien.getText().toUpperCase());
		}
		else if(o== btnThongKe) {
			btnThongKe.setOpaque(true);
			lblTieuDe.setText(btnThongKe.getText().toUpperCase());
		}
//		pnlWest.revalidate();
	    pnlWest.repaint();  //sẽ gửi một yêu cầu "vẽ lại" vào hàng đợi
//	    this.revalidate();
//	    this.repaint();
	}

}
