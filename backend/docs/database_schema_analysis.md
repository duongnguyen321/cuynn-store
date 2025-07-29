# Database Schema Analysis - Cua hang

Từ dbdocs.io, hệ thống có 50 bảng với 315 fields. Các bảng chính bao gồm:

## Bảng Người dùng và Phân quyền:
- NguoiDung (Users)
- ThongTinNguoiDung (User Details)
- DiaChiNguoiDung (User Addresses)
- NhanVien (Employees)
- VaiTro (Roles)
- VaiTroNhanVien (Employee Roles)
- Quyen (Permissions)
- QuyenVaiTro (Role Permissions)

## Bảng Khách hàng thân thiết:
- HangThanhVien (Membership Tiers)
- LichSuDiemTichLuy (Points History)
- NhomKhachHang (Customer Groups)
- NhomKhachHang_NguoiDung (Customer Group Members)

## Bảng Sản phẩm:
- SanPham (Products)
- BienTheSanPham (Product Variants)
- ThuocTinh (Attributes)
- GiaTriThuocTinh (Attribute Values)
- BienThe_GiaTriThuocTinh (Variant Attributes)
- HinhAnh (Images)
- Tag (Tags)
- TagSanPham (Product Tags)
- DanhGiaSanPham (Product Reviews)

## Bảng Kho và Cửa hàng:
- CuaHang (Stores)
- TonKho (Inventory)
- NhaCungCap (Suppliers)
- PhieuNhapKho (Import Orders)
- ChiTietPhieuNhap (Import Order Details)

## Bảng Đơn hàng và Thanh toán:
- GioHang (Cart)
- HoaDon (Orders)
- ChiTietHoaDon (Order Items)
- GoiGiaoHang (Shipping Packages)
- ChiTietGoiGiaoHang (Package Details)
- DonTraHang (Return Orders)
- ChiTietDonTraHang (Return Details)
- LichSuGiaoDichThanhToan (Payment Transactions)
- GiaoDichHoanTien (Refund Transactions)

## Bảng Khuyến mãi:
- Voucher (Vouchers)
- KhuyenMai (Promotions)

## Bảng B2B:
- CongTy (Companies)

Hệ thống này rất phức tạp và toàn diện, hỗ trợ:
- Quản lý phân quyền RBAC
- Hệ thống khách hàng thân thiết với điểm tích lũy
- Quản lý sản phẩm với biến thể và thuộc tính động
- Quản lý kho và nhập hàng
- Hệ thống đơn hàng và giao hàng phức tạp
- Hệ thống thanh toán và hoàn tiền
- Hỗ trợ B2B
- Khuyến mãi và voucher

