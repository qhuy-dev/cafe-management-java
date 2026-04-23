package app;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Toolkit lay chi so kich thuoc hien tai cua man hinh
	int width = (int) (screenSize.getWidth()*0.8);
	int height = (int) (screenSize.getHeight()*0.85);
	JTextField txtTaiKhoan,txtMatKhau;
	JPanel pnlDangNhap;
	JLabel lblDangNhap;
	JLayeredPane phanLop;
	JButton btnDangNhap;
	Color mauChu;
	Dimension khoangCach = new Dimension(100, 0);
	public LoginForm() {

	    ImageIcon anh = new ImageIcon("imgs/anhDangNhap.png");
	    Image anhSua = anh.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    JLabel background = new JLabel(new ImageIcon(anhSua));
	    
	    mauChu = Color.decode("#891910");
	    
	    lblDangNhap = new JLabel("Đăng Nhập");
	    lblDangNhap.setForeground(mauChu);
	    lblDangNhap.setFont(new Font("Tohoma", Font.BOLD, 36));
	    
	    txtTaiKhoan = new JTextField();
	    txtMatKhau = new JPasswordField();
	    txtTaiKhoan.setOpaque(false);
	    txtMatKhau.setOpaque(false);
	    txtTaiKhoan.setBorder(BorderFactory.createLineBorder(mauChu));
	    txtTaiKhoan.setFont(new Font("Tohoma", Font.BOLD, 18));
	    txtTaiKhoan.setForeground(mauChu);
	    txtMatKhau.setBorder(BorderFactory.createLineBorder(mauChu));
	    txtMatKhau.setFont(new Font("Tohoma", Font.BOLD, 18));
	    txtMatKhau.setForeground(mauChu);
	    
	    btnDangNhap = new JButton("Đăng Nhập");
	    btnDangNhap.setBackground(mauChu);
	    btnDangNhap.setForeground(Color.white);
	    btnDangNhap.setFont(new Font("Tohoma", Font.BOLD, 18));
	    btnDangNhap.setPreferredSize(new Dimension(150, 60));
	    
	    JPanel pnlTieuDe = new JPanel();
	    pnlTieuDe.add(lblDangNhap);
	    pnlTieuDe.setOpaque(false);
	    
	    pnlDangNhap = new JPanel();
	    pnlDangNhap.setLayout(new BoxLayout(pnlDangNhap, BoxLayout.Y_AXIS));
	    pnlDangNhap.setOpaque(false); // Dòng quan trọng nhất: tắt độ mờ đục
	    int widthDangNhap = 550;
	    int heightDangNhap = 400;
//	    pnlDangNhap.setPreferredSize(new Dimension(100, 100));
	    
	    
	    JPanel pnlTaiKhoan = new JPanel();
	    JPanel pnlMatKhau = new JPanel();
	    JPanel pnlNut = new JPanel();
	    pnlTaiKhoan.setLayout(new BoxLayout(pnlTaiKhoan, BoxLayout.X_AXIS));
	    pnlMatKhau.setLayout(new BoxLayout(pnlMatKhau, BoxLayout.X_AXIS));
	    JLabel lblTK = new JLabel("Tài khoản");
	    lblTK.setPreferredSize(khoangCach);
	    lblTK.setForeground(mauChu);
	    lblTK.setFont(new Font("Tohoma", Font.BOLD, 18));
	    JLabel lblMK = new JLabel("Mật khẩu");
	    lblMK.setForeground(mauChu);
	    lblMK.setPreferredSize(khoangCach);
	    lblMK.setFont(new Font("Tohoma", Font.BOLD, 18));
	    pnlTaiKhoan.add(lblTK);
	    pnlTaiKhoan.add(txtTaiKhoan);
//	    pnlTaiKhoan.setMaximumSize(new Dimension(widthDangNhap, 200));
	    pnlTaiKhoan.setOpaque(false);
	    pnlMatKhau.add(lblMK);
	    pnlMatKhau.add(txtMatKhau);
//	    pnlMatKhau.setMaximumSize(new Dimension(widthDangNhap, 200));
	    pnlMatKhau.setOpaque(false);
	    pnlNut.add(btnDangNhap);
	    pnlNut.setOpaque(false);
	    
	    
	    pnlDangNhap.add(pnlTieuDe);
	    pnlDangNhap.add(Box.createVerticalStrut(50));
	    pnlDangNhap.add(pnlTaiKhoan);
	    pnlDangNhap.add(Box.createVerticalStrut(60));
	    pnlDangNhap.add(pnlMatKhau);
	    pnlDangNhap.add(Box.createVerticalStrut(80));
	    pnlDangNhap.add(pnlNut);
	    
	    phanLop = new JLayeredPane();
	    background.setSize(width, height);
	    pnlDangNhap.setBounds((width-widthDangNhap)/2, (height-heightDangNhap)/2, widthDangNhap, heightDangNhap);
	    phanLop.add(background,JLayeredPane.DEFAULT_LAYER);
	    phanLop.add(pnlDangNhap,JLayeredPane.PALETTE_LAYER);
	    phanLop.setSize(width, height);
	    
	    
//	    add(background);
//	    add(pnlDangNhap);
	    setContentPane(phanLop);
	    setSize(width, height);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	public static void main(String[] args) {
		new LoginForm().setVisible(true);
	}

}
