package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Management", description = "Order processing and management APIs")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping("/checkout")
    @Operation(summary = "Create order from cart", description = "Process checkout and create a new order from the user's cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid order data or insufficient stock"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Cart is empty or not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> checkout(
            @Valid @RequestBody CheckoutRequest request,
            Principal principal) {
        try {
            OrderResponse order = orderService.checkout(principal.getName(), request);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Order created successfully", order));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to create order: " + e.getMessage()));
        }
    }
    
    @GetMapping
    @Operation(summary = "Get user's orders", description = "Get paginated list of orders for the current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orders retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserOrders(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Filter by order status") @RequestParam(required = false) String status,
            Principal principal) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<OrderResponse> orders = orderService.getUserOrders(principal.getName(), status, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Orders retrieved successfully", orders));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve orders: " + e.getMessage()));
        }
    }
    
    @GetMapping("/admin")
    @Operation(summary = "Get all orders (Admin)", description = "Get paginated list of all orders for admin management")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orders retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllOrders(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Filter by order status") @RequestParam(required = false) String status,
            @Parameter(description = "Filter by payment status") @RequestParam(required = false) String paymentStatus,
            @Parameter(description = "Search by order number or customer") @RequestParam(required = false) String search) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            OrderSearchRequest searchRequest = new OrderSearchRequest();
            searchRequest.setStatus(status);
            searchRequest.setPaymentStatus(paymentStatus);
            searchRequest.setSearch(search);
            
            Page<OrderResponse> orders = orderService.getAllOrders(searchRequest, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Orders retrieved successfully", orders));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve orders: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Get detailed order information by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your order"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getOrderById(
            @Parameter(description = "Order ID") @PathVariable Long id,
            Principal principal) {
        try {
            OrderDetailResponse order = orderService.getOrderDetail(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Order retrieved successfully", order));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve order: " + e.getMessage()));
        }
    }
    
    @GetMapping("/number/{orderNumber}")
    @Operation(summary = "Get order by order number", description = "Get order information by order number")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your order"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getOrderByNumber(
            @Parameter(description = "Order number") @PathVariable String orderNumber,
            Principal principal) {
        try {
            OrderDetailResponse order = orderService.getOrderDetailByNumber(orderNumber, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Order retrieved successfully", order));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve order: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update order status (Admin)", description = "Update the status of an order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order status updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid status or update not allowed"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateOrderStatus(
            @Parameter(description = "Order ID") @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request,
            Principal principal) {
        try {
            OrderResponse order = orderService.updateOrderStatus(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Order status updated successfully", order));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update order status: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/payment-status")
    @Operation(summary = "Update payment status (Admin)", description = "Update the payment status of an order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment status updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid payment status or update not allowed"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePaymentStatus(
            @Parameter(description = "Order ID") @PathVariable Long id,
            @Valid @RequestBody UpdatePaymentStatusRequest request,
            Principal principal) {
        try {
            OrderResponse order = orderService.updatePaymentStatus(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Payment status updated successfully", order));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update payment status: " + e.getMessage()));
        }
    }
    
    @PostMapping("/{id}/cancel")
    @Operation(summary = "Cancel order", description = "Cancel an order if it's in cancellable status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order cancelled successfully"),
        @ApiResponse(responseCode = "400", description = "Order cannot be cancelled"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your order"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> cancelOrder(
            @Parameter(description = "Order ID") @PathVariable Long id,
            @Valid @RequestBody CancelOrderRequest request,
            Principal principal) {
        try {
            OrderResponse order = orderService.cancelOrder(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Order cancelled successfully", order));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to cancel order: " + e.getMessage()));
        }
    }
    
    @PostMapping("/{id}/return")
    @Operation(summary = "Request order return", description = "Request a return for delivered order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Return request created successfully"),
        @ApiResponse(responseCode = "400", description = "Order cannot be returned or invalid request"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your order"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> requestReturn(
            @Parameter(description = "Order ID") @PathVariable Long id,
            @Valid @RequestBody CreateReturnRequest request,
            Principal principal) {
        try {
            ReturnOrderResponse returnOrder = orderService.createReturnRequest(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Return request created successfully", returnOrder));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to create return request: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}/tracking")
    @Operation(summary = "Get order tracking", description = "Get order tracking information and delivery status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tracking information retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your order"),
        @ApiResponse(responseCode = "404", description = "Order not found or no tracking available")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getOrderTracking(
            @Parameter(description = "Order ID") @PathVariable Long id,
            Principal principal) {
        try {
            OrderTrackingResponse tracking = orderService.getOrderTracking(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Tracking information retrieved successfully", tracking));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve tracking information: " + e.getMessage()));
        }
    }
    
    @GetMapping("/statistics")
    @Operation(summary = "Get order statistics (Admin)", description = "Get order statistics for dashboard")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOrderStatistics(
            @Parameter(description = "Start date (YYYY-MM-DD)") @RequestParam(required = false) String startDate,
            @Parameter(description = "End date (YYYY-MM-DD)") @RequestParam(required = false) String endDate) {
        try {
            OrderStatisticsResponse statistics = orderService.getOrderStatistics(startDate, endDate);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Statistics retrieved successfully", statistics));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve statistics: " + e.getMessage()));
        }
    }
}

