package com.zmen.backend.controller;

import com.zmen.backend.dto.OrderRequest;
import com.zmen.backend.entity.Cart;
import com.zmen.backend.entity.Order;
import com.zmen.backend.entity.User;
import com.zmen.backend.service.CartService;
import com.zmen.backend.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@Api(tags = "Order Management")
@CrossOrigin(origins = "*")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private CartService cartService;
    
    @PostMapping("/checkout")
    @ApiOperation("Create order from cart")
    public ResponseEntity<Map<String, Object>> checkout(
            @RequestBody OrderRequest orderRequest,
            HttpServletRequest request) {
        try {
            String sessionId = request.getSession().getId();
            User user = getCurrentUser(); // Placeholder for authentication
            
            Cart cart = cartService.getOrCreateCart(user, sessionId);
            Order order = orderService.createOrderFromCart(cart, orderRequest);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Order created successfully");
            response.put("order", order);
            response.put("orderNumber", order.getOrderNumber());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping
    @ApiOperation("Get all orders with pagination")
    public ResponseEntity<Page<Order>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Order> orders = orderService.getAllOrders(pageable);
        
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/status/{status}")
    @ApiOperation("Get orders by status")
    public ResponseEntity<Page<Order>> getOrdersByStatus(
            @PathVariable String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        try {
            Order.OrderStatus orderStatus = Order.OrderStatus.valueOf(status.toUpperCase());
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
            Page<Order> orders = orderService.getOrdersByStatus(orderStatus, pageable);
            
            return ResponseEntity.ok(orders);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation("Get order by ID")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/number/{orderNumber}")
    @ApiOperation("Get order by order number")
    public ResponseEntity<Order> getOrderByNumber(@PathVariable String orderNumber) {
        Optional<Order> order = orderService.getOrderByNumber(orderNumber);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}/status")
    @ApiOperation("Update order status")
    public ResponseEntity<Map<String, Object>> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        try {
            Order.OrderStatus orderStatus = Order.OrderStatus.valueOf(status.toUpperCase());
            Order order = orderService.updateOrderStatus(id, orderStatus);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Order status updated successfully");
            response.put("order", order);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PutMapping("/{id}/payment")
    @ApiOperation("Update payment status")
    public ResponseEntity<Map<String, Object>> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String paymentStatus,
            @RequestParam(required = false) String paymentReference) {
        try {
            Order.PaymentStatus status = Order.PaymentStatus.valueOf(paymentStatus.toUpperCase());
            Order order = orderService.updatePaymentStatus(id, status, paymentReference);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Payment status updated successfully");
            response.put("order", order);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    // Helper method to get current user (placeholder for authentication)
    private User getCurrentUser() {
        // For now, return null to use session-based functionality
        // In a real app, this would get the authenticated user
        return null;
    }
}

