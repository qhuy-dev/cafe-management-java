package test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MenuTest {
    
    public static void main(String[] args) {
    	JFrame frame = new JFrame("Ví dụ JPopupMenu");
        
        // 1. Tạo JPopupMenu
        final JPopupMenu popupMenu = new JPopupMenu();
        
        // 2. Tạo các Item cho menu
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        
        popupMenu.add(cut);
        popupMenu.add(copy);
        popupMenu.add(paste);
        
        // 3. Thêm sự kiện chuột để hiển thị Menu
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Kiểm tra xem có phải là nhấn chuột phải không (tùy hệ điều hành)
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}