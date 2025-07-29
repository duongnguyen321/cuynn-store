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
@RequestMapping("/api/blog")
@Tag(name = "Blog API", description = "API quản lý blog và nội dung")
@CrossOrigin(origins = "*")
public class BlogController {

    private Map<String, Object> createBlogPost(Long id, String title, String slug, String image, String summary, 
                                               String authorName, String status, String publishDate, 
                                               int views, int likes, int comments) {
        Map<String, Object> post = new HashMap<>();
        post.put("id", id);
        post.put("tieuDe", title);
        post.put("slug", slug);
        post.put("anhDaiDien", image);
        post.put("tomTat", summary);
        
        Map<String, Object> author = new HashMap<>();
        author.put("id", 1L);
        author.put("hoTen", authorName);
        post.put("tacGia", author);
        
        post.put("trangThai", status);
        post.put("ngayXuatBan", publishDate);
        post.put("luotXem", views);
        post.put("luotThich", likes);
        post.put("soComment", comments);
        return post;
    }

    @GetMapping("/posts")
    @Operation(summary = "Danh sách bài viết", description = "Lấy tất cả bài viết blog với phân trang")
    public ResponseEntity<Map<String, Object>> getAllPosts(
            Pageable pageable,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", Arrays.asList(
            createBlogPost(1L, "Xu hướng thời trang nam 2024", "xu-huong-thoi-trang-nam-2024", 
                         "/images/blog/post1.jpg", "Khám phá những xu hướng thời trang nam nổi bật trong năm 2024",
                         "Nguyễn Văn A", "PUBLISHED", "2024-01-15", 1250, 45, 12),
            createBlogPost(2L, "Cách phối đồ đẹp cho nam giới", "cach-phoi-do-dep-cho-nam-gioi",
                         "/images/blog/post2.jpg", "Hướng dẫn chi tiết cách phối đồ nam thanh lịch và phong cách",
                         "Trần Thị B", "PUBLISHED", "2024-01-10", 980, 32, 8)
        ));
        response.put("totalElements", 50);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/{slug}")
    @Operation(summary = "Chi tiết bài viết", description = "Lấy nội dung đầy đủ của bài viết")
    public ResponseEntity<Map<String, Object>> getPostBySlug(@PathVariable String slug) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", 1L);
        response.put("tieuDe", "Xu hướng thời trang nam 2024");
        response.put("slug", slug);
        response.put("anhDaiDien", "/images/blog/post1.jpg");
        response.put("tomTat", "Khám phá những xu hướng thời trang nam nổi bật trong năm 2024");
        response.put("noiDung", "<p>Nội dung đầy đủ của bài viết...</p>");
        
        Map<String, Object> author = new HashMap<>();
        author.put("id", 1L);
        author.put("hoTen", "Nguyễn Văn A");
        author.put("avatar", "/images/authors/author1.jpg");
        author.put("tieuSu", "Chuyên gia thời trang với 10 năm kinh nghiệm");
        response.put("tacGia", author);
        
        response.put("trangThai", "PUBLISHED");
        response.put("ngayXuatBan", "2024-01-15");
        response.put("ngayCapNhat", "2024-01-16");
        response.put("luotXem", 1250);
        response.put("luotThich", 45);
        response.put("tags", Arrays.asList("thời trang", "nam giới", "xu hướng 2024"));
        response.put("seoTitle", "Xu hướng thời trang nam 2024 - ZMEN Blog");
        response.put("seoDescription", "Khám phá những xu hướng thời trang nam nổi bật nhất trong năm 2024");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/posts")
    @Operation(summary = "Tạo bài viết mới", description = "Thêm bài viết blog mới")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Tạo bài viết thành công");
        response.put("id", 10L);
        response.put("tieuDe", request.get("tieuDe"));
        response.put("slug", request.get("slug"));
        response.put("trangThai", "DRAFT");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/posts/{id}")
    @Operation(summary = "Cập nhật bài viết", description = "Cập nhật nội dung bài viết")
    public ResponseEntity<Map<String, Object>> updatePost(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cập nhật bài viết thành công");
        response.put("id", id);
        response.put("tieuDe", request.get("tieuDe"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/categories")
    @Operation(summary = "Danh mục blog", description = "Lấy tất cả danh mục bài viết")
    public ResponseEntity<Map<String, Object>> getBlogCategories() {
        Map<String, Object> response = new HashMap<>();
        response.put("categories", Arrays.asList(
            Map.of("id", 1L, "tenDanhMuc", "Thời trang", "slug", "thoi-trang", "soBaiViet", 25),
            Map.of("id", 2L, "tenDanhMuc", "Phong cách", "slug", "phong-cach", "soBaiViet", 18),
            Map.of("id", 3L, "tenDanhMuc", "Xu hướng", "slug", "xu-huong", "soBaiViet", 12)
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "Tìm kiếm bài viết", description = "Tìm kiếm bài viết theo từ khóa")
    public ResponseEntity<Map<String, Object>> searchPosts(@RequestParam String q, Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        response.put("query", q);
        response.put("content", Arrays.asList(
            Map.of("id", 1L, "tieuDe", "Xu hướng thời trang nam 2024", "tomTat", "Khám phá xu hướng...", "luotXem", 1250)
        ));
        response.put("totalElements", 5);
        return ResponseEntity.ok(response);
    }
}