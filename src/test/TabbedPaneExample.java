package test;

import javax.swing.*;

public class TabbedPaneExample {
    public static void main(String[] args) {
        // 1. Tạo khung cửa sổ (Frame)
        JFrame frame = new JFrame("Ví dụ Chia Tab trong Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 2. Tạo đối tượng JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // 3. Tạo Tab thứ nhất
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Đây là nội dung của Tab Trang chủ"));
        tabbedPane.addTab("Trang chủ", panel1);

        // 4. Tạo Tab thứ hai
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Đây là phần Cài đặt hệ thống"));
        tabbedPane.addTab("Cài đặt", panel2);

        // 5. Tạo Tab thứ ba
        JPanel panel3 = new JPanel();
        panel3.add(new JButton("Click me!"));
        tabbedPane.addTab("Hỗ trợ", panel3);

        // 6. Thêm TabbedPane vào Frame và hiển thị
        frame.add(tabbedPane);
        frame.setLocationRelativeTo(null); // Hiển thị giữa màn hình
        frame.setVisible(true);
    }
}

