package app;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import bus.SanPham_BUS;
import dao.Ban_DAO;
import dao.HoaDon_DAO;
import entity.BanCafe;
import entity.SanPham;
import dao.BanCafe_DAO;

public class PanelOrder implements ActionListener {
    private JPanel pnlCenter;
    private JPanel pnlGridBan;
    
    
    // Bảng Sản Phẩm
    private DefaultTableModel modelSanPham;
    private JTable tblSanPham;
    
    // Bảng Giỏ Hàng
    private DefaultTableModel modelCart;
    private JTable tblCart;
    
    private JLabel lblTongTien;
    private JLabel lblBanDangChon;
    private JButton btnXoaMon, btnThanhToan;
    
    private String banHienTai = ""; // Lưu mã bàn đang được chọn
    private DecimalFormat df = new DecimalFormat("#,### VNĐ");
    private double tongTien = 0;
    private Ban_DAO banDAO = new Ban_DAO();
    private BanCafe_DAO BanCafeDAO = new BanCafe_DAO();

    public JPanel getPanelOrder() {
        pnlCenter = new JPanel(new BorderLayout(10, 10));
        pnlCenter.setBackground(new Color(255, 248, 235));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlTableLayout = new JPanel(new BorderLayout());
        pnlTableLayout.setOpaque(false);
        pnlTableLayout.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 160, 100), 2),
                "Danh Sách Bàn", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Tahoma", Font.BOLD, 15), new Color(120, 70, 20)));
        pnlTableLayout.setPreferredSize(new Dimension(350, 0));

        pnlGridBan = new JPanel(new GridLayout(0, 3, 10, 10));
        pnlGridBan.setOpaque(false);
        pnlGridBan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollBan = new JScrollPane(pnlGridBan);
        scrollBan.setBorder(null);
        scrollBan.setOpaque(false);
        scrollBan.getViewport().setOpaque(false);
        pnlTableLayout.add(scrollBan, BorderLayout.CENTER);

        JPanel pnlRight = new JPanel(new BorderLayout(0, 10));
        pnlRight.setOpaque(false);

        // 2.1. Bảng Sản Phẩm 
        JPanel pnlMenu = new JPanel(new BorderLayout());
        pnlMenu.setOpaque(false);
        pnlMenu.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 160, 100), 2),
                "Danh Sách Sản Phẩm (Click đúp để thêm vào order)", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Tahoma", Font.BOLD, 14), new Color(120, 70, 20)));

        String[] colsSP = {"Mã SP", "Tên Sản Phẩm", "Giá Tiền"};
        modelSanPham = new DefaultTableModel(colsSP, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } 
        };
        tblSanPham = new JTable(modelSanPham);
        styleTable(tblSanPham);
        pnlMenu.add(new JScrollPane(tblSanPham), BorderLayout.CENTER);

        // 2.2. Bảng Giỏ Hàng 
        JPanel pnlCart = new JPanel(new BorderLayout());
        pnlCart.setOpaque(false);
        
        lblBanDangChon = new JLabel("Chưa chọn bàn");
        lblBanDangChon.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblBanDangChon.setForeground(new Color(45, 105, 225));
        
        JPanel pnlTitleCart = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTitleCart.setOpaque(false);
        pnlTitleCart.add(new JLabel("Giỏ Hàng - "));
        pnlTitleCart.add(lblBanDangChon);
        pnlCart.add(pnlTitleCart, BorderLayout.NORTH);

        String[] colsCart = {"Mã SP", "Tên Món", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        modelCart = new DefaultTableModel(colsCart, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblCart = new JTable(modelCart);
        styleTable(tblCart);
        pnlCart.add(new JScrollPane(tblCart), BorderLayout.CENTER);

        // 2.3. Khu vực tính tiền & Nút chức năng
        JPanel pnlActions = new JPanel(new BorderLayout());
        pnlActions.setOpaque(false);
        pnlActions.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        lblTongTien = new JLabel("Tổng Tiền: 0 VNĐ");
        lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTongTien.setForeground(Color.RED);
        pnlActions.add(lblTongTien, BorderLayout.WEST);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlButtons.setOpaque(false);
        
        btnXoaMon = new JButton("Xóa Món Chọn");
        btnXoaMon.setBackground(new Color(200, 50, 50));
        btnXoaMon.setForeground(Color.WHITE);
        btnXoaMon.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXoaMon.addActionListener(this);

        btnThanhToan = new JButton("Thanh Toán");
        btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnThanhToan.setBackground(new Color(46, 139, 87));
        btnThanhToan.setForeground(Color.WHITE);
        btnThanhToan.addActionListener(this);

        pnlButtons.add(btnXoaMon);
        pnlButtons.add(btnThanhToan);
        pnlActions.add(pnlButtons, BorderLayout.EAST);
        pnlCart.add(pnlActions, BorderLayout.SOUTH);

        JSplitPane splitRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnlMenu, pnlCart);
        splitRight.setResizeWeight(0.5);
        splitRight.setOpaque(false);
        splitRight.setBorder(null);
        pnlRight.add(splitRight, BorderLayout.CENTER);

        pnlCenter.add(pnlTableLayout, BorderLayout.WEST);
        pnlCenter.add(pnlRight, BorderLayout.CENTER);

        addEvents();
        
        loadDataBan(); 
        loadDataSanPham();

        return pnlCenter;
    }

    private void addEvents() {
        tblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { 
                    if(banHienTai.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn trước khi order!");
                        return;
                    }
                    
                    int row = tblSanPham.getSelectedRow();
                    String maSP = modelSanPham.getValueAt(row, 0).toString();
                    String tenSP = modelSanPham.getValueAt(row, 1).toString();
                    double giaSP = Double.parseDouble(modelSanPham.getValueAt(row, 2).toString());
                    
                    themVaoGioHang(maSP, tenSP, giaSP);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        if (o == btnXoaMon) {
            int row = tblCart.getSelectedRow();
            if (row >= 0) {
                modelCart.removeRow(row);
                capNhatTongTien();
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn món cần xóa trong giỏ hàng!");
            }
        } 
        else if (o == btnThanhToan) {
            if (banHienTai.isEmpty() || modelCart.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Chưa có món nào để thanh toán!");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(null, 
                    "Thanh toán cho " + lblBanDangChon.getText() + " với số tiền " + df.format(tongTien) + "?", 
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
                    
            if (confirm == JOptionPane.YES_OPTION) {
                
                // 1. Lấy mã nhân viên đang đăng nhập (Từ file Main UIQuanLyBanHang)
                String maNhanVienHienTai = "NV001"; // Default backup
                if (UIQuanLyBanHang.nhanVien != null) {
                    maNhanVienHienTai = UIQuanLyBanHang.nhanVien.getMaNhanVien();
                }
                
                // 2. Gọi DAO tạo mã hóa đơn mới
                HoaDon_DAO hdDao = new HoaDon_DAO();
                String maHoaDonMoi = hdDao.getNextMaHoaDon();
                
                // 3. Gọi hàm Thanh Toán Transaction
                boolean thanhCong = hdDao.thanhToan(maHoaDonMoi, maNhanVienHienTai, banHienTai, tongTien, modelCart);
                
                if (thanhCong) {
                    JOptionPane.showMessageDialog(null, "Thanh toán thành công! Mã hóa đơn: " + maHoaDonMoi);
                    
                    // Reset giao diện sau khi thanh toán
                    modelCart.setRowCount(0);
                    capNhatTongTien();
                    banHienTai = "";
                    lblBanDangChon.setText("Chưa chọn bàn");
                    
                    // Load lại danh sách bàn để cập nhật trạng thái (từ Đỏ -> Xanh lá)
                    loadDataBan(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Thanh toán thất bại! Vui lòng kiểm tra lại CSDL.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void themVaoGioHang(String maSP, String tenSP, double giaSP) {
        boolean daCo = false;
        for (int i = 0; i < modelCart.getRowCount(); i++) {
            if (modelCart.getValueAt(i, 0).toString().equals(maSP)) {
                int soLuongCu = Integer.parseInt(modelCart.getValueAt(i, 2).toString());
                int soLuongMoi = soLuongCu + 1;
                double thanhTienMoi = soLuongMoi * giaSP;
                
                modelCart.setValueAt(soLuongMoi, i, 2);
                modelCart.setValueAt(thanhTienMoi, i, 4);
                daCo = true;
                break;
            }
        }
        if (!daCo) {
            modelCart.addRow(new Object[]{maSP, tenSP, 1, giaSP, giaSP});
        }
        capNhatTongTien();
    }

    private void capNhatTongTien() {
        tongTien = 0;
        for (int i = 0; i < modelCart.getRowCount(); i++) {
            tongTien += Double.parseDouble(modelCart.getValueAt(i, 4).toString());
        }
        lblTongTien.setText("Tổng Tiền: " + df.format(tongTien));
    }

    private void styleTable(JTable table) {
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(180, 120, 60));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(255, 204, 153));
    }

    private void loadDataSanPham() {
        modelSanPham.setRowCount(0); 
        List<SanPham> listSP = BanCafeDAO.getAllSanPhamBan(); 
        
        for (SanPham sp : listSP) {
            modelSanPham.addRow(new Object[]{
                sp.getMaSanPham(), 
                sp.getTenSanPham(), 
                sp.getGiaTien()
            });
        }
    }

    private void loadDataBan() {
        pnlGridBan.removeAll();
        
        List<BanCafe> listBan = BanCafeDAO.getAllTable();
        
        for (BanCafe ban : listBan) {
            String maBan = ban.getMaBan();
            String tenBan = ban.getTenBan();
            
            final int[] trangThai = { ban.getTrangThai() }; 
            
            JButton btnBan = new JButton(tenBan);
            btnBan.setFont(new Font("Tahoma", Font.BOLD, 14));
            btnBan.setCursor(new Cursor(Cursor.HAND_CURSOR));
            if (trangThai[0] == 1) {
                btnBan.setBackground(new Color(255, 102, 102)); // Đỏ: Đang dùng
                btnBan.setForeground(Color.WHITE);
            } else {
                btnBan.setBackground(new Color(153, 255, 153)); // Xanh: Trống
                btnBan.setForeground(Color.BLACK);
            }

            btnBan.addActionListener(e -> {
                if (trangThai[0] == 0) {
                    int cf = JOptionPane.showConfirmDialog(pnlCenter, 
                            "Khách vào " + tenBan + " đúng không?", "Mở Bàn", JOptionPane.YES_NO_OPTION);
                            
                    if (cf == JOptionPane.YES_OPTION) {
                        if (banDAO.updateTrangThaiBan(maBan, 1)) {
                            btnBan.setBackground(new Color(255, 102, 102)); // Đổi sang Đỏ
                            btnBan.setForeground(Color.WHITE);
                            trangThai[0] = 1; 
                            banHienTai = maBan;
                            lblBanDangChon.setText(tenBan);
                            modelCart.setRowCount(0);
                            capNhatTongTien();
                        }
                    }
                } 
                else {
                    banHienTai = maBan;
                    lblBanDangChon.setText(tenBan);
                    
                    int cf = JOptionPane.showConfirmDialog(pnlCenter, 
                            "Bạn có chắc chắn muốn hủy " + tenBan + " không? ", 
                            "Hủy Bàn", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                            
                    if (cf == JOptionPane.YES_OPTION) {
                        if (banDAO.updateTrangThaiBan(maBan, 0)) {
                            btnBan.setBackground(new Color(153, 255, 153)); // Đổi lại Xanh lá
                            btnBan.setForeground(Color.BLACK);
                            trangThai[0] = 0; 
                            modelCart.setRowCount(0);
                            capNhatTongTien();
                            banHienTai = "";
                            lblBanDangChon.setText("Chưa chọn bàn");
                        }
                    }
                }
            });
            
            pnlGridBan.add(btnBan);
        }
        
        pnlGridBan.revalidate();
        pnlGridBan.repaint();
    }
   
}