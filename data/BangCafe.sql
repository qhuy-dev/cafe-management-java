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

-- 1. Chèn dữ liệu bảng SanPham (Đồ uống & Thức ăn)
INSERT INTO SanPham (maSanPham, tenSanPham, giaTien, trangThai) VALUES
('SP001', N'Cà phê đen', 25000, 1),
('SP002', N'Cà phê sữa', 29000, 1),
('SP003', N'Bạc xỉu', 32000, 1),
('SP004', N'Trà đào cam sả', 45000, 1),
('SP005', N'Trà sữa truyền thống', 35000, 1),
('SP006', N'Ép cam tươi', 40000, 1),
('SP007', N'Sinh tố bơ', 45000, 1),
('SP008', N'Bánh mì kẹp thịt', 25000, 1),
('SP009', N'Croissant', 30000, 1),
('SP010', N'Tiramisu', 35000, 0); -- Hết hàng

-- 2. Chèn dữ liệu bảng KhachHang
INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai) VALUES
('KH001', N'Phạm Minh Hào', '0901234567'), -- Thông tin khớp với tên bạn
('KH002', N'Lê Quang Huy', '0912345678'), -- Bạn qhuy-dev của bạn
('KH003', N'Nguyễn Văn A', '0988888888'),
('KH004', N'Trần Thị B', '0977777777'),
('KH005', N'Lê Văn C', '0966666666'),
('KH006', N'Hoàng Thị D', '0955555555'),
('KH007', N'Ngô Văn E', '0944444444'),
('KH008', N'Lý Thị F', '0933333333'),
('KH009', N'Vũ Văn G', '0922222222'),
('KH010', N'Đỗ Thị H', '0911111111');

-- 3. Chèn dữ liệu bảng NhanVien (Password mặc định là 123)
INSERT INTO NhanVien (maNhanVien, hoTen, username, password, role, soDienThoai, diaChi, email, tienLuong, ngaySinh, gioiTinh) VALUES
('NV001', N'Quản Lý Toàn', 'admin', '123', 'admin', '0900000001', N'Gò Vấp', 'toan@gmail.com', 15000000, '1990-01-01', N'Nam'),
('NV002', N'Nhân Viên Chi', 'chi123', '123', 'staff', '0900000002', N'Củ Chi', 'chi@gmail.com', 7000000, '2000-05-15', N'Nữ'),
('NV003', N'Nhân Viên Nam', 'nam456', '123', 'staff', '0900000003', N'Quận 12', 'nam@gmail.com', 7000000, '1998-10-20', N'Nam'),
('NV004', N'Nhân Viên Lan', 'lan789', '123', 'staff', '0900000004', N'Hóc Môn', 'lan@gmail.com', 7000000, '2002-02-12', N'Nữ'),
('NV005', N'Nhân Viên Hùng', 'hung12', '123', 'staff', '0900000005', N'Gò Vấp', 'hung@gmail.com', 7200000, '1995-12-30', N'Nam'),
('NV006', N'Nhân Viên Mai', 'mai34', '123', 'staff', '0900000006', N'Bình Thạnh', 'mai@gmail.com', 7200000, '2001-07-18', N'Nữ'),
('NV007', N'Nhân Viên Sơn', 'son56', '123', 'staff', '0900000007', N'Phú Nhuận', 'son@gmail.com', 6800000, '1999-04-05', N'Nam'),
('NV008', N'Nhân Viên Hoa', 'hoa78', '123', 'staff', '0900000008', N'Quận 1', 'hoa@gmail.com', 6800000, '2003-09-25', N'Nữ'),
('NV009', N'Nhân Viên Dũng', 'dung90', '123', 'staff', '0900000009', N'Tân Bình', 'dung@gmail.com', 7500000, '1997-11-11', N'Nam'),
('NV010', N'Nhân Viên Tuyết', 'tuyet01', '123', 'staff', '0900000010', N'Gò Vấp', 'tuyet@gmail.com', 7000000, '2000-01-01', N'Nữ');

-- 4. Chèn dữ liệu bảng TableCafe (0: Trống, 1: Có khách)
INSERT INTO TableCafe (maBan, tenBan, trangThai) VALUES
('B01', N'Bàn 1 (Cửa sổ)', 1),
('B02', N'Bàn 2', 0),
('B03', N'Bàn 3', 0),
('B04', N'Bàn 4 (Góc)', 1),
('B05', N'Bàn 5', 0),
('B06', N'Bàn 6 (Sân thượng)', 0),
('B07', N'Bàn 7', 1),
('B08', N'Bàn 8', 0),
('B09', N'Bàn VIP 1', 0),
('B10', N'Bàn VIP 2', 0);

-- 5. Chèn dữ liệu bảng HoaDon
INSERT INTO HoaDon (maHoaDon, ngayTao, maKhachHang, maNhanVien, maBan) VALUES
('HD001', '2026-04-27 08:30:00', 'KH001', 'NV002', 'B01'),
('HD002', '2026-04-27 09:15:00', 'KH002', 'NV003', 'B04'),
('HD003', '2026-04-27 10:00:00', 'KH003', 'NV002', 'B07'),
('HD004', '2026-04-27 11:30:00', 'KH004', 'NV004', 'B01'),
('HD005', '2026-04-27 13:00:00', 'KH005', 'NV005', 'B02'),
('HD006', '2026-04-27 14:45:00', 'KH006', 'NV002', 'B03'),
('HD007', '2026-04-27 16:20:00', 'KH007', 'NV007', 'B05'),
('HD008', '2026-04-27 18:00:00', 'KH008', 'NV003', 'B06'),
('HD009', '2026-04-27 19:30:00', 'KH009', 'NV010', 'B08'),
('HD010', '2026-04-27 20:45:00', 'KH010', 'NV009', 'B09');

-- 6. Chèn dữ liệu bảng ChiTietHoaDon (Mối quan hệ 1-Nhiều)
INSERT INTO ChiTietHoaDon (maHoaDon, maSanPham, soLuong, giaTien) VALUES
('HD001', 'SP001', 2, 25000), -- HD001 mua 2 Cafe đen
('HD001', 'SP008', 1, 25000), -- HD001 mua thêm 1 Bánh mì
('HD002', 'SP004', 1, 45000),
('HD002', 'SP009', 2, 30000),
('HD003', 'SP002', 1, 29000),
('HD004', 'SP003', 3, 32000),
('HD005', 'SP006', 1, 40000),
('HD006', 'SP005', 2, 35000),
('HD007', 'SP007', 1, 45000),
('HD008', 'SP001', 1, 25000),
('HD009', 'SP002', 2, 29000),
('HD010', 'SP004', 1, 45000);