package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.service.PaymentService;
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
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment Management", description = "Payment processing and transaction management APIs")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    @GetMapping("/methods")
    @Operation(summary = "Get payment methods", description = "Get all available payment methods")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment methods retrieved successfully")
    })
    public ResponseEntity<?> getPaymentMethods() {
        try {
            List<PaymentMethodResponse> paymentMethods = paymentService.getAvailablePaymentMethods();
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Payment methods retrieved successfully", paymentMethods));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve payment methods: " + e.getMessage()));
        }
    }
    
    @PostMapping("/process")
    @Operation(summary = "Process payment", description = "Process payment for an order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment processed successfully"),
        @ApiResponse(responseCode = "400", description = "Payment processing failed"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> processPayment(
            @Valid @RequestBody ProcessPaymentRequest request,
            Principal principal) {
        try {
            PaymentResponse payment = paymentService.processPayment(request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Payment processed successfully", payment));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Payment processing failed: " + e.getMessage()));
        }
    }
    
    @GetMapping("/transactions")
    @Operation(summary = "Get user transactions", description = "Get payment transactions for the current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserTransactions(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "transactionTime") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Filter by status") @RequestParam(required = false) String status,
            Principal principal) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<PaymentTransactionResponse> transactions = paymentService.getUserTransactions(principal.getName(), status, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Transactions retrieved successfully", transactions));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve transactions: " + e.getMessage()));
        }
    }
    
    @GetMapping("/transactions/{transactionId}")
    @Operation(summary = "Get transaction details", description = "Get detailed information about a payment transaction")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transaction retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your transaction"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getTransactionDetails(
            @Parameter(description = "Transaction ID") @PathVariable String transactionId,
            Principal principal) {
        try {
            PaymentTransactionDetailResponse transaction = paymentService.getTransactionDetails(transactionId, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Transaction retrieved successfully", transaction));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve transaction: " + e.getMessage()));
        }
    }
    
    @PostMapping("/refund")
    @Operation(summary = "Request refund", description = "Request a refund for a payment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Refund request created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid refund request"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> requestRefund(
            @Valid @RequestBody RefundRequest request,
            Principal principal) {
        try {
            RefundResponse refund = paymentService.requestRefund(request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Refund request created successfully", refund));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to request refund: " + e.getMessage()));
        }
    }
    
    @GetMapping("/refunds")
    @Operation(summary = "Get user refunds", description = "Get refund requests for the current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Refunds retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserRefunds(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "requestTime") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Filter by status") @RequestParam(required = false) String status,
            Principal principal) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<RefundResponse> refunds = paymentService.getUserRefunds(principal.getName(), status, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Refunds retrieved successfully", refunds));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve refunds: " + e.getMessage()));
        }
    }
    
    @PostMapping("/webhook/stripe")
    @Operation(summary = "Stripe webhook", description = "Handle Stripe payment webhook events")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Webhook processed successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid webhook data")
    })
    public ResponseEntity<?> handleStripeWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String signature) {
        try {
            paymentService.handleStripeWebhook(payload, signature);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Webhook processed successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to process webhook: " + e.getMessage()));
        }
    }
    
    @PostMapping("/webhook/paypal")
    @Operation(summary = "PayPal webhook", description = "Handle PayPal payment webhook events")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Webhook processed successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid webhook data")
    })
    public ResponseEntity<?> handlePayPalWebhook(
            @RequestBody String payload,
            @RequestHeader Map<String, String> headers) {
        try {
            paymentService.handlePayPalWebhook(payload, headers);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Webhook processed successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to process webhook: " + e.getMessage()));
        }
    }
    
    @GetMapping("/admin/transactions")
    @Operation(summary = "Get all transactions (Admin)", description = "Get all payment transactions for admin management")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllTransactions(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "50") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "transactionTime") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Filter by status") @RequestParam(required = false) String status,
            @Parameter(description = "Filter by payment method") @RequestParam(required = false) String paymentMethod,
            @Parameter(description = "Search by transaction code or user") @RequestParam(required = false) String search) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            PaymentSearchRequest searchRequest = new PaymentSearchRequest();
            searchRequest.setStatus(status);
            searchRequest.setPaymentMethod(paymentMethod);
            searchRequest.setSearch(search);
            
            Page<PaymentTransactionResponse> transactions = paymentService.getAllTransactions(searchRequest, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Transactions retrieved successfully", transactions));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve transactions: " + e.getMessage()));
        }
    }
    
    @GetMapping("/admin/refunds")
    @Operation(summary = "Get all refunds (Admin)", description = "Get all refund requests for admin management")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Refunds retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllRefunds(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "50") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "requestTime") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Filter by status") @RequestParam(required = false) String status) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<RefundResponse> refunds = paymentService.getAllRefunds(status, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Refunds retrieved successfully", refunds));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve refunds: " + e.getMessage()));
        }
    }
    
    @PutMapping("/admin/refunds/{refundId}/process")
    @Operation(summary = "Process refund (Admin)", description = "Process a refund request")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Refund processed successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Refund not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> processRefund(
            @Parameter(description = "Refund ID") @PathVariable Long refundId,
            @Valid @RequestBody ProcessRefundRequest request,
            Principal principal) {
        try {
            RefundResponse refund = paymentService.processRefund(refundId, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Refund processed successfully", refund));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to process refund: " + e.getMessage()));
        }
    }
    
    @GetMapping("/statistics")
    @Operation(summary = "Get payment statistics (Admin)", description = "Get payment and transaction statistics")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPaymentStatistics(
            @Parameter(description = "Start date (YYYY-MM-DD)") @RequestParam(required = false) String startDate,
            @Parameter(description = "End date (YYYY-MM-DD)") @RequestParam(required = false) String endDate) {
        try {
            PaymentStatisticsResponse statistics = paymentService.getPaymentStatistics(startDate, endDate);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Statistics retrieved successfully", statistics));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve statistics: " + e.getMessage()));
        }
    }
}