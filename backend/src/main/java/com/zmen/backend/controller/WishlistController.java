package com.zmen.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "Wishlist API", description = "API quản lý danh sách yêu thích")
public class WishlistController {

    @GetMapping("/user/{userId}")
    @Operation(summary = "Lấy danh sách yêu thích", description = "Lấy danh sách sản phẩm yêu thích của người dùng")
    public ResponseEntity<Page<Object>> getUserWishlist(@PathVariable Long userId, Pageable pageable) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/{userId}/product/{productId}")
    @Operation(summary = "Thêm vào yêu thích", description = "Thêm sản phẩm vào danh sách yêu thích")
    public ResponseEntity<Object> addToWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}/product/{productId}")
    @Operation(summary = "Xóa khỏi yêu thích", description = "Xóa sản phẩm khỏi danh sách yêu thích")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/check/{productId}")
    @Operation(summary = "Kiểm tra yêu thích", description = "Kiểm tra sản phẩm có trong danh sách yêu thích không")
    public ResponseEntity<Object> checkWishlistItem(@PathVariable Long userId, @PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}")
    @Operation(summary = "Xóa toàn bộ yêu thích", description = "Xóa tất cả sản phẩm khỏi danh sách yêu thích")
    public ResponseEntity<Void> clearWishlist(@PathVariable Long userId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/{userId}/move-to-cart")
    @Operation(summary = "Chuyển vào giỏ hàng", description = "Chuyển sản phẩm từ danh sách yêu thích vào giỏ hàng")
    public ResponseEntity<Object> moveToCart(@PathVariable Long userId, @RequestBody Object moveRequest) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/count")
    @Operation(summary = "Đếm số lượng yêu thích", description = "Lấy số lượng sản phẩm trong danh sách yêu thích")
    public ResponseEntity<Object> getWishlistCount(@PathVariable Long userId) {
        return ResponseEntity.ok().build();
    }
}

