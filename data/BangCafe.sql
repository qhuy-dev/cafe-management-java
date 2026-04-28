CREATE DATABASE QLCafe
GO
USE QLCafe


CREATE TABLE SanPham
(
	maSanPham NVARCHAR(100) PRIMARY KEY,
	tenSanPham NVARCHAR(150) NOT NULL,
	giaTien FLOAT NOT NULL CHECK (giaTien > 0),
	trangThai BIT DEFAULT 1,
)

CREATE TABLE KhachHang 
(
	maKhachHang NVARCHAR(100) PRIMARY KEY,
	hoTen NVARCHAR(100) NOT NULL, 
	soDienThoai NVARCHAR(15)
)

CREATE TABLE NhanVien
(
	maNhanVien NVARCHAR(100) PRIMARY KEY,
	hoTen NVARCHAR(100) NOT NULL,
	username NVARCHAR(100) UNIQUE NOT NULL,
	password NVARCHAR(100) NOT NULL,
	role NVARCHAR(20) DEFAULT 'staff',
	soDienThoai NVARCHAR(15),
	diaChi NVARCHAR(150),
	email NVARCHAR(100),
	tienLuong FLOAT,
	ngaySinh DATE,
	gioiTinh NVARCHAR(10)
)

CREATE TABLE TableCafe
(
	maBan NVARCHAR(100) PRIMARY KEY,
	tenBan NVARCHAR(50),
	trangThai INT DEFAULT 0
)

CREATE TABLE HoaDon
(
	maHoaDon NVARCHAR(100) PRIMARY KEY,
	ngayTao DATETIME DEFAULT GETDATE(),

	maKhachHang NVARCHAR(100),
	maNhanVien NVARCHAR(100),
	maBan NVARCHAR(100),

	FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang),
	FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien),
	FOREIGN KEY (maBan) REFERENCES TableCafe(maBan)
)

CREATE TABLE ChiTietHoaDon
(
	maHoaDon NVARCHAR(100),
	maSanPham NVARCHAR(100),
	soLuong INT CHECK (soLuong > 0),
	giaTien FLOAT,
	
	PRIMARY KEY (maHoaDon, maSanPham),
	FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
	FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)
)

-- 1. Chèn dữ liệu bảng SanPham (Cập nhật lên 15 sản phẩm)
INSERT INTO SanPham (maSanPham, tenSanPham, giaTien, trangThai) VALUES
('SP001', N'Cà phê đen đá', 25000, 1),
('SP002', N'Cà phê sữa đá', 29000, 1),
('SP003', N'Bạc xỉu Sài Gòn', 32000, 1),
('SP004', N'Trà đào cam sả', 45000, 1),
('SP005', N'Trà sữa trân châu', 35000, 1),
('SP006', N'Nước ép cam tươi', 40000, 1),
('SP007', N'Sinh tố bơ sáp', 45000, 1),
('SP008', N'Bánh mì kẹp thịt', 25000, 1),
('SP009', N'Bánh Croissant bơ', 30000, 1),
('SP010', N'Bánh Tiramisu', 35000, 1),
('SP011', N'Trà vải hạt sen', 38000, 1),
('SP012', N'Matcha đá xay', 48000, 1),
('SP013', N'Cacao nóng', 30000, 1),
('SP014', N'Soda Ý việt quất', 35000, 1),
('SP015', N'Bánh Flan cốt dừa', 20000, 1);

-- 2. Chèn dữ liệu bảng KhachHang (Họ tên đầy đủ)
INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai) VALUES
('KH001', N'Phạm Minh Hào', '0901234567'),
('KH002', N'Lê Quang Huy', '0912345678'),
('KH003', N'Nguyễn Hoàng Nam', '0988888888'),
('KH004', N'Trần Thị Tuyết Mai', '0977777777'),
('KH005', N'Lê Văn Quốc Anh', '0966666666'),
('KH006', N'Hoàng Thị Ngọc Diệp', '0955555555'),
('KH007', N'Ngô Văn Minh Tú', '0944444444'),
('KH008', N'Lý Thị Thanh Thảo', '0933333333'),
('KH009', N'Vũ Văn Tiến Dũng', '0922222222'),
('KH010', N'Đỗ Thị Thúy Hằng', '0911111111');

