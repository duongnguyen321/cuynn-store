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
@RequestMapping("/api/admin")
@Tag(name = "Admin API", description = "API quản trị hệ thống - chỉ dành cho admin")
@CrossOrigin(origins = "*")
public class AdminController {

    // ============ USER MANAGEMENT ============
    @GetMapping("/users")
    @Operation(summary = "Quản lý người dùng", description = "Lấy danh sách tất cả người dùng với bộ lọc")
    public ResponseEntity<Map<String, Object>> getAllUsers(
            Pageable pageable,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "email", "user1@example.com", "hoTen", "Nguyễn Văn A", "role", "CUSTOMER", "trangThai", "ACTIVE"),
            Map.of("id", 2L, "email", "user2@example.com", "hoTen", "Trần Thị B", "role", "CUSTOMER", "trangThai", "ACTIVE")
        ));
        response.put("total", 100);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}/status")
    @Operation(summary = "Cập nhật trạng thái user", description = "Kích hoạt/vô hiệu hóa tài khoản người dùng")
    public ResponseEntity<Map<String, Object>> updateUserStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cập nhật trạng thái thành công");
        response.put("userId", id);
        response.put("newStatus", request.get("status"));
        return ResponseEntity.ok(response);
    }

    // ============ STAFF MANAGEMENT ============
    @GetMapping("/staff")
    @Operation(summary = "Quản lý nhân viên", description = "Lấy danh sách tất cả nhân viên")
    public ResponseEntity<Map<String, Object>> getAllStaff(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "hoTen", "Nguyễn Văn C", "email", "staff1@company.com", "cuaHang", "Cửa hàng chính", "trangThai", "ACTIVE"),
            Map.of("id", 2L, "hoTen", "Lê Thị D", "email", "staff2@company.com", "cuaHang", "Chi nhánh 2", "trangThai", "ACTIVE")
        ));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/staff")
    @Operation(summary = "Tạo nhân viên mới", description = "Thêm nhân viên mới vào hệ thống")
    public ResponseEntity<Map<String, Object>> createStaff(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Tạo nhân viên thành công");
        response.put("staffId", 3L);
        response.put("hoTen", request.get("hoTen"));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/staff/{id}/roles")
    @Operation(summary = "Phân quyền nhân viên", description = "Gán vai trò và quyền cho nhân viên")
    public ResponseEntity<Map<String, Object>> assignStaffRoles(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Phân quyền thành công");
        response.put("staffId", id);
        response.put("roles", request.get("roles"));
        return ResponseEntity.ok(response);
    }

    // ============ STORE MANAGEMENT ============
    @GetMapping("/stores")
    @Operation(summary = "Quản lý cửa hàng", description = "Lấy danh sách tất cả cửa hàng")
    public ResponseEntity<Map<String, Object>> getAllStores(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "tenCuaHang", "Cửa hàng chính", "diaChi", "123 Nguyễn Huệ, Q1, TP.HCM", "trangThai", "ACTIVE"),
            Map.of("id", 2L, "tenCuaHang", "Chi nhánh 2", "diaChi", "456 Lê Lợi, Q3, TP.HCM", "trangThai", "ACTIVE")
        ));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/stores")
    @Operation(summary = "Tạo cửa hàng mới", description = "Thêm cửa hàng/chi nhánh mới")
    public ResponseEntity<Map<String, Object>> createStore(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Tạo cửa hàng thành công");
        response.put("storeId", 3L);
        response.put("tenCuaHang", request.get("tenCuaHang"));
        return ResponseEntity.ok(response);
    }

    // ============ PRODUCT MANAGEMENT ============
    @GetMapping("/products/pending")
    @Operation(summary = "Sản phẩm chờ duyệt", description = "Lấy danh sách sản phẩm chờ duyệt")
    public ResponseEntity<Map<String, Object>> getPendingProducts(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "tenSanPham", "Áo thun ABC", "gia", 150000, "trangThai", "PENDING"),
            Map.of("id", 2L, "tenSanPham", "Quần jean XYZ", "gia", 250000, "trangThai", "PENDING")
        ));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/products/{id}/approve")
    @Operation(summary = "Duyệt sản phẩm", description = "Phê duyệt sản phẩm để hiển thị")
    public ResponseEntity<Map<String, Object>> approveProduct(@PathVariable Long id, @RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Duyệt sản phẩm thành công");
        response.put("productId", id);
        response.put("status", "APPROVED");
        return ResponseEntity.ok(response);
    }

    // ============ ORDER MANAGEMENT ============
    @GetMapping("/orders/dashboard")
    @Operation(summary = "Dashboard đơn hàng", description = "Thống kê tổng quan đơn hàng")
    public ResponseEntity<Map<String, Object>> getOrderDashboard() {
        Map<String, Object> response = new HashMap<>();
        response.put("totalOrders", 1234);
        response.put("pendingOrders", 56);
        response.put("processingOrders", 78);
        response.put("shippedOrders", 90);
        response.put("deliveredOrders", 1000);
        response.put("cancelledOrders", 10);
        response.put("todayRevenue", 12500000);
        response.put("monthRevenue", 350000000);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/orders/problematic")
    @Operation(summary = "Đơn hàng có vấn đề", description = "Lấy danh sách đơn hàng cần xử lý")
    public ResponseEntity<Map<String, Object>> getProblematicOrders(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "maHoaDon", "HD001", "khachHang", "Nguyễn A", "vanDe", "Khiếu nại chất lượng", "mucDo", "HIGH"),
            Map.of("id", 2L, "maHoaDon", "HD002", "khachHang", "Trần B", "vanDe", "Giao hàng trễ", "mucDo", "MEDIUM")
        ));
        return ResponseEntity.ok(response);
    }

    // ============ FINANCIAL MANAGEMENT ============
    @GetMapping("/finance/revenue")
    @Operation(summary = "Báo cáo doanh thu", description = "Thống kê doanh thu theo thời gian")
    public ResponseEntity<Map<String, Object>> getRevenueReport(
            @RequestParam String period,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        Map<String, Object> response = new HashMap<>();
        response.put("totalRevenue", 100000000);
        response.put("totalOrders", 500);
        response.put("averageOrderValue", 200000);
        response.put("chartData", Arrays.asList(
            Map.of("date", "2024-01-01", "revenue", 1000000),
            Map.of("date", "2024-01-02", "revenue", 1500000)
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/finance/payments")
    @Operation(summary = "Quản lý thanh toán", description = "Theo dõi các giao dịch thanh toán")
    public ResponseEntity<Map<String, Object>> getPaymentTransactions(
            Pageable pageable,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String method) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "hoaDon", "HD001", "soTien", 200000, "phuongThuc", "VNPAY", "trangThai", "SUCCESS"),
            Map.of("id", 2L, "hoaDon", "HD002", "soTien", 150000, "phuongThuc", "COD", "trangThai", "PENDING")
        ));
        return ResponseEntity.ok(response);
    }

    // ============ INVENTORY MANAGEMENT ============
    @GetMapping("/inventory/low-stock")
    @Operation(summary = "Cảnh báo tồn kho thấp", description = "Sản phẩm sắp hết hàng")
    public ResponseEntity<Map<String, Object>> getLowStockProducts() {
        Map<String, Object> response = new HashMap<>();
        response.put("products", Arrays.asList(
            Map.of("id", 1L, "tenSanPham", "Áo thun A", "tonKho", 5, "nguongCanhBao", 10),
            Map.of("id", 2L, "tenSanPham", "Quần jean B", "tonKho", 2, "nguongCanhBao", 5)
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/inventory/movements")
    @Operation(summary = "Lịch sử xuất nhập kho", description = "Theo dõi các giao dịch kho")
    public ResponseEntity<Map<String, Object>> getInventoryMovements(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "sanPham", "Áo thun A", "loai", "NHẬP", "soLuong", 100, "ngay", "2024-01-01"),
            Map.of("id", 2L, "sanPham", "Áo thun A", "loai", "XUẤT", "soLuong", 50, "ngay", "2024-01-02")
        ));
        return ResponseEntity.ok(response);
    }

    // ============ CUSTOMER SUPPORT ============
    @GetMapping("/support/tickets/urgent")
    @Operation(summary = "Ticket khẩn cấp", description = "Các yêu cầu hỗ trợ ưu tiên cao")
    public ResponseEntity<Map<String, Object>> getUrgentTickets() {
        Map<String, Object> response = new HashMap<>();
        response.put("tickets", Arrays.asList(
            Map.of("id", 1L, "tieuDe", "Lỗi thanh toán", "khachHang", "Nguyễn A", "mucDo", "URGENT", "trangThai", "OPEN"),
            Map.of("id", 2L, "tieuDe", "Sản phẩm lỗi", "khachHang", "Trần B", "mucDo", "HIGH", "trangThai", "IN_PROGRESS")
        ));
        return ResponseEntity.ok(response);
    }

    // ============ SYSTEM CONFIGURATION ============
    @GetMapping("/config/settings")
    @Operation(summary = "Cấu hình hệ thống", description = "Lấy các thiết lập hệ thống")
    public ResponseEntity<Map<String, Object>> getSystemSettings() {
        Map<String, Object> response = new HashMap<>();
        response.put("siteName", "ZMEN Store");
        response.put("currency", "VND");
        response.put("taxRate", 10);
        response.put("shippingFee", 30000);
        response.put("freeShippingThreshold", 500000);
        response.put("loyaltyPointRate", 0.01);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/config/settings")
    @Operation(summary = "Cập nhật cấu hình", description = "Cập nhật thiết lập hệ thống")
    public ResponseEntity<Map<String, Object>> updateSystemSettings(@RequestBody Map<String, Object> settings) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cấu hình đã được cập nhật");
        response.put("updatedSettings", settings);
        return ResponseEntity.ok(response);
    }

    // ============ ANALYTICS ============
    @GetMapping("/analytics/overview")
    @Operation(summary = "Thống kế tổng quan", description = "Dashboard analytics chính")
    public ResponseEntity<Map<String, Object>> getAnalyticsOverview() {
        Map<String, Object> response = new HashMap<>();
        response.put("totalCustomers", 5000);
        response.put("activeCustomers", 1200);
        response.put("totalProducts", 500);
        response.put("totalOrders", 10000);
        response.put("conversionRate", 3.2);
        response.put("averageOrderValue", 180000);
        response.put("customerLifetimeValue", 2500000);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/analytics/top-products")
    @Operation(summary = "Sản phẩm bán chạy", description = "Top sản phẩm theo doanh số")
    public ResponseEntity<Map<String, Object>> getTopProducts(@RequestParam(defaultValue = "10") int limit) {
        Map<String, Object> response = new HashMap<>();
        response.put("products", Arrays.asList(
            Map.of("id", 1L, "tenSanPham", "Áo thun basic", "soLuongBan", 500, "doanhThu", 75000000),
            Map.of("id", 2L, "tenSanPham", "Quần jean slim", "soLuongBan", 300, "doanhThu", 90000000)
        ));
        return ResponseEntity.ok(response);
    }
}