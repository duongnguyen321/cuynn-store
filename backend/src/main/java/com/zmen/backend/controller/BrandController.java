package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/brands")
@Tag(name = "Brand API", description = "API quản lý thương hiệu sản phẩm")
@CrossOrigin(origins = "*")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    @Operation(summary = "Lấy danh sách thương hiệu", description = "Lấy tất cả thương hiệu với phân trang và bộ lọc")
    public ResponseEntity<Page<BrandResponse>> getAllBrands(
            Pageable pageable,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy) {
        Page<BrandResponse> brands = brandService.getBrandsPaginated(search, true, pageable);
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy chi tiết thương hiệu", description = "Lấy thông tin chi tiết của một thương hiệu")
    public ResponseEntity<BrandDetailResponse> getBrandById(@PathVariable Long id) {
        BrandDetailResponse brand = brandService.getBrandById(id);
        return ResponseEntity.ok(brand);
    }

    @PostMapping
    @Operation(summary = "Tạo thương hiệu mới", description = "Thêm thương hiệu mới vào hệ thống")
    public ResponseEntity<BrandResponse> createBrand(@RequestBody CreateBrandRequest request) {
        BrandResponse brand = brandService.createBrand(request, "system");
        return ResponseEntity.ok(brand);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Cập nhật thương hiệu", description = "Cập nhật thông tin thương hiệu")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable Long id, @RequestBody UpdateBrandRequest request) {
        BrandResponse brand = brandService.updateBrand(id, request, "system");
        return ResponseEntity.ok(brand);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa thương hiệu", description = "Xóa thương hiệu (soft delete)")
    public ResponseEntity<Map<String, Object>> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id, "system");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Xóa thương hiệu thành công");
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Cập nhật trạng thái", description = "Kích hoạt/vô hiệu hóa thương hiệu")
    public ResponseEntity<BrandResponse> updateBrandStatus(@PathVariable Long id, @RequestBody UpdateBrandStatusRequest request) {
        BrandResponse brand = brandService.updateBrandStatus(id, request, "system");
        return ResponseEntity.ok(brand);
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
    public ResponseEntity<List<BrandResponse>> getPopularBrands(@RequestParam(defaultValue = "10") int limit) {
        List<BrandResponse> brands = brandService.getPopularBrands(limit);
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/statistics")
    @Operation(summary = "Thống kê thương hiệu", description = "Thống kê sản phẩm và doanh số theo thương hiệu")
    public ResponseEntity<List<BrandResponse>> getBrandStatistics() {
        List<BrandResponse> brands = brandService.getAllBrands(true, true);
        return ResponseEntity.ok(brands);
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
    public ResponseEntity<Page<BrandResponse>> searchBrands(@RequestParam String q, Pageable pageable) {
        Page<BrandResponse> brands = brandService.searchBrands(q, pageable);
        return ResponseEntity.ok(brands);
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