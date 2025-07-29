package com.zmen.backend.controller;

import com.zmen.backend.entity.Order;
import com.zmen.backend.entity.Product;
import com.zmen.backend.entity.User;
import com.zmen.backend.repository.OrderRepository;
import com.zmen.backend.repository.ProductRepository;
import com.zmen.backend.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@Api(tags = "Admin Dashboard")
@CrossOrigin(origins = "*")
public class AdminController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @GetMapping("/dashboard")
    @ApiOperation("Get dashboard statistics")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Basic counts
        long totalUsers = userRepository.count();
        long totalProducts = productRepository.count();
        long totalOrders = orderRepository.count();
        
        // Order status counts
        long pendingOrders = orderRepository.countByStatus(Order.OrderStatus.PENDING);
        long confirmedOrders = orderRepository.countByStatus(Order.OrderStatus.CONFIRMED);
        long shippedOrders = orderRepository.countByStatus(Order.OrderStatus.SHIPPED);
        long deliveredOrders = orderRepository.countByStatus(Order.OrderStatus.DELIVERED);
        
        // Revenue calculation (last 30 days)
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minus(30, ChronoUnit.DAYS);
        LocalDateTime now = LocalDateTime.now();
        Double monthlyRevenue = orderRepository.getTotalRevenueByDateRange(thirtyDaysAgo, now);
        
        stats.put("totalUsers", totalUsers);
        stats.put("totalProducts", totalProducts);
        stats.put("totalOrders", totalOrders);
        stats.put("pendingOrders", pendingOrders);
        stats.put("confirmedOrders", confirmedOrders);
        stats.put("shippedOrders", shippedOrders);
        stats.put("deliveredOrders", deliveredOrders);
        stats.put("monthlyRevenue", monthlyRevenue != null ? monthlyRevenue : 0.0);
        
        return ResponseEntity.ok(stats);
    }
    
    @GetMapping("/users")
    @ApiOperation("Get all users")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<User> users = userRepository.findAll(pageable);
        
        return ResponseEntity.ok(users);
    }
    
    @PostMapping("/products")
    @ApiOperation("Create new product")
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productRepository.save(product);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Product created successfully");
            response.put("product", savedProduct);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PutMapping("/products/{id}")
    @ApiOperation("Update product")
    public ResponseEntity<Map<String, Object>> updateProduct(
            @PathVariable Long id,
            @RequestBody Product productUpdate) {
        try {
            Optional<Product> productOpt = productRepository.findById(id);
            if (!productOpt.isPresent()) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Product not found");
                return ResponseEntity.notFound().build();
            }
            
            Product product = productOpt.get();
            
            // Update fields
            if (productUpdate.getName() != null) product.setName(productUpdate.getName());
            if (productUpdate.getDescription() != null) product.setDescription(productUpdate.getDescription());
            if (productUpdate.getPrice() != null) product.setPrice(productUpdate.getPrice());
            if (productUpdate.getOriginalPrice() != null) product.setOriginalPrice(productUpdate.getOriginalPrice());
            if (productUpdate.getDiscountPercentage() != null) product.setDiscountPercentage(productUpdate.getDiscountPercentage());
            if (productUpdate.getCategory() != null) product.setCategory(productUpdate.getCategory());
            if (productUpdate.getBrand() != null) product.setBrand(productUpdate.getBrand());
            if (productUpdate.getImageUrl() != null) product.setImageUrl(productUpdate.getImageUrl());
            if (productUpdate.getStockQuantity() != null) product.setStockQuantity(productUpdate.getStockQuantity());
            if (productUpdate.getStatus() != null) product.setStatus(productUpdate.getStatus());
            if (productUpdate.getFeatured() != null) product.setFeatured(productUpdate.getFeatured());
            if (productUpdate.getIsNew() != null) product.setIsNew(productUpdate.getIsNew());
            if (productUpdate.getBestSeller() != null) product.setBestSeller(productUpdate.getBestSeller());
            
            Product savedProduct = productRepository.save(product);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Product updated successfully");
            response.put("product", savedProduct);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @DeleteMapping("/products/{id}")
    @ApiOperation("Delete product")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable Long id) {
        try {
            Optional<Product> productOpt = productRepository.findById(id);
            if (!productOpt.isPresent()) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Product not found");
                return ResponseEntity.notFound().build();
            }
            
            // Soft delete by setting status to INACTIVE
            Product product = productOpt.get();
            product.setStatus("INACTIVE");
            productRepository.save(product);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Product deleted successfully");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/recent-orders")
    @ApiOperation("Get recent orders")
    public ResponseEntity<List<Order>> getRecentOrders(
            @RequestParam(defaultValue = "10") int limit) {
        
        Pageable pageable = PageRequest.of(0, limit, Sort.by("createdAt").descending());
        Page<Order> ordersPage = orderRepository.findAll(pageable);
        
        return ResponseEntity.ok(ordersPage.getContent());
    }
}