-- 3. Chèn dữ liệu bảng NhanVien (Role Tiếng Việt: Quản lý/Nhân viên)
INSERT INTO NhanVien (maNhanVien, hoTen, username, password, role, soDienThoai, diaChi, email, tienLuong, ngaySinh, gioiTinh) VALUES
('NV001', N'Nguyễn Tất Thành Toàn', 'admin', '123', N'Quản lý', '0900000001', N'Gò Vấp, HCM', 'toan@gmail.com', 15000000, '1990-01-01', N'Nam'),
('NV002', N'Trần Thị Linh Chi', 'chi123', '123', N'Nhân viên', '0900000002', N'Củ Chi, HCM', 'chi@gmail.com', 7000000, '2000-05-15', N'Nữ'),
('NV003', N'Phan Hoàng Nam', 'nam456', '123', N'Nhân viên', '0900000003', N'Quận 12, HCM', 'nam@gmail.com', 7000000, '1998-10-20', N'Nam'),
('NV004', N'Đặng Thị Ngọc Lan', 'lan789', '123', N'Nhân viên', '0900000004', N'Hóc Môn, HCM', 'lan@gmail.com', 7000000, '2002-02-12', N'Nữ'),
('NV005', N'Bùi Xuân Hùng', 'hung12', '123', N'Nhân viên', '0900000005', N'Gò Vấp, HCM', 'hung@gmail.com', 7200000, '1995-12-30', N'Nam');

-- 4. Chèn dữ liệu bảng TableCafe
INSERT INTO TableCafe (maBan, tenBan, trangThai) VALUES
('B01', N'Bàn 1 (Cửa sổ)', 0), ('B02', N'Bàn 2', 0), ('B03', N'Bàn 3', 0), ('B04', N'Bàn 4 (Góc)', 0), ('B05', N'Bàn 5', 0),
('B06', N'Bàn 6 (Lầu)', 0), ('B07', N'Bàn 7', 0), ('B08', N'Bàn 8', 0), ('B09', N'Bàn VIP 1', 0), ('B10', N'Bàn VIP 2', 0);

-- 5. Chèn dữ liệu bảng HoaDon (Cập nhật lên 20 hóa đơn)
INSERT INTO HoaDon (maHoaDon, ngayTao, maKhachHang, maNhanVien, maBan) VALUES
('HD001', '2026-04-28 08:00:00', 'KH001', 'NV002', 'B01'), ('HD002', '2026-04-28 08:15:00', 'KH002', 'NV003', 'B04'),
('HD003', '2026-04-28 08:30:00', 'KH003', 'NV002', 'B07'), ('HD004', '2026-04-28 09:00:00', 'KH004', 'NV004', 'B01'),
('HD005', '2026-04-28 09:30:00', 'KH005', 'NV005', 'B02'), ('HD006', '2026-04-28 10:00:00', 'KH006', 'NV002', 'B03'),
('HD007', '2026-04-28 10:15:00', 'KH007', 'NV003', 'B05'), ('HD008', '2026-04-28 10:45:00', 'KH008', 'NV003', 'B06'),
('HD009', '2026-04-28 11:00:00', 'KH009', 'NV004', 'B08'), ('HD010', '2026-04-28 11:30:00', 'KH010', 'NV005', 'B09'),
('HD011', '2026-04-28 12:00:00', 'KH001', 'NV002', 'B10'), ('HD012', '2026-04-28 13:00:00', 'KH002', 'NV003', 'B02'),
('HD013', '2026-04-28 14:00:00', 'KH003', 'NV004', 'B04'), ('HD014', '2026-04-28 15:00:00', 'KH004', 'NV005', 'B05'),
('HD015', '2026-04-28 16:00:00', 'KH005', 'NV002', 'B01'), ('HD016', '2026-04-28 17:00:00', 'KH006', 'NV003', 'B03'),
('HD017', '2026-04-28 18:00:00', 'KH007', 'NV004', 'B07'), ('HD018', '2026-04-28 19:00:00', 'KH008', 'NV005', 'B08'),
('HD019', '2026-04-28 20:00:00', 'KH009', 'NV002', 'B09'), ('HD020', '2026-04-28 21:00:00', 'KH010', 'NV003', 'B06');

-- 6. Chèn dữ liệu bảng ChiTietHoaDon
INSERT INTO ChiTietHoaDon (maHoaDon, maSanPham, soLuong, giaTien) VALUES
('HD001', 'SP001', 2, 25000), ('HD001', 'SP008', 1, 25000),
('HD002', 'SP004', 1, 45000), ('HD003', 'SP002', 1, 29000),
('HD004', 'SP003', 3, 32000), ('HD005', 'SP006', 1, 40000),
('HD006', 'SP005', 2, 35000), ('HD007', 'SP011', 1, 38000),
('HD008', 'SP012', 1, 48000), ('HD009', 'SP002', 2, 29000),
('HD010', 'SP015', 2, 20000), ('HD011', 'SP014', 1, 35000),
('HD012', 'SP013', 1, 30000), ('HD013', 'SP007', 1, 45000),
('HD014', 'SP001', 2, 25000), ('HD015', 'SP010', 1, 35000),
('HD016', 'SP004', 2, 45000), ('HD017', 'SP005', 1, 35000),
('HD018', 'SP009', 3, 30000), ('HD019', 'SP003', 1, 32000),
('HD020', 'SP002', 1, 29000);