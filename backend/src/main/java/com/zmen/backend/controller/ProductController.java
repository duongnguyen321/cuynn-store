package com.zmen.backend.controller;

import com.zmen.backend.entity.Product;
import com.zmen.backend.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "API quản lý sản phẩm")
@CrossOrigin(origins = "*")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping
    @Operation(summary = "Lấy danh sách sản phẩm", description = "Lấy danh sách tất cả sản phẩm với phân trang")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String status) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByDeletedAtIsNull(pageable);
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Lấy chi tiết sản phẩm", description = "Lấy thông tin chi tiết của một sản phẩm")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findByIdAndDeletedAtIsNull(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    @Operation(summary = "Tạo sản phẩm mới", description = "Thêm sản phẩm mới vào hệ thống")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Cập nhật sản phẩm", description = "Cập nhật thông tin sản phẩm")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = productRepository.findByIdAndDeletedAtIsNull(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            Product savedProduct = productRepository.save(product);
            return ResponseEntity.ok(savedProduct);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa sản phẩm", description = "Xóa sản phẩm khỏi hệ thống (soft delete)")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findByIdAndDeletedAtIsNull(id);
        if (product.isPresent()) {
            Product p = product.get();
            p.setDeletedAt(new java.util.Date());
            productRepository.save(p);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/search")
    @Operation(summary = "Tìm kiếm sản phẩm", description = "Tìm kiếm sản phẩm theo từ khóa, danh mục, giá")
    public ResponseEntity<Page<Product>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        java.math.BigDecimal minPriceBD = minPrice != null ? new java.math.BigDecimal(minPrice) : null;
        java.math.BigDecimal maxPriceBD = maxPrice != null ? new java.math.BigDecimal(maxPrice) : null;
        Page<Product> products = productRepository.searchProducts(keyword, minPriceBD, maxPriceBD, pageable);
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/categories")
    @Operation(summary = "Lấy danh sách danh mục", description = "Lấy tất cả danh mục sản phẩm")
    public ResponseEntity<List<String>> getCategories() {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/brands")
    @Operation(summary = "Lấy danh sách thương hiệu", description = "Lấy tất cả thương hiệu sản phẩm")
    public ResponseEntity<List<String>> getBrands() {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/variants")
    @Operation(summary = "Lấy biến thể sản phẩm", description = "Lấy tất cả biến thể của một sản phẩm")
    public ResponseEntity<List<Object>> getProductVariants(@PathVariable Long id) {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/variants")
    @Operation(summary = "Thêm biến thể sản phẩm", description = "Thêm biến thể mới cho sản phẩm")
    public ResponseEntity<Object> addProductVariant(@PathVariable Long id, @RequestBody Object variantRequest) {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/images")
    @Operation(summary = "Lấy hình ảnh sản phẩm", description = "Lấy tất cả hình ảnh của sản phẩm")
    public ResponseEntity<List<Object>> getProductImages(@PathVariable Long id) {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/images")
    @Operation(summary = "Thêm hình ảnh sản phẩm", description = "Upload hình ảnh mới cho sản phẩm")
    public ResponseEntity<Object> addProductImage(@PathVariable Long id, @RequestBody Object imageRequest) {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/reviews")
    @Operation(summary = "Lấy đánh giá sản phẩm", description = "Lấy tất cả đánh giá của sản phẩm")
    public ResponseEntity<Page<Object>> getProductReviews(@PathVariable Long id, 
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/reviews")
    @Operation(summary = "Thêm đánh giá sản phẩm", description = "Thêm đánh giá mới cho sản phẩm")
    public ResponseEntity<Object> addProductReview(@PathVariable Long id, @RequestBody Object reviewRequest) {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/featured")
    @Operation(summary = "Sản phẩm nổi bật", description = "Lấy danh sách sản phẩm nổi bật")
    public ResponseEntity<List<Product>> getFeaturedProducts() {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/bestsellers")
    @Operation(summary = "Sản phẩm bán chạy", description = "Lấy danh sách sản phẩm bán chạy nhất")
    public ResponseEntity<List<Product>> getBestsellerProducts() {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/new-arrivals")
    @Operation(summary = "Sản phẩm mới", description = "Lấy danh sách sản phẩm mới nhất")
    public ResponseEntity<List<Product>> getNewArrivals() {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/related")
    @Operation(summary = "Sản phẩm liên quan", description = "Lấy danh sách sản phẩm liên quan")
    public ResponseEntity<List<Product>> getRelatedProducts(@PathVariable Long id) {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/stock")
    @Operation(summary = "Kiểm tra tồn kho", description = "Kiểm tra số lượng tồn kho của sản phẩm")
    public ResponseEntity<Object> checkProductStock(@PathVariable Long id) {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}/stock")
    @Operation(summary = "Cập nhật tồn kho", description = "Cập nhật số lượng tồn kho sản phẩm")
    public ResponseEntity<Object> updateProductStock(@PathVariable Long id, @RequestBody Object stockRequest) {
        // Implementation will be added later
        return ResponseEntity.ok().build();
    }
}

