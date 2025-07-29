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
@RequestMapping("/api/brands")
@Tag(name = "Brand API", description = "API quản lý thương hiệu sản phẩm")
@CrossOrigin(origins = "*")
public class BrandController {

    @GetMapping
    @Operation(summary = "Lấy danh sách thương hiệu", description = "Lấy tất cả thương hiệu với phân trang và bộ lọc")
    public ResponseEntity<Map<String, Object>> getAllBrands(
            Pageable pageable,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            Map.of(
                "id", 1L,
                "tenThuongHieu", "Nike",
                "slug", "nike",
                "logo", "/images/brands/nike-logo.png",
                "moTa", "Thương hiệu thể thao hàng đầu thế giới",
                "trangThai", "ACTIVE",
                "soSanPham", 125,
                "quocGia", "USA",
                "website", "https://nike.com",
                "namThanhLap", 1964
            ),
            Map.of(
                "id", 2L,
                "tenThuongHieu", "Adidas", 
                "slug", "adidas",
                "logo", "/images/brands/adidas-logo.png",
                "moTa", "Thương hiệu thể thao Đức nổi tiếng",
                "trangThai", "ACTIVE",
                "soSanPham", 98,
                "quocGia", "Germany",
                "website", "https://adidas.com",
                "namThanhLap", 1949
            )
        ));
        response.put("totalElements", 45);
        response.put("totalPages", 5);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy chi tiết thương hiệu", description = "Lấy thông tin chi tiết của một thương hiệu")
    public ResponseEntity<Map<String, Object>> getBrandById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("tenThuongHieu", "Nike");
        response.put("slug", "nike");
        response.put("logo", "/images/brands/nike-logo.png");
        response.put("banner", "/images/brands/nike-banner.jpg");
        response.put("moTa", "Nike là thương hiệu thể thao hàng đầu thế giới, được thành lập năm 1964");
        response.put("moTaChiTiet", "Nike Inc. là một tập đoàn đa quốc gia của Mỹ...");
        response.put("trangThai", "ACTIVE");
        response.put("quocGia", "USA");
        response.put("website", "https://nike.com");
        response.put("namThanhLap", 1964);
        response.put("soSanPham", 125);
        response.put("seoTitle", "Nike - Thương hiệu thể thao hàng đầu");
        response.put("seoDescription", "Khám phá bộ sưu tập Nike tại ZMEN Store");
        response.put("tongDanhSo", 15500000);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Tạo thương hiệu mới", description = "Thêm thương hiệu mới vào hệ thống")
    public ResponseEntity<Map<String, Object>> createBrand(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Tạo thương hiệu thành công");
        response.put("id", 10L);
        response.put("tenThuongHieu", request.get("tenThuongHieu"));
        response.put("slug", request.get("slug"));
        response.put("trangThai", "ACTIVE");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Cập nhật thương hiệu", description = "Cập nhật thông tin thương hiệu")
    public ResponseEntity<Map<String, Object>> updateBrand(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cập nhật thương hiệu thành công");
        response.put("id", id);
        response.put("tenThuongHieu", request.get("tenThuongHieu"));
        response.put("slug", request.get("slug"));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa thương hiệu", description = "Xóa thương hiệu (soft delete)")
    public ResponseEntity<Map<String, Object>> deleteBrand(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Xóa thương hiệu thành công");
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Cập nhật trạng thái", description = "Kích hoạt/vô hiệu hóa thương hiệu")
    public ResponseEntity<Map<String, Object>> updateBrandStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cập nhật trạng thái thành công");
        response.put("id", id);
        response.put("trangThai", request.get("trangThai"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/products")
    @Operation(summary = "Sản phẩm theo thương hiệu", description = "Lấy danh sách sản phẩm của thương hiệu")
    public ResponseEntity<Map<String, Object>> getProductsByBrand(
            @PathVariable Long id,
            Pageable pageable,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        Map<String, Object> response = new HashMap<>();
        response.put("brand", Map.of("id", id, "tenThuongHieu", "Nike"));
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "tenSanPham", "Nike Air Max", "gia", 2500000, "hinhAnh", "/images/nike1.jpg"),
            Map.of("id", 2L, "tenSanPham", "Nike React", "gia", 2800000, "hinhAnh", "/images/nike2.jpg")
        ));
        response.put("totalElements", 125);
        response.put("totalPages", 13);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/popular")
    @Operation(summary = "Thương hiệu phổ biến", description = "Lấy danh sách thương hiệu bán chạy nhất")
    public ResponseEntity<Map<String, Object>> getPopularBrands(@RequestParam(defaultValue = "10") int limit) {
        Map<String, Object> response = new HashMap<>();
        response.put("brands", Arrays.asList(
            Map.of("id", 1L, "tenThuongHieu", "Nike", "soLuotMua", 1250, "doanhThu", 125000000),
            Map.of("id", 2L, "tenThuongHieu", "Adidas", "soLuotMua", 980, "doanhThu", 98000000),
            Map.of("id", 3L, "tenThuongHieu", "Puma", "soLuotMua", 650, "doanhThu", 65000000)
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics")
    @Operation(summary = "Thống kê thương hiệu", description = "Thống kê sản phẩm và doanh số theo thương hiệu")
    public ResponseEntity<Map<String, Object>> getBrandStatistics() {
        Map<String, Object> response = new HashMap<>();
        response.put("statistics", Arrays.asList(
            Map.of(
                "brandId", 1L,
                "tenThuongHieu", "Nike",
                "soSanPham", 125,
                "soSanPhamBan", 1250,
                "doanhThu", 125000000,
                "trangThai", "ACTIVE",
                "tileChuyenDoi", 12.5
            ),
            Map.of(
                "brandId", 2L,
                "tenThuongHieu", "Adidas",
                "soSanPham", 98,
                "soSanPhamBan", 980,
                "doanhThu", 98000000,
                "trangThai", "ACTIVE",
                "tileChuyenDoi", 10.8
            )
        ));
        response.put("tongDoanhThu", 223000000);
        response.put("tongSanPham", 223);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/upload-logo")
    @Operation(summary = "Upload logo thương hiệu", description = "Upload logo cho thương hiệu")
    public ResponseEntity<Map<String, Object>> uploadBrandLogo(@PathVariable Long id, @RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Upload logo thành công");
        response.put("brandId", id);
        response.put("logoUrl", "/images/brands/" + id + "-logo.png");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/upload-banner")
    @Operation(summary = "Upload banner thương hiệu", description = "Upload banner cho trang thương hiệu")
    public ResponseEntity<Map<String, Object>> uploadBrandBanner(@PathVariable Long id, @RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Upload banner thành công");
        response.put("brandId", id);
        response.put("bannerUrl", "/images/brands/" + id + "-banner.jpg");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "Tìm kiếm thương hiệu", description = "Tìm kiếm thương hiệu theo tên")
    public ResponseEntity<Map<String, Object>> searchBrands(@RequestParam String q) {
        Map<String, Object> response = new HashMap<>();
        response.put("results", Arrays.asList(
            Map.of("id", 1L, "tenThuongHieu", "Nike", "slug", "nike", "soSanPham", 125),
            Map.of("id", 2L, "tenThuongHieu", "Adidas", "slug", "adidas", "soSanPham", 98)
        ));
        response.put("query", q);
        response.put("total", 2);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/reviews")
    @Operation(summary = "Đánh giá thương hiệu", description = "Lấy đánh giá và nhận xét về thương hiệu")
    public ResponseEntity<Map<String, Object>> getBrandReviews(
            @PathVariable Long id,
            Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("brand", Map.of("id", id, "tenThuongHieu", "Nike"));
        response.put("avgRating", 4.3);
        response.put("totalReviews", 2850);
        response.put("content", Arrays.asList(
            Map.of(
                "id", 1L,
                "nguoiDung", "Nguyễn A",
                "soSao", 5,
                "noiDung", "Chất lượng sản phẩm Nike rất tốt!",
                "ngayTao", "2024-01-15"
            ),
            Map.of(
                "id", 2L,
                "nguoiDung", "Trần B", 
                "soSao", 4,
                "noiDung", "Thiết kế đẹp, giá hơi cao",
                "ngayTao", "2024-01-14"
            )
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/competitors")
    @Operation(summary = "Thương hiệu cạnh tranh", description = "Lấy danh sách thương hiệu cạnh tranh")
    public ResponseEntity<Map<String, Object>> getBrandCompetitors(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("brand", Map.of("id", id, "tenThuongHieu", "Nike"));
        response.put("competitors", Arrays.asList(
            Map.of("id", 2L, "tenThuongHieu", "Adidas", "mucDoTuongDong", 95),
            Map.of("id", 3L, "tenThuongHieu", "Puma", "mucDoTuongDong", 85),
            Map.of("id", 4L, "tenThuongHieu", "Under Armour", "mucDoTuongDong", 75)
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/trending")
    @Operation(summary = "Thương hiệu đang trending", description = "Thương hiệu đang được quan tâm nhiều")
    public ResponseEntity<Map<String, Object>> getTrendingBrands() {
        Map<String, Object> response = new HashMap<>();
        response.put("brands", Arrays.asList(
            Map.of("id", 1L, "tenThuongHieu", "Nike", "luotXem", 15000, "tangTruong", 25.5),
            Map.of("id", 5L, "tenThuongHieu", "Jordan", "luotXem", 12000, "tangTruong", 45.2),
            Map.of("id", 2L, "tenThuongHieu", "Adidas", "luotXem", 11500, "tangTruong", 18.7)
        ));
        response.put("thoiGian", "7 ngày qua");
        return ResponseEntity.ok(response);
    }
}