package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

public class UIQuanLyBanHang extends JFrame implements ActionListener,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Dimension kichThuocMan = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) (kichThuocMan.getWidth()*0.85);
	int height = (int) (kichThuocMan.getHeight()*0.9);
	JPanel pnlNorth,pnlWest;
	ImageIcon logo;
	Image imgLogo;
	JLabel lblLogo,lblTieuDe;
	JButton btnTrangChu,btnBanHang,btnSanPham,btnKhachHang,btnNhanVien,btnThongKe;
//	JButton btnTrangChu,btnBanHang,btnSanPham;
//	Color w,n;
	public UIQuanLyBanHang() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());

	    pnlNorth = new JPanel();
	    pnlNorth.setLayout(new BorderLayout());
	    pnlNorth.setBackground(new Color(253, 243, 227));
	    pnlNorth.setPreferredSize(new Dimension(width, 160));

	    pnlWest = new JPanel();
	    pnlWest.setLayout(new FlowLayout(FlowLayout.LEFT, 10, (int)(height*0.06)));
//	    pnlWest.setAlignmentY(CENTER_ALIGNMENT);
	    pnlWest.setBackground(new Color(224, 192, 151));
	    pnlWest.setPreferredSize(new Dimension(200, height));
	    
	    JPanel pnlLogo = new JPanel();
	    pnlLogo.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 7));
	    pnlLogo.setPreferredSize(new Dimension(200, height));
	    pnlLogo.setOpaque(false);
	    
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
	    
	    ImageIcon iconTC = new ImageIcon("imgs/home.png");
	    Image imgTC = iconTC.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    btnTrangChu = new JButton("Trang Chủ",new ImageIcon(imgTC));
	    btnTrangChu.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnTrangChu.setForeground(Color.WHITE);
	    btnTrangChu.setContentAreaFilled(false); // Xóa nền mặc định
	    btnTrangChu.setBorderPainted(false);     // Xóa viền nút
	    btnTrangChu.setFocusPainted(false);      // Xóa khung focus khi click
	    btnTrangChu.addActionListener(this);
	    pnlWest.add(btnTrangChu);
	    
	    ImageIcon iconBH = new ImageIcon("imgs/banHang.png");
	    Image imgBH = iconBH.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    btnBanHang = new JButton("Bán Hàng", new ImageIcon(imgBH));
	    btnBanHang.setFont(new Font("Tahoma", Font.PLAIN, 24));
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
	    btnThongKe.setForeground(Color.WHITE);
	    btnThongKe.setContentAreaFilled(false); // Xóa nền mặc định
	    btnThongKe.setBorderPainted(false);     // Xóa viền nút
	    btnThongKe.setFocusPainted(false);      // Xóa khung focus khi click
	    btnThongKe.addActionListener(this);
	    pnlWest.add(btnThongKe);
	    
	    
	    pnlNorth.add(pnlLogo,BorderLayout.WEST);
	    pnlNorth.add(lblTieuDe);
	    
	    add(pnlNorth, BorderLayout.NORTH);

	   
	    
	    
	    
	    
	    add(pnlWest, BorderLayout.WEST);

	    setSize(width, height);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	public static void main(String[] args) {
		new UIQuanLyBanHang().setVisible(true);
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
		if(o== btnTrangChu) {
			lblTieuDe.setText(btnTrangChu.getText().toUpperCase());
		}
		else if(o== btnBanHang) {
			lblTieuDe.setText(btnBanHang.getText().toUpperCase());
		}
	}

}
