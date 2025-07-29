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
@RequestMapping("/api/reviews")
@Tag(name = "Review API", description = "API quản lý đánh giá và nhận xét sản phẩm")
@CrossOrigin(origins = "*")
public class ReviewController {

    @GetMapping("/product/{productId}")
    @Operation(summary = "Đánh giá theo sản phẩm", description = "Lấy tất cả đánh giá của một sản phẩm")
    public ResponseEntity<Map<String, Object>> getProductReviews(
            @PathVariable Long productId,
            Pageable pageable,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) String sortBy) {
        Map<String, Object> response = new HashMap<>();
        response.put("product", Map.of("id", productId, "tenSanPham", "Áo thun basic"));
        response.put("summary", Map.of(
            "avgRating", 4.2,
            "totalReviews", 156,
            "ratingDistribution", Map.of(
                "5", 65, "4", 45, "3", 25, "2", 15, "1", 6
            )
        ));
        response.put("content", Arrays.asList(
            Map.of(
                "id", 1L,
                "nguoiDung", Map.of("id", 1L, "hoTen", "Nguyễn A"),
                "soSao", 5,
                "tieuDe", "Sản phẩm rất tốt!",
                "noiDung", "Chất liệu mềm mại, form áo đẹp. Sẽ mua thêm!",
                "ngayTao", "2024-01-15",
                "hinhAnh", Arrays.asList("/images/review1.jpg"),
                "luotThich", 12,
                "luotKhongThich", 1
            )
        ));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Tạo đánh giá mới", description = "Thêm đánh giá mới cho sản phẩm")
    public ResponseEntity<Map<String, Object>> createReview(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Tạo đánh giá thành công");
        response.put("reviewId", 100L);
        response.put("soSao", request.get("soSao"));
        response.put("noiDung", request.get("noiDung"));
        response.put("trangThai", "PENDING_APPROVAL");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pending")
    @Operation(summary = "Đánh giá chờ duyệt", description = "Lấy danh sách đánh giá chờ kiểm duyệt")
    public ResponseEntity<Map<String, Object>> getPendingReviews(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "sanPham", "Áo thun ABC", "nguoiDung", "Nguyễn A", "soSao", 4, "ngayTao", "2024-01-15"),
            Map.of("id", 2L, "sanPham", "Quần jean XYZ", "nguoiDung", "Trần B", "soSao", 5, "ngayTao", "2024-01-14")
        ));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/approve")
    @Operation(summary = "Duyệt đánh giá", description = "Phê duyệt đánh giá để hiển thị công khai")
    public ResponseEntity<Map<String, Object>> approveReview(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Duyệt đánh giá thành công");
        response.put("reviewId", id);
        response.put("trangThai", "APPROVED");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/helpful")
    @Operation(summary = "Đánh dấu hữu ích", description = "Đánh dấu đánh giá là hữu ích")
    public ResponseEntity<Map<String, Object>> markAsHelpful(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Đã đánh dấu hữu ích");
        response.put("reviewId", id);
        response.put("luotThich", 13);
        return ResponseEntity.ok(response);
    }
}