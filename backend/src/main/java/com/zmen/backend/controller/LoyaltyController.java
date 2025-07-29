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
@RequestMapping("/api/loyalty")
@Tag(name = "Loyalty API", description = "API quản lý điểm thưởng và khách hàng thân thiết")
@CrossOrigin(origins = "*")
public class LoyaltyController {

    @GetMapping("/points/{userId}")
    @Operation(summary = "Điểm tích lũy của khách hàng", description = "Lấy thông tin điểm tích lũy hiện tại")
    public ResponseEntity<Map<String, Object>> getUserLoyaltyPoints(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("tongDiem", 2850);
        response.put("diemSuDungDuoc", 2850);
        response.put("diemSapHetHan", 150);
        response.put("hangThanhVien", Map.of(
            "id", 2L,
            "tenHang", "Bạc",
            "diemToiThieu", 1000,
            "quyenLoi", Arrays.asList("Giảm giá 5%", "Miễn phí giao hàng", "Ưu tiên hỗ trợ")
        ));
        response.put("diemCanDat", Map.of(
            "hangTiepTheo", "Vàng",
            "diemCanThem", 1150
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/points/{userId}/history")
    @Operation(summary = "Lịch sử điểm tích lũy", description = "Xem lịch sử giao dịch điểm thưởng")
    public ResponseEntity<Map<String, Object>> getPointsHistory(@PathVariable Long userId, Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("content", Arrays.asList(
            Map.of(
                "id", 1L,
                "loaiGiaoDich", "EARN",
                "diemThayDoi", 150,
                "lyDo", "Mua hàng đơn #HD001",
                "ngayTao", "2024-01-15",
                "diemSauGiaoDich", 2850
            ),
            Map.of(
                "id", 2L,
                "loaiGiaoDich", "REDEEM",
                "diemThayDoi", -200,
                "lyDo", "Đổi voucher giảm giá 50K",
                "ngayTao", "2024-01-10",
                "diemSauGiaoDich", 2700
            )
        ));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/points/{userId}/earn")
    @Operation(summary = "Cộng điểm thưởng", description = "Cộng điểm cho khách hàng khi mua hàng")
    public ResponseEntity<Map<String, Object>> earnPoints(@PathVariable Long userId, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cộng điểm thành công");
        response.put("userId", userId);
        response.put("diemCong", request.get("diem"));
        response.put("tongDiemMoi", 3000);
        response.put("lyDo", request.get("lyDo"));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/points/{userId}/redeem")
    @Operation(summary = "Quy đổi điểm thưởng", description = "Sử dụng điểm để đổi quà hoặc giảm giá")
    public ResponseEntity<Map<String, Object>> redeemPoints(@PathVariable Long userId, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Quy đổi điểm thành công");
        response.put("userId", userId);
        response.put("diemSuDung", request.get("diem"));
        response.put("tongDiemConLai", 2650);
        response.put("phanThuong", request.get("phanThuong"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tiers")
    @Operation(summary = "Các hạng thành viên", description = "Lấy thông tin về các hạng khách hàng thân thiết")
    public ResponseEntity<Map<String, Object>> getMembershipTiers() {
        Map<String, Object> response = new HashMap<>();
        response.put("tiers", Arrays.asList(
            Map.of(
                "id", 1L,
                "tenHang", "Đồng",
                "diemToiThieu", 0,
                "tyLeHoanDiem", 1.0,
                "quyenLoi", Arrays.asList("Tích điểm cơ bản")
            ),
            Map.of(
                "id", 2L,
                "tenHang", "Bạc", 
                "diemToiThieu", 1000,
                "tyLeHoanDiem", 1.2,
                "quyenLoi", Arrays.asList("Giảm giá 5%", "Miễn phí giao hàng đơn > 300K")
            ),
            Map.of(
                "id", 3L,
                "tenHang", "Vàng",
                "diemToiThieu", 4000,
                "tyLeHoanDiem", 1.5,
                "quyenLoi", Arrays.asList("Giảm giá 10%", "Miễn phí giao hàng", "Ưu tiên hỗ trợ")
            ),
            Map.of(
                "id", 4L,
                "tenHang", "Kim cương",
                "diemToiThieu", 10000,
                "tyLeHoanDiem", 2.0,
                "quyenLoi", Arrays.asList("Giảm giá 15%", "Miễn phí giao hàng", "Tư vấn cá nhân", "Sự kiện VIP")
            )
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rewards")
    @Operation(summary = "Danh sách phần thưởng", description = "Các phần thưởng có thể đổi bằng điểm")
    public ResponseEntity<Map<String, Object>> getAvailableRewards() {
        Map<String, Object> response = new HashMap<>();
        response.put("rewards", Arrays.asList(
            Map.of(
                "id", 1L,
                "tenPhanThuong", "Voucher giảm giá 50K",
                "diemCanThiet", 500,
                "moTa", "Giảm 50,000đ cho đơn hàng từ 300,000đ",
                "soLuongConLai", 100,
                "trangThai", "ACTIVE"
            ),
            Map.of(
                "id", 2L,
                "tenPhanThuong", "Miễn phí giao hàng",
                "diemCanThiet", 200,
                "moTa", "Miễn phí ship cho đơn hàng bất kỳ",
                "soLuongConLai", 50,
                "trangThai", "ACTIVE"
            ),
            Map.of(
                "id", 3L,
                "tenPhanThuong", "Áo thun ZMEN",
                "diemCanThiet", 2000,
                "moTa", "Áo thun thương hiệu ZMEN size M",
                "soLuongConLai", 20,
                "trangThai", "ACTIVE"
            )
        ));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/rewards/{rewardId}/redeem")
    @Operation(summary = "Đổi phần thưởng", description = "Đổi điểm lấy phần thưởng cụ thể")
    public ResponseEntity<Map<String, Object>> redeemReward(
            @PathVariable Long rewardId,
            @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Đổi phần thưởng thành công");
        response.put("rewardId", rewardId);
        response.put("userId", request.get("userId"));
        response.put("diemSuDung", 500);
        response.put("maDoi", "RW" + System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics")
    @Operation(summary = "Thống kê loyalty", description = "Thống kê tổng quan chương trình khách hàng thân thiết")
    public ResponseEntity<Map<String, Object>> getLoyaltyStatistics() {
        Map<String, Object> response = new HashMap<>();
        response.put("tongThanhVien", 12500);
        response.put("thanhVienHoatDong", 8900);
        response.put("tongDiemDaPhat", 2850000);
        response.put("tongDiemDaSuDung", 1200000);
        response.put("phanBoHang", Map.of(
            "dong", 6500,
            "bac", 3200,
            "vang", 1800,
            "kimCuong", 200
        ));
        response.put("tyLeChuyenDoi", 15.5);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/top-members")
    @Operation(summary = "Top khách hàng thân thiết", description = "Danh sách khách hàng có điểm cao nhất")
    public ResponseEntity<Map<String, Object>> getTopMembers(@RequestParam(defaultValue = "10") int limit) {
        Map<String, Object> response = new HashMap<>();
        response.put("members", Arrays.asList(
            Map.of("userId", 1L, "hoTen", "Nguyễn Văn A", "tongDiem", 15000, "hangThanhVien", "Kim cương"),
            Map.of("userId", 2L, "hoTen", "Trần Thị B", "tongDiem", 12500, "hangThanhVien", "Kim cương"),
            Map.of("userId", 3L, "hoTen", "Lê Văn C", "tongDiem", 8900, "hangThanhVien", "Vàng")
        ));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/birthday-bonus")
    @Operation(summary = "Điểm thưởng sinh nhật", description = "Tặng điểm thưởng sinh nhật cho khách hàng")
    public ResponseEntity<Map<String, Object>> grantBirthdayBonus(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Tặng điểm sinh nhật thành công");
        response.put("userId", request.get("userId"));
        response.put("diemTang", 500);
        response.put("lyDo", "Chúc mừng sinh nhật!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/expiring-points")
    @Operation(summary = "Điểm sắp hết hạn", description = "Danh sách khách hàng có điểm sắp hết hạn")
    public ResponseEntity<Map<String, Object>> getExpiringPoints(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of("userId", 1L, "hoTen", "Nguyễn A", "diemSapHetHan", 150, "ngayHetHan", "2024-02-15"),
            Map.of("userId", 2L, "hoTen", "Trần B", "diemSapHetHan", 200, "ngayHetHan", "2024-02-20")
        ));
        return ResponseEntity.ok(response);
    }
}