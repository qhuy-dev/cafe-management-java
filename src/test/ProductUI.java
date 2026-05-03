package test;	
	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Component;
	import java.awt.Cursor;
	import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.Font;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JCheckBox;
	import javax.swing.JFileChooser;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JTable;
	import javax.swing.JTextField;
	import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bus.SanPham_BUS;
	
	import java.util.List;
	
	import dao.NhanVien_DAO;
	
	import enity.NhanVien;
	import enity.SanPham;
		
		public class ProductUI extends JFrame implements ActionListener, MouseListener {
			SanPham_BUS sanPham_BUS = new SanPham_BUS();
			Dimension kichThuocMan = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) (kichThuocMan.getWidth()*0.85);
			int height = (int) (kichThuocMan.getHeight()*0.9);
			JPanel pnlNorth,pnlWest;
			ImageIcon logo;
			Image imgLogo;
			JLabel lblLogo,lblTieuDe;
			JButton btnTrangChu,btnBanHang,btnSanPham,btnKhachHang,btnNhanVien,btnThongKe;
			JTextField txtUsername,txtPassword;
			static NhanVien nhanVien;
			NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
			private JPanel pnlCenter;
			private JTextField txtma;
			private JTextField txtten;
			private JButton btnanh;
			private JTextField txtgia;
			private JCheckBox chktrangthai;
			private DefaultTableModel tableModel;
			private JTable table;
			private JButton btnthem;
			private JButton btnsua;
			private JButton btnxoa;
			private JButton btnlammoi;
			private JButton btntimkiem;
			private JLabel lblpreviewAnh;
			private String anhDuongDan="";
			public ProductUI() {
				setTitle("Quản lý sản phẩm");
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
			    lblTieuDe = new JLabel("Sản Phẩm",new ImageIcon(imgKhung),JLabel.CENTER);
			    lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 22));
			    lblTieuDe.setForeground(Color.white);
			    lblTieuDe.setHorizontalTextPosition(JLabel.CENTER);
			    lblTieuDe.setVerticalTextPosition(JLabel.CENTER);
			    
			    ImageIcon logoTen = new ImageIcon("imgs/plant.png");
			    Image imgTen = logoTen.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
			    String tenNV = nhanVien != null ? nhanVien.getHoTen() : "";
			    JLabel lblTen;
			    lblTen = new JLabel(tenNV,new ImageIcon(imgTen),JLabel.CENTER);
	//		    System.out.println(nhanVien.getHoTen());
			    lblTen.setFont(new Font("Tahoma", Font.BOLD, 14));
			    lblTen.setForeground(new Color(8, 69, 126));
			    lblTen.setHorizontalTextPosition(JLabel.CENTER);
			    lblTen.setVerticalTextPosition(JLabel.NORTH);
			    
			    ImageIcon logoRole = new ImageIcon("imgs/star.png");
			    Image imgRole = logoRole.getImage().getScaledInstance(80, 40, Image.SCALE_SMOOTH);
			    String chucVu = nhanVien != null ? nhanVien.getRole() : "";
			    JLabel lblChucVu;
			    lblChucVu = new JLabel(chucVu,new ImageIcon(imgRole),JLabel.LEFT);
	//		    System.out.println(nhanVien.getHoTen());
			    lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 16));
	//		    lblTen.setForeground(Color.white);
			    lblChucVu.setHorizontalTextPosition(JLabel.RIGHT);
			    lblChucVu.setVerticalTextPosition(JLabel.CENTER);
			    
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
			    
			    
			    add(pnlCenter);
			    
			    addTableListener();                          
			    loadTable(sanPham_BUS.danhSachSanPham());  
		        
			    
			}
			public static void main(String[] args) {
				new ProductUI().setVisible(true);
			}
	
			
	
			@Override
			public void actionPerformed(ActionEvent e) {
				Object o=e.getSource();
				if(o== btnTrangChu) {
					lblTieuDe.setText(btnTrangChu.getText().toUpperCase());
					PanelTrangChu pnlTrangChu = new PanelTrangChu();
					pnlTrangChu.setMaximumSize(new Dimension(pnlTrangChu.getWidth(), 100));
					add(pnlTrangChu.TrangChu(), BorderLayout.CENTER);
				}
				else if(o== btnBanHang) {
					lblTieuDe.setText(btnBanHang.getText().toUpperCase());
				}else if(o==btnKhachHang) {
					lblTieuDe.setText("Khách Hàng");
					new CustomerUI().setVisible(true);dispose();
				}else if(o==btnSanPham) {
					lblTieuDe.setText("Sản Phẩm");
					new ProductUI().setVisible(true);dispose();
				}
				
				if (o == btnthem) {
		            SanPham sp = layDuLieu();
		            if (sp == null) return;
		            try {
						if (sanPham_BUS.themSanPham(sp)) {
						    JOptionPane.showMessageDialog(this, "Thêm thành công!");
						    xoaForm();loadTable(sanPham_BUS.danhSachSanPham());
						} 
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this,e1.getMessage(),"Lỗi", JOptionPane.ERROR_MESSAGE);
					}
		 
		        } else if (o == btnsua) {
		            if (txtma.getText().isEmpty()) { JOptionPane.showMessageDialog(this, "Chọn sản phẩm cần sửa!"); return; }
		            SanPham sp = layDuLieu();
		            if (sp == null) return;
		            try {
						if (sanPham_BUS.chinhSuaSanPham(sp)) {
						    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
						    xoaForm(); loadTable(sanPham_BUS.danhSachSanPham());
						} 
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
		 
		        } else if (o == btnxoa) {
		            if (txtma.getText().isEmpty()) { JOptionPane.showMessageDialog(this, "Chọn sản phẩm cần xóa!"); return; }
		            int cf = JOptionPane.showConfirmDialog(this, "Xác nhận xóa sản phẩm?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		            if (cf == JOptionPane.YES_OPTION) {
		                try {
							if (sanPham_BUS.xoaSanPham(txtma.getText().trim())) {
							    JOptionPane.showMessageDialog(this, "Xóa thành công!");
							    xoaForm(); loadTable(sanPham_BUS.danhSachSanPham());
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(this, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
						}
		            }
		 
		        } else if (o == btnlammoi) {
		            xoaForm(); loadTable(sanPham_BUS.danhSachSanPham());
		        }else if(o==btntimkiem) {
		        	String masp=txtma.getText().trim();
		        	String tensp=txtten.getText().trim();
		        	loadTable(sanPham_BUS.timKiemSanPham(masp, tensp));
		        }else if(o==btnanh) {
		        	JFileChooser fc=new JFileChooser("imgs");
		        	fc.setFileFilter(new FileNameExtensionFilter("Ảnh (*.jpg, &.png)", "jpg", "png"));
		        	if(fc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
		        		anhDuongDan="imgs/" +fc.getSelectedFile().getName();
		        		ImageIcon icon=new ImageIcon(anhDuongDan);
		        		Image img=icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		        		lblpreviewAnh.setIcon(new ImageIcon(img));
		        	}
		        }
				
			
	}
		 
		    @Override public void mouseClicked(MouseEvent e)  {}
		    @Override public void mousePressed(MouseEvent e)  {}
		    @Override public void mouseReleased(MouseEvent e) {}
		    @Override public void mouseEntered(MouseEvent e)  { ((JButton) e.getSource()).setForeground(new Color(255, 220, 100)); }
		    @Override public void mouseExited(MouseEvent e)   { ((JButton) e.getSource()).setForeground(Color.WHITE); }
		 
		   
			private JButton taoNut(String ten, Color bg) {
			    JButton btn = new JButton(ten);
			    btn.setFont(new Font("Tahoma", Font.BOLD, 14));
			    btn.setBackground(bg);
			    btn.setForeground(Color.WHITE);
			    btn.setFocusPainted(false);
			    btn.setPreferredSize(new Dimension(100, 35));
			    btn.setMaximumSize(new Dimension(100, 35));
			    btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			    btn.addActionListener(this);
			    return btn;
			}
		    private void loadTable(List<SanPham> list) {
		        tableModel.setRowCount(0);
		        for (SanPham sp : list) {
		            tableModel.addRow(new Object[]{
		                sp.getMaSanPham(),
		                sp.getAnh(),
		                sp.getTenSanPham(),
		                String.format("%,.0f đ", sp.getGiaTien()),
		                sp.isTrangThai() ? "Còn bán" : "Ngừng bán"
		            });
		        }
		    }
		    private void addTableListener() {
		        table.addMouseListener(new MouseAdapter() {
		            @Override 
		            	public void mouseClicked(MouseEvent e) {
		                int row = table.getSelectedRow();
		                if (row < 0) return;
		                String ma = tableModel.getValueAt(row, 0).toString();
		                SanPham sp = sanPham_BUS.timKiemTheoMa(ma);
		                if (sp == null) return;
		                txtma.setText(sp.getMaSanPham());
		                txtten.setText(sp.getTenSanPham());
		                txtgia.setText(String.valueOf(sp.getGiaTien()));
		                chktrangthai.setSelected(sp.isTrangThai());
		                anhDuongDan =sp.getAnh()!=null?sp.getAnh():"";
		                ImageIcon icon=new ImageIcon(anhDuongDan);
		                Image img=icon.getImage().getScaledInstance(60, 60,Image.SCALE_SMOOTH);
		                lblpreviewAnh.setIcon(new ImageIcon(img));
		            }
		        });
		 
		        table.setRowHeight(60);
		        table.getTableHeader().setBackground(new Color(180, 120, 60));
		        table.getTableHeader().setForeground(Color.WHITE);
		        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
	
		        
		        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		            @Override
		            public Component getTableCellRendererComponent(
		                    JTable table, Object value, boolean isSelected,
		                    boolean hasFocus, int row, int column) {
		                Component c = super.getTableCellRendererComponent(
		                        table, value, isSelected, hasFocus, row, column);
		                if (isSelected) {
		                    c.setBackground(new Color(160, 100, 40)); // ← màu khi chọn
		                    c.setForeground(Color.WHITE);
		                } else if (row % 2 == 0) {
		                    c.setBackground(new Color(255, 248, 235)); // ← dòng chẵn
		                    c.setForeground(new Color(80, 45, 10));
		                } else {
		                    c.setBackground(new Color(240, 220, 195)); // ← dòng lẻ
		                    c.setForeground(new Color(80, 45, 10));
		                }
		                return c;
		            }
		        });
	
		        table.getColumnModel().getColumn(1).setCellRenderer(
		            new TableCellRenderer() {
		                @Override
		                public Component getTableCellRendererComponent(
		                        JTable table, Object value, boolean isSelected,
		                        boolean hasFocus, int row, int column) {
		                    JLabel lbl = new JLabel();
		                    lbl.setHorizontalAlignment(JLabel.CENTER);
		                    if (isSelected) {
		                        lbl.setBackground(new Color(160, 100, 40));
		                    } else if (row % 2 == 0) {
		                        lbl.setBackground(new Color(255, 248, 235));
		                    } else {
		                        lbl.setBackground(new Color(240, 220, 195));
		                    }
		                    lbl.setOpaque(true);
		                    if (value != null && !value.toString().isEmpty()) {
		                        ImageIcon icon = new ImageIcon(value.toString());
		                        Image img = icon.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
		                        lbl.setIcon(new ImageIcon(img));
		                    }
		                    return lbl;
		                }
		            }
		        );
		        table.setGridColor(new Color(200, 155, 90));
		        table.setShowGrid(true);
		        
		        
		    }
		 
		    private void xoaForm() {
		        txtma.setText(""); txtten.setText("");
		        txtgia.setText(""); 
		        chktrangthai.setSelected(true);
		        table.clearSelection();
		        txtma.requestFocus();
		        anhDuongDan="";
		        ImageIcon icon=new ImageIcon("");
		        Image img=icon.getImage().getScaledInstance(60, 60,Image.SCALE_SMOOTH);
		        lblpreviewAnh.setIcon(new ImageIcon(img));
		    }
		 
		    private SanPham layDuLieu() {
		        String ma  = txtma.getText().trim();
		        String ten = txtten.getText().trim();
		        String gia = txtgia.getText().trim();
		        if (ma.isEmpty() || ten.isEmpty() || gia.isEmpty()) {
		            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!"); return null;
		        }
		        try {
		            SanPham sp= new SanPham(ma, ten, Double.parseDouble(gia), chktrangthai.isSelected());
		            sp.setAnh(anhDuongDan);
		            return sp;
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(this, "Giá tiền phải là số hợp lệ!"); return null;
		        }
		    }
	
		  
	}