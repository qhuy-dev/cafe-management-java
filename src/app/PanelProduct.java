package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bus.SanPham_BUS;
import enity.SanPham;

public class PanelProduct implements ActionListener {
	SanPham_BUS sanPham_BUS = new SanPham_BUS();
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
	private String anhDuongDan = "";
	private JFrame owner;

	public PanelProduct(JFrame owner) {
		this.owner = owner;
	}

	public JPanel SanPham() {
		pnlCenter = new JPanel();
		pnlCenter.setPreferredSize(new Dimension(800, 160));
		pnlCenter.setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(255, 248, 235));
		pnlCenter.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(new Color(200, 160, 100), 2),
			"Thông tin sản phẩm", 0, 0,
			new Font("Times New Roman", Font.BOLD, 15),
			new Color(120, 70, 20)));

		Box b = Box.createVerticalBox();
		Box b1, b2, b3, b4;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(new JLabel("Mã SP:"));
		b1.add(Box.createHorizontalStrut(5));
		b1.add(txtma = new JTextField());
		b1.add(Box.createHorizontalStrut(20));
		b1.add(new JLabel("Tên SP:"));
		b1.add(Box.createHorizontalStrut(5));
		b1.add(txtten = new JTextField());
		b1.add(Box.createHorizontalStrut(20));
		b1.add(btnanh = new JButton("Chọn ảnh"));
		b1.add(Box.createHorizontalStrut(20));
		btnanh.addActionListener(this);
		b1.add(Box.createVerticalStrut(10));
		ImageIcon iconPreview = new ImageIcon("");
		Image imgPreview = iconPreview.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		b1.add(lblpreviewAnh = new JLabel(new ImageIcon(imgPreview)));
		b1.add(Box.createHorizontalStrut(20));
		lblpreviewAnh.setPreferredSize(new Dimension(60, 60));
		lblpreviewAnh.setMaximumSize(new Dimension(60, 60));
		lblpreviewAnh.setBorder(BorderFactory.createLineBorder(new Color(200, 160, 100)));

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(new JLabel("Trạng thái:"));
		b2.add(Box.createHorizontalStrut(5));
		b2.add(chktrangthai = new JCheckBox("Còn bán"));
		b2.add(Box.createHorizontalStrut(20));
		b2.add(new JLabel("Giá tiền:"));
		b2.add(Box.createHorizontalStrut(5));
		b2.add(txtgia = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(Box.createHorizontalGlue());
		b3.add(btnthem    = taoNut("Thêm",    new Color(46, 139, 87)));
		b3.add(Box.createHorizontalStrut(40));
		b3.add(btnsua     = taoNut("Sửa",     new Color(30, 100, 200)));
		b3.add(Box.createHorizontalStrut(40));
		b3.add(btnxoa     = taoNut("Xóa",     new Color(200, 50, 50)));
		b3.add(Box.createHorizontalStrut(40));
		b3.add(btnlammoi  = taoNut("Làm Mới", new Color(130, 100, 60)));
		b3.add(Box.createHorizontalStrut(40));
		b3.add(btntimkiem = taoNut("Tìm",     new Color(180, 120, 0)));
		b3.add(Box.createHorizontalGlue());

		b.add(b4 = Box.createHorizontalBox());
		String[] headers = "Mã;Ảnh;Tên;Giá;Trạng Thái".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		b4.add(scroll);

		pnlCenter.add(b);

		addTableListener();
		loadTable(sanPham_BUS.danhSachSanPham());

		return pnlCenter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnthem) {
			SanPham sp = layDuLieu();
			if (sp == null) return;
			try {
				if (sanPham_BUS.themSanPham(sp)) {
					JOptionPane.showMessageDialog(owner, "Thêm thành công!");
					xoaForm(); loadTable(sanPham_BUS.danhSachSanPham());
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(owner, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		} else if (o == btnsua) {
			if (txtma.getText().isEmpty()) { JOptionPane.showMessageDialog(owner, "Chọn sản phẩm cần sửa!"); return; }
			SanPham sp = layDuLieu();
			if (sp == null) return;
			try {
				if (sanPham_BUS.chinhSuaSanPham(sp)) {
					JOptionPane.showMessageDialog(owner, "Cập nhật thành công!");
					xoaForm(); loadTable(sanPham_BUS.danhSachSanPham());
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(owner, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		} else if (o == btnxoa) {
			if (txtma.getText().isEmpty()) { JOptionPane.showMessageDialog(owner, "Chọn sản phẩm cần xóa!"); return; }
			int cf = JOptionPane.showConfirmDialog(owner, "Xác nhận xóa sản phẩm?", "Xác nhận", JOptionPane.YES_NO_OPTION);
			if (cf == JOptionPane.YES_OPTION) {
				try {
					if (sanPham_BUS.xoaSanPham(txtma.getText().trim())) {
						JOptionPane.showMessageDialog(owner, "Xóa thành công!");
						xoaForm(); loadTable(sanPham_BUS.danhSachSanPham());
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(owner, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else if (o == btnlammoi) {
			xoaForm(); loadTable(sanPham_BUS.danhSachSanPham());

		} else if (o == btntimkiem) {
			String masp  = txtma.getText().trim();
			String tensp = txtten.getText().trim();
			loadTable(sanPham_BUS.timKiemSanPham(masp, tensp));

		} else if (o == btnanh) {
			JFileChooser fc = new JFileChooser("imgs");
			fc.setFileFilter(new FileNameExtensionFilter("Ảnh (*.jpg, &.png)", "jpg", "png"));
			if (fc.showOpenDialog(owner) == JFileChooser.APPROVE_OPTION) {
				anhDuongDan = "imgs/" + fc.getSelectedFile().getName();
				ImageIcon icon = new ImageIcon(anhDuongDan);
				Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
				lblpreviewAnh.setIcon(new ImageIcon(img));
			}
		}
	}

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
				anhDuongDan = sp.getAnh() != null ? sp.getAnh() : "";
				ImageIcon icon = new ImageIcon(anhDuongDan);
				Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
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
					c.setBackground(new Color(160, 100, 40));
					c.setForeground(Color.WHITE);
				} else if (row % 2 == 0) {
					c.setBackground(new Color(255, 248, 235));
					c.setForeground(new Color(80, 45, 10));
				} else {
					c.setBackground(new Color(240, 220, 195));
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
		anhDuongDan = "";
		ImageIcon icon = new ImageIcon("");
		Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		lblpreviewAnh.setIcon(new ImageIcon(img));
	}

	private SanPham layDuLieu() {
		String ma  = txtma.getText().trim();
		String ten = txtten.getText().trim();
		String gia = txtgia.getText().trim();
		if (ma.isEmpty() || ten.isEmpty() || gia.isEmpty()) {
			JOptionPane.showMessageDialog(owner, "Vui lòng nhập đầy đủ thông tin!"); return null;
		}
		try {
			SanPham sp = new SanPham(ma, ten, Double.parseDouble(gia), chktrangthai.isSelected());
			sp.setAnh(anhDuongDan);
			return sp;
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(owner, "Giá tiền phải là số hợp lệ!"); return null;
		}
	}
}