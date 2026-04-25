package test;

import javax.swing.*;

public class MainForm extends JFrame {
    public MainForm(String user) {
        setTitle("Trang Chủ");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Hiển thị giữa màn hình

        JLabel lblWelcome = new JLabel("Chào mừng " + user + " đến với hệ thống!", SwingConstants.CENTER);
        add(lblWelcome);
    }
}
