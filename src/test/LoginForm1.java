package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm1 extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginForm1() {
        setTitle("Đăng Nhập");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));
        setLocationRelativeTo(null);

        add(new JLabel(" Username:"));
        txtUser = new JTextField();
        add(txtUser);

        add(new JLabel(" Password:"));
        txtPass = new JPasswordField();
        add(txtPass);

        btnLogin = new JButton("Đăng nhập");
        add(btnLogin);

        // Xử lý sự kiện khi nhấn nút
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtUser.getText();
                String pass = new String(txtPass.getPassword());

                // Giả định user/pass đúng là admin/123
                if (user.equals("admin") && pass.equals("123")) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                    
                    // 1. Mở trang chủ
                    MainForm main = new MainForm(user);
                    main.setVisible(true);
                    
                    // 2. Đóng form hiện tại
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu!");
                }
            }
        });
    }

    public static void main(String[] args) {
        new LoginForm1().setVisible(true);
    }
}