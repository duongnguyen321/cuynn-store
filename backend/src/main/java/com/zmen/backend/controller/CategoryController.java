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
@RequestMapping("/api/categories")
@Tag(name = "Category API", description = "API quản lý danh mục sản phẩm")
@CrossOrigin(origins = "*")
public class CategoryController {

    @GetMapping
    @Operation(summary = "Lấy danh sách danh mục", description = "Lấy tất cả danh mục sản phẩm với cấu trúc cây")
    public ResponseEntity<Map<String, Object>> getAllCategories(
            @RequestParam(required = false) Boolean includeInactive,
            @RequestParam(required = false) String search) {
        Map<String, Object> response = new HashMap<>();
        response.put("categories", Arrays.asList(
            Map.of(
                "id", 1L,
                "tenDanhMuc", "Áo nam",
                "slug", "ao-nam", 
                "thuTu", 1,
                "trangThai", "ACTIVE",
                "soSanPham", 45,
                "children", Arrays.asList(
                    Map.of("id", 11L, "tenDanhMuc", "Áo thun", "slug", "ao-thun", "soSanPham", 25),
                    Map.of("id", 12L, "tenDanhMuc", "Áo polo", "slug", "ao-polo", "soSanPham", 20)
                )
            ),
            Map.of(
                "id", 2L,
                "tenDanhMuc", "Quần nam", 
                "slug", "quan-nam",
                "thuTu", 2,
                "trangThai", "ACTIVE",
                "soSanPham", 32,
                "children", Arrays.asList(
                    Map.of("id", 21L, "tenDanhMuc", "Quần jean", "slug", "quan-jean", "soSanPham", 18),
                    Map.of("id", 22L, "tenDanhMuc", "Quần short", "slug", "quan-short", "soSanPham", 14)
                )
            )
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy chi tiết danh mục", description = "Lấy thông tin chi tiết của một danh mục")
    public ResponseEntity<Map<String, Object>> getCategoryById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("tenDanhMuc", "Áo nam");
        response.put("slug", "ao-nam");
        response.put("moTa", "Bộ sưu tập áo nam đa dạng, phong cách");
        response.put("hinhAnh", "/images/categories/ao-nam.jpg");
        response.put("thuTu", 1);
        response.put("trangThai", "ACTIVE");
        response.put("danhMucCha", null);
        response.put("seoTitle", "Áo nam - Thời trang ZMEN");
        response.put("seoDescription", "Khám phá bộ sưu tập áo nam đa dạng tại ZMEN");
        response.put("soSanPham", 45);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Tạo danh mục mới", description = "Thêm danh mục sản phẩm mới")
    public ResponseEntity<Map<String, Object>> createCategory(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Tạo danh mục thành công");
        response.put("id", 10L);
        response.put("tenDanhMuc", request.get("tenDanhMuc"));
        response.put("slug", request.get("slug"));
        response.put("trangThai", "ACTIVE");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Cập nhật danh mục", description = "Cập nhật thông tin danh mục")
    public ResponseEntity<Map<String, Object>> updateCategory(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cập nhật danh mục thành công");
        response.put("id", id);
        response.put("tenDanhMuc", request.get("tenDanhMuc"));
        response.put("slug", request.get("slug"));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa danh mục", description = "Xóa danh mục (soft delete)")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Xóa danh mục thành công");
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Cập nhật trạng thái", description = "Kích hoạt/vô hiệu hóa danh mục")
    public ResponseEntity<Map<String, Object>> updateCategoryStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cập nhật trạng thái thành công");
        response.put("id", id);
        response.put("trangThai", request.get("trangThai"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/products")
    @Operation(summary = "Sản phẩm theo danh mục", description = "Lấy danh sách sản phẩm thuộc danh mục")
    public ResponseEntity<Map<String, Object>> getProductsByCategory(
            @PathVariable Long id,
            Pageable pageable,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        Map<String, Object> response = new HashMap<>();
        response.put("category", Map.of("id", id, "tenDanhMuc", "Áo nam"));
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "tenSanPham", "Áo thun basic", "gia", 150000, "hinhAnh", "/images/ao1.jpg"),
            Map.of("id", 2L, "tenSanPham", "Áo polo classic", "gia", 250000, "hinhAnh", "/images/ao2.jpg")
        ));
        response.put("totalElements", 45);
        response.put("totalPages", 5);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reorder")
    @Operation(summary = "Sắp xếp lại danh mục", description = "Cập nhật thứ tự hiển thị các danh mục")
    public ResponseEntity<Map<String, Object>> reorderCategories(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Sắp xếp danh mục thành công");
        response.put("categories", request.get("categories"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tree")
    @Operation(summary = "Cây danh mục", description = "Lấy cấu trúc cây đầy đủ của danh mục")
    public ResponseEntity<Map<String, Object>> getCategoryTree() {
        Map<String, Object> response = new HashMap<>();
        response.put("tree", Arrays.asList(
            Map.of(
                "id", 1L,
                "tenDanhMuc", "Thời trang nam",
                "level", 0,
                "children", Arrays.asList(
                    Map.of(
                        "id", 11L,
                        "tenDanhMuc", "Áo nam",
                        "level", 1,
                        "children", Arrays.asList(
                            Map.of("id", 111L, "tenDanhMuc", "Áo thun", "level", 2),
                            Map.of("id", 112L, "tenDanhMuc", "Áo polo", "level", 2)
                        )
                    ),
                    Map.of(
                        "id", 12L,
                        "tenDanhMuc", "Quần nam", 
                        "level", 1,
                        "children", Arrays.asList(
                            Map.of("id", 121L, "tenDanhMuc", "Quần jean", "level", 2),
                            Map.of("id", 122L, "tenDanhMuc", "Quần short", "level", 2)
                        )
                    )
                )
            )
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/breadcrumb/{id}")
    @Operation(summary = "Breadcrumb danh mục", description = "Lấy đường dẫn breadcrumb của danh mục")
    public ResponseEntity<Map<String, Object>> getCategoryBreadcrumb(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("breadcrumb", Arrays.asList(
            Map.of("id", 1L, "tenDanhMuc", "Thời trang nam", "slug", "thoi-trang-nam"),
            Map.of("id", 11L, "tenDanhMuc", "Áo nam", "slug", "ao-nam"), 
            Map.of("id", 111L, "tenDanhMuc", "Áo thun", "slug", "ao-thun")
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics")
    @Operation(summary = "Thống kê danh mục", description = "Thống kê sản phẩm và doanh số theo danh mục")
    public ResponseEntity<Map<String, Object>> getCategoryStatistics() {
        Map<String, Object> response = new HashMap<>();
        response.put("statistics", Arrays.asList(
            Map.of(
                "categoryId", 1L,
                "tenDanhMuc", "Áo nam",
                "soSanPham", 45,
                "soSanPhamBan", 320,
                "doanhThu", 48000000,
                "trangThai", "ACTIVE"
            ),
            Map.of(
                "categoryId", 2L,
                "tenDanhMuc", "Quần nam",
                "soSanPham", 32,
                "soSanPhamBan", 180,
                "doanhThu", 54000000,
                "trangThai", "ACTIVE"
            )
        ));
        response.put("tongDoanhThu", 102000000);
        response.put("tongSanPham", 77);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/popular")
    @Operation(summary = "Danh mục phổ biến", description = "Lấy danh sách danh mục bán chạy nhất")
    public ResponseEntity<Map<String, Object>> getPopularCategories(@RequestParam(defaultValue = "10") int limit) {
        Map<String, Object> response = new HashMap<>();
        response.put("categories", Arrays.asList(
            Map.of("id", 1L, "tenDanhMuc", "Áo thun", "soLuotMua", 520, "doanhThu", 78000000),
            Map.of("id", 2L, "tenDanhMuc", "Quần jean", "soLuotMua", 380, "doanhThu", 95000000),
            Map.of("id", 3L, "tenDanhMuc", "Áo polo", "soLuotMua", 290, "doanhThu", 58000000)
        ));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/upload-image")
    @Operation(summary = "Upload ảnh danh mục", description = "Upload ảnh đại diện cho danh mục")
    public ResponseEntity<Map<String, Object>> uploadCategoryImage(@PathVariable Long id, @RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Upload ảnh thành công");
        response.put("categoryId", id);
        response.put("imageUrl", "/images/categories/" + id + ".jpg");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "Tìm kiếm danh mục", description = "Tìm kiếm danh mục theo tên")
    public ResponseEntity<Map<String, Object>> searchCategories(@RequestParam String q) {
        Map<String, Object> response = new HashMap<>();
        response.put("results", Arrays.asList(
            Map.of("id", 1L, "tenDanhMuc", "Áo thun nam", "slug", "ao-thun-nam", "level", 2),
            Map.of("id", 2L, "tenDanhMuc", "Áo polo nam", "slug", "ao-polo-nam", "level", 2)
        ));
        response.put("query", q);
        response.put("total", 2);
        return ResponseEntity.ok(response);
    }
}