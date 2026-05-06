package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import entity.NhanVien;

public class PanelInvoice implements ActionListener {
    private JPanel pnlCenter;
    private JTable tblHoaDon, tblChiTiet;
    private DefaultTableModel modelHoaDon, modelChiTiet;
    
    // Các component cho phần tìm kiếm
    private JTextField txtTuNgay, txtDenNgay;
    private JComboBox<String> cmbNhanVien;
    private JButton btnSearch, btnExport;
    
    // Lưu danh sách nhân viên để lấy mã (Mã NV tương ứng với index của ComboBox)
    private List<NhanVien> listNV = new ArrayList<>();
    
    private DecimalFormat df = new DecimalFormat("#,### VNĐ");
    private SimpleDateFormat sdfOut = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat sdfIn = new SimpleDateFormat("dd/MM/yyyy");

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
                "Tìm Kiếm Nâng Cao (Định dạng: dd/MM/yyyy)", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Tahoma", Font.BOLD, 15), new Color(120, 70, 20)));

        txtTuNgay = new JTextField(10);
        txtDenNgay = new JTextField(10);
        cmbNhanVien = new JComboBox<>();
        
        pnlSearch.add(new JLabel("Từ ngày:"));
        pnlSearch.add(txtTuNgay);
        pnlSearch.add(new JLabel("Đến ngày:"));
        pnlSearch.add(txtDenNgay);
        
        pnlSearch.add(new JLabel("Nhân viên:"));
        pnlSearch.add(cmbNhanVien);

        btnSearch = new JButton("Tìm Kiếm");
        btnSearch.setBackground(new Color(180, 120, 0));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.addActionListener(this); // Gắn sự kiện click
        pnlSearch.add(btnSearch);

        btnExport = new JButton("Xuất Hóa Đơn (PDF)");
        btnExport.setBackground(new Color(30, 100, 200));
        btnExport.setForeground(Color.WHITE);
        pnlSearch.add(btnExport);

        // ==========================================
        // 2. LIỆT KÊ (MASTER - DETAILS)
        // ==========================================
        JPanel pnlMaster = new JPanel(new BorderLayout());
        pnlMaster.setOpaque(false);
        pnlMaster.setBorder(BorderFactory.createTitledBorder(null, "Danh sách Hóa Đơn", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14)));
        
        String[] colsMaster = {"Mã HĐ", "Ngày Tạo", "Nhân Viên", "Khách Hàng", "Tổng Tiền"};
        modelHoaDon = new DefaultTableModel(colsMaster, 0){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tblHoaDon = new JTable(modelHoaDon);
        styleTable(tblHoaDon);
        tblHoaDon.getSelectionModel().addListSelectionListener(e -> {
            // !e.getValueIsAdjusting() giúp sự kiện chỉ kích hoạt 1 lần khi người dùng đã click xong
            if (!e.getValueIsAdjusting()) {
                int row = tblHoaDon.getSelectedRow();
                if (row >= 0) {
                    // Lấy mã hóa đơn ở cột 0 của dòng được chọn
                    String maHD = tblHoaDon.getValueAt(row, 0).toString();
                    loadChiTietHoaDon(maHD); // Gọi hàm đổ dữ liệu xuống bảng dưới
                }
            }
        });
        pnlMaster.add(new JScrollPane(tblHoaDon), BorderLayout.CENTER);

        JPanel pnlDetail = new JPanel(new BorderLayout());
        pnlDetail.setOpaque(false);
        pnlDetail.setBorder(BorderFactory.createTitledBorder(null, "Chi tiết Hóa Đơn đã chọn", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14)));
        
        String[] colsDetail = {"Mã SP", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        modelChiTiet = new DefaultTableModel(colsDetail, 0){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tblChiTiet = new JTable(modelChiTiet);
        styleTable(tblChiTiet);
        pnlDetail.add(new JScrollPane(tblChiTiet), BorderLayout.CENTER);

        JSplitPane splitTable = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnlMaster, pnlDetail);
        splitTable.setResizeWeight(0.6);
        splitTable.setOpaque(false);
        splitTable.setBorder(null);

        pnlCenter.add(pnlSearch, BorderLayout.NORTH);
        pnlCenter.add(splitTable, BorderLayout.CENTER);

        // ==== KHỞI TẠO DỮ LIỆU ====
        loadComboBoxNhanVien();
        btnSearch.doClick(); // Tự động click tìm kiếm lần đầu để load toàn bộ data lên bảng

        return pnlCenter;
    }

    private void styleTable(JTable table) {
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(180, 120, 60));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(255, 204, 153));
    }

    // Đổ dữ liệu nhân viên từ CSDL lên ComboBox
    private void loadComboBoxNhanVien() {
        cmbNhanVien.addItem("Tất cả"); // Index 0
        NhanVien_DAO nvDao = new NhanVien_DAO();
        listNV = nvDao.getAllNhanVien();
        
        for (NhanVien nv : listNV) {
            cmbNhanVien.addItem(nv.getHoTen());
        }
    }
    private void loadChiTietHoaDon(String maHoaDon) {
        modelChiTiet.setRowCount(0); // Xóa dữ liệu cũ trên bảng chi tiết
        dao.CTHoaDon_DAO ctDao = new dao.CTHoaDon_DAO();
        List<Object[]> listCT = ctDao.getChiTietByMaHD(maHoaDon);
        
        for (Object[] row : listCT) {
            String maSP = row[0].toString();
            String tenSP = row[1].toString();
            int soLuong = (int) row[2];
            double donGia = (double) row[3];
            double thanhTien = soLuong * donGia;
            
            modelChiTiet.addRow(new Object[]{
                maSP, 
                tenSP, 
                soLuong, 
                df.format(donGia), 
                df.format(thanhTien)
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        if (o == btnSearch) {
            java.sql.Date sqlTuNgay = null;
            java.sql.Date sqlDenNgay = null;
            
            try {
                if (!txtTuNgay.getText().trim().isEmpty()) {
                    java.util.Date parsedDate = sdfIn.parse(txtTuNgay.getText().trim());
                    sqlTuNgay = new java.sql.Date(parsedDate.getTime());
                }
                if (!txtDenNgay.getText().trim().isEmpty()) {
                    java.util.Date parsedDate = sdfIn.parse(txtDenNgay.getText().trim());
                    sqlDenNgay = new java.sql.Date(parsedDate.getTime());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(pnlCenter, "Vui lòng nhập ngày đúng định dạng dd/MM/yyyy", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy mã nhân viên đang chọn
            String maNV = "ALL";
            int selectedIndex = cmbNhanVien.getSelectedIndex();
            if (selectedIndex > 0) {
                // Do Index 0 là "Tất cả", nên nhân viên trong list sẽ là index - 1
                maNV = listNV.get(selectedIndex - 1).getMaNhanVien(); 
            }

            // Gọi DAO tìm kiếm và đổ lên Table
            HoaDon_DAO hdDao = new HoaDon_DAO();
            List<Object[]> result = hdDao.timKiemHoaDon(sqlTuNgay, sqlDenNgay, maNV);
            
            modelHoaDon.setRowCount(0); // Xóa data cũ
            for (Object[] row : result) {
                // Format lại ngày tháng và tiền tệ trước khi đẩy lên UI
                String ngayTaoStr = sdfOut.format((java.util.Date) row[1]);
                String tongTienStr = df.format(row[4]);
                
                modelHoaDon.addRow(new Object[]{
                    row[0], ngayTaoStr, row[2], row[3], tongTienStr
                });
            }
        }
    }
}