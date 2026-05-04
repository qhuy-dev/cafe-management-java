package app;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class PanelInvoice {
    private JPanel pnlCenter;
    private JTable tblHoaDon, tblChiTiet;

    public JPanel getPanelInvoice() {
        pnlCenter = new JPanel(new BorderLayout(10, 10));
        pnlCenter.setBackground(new Color(255, 248, 235));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ==========================================
        // 1. TÌM KIẾM NÂNG CAO
        // ==========================================
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        pnlSearch.setOpaque(false);
        pnlSearch.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 160, 100), 2),
                "Tìm Kiếm Nâng Cao", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Tahoma", Font.BOLD, 15), new Color(120, 70, 20)));

        pnlSearch.add(new JLabel("Từ ngày:"));
        pnlSearch.add(new JTextField(10)); // Sau này có thể ráp JDateChooser
        pnlSearch.add(new JLabel("Đến ngày:"));
        pnlSearch.add(new JTextField(10));
        
        pnlSearch.add(new JLabel("Nhân viên:"));
        pnlSearch.add(new JComboBox<>(new String[]{"Tất cả", "Trần Thị Linh Chi", "Phan Hoàng Nam"}));

        JButton btnSearch = new JButton("Tìm Kiếm");
        btnSearch.setBackground(new Color(180, 120, 0));
        btnSearch.setForeground(Color.WHITE);
        pnlSearch.add(btnSearch);

        JButton btnExport = new JButton("Xuất Hóa Đơn (PDF)");
        btnExport.setBackground(new Color(30, 100, 200));
        btnExport.setForeground(Color.WHITE);
        pnlSearch.add(btnExport);

        // ==========================================
        // 2. LIỆT KÊ (MASTER - DETAILS)
        // ==========================================
        // Bảng Hóa Đơn (Master)
        JPanel pnlMaster = new JPanel(new BorderLayout());
        pnlMaster.setOpaque(false);
        pnlMaster.setBorder(BorderFactory.createTitledBorder(null, "Danh sách Hóa Đơn", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14)));
        
        String[] colsMaster = {"Mã HĐ", "Ngày Tạo", "Nhân Viên", "Khách Hàng", "Tổng Tiền"};
        tblHoaDon = new JTable(new DefaultTableModel(colsMaster, 0));
        styleTable(tblHoaDon);
        pnlMaster.add(new JScrollPane(tblHoaDon), BorderLayout.CENTER);

        // Bảng Chi Tiết Hóa Đơn (Details)
        JPanel pnlDetail = new JPanel(new BorderLayout());
        pnlDetail.setOpaque(false);
        pnlDetail.setBorder(BorderFactory.createTitledBorder(null, "Chi tiết Hóa Đơn đã chọn", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14)));
        
        String[] colsDetail = {"Mã SP", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        tblChiTiet = new JTable(new DefaultTableModel(colsDetail, 0));
        styleTable(tblChiTiet);
        pnlDetail.add(new JScrollPane(tblChiTiet), BorderLayout.CENTER);

        // Dùng JSplitPane chia trên dưới, kéo giãn được
        JSplitPane splitTable = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnlMaster, pnlDetail);
        splitTable.setResizeWeight(0.6);
        splitTable.setOpaque(false);
        splitTable.setBorder(null);

        pnlCenter.add(pnlSearch, BorderLayout.NORTH);
        pnlCenter.add(splitTable, BorderLayout.CENTER);

        return pnlCenter;
    }

    private void styleTable(JTable table) {
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(180, 120, 60));
        table.getTableHeader().setForeground(Color.WHITE);
    }
}