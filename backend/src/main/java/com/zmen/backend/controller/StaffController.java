package com.zmen.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

@RestController
@RequestMapping("/api/staff")
@Tag(name = "Staff API", description = "API dành cho nhân viên cửa hàng offline")
@CrossOrigin(origins = "*")
public class StaffController {

    // ============ OFFLINE STORE OPERATIONS ============
    @GetMapping("/dashboard")
    @Operation(summary = "Dashboard nhân viên", description = "Thông tin tổng quan ca làm việc")
    public ResponseEntity<Map<String, Object>> getStaffDashboard(@RequestHeader("Authorization") String token) {
        Map<String, Object> response = new HashMap<>();
        response.put("caLamViec", Map.of(
            "batDau", "08:00",
            "ketThuc", "17:00",
            "trangThai", "DANG_LAM"
        ));
        response.put("donHangHomNay", 25);
        response.put("doanhThuHomNay", 5500000);
        response.put("khachHangMoi", 8);
        response.put("tonKhoCanCapNhat", 3);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkin")
    @Operation(summary = "Check-in ca làm", description = "Bắt đầu ca làm việc")
    public ResponseEntity<Map<String, Object>> checkIn(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Check-in thành công");
        response.put("thoiGian", System.currentTimeMillis());
        response.put("caLamViec", "CA_SANG");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkout")
    @Operation(summary = "Check-out ca làm", description = "Kết thúc ca làm việc")  
    public ResponseEntity<Map<String, Object>> checkOut(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Check-out thành công");
        response.put("thoiGian", System.currentTimeMillis());
        response.put("tongThoiGian", "8h 30m");
        response.put("doanhThuCa", 5500000);
        return ResponseEntity.ok(response);
    }

    // ============ POS (POINT OF SALE) SYSTEM ============
    @PostMapping("/pos/create-order")
    @Operation(summary = "Tạo đơn tại quầy", description = "Tạo đơn hàng khách mua trực tiếp tại cửa hàng")
    public ResponseEntity<Map<String, Object>> createPOSOrder(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Tạo đơn hàng thành công");
        response.put("maHoaDon", "POS001");
        response.put("tongTien", request.get("tongTien"));
        response.put("phuongThucThanhToan", request.get("phuongThucThanhToan"));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/pos/process-payment")
    @Operation(summary = "Xử lý thanh toán tại quầy", description = "Xử lý thanh toán cho đơn hàng POS")
    public ResponseEntity<Map<String, Object>> processPOSPayment(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Thanh toán thành công");
        response.put("maGiaoDich", "TXN" + System.currentTimeMillis());
        response.put("soTienNhan", request.get("soTienNhan"));
        response.put("tienThua", (Integer)request.get("soTienNhan") - (Integer)request.get("tongTien"));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/pos/print-receipt")
    @Operation(summary = "In hóa đơn", description = "In hóa đơn cho khách hàng")
    public ResponseEntity<Map<String, Object>> printReceipt(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "In hóa đơn thành công");
        response.put("maHoaDon", request.get("maHoaDon"));
        response.put("filePath", "/receipts/" + request.get("maHoaDon") + ".pdf");
        return ResponseEntity.ok(response);
    }

    // ============ CUSTOMER SERVICE ============
    @PostMapping("/customers/register")
    @Operation(summary = "Đăng ký khách hàng tại quầy", description = "Tạo tài khoản khách hàng mới tại cửa hàng")
    public ResponseEntity<Map<String, Object>> registerCustomerInStore(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Đăng ký khách hàng thành công");
        response.put("customerId", System.currentTimeMillis());
        response.put("memberCard", "MB" + System.currentTimeMillis());
        response.put("hoTen", request.get("hoTen"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customers/search")
    @Operation(summary = "Tìm kiếm khách hàng", description = "Tìm kiếm thông tin khách hàng")
    public ResponseEntity<Map<String, Object>> searchCustomer(@RequestParam String query) {
        Map<String, Object> response = new HashMap<>();
        response.put("customers", Arrays.asList(
            Map.of("id", 1L, "hoTen", "Nguyễn Văn A", "soDienThoai", "0901234567", "diemTichLuy", 1500),
            Map.of("id", 2L, "hoTen", "Trần Thị B", "soDienThoai", "0907654321", "diemTichLuy", 2300)
        ));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/customers/{customerId}/loyalty-points")
    @Operation(summary = "Cộng điểm thưởng", description = "Cộng điểm tích lũy cho khách hàng")
    public ResponseEntity<Map<String, Object>> addLoyaltyPoints(
            @PathVariable Long customerId, 
            @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cộng điểm thành công");
        response.put("customerId", customerId);
        response.put("diemCong", request.get("diem"));
        response.put("tongDiem", 2800);
        return ResponseEntity.ok(response);
    }

    // ============ INVENTORY MANAGEMENT ============
    @GetMapping("/inventory/current-store")
    @Operation(summary = "Tồn kho cửa hàng", description = "Xem tồn kho tại cửa hàng hiện tại")
    public ResponseEntity<Map<String, Object>> getCurrentStoreInventory(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "tenSanPham", "Áo thun basic", "soLuong", 25, "giaBan", 150000),
            Map.of("id", 2L, "tenSanPham", "Quần jean slim", "soLuong", 15, "giaBan", 350000)
        ));
        response.put("cuaHang", "Cửa hàng chính");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/inventory/{productId}/update-stock")
    @Operation(summary = "Cập nhật tồn kho", description = "Cập nhật số lượng tồn kho sản phẩm")
    public ResponseEntity<Map<String, Object>> updateProductStock(
            @PathVariable Long productId,
            @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cập nhật tồn kho thành công");
        response.put("productId", productId);
        response.put("soLuongMoi", request.get("soLuong"));
        response.put("lyDo", request.get("lyDo"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/inventory/low-stock-alert")
    @Operation(summary = "Cảnh báo sắp hết hàng", description = "Danh sách sản phẩm sắp hết hàng tại cửa hàng")
    public ResponseEntity<Map<String, Object>> getLowStockAlert() {
        Map<String, Object> response = new HashMap<>();
        response.put("products", Arrays.asList(
            Map.of("id", 1L, "tenSanPham", "Áo polo", "soLuong", 3, "nguongToiThieu", 5),
            Map.of("id", 2L, "tenSanPham", "Quần short", "soLuong", 2, "nguongToiThieu", 10)
        ));
        return ResponseEntity.ok(response);
    }

    // ============ PRODUCT MANAGEMENT ============ 
    @GetMapping("/products/barcode/{barcode}")
    @Operation(summary = "Quét mã vạch", description = "Lấy thông tin sản phẩm bằng mã vạch")
    public ResponseEntity<Map<String, Object>> getProductByBarcode(@PathVariable String barcode) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", 1L);
        response.put("tenSanPham", "Áo thun basic");
        response.put("sku", barcode);
        response.put("giaBan", 150000);
        response.put("soLuongTon", 25);
        response.put("hinhAnh", "/images/ao-thun-basic.jpg");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/products/{productId}/price-check")
    @Operation(summary = "Kiểm tra giá", description = "Kiểm tra giá bán hiện tại của sản phẩm")
    public ResponseEntity<Map<String, Object>> checkProductPrice(@PathVariable Long productId) {
        Map<String, Object> response = new HashMap<>();
        response.put("productId", productId);
        response.put("giaBanLe", 150000);
        response.put("giaKhuyenMai", 120000);
        response.put("coKhuyenMai", true);
        response.put("ngayKetThucKM", "2024-02-15");
        return ResponseEntity.ok(response);
    }

    // ============ SALES REPORTING ============
    @GetMapping("/reports/daily-sales")
    @Operation(summary = "Báo cáo bán hàng ngày", description = "Thống kê bán hàng trong ngày")
    public ResponseEntity<Map<String, Object>> getDailySalesReport() {
        Map<String, Object> response = new HashMap<>();
        response.put("ngayBaoCao", "2024-01-15");
        response.put("tongDonHang", 45);
        response.put("tongDoanhThu", 8750000);
        response.put("khachHangMoi", 12);
        response.put("sanPhamBanChay", Arrays.asList(
            Map.of("tenSanPham", "Áo thun basic", "soLuong", 15),
            Map.of("tenSanPham", "Quần jean", "soLuong", 8)
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reports/shift-handover")
    @Operation(summary = "Báo cáo bàn giao ca", description = "Thông tin bàn giao giữa các ca làm việc")
    public ResponseEntity<Map<String, Object>> getShiftHandoverReport() {
        Map<String, Object> response = new HashMap<>();
        response.put("caHienTai", Map.of(
            "nhanVien", "Nguyễn Văn A",
            "batDau", "08:00",
            "doanhThu", 3200000,
            "soDonHang", 22
        ));
        response.put("caTiepTheo", "CA_CHIEU");
        response.put("vanDeCanLuuY", Arrays.asList(
            "Sản phẩm ID #123 sắp hết hàng",
            "Khách hàng VIP đặt hàng sẽ đến lấy lúc 14:00"
        ));
        return ResponseEntity.ok(response);
    }

    // ============ CUSTOMER INQUIRIES ============
    @PostMapping("/customer-service/create-ticket")
    @Operation(summary = "Tạo ticket hỗ trợ", description = "Tạo yêu cầu hỗ trợ cho khách hàng tại cửa hàng")
    public ResponseEntity<Map<String, Object>> createSupportTicket(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Tạo ticket thành công");
        response.put("ticketId", "TK" + System.currentTimeMillis());
        response.put("khachHang", request.get("khachHang"));
        response.put("vanDe", request.get("vanDe"));
        response.put("mucDo", request.get("mucDo"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer-service/return-exchange")
    @Operation(summary = "Xử lý đổi trả", description = "Quản lý yêu cầu đổi trả hàng tại cửa hàng")
    public ResponseEntity<Map<String, Object>> getReturnExchangeRequests(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "maHoaDon", "HD001", "khachHang", "Nguyễn A", "lyDo", "Lỗi sản phẩm", "trangThai", "PENDING"),
            Map.of("id", 2L, "maHoaDon", "HD002", "khachHang", "Trần B", "lyDo", "Không vừa size", "trangThai", "APPROVED")
        ));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/customer-service/process-return/{returnId}")
    @Operation(summary = "Xử lý đổi trả", description = "Chấp nhận hoặc từ chối yêu cầu đổi trả")
    public ResponseEntity<Map<String, Object>> processReturn(
            @PathVariable Long returnId,
            @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Xử lý đổi trả thành công");
        response.put("returnId", returnId);
        response.put("trangThai", request.get("trangThai"));
        response.put("soTienHoan", request.get("soTienHoan"));
        return ResponseEntity.ok(response);
    }
}