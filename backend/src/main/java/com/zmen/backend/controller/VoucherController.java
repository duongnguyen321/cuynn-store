package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.service.VoucherService;
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
@RequestMapping("/api/vouchers")
@Tag(name = "Voucher Management", description = "Voucher and promotion management APIs")
@CrossOrigin(origins = "*")
public class VoucherController {
    
    @Autowired
    private VoucherService voucherService;
    
    @GetMapping("/public")
    @Operation(summary = "Get public vouchers", description = "Get publicly available vouchers and promotions")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Public vouchers retrieved successfully")
    })
    public ResponseEntity<?> getPublicVouchers(
            @Parameter(description = "Filter by active status") @RequestParam(defaultValue = "true") boolean activeOnly,
            @Parameter(description = "Maximum number of vouchers") @RequestParam(defaultValue = "20") int limit) {
        try {
            List<VoucherResponse> vouchers = voucherService.getPublicVouchers(activeOnly, limit);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Public vouchers retrieved successfully", vouchers));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve public vouchers: " + e.getMessage()));
        }
    }
    
    @PostMapping("/validate")
    @Operation(summary = "Validate voucher", description = "Validate a voucher code and check if it can be applied")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voucher validation completed"),
        @ApiResponse(responseCode = "404", description = "Voucher not found"),
        @ApiResponse(responseCode = "400", description = "Voucher not valid or expired")
    })
    public ResponseEntity<?> validateVoucher(
            @Valid @RequestBody ValidateVoucherRequest request) {
        try {
            VoucherValidationResponse validation = voucherService.validateVoucher(request);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Voucher validation completed", validation));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Voucher validation failed: " + e.getMessage()));
        }
    }
    
    @GetMapping("/user")
    @Operation(summary = "Get user vouchers", description = "Get vouchers available to the current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User vouchers retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getUserVouchers(
            @Parameter(description = "Filter by usage status") @RequestParam(required = false) Boolean used,
            @Parameter(description = "Filter by active status") @RequestParam(defaultValue = "true") boolean activeOnly,
            Principal principal) {
        try {
            List<VoucherResponse> vouchers = voucherService.getUserVouchers(principal.getName(), used, activeOnly);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("User vouchers retrieved successfully", vouchers));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve user vouchers: " + e.getMessage()));
        }
    }
    
    @PostMapping("/claim/{voucherId}")
    @Operation(summary = "Claim voucher", description = "Claim a voucher for the current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voucher claimed successfully"),
        @ApiResponse(responseCode = "400", description = "Voucher already claimed or not available"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Voucher not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> claimVoucher(
            @Parameter(description = "Voucher ID") @PathVariable Long voucherId,
            Principal principal) {
        try {
            VoucherResponse voucher = voucherService.claimVoucher(voucherId, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Voucher claimed successfully", voucher));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to claim voucher: " + e.getMessage()));
        }
    }
    
    @GetMapping("/admin")
    @Operation(summary = "Get all vouchers (Admin)", description = "Get all vouchers for admin management")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vouchers retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getAllVouchers(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Search by code or name") @RequestParam(required = false) String search,
            @Parameter(description = "Filter by active status") @RequestParam(required = false) Boolean active,
            @Parameter(description = "Filter by voucher type") @RequestParam(required = false) String type) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            VoucherSearchRequest searchRequest = new VoucherSearchRequest();
            searchRequest.setSearch(search);
            searchRequest.setActive(active);
            searchRequest.setType(type);
            
            Page<VoucherResponse> vouchers = voucherService.getAllVouchers(searchRequest, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Vouchers retrieved successfully", vouchers));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve vouchers: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get voucher by ID", description = "Get detailed voucher information by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voucher retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Voucher not found")
    })
    public ResponseEntity<?> getVoucherById(@Parameter(description = "Voucher ID") @PathVariable Long id) {
        try {
            VoucherDetailResponse voucher = voucherService.getVoucherById(id);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Voucher retrieved successfully", voucher));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve voucher: " + e.getMessage()));
        }
    }
    
    @PostMapping
    @Operation(summary = "Create voucher (Admin)", description = "Create a new voucher")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Voucher created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid voucher data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createVoucher(
            @Valid @RequestBody CreateVoucherRequest request,
            Principal principal) {
        try {
            VoucherResponse voucher = voucherService.createVoucher(request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Voucher created successfully", voucher));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to create voucher: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update voucher (Admin)", description = "Update an existing voucher")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voucher updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid voucher data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Voucher not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateVoucher(
            @Parameter(description = "Voucher ID") @PathVariable Long id,
            @Valid @RequestBody UpdateVoucherRequest request,
            Principal principal) {
        try {
            VoucherResponse voucher = voucherService.updateVoucher(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Voucher updated successfully", voucher));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update voucher: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete voucher (Admin)", description = "Soft delete a voucher")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voucher deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Cannot delete voucher in use"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Voucher not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteVoucher(
            @Parameter(description = "Voucher ID") @PathVariable Long id,
            Principal principal) {
        try {
            voucherService.deleteVoucher(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Voucher deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to delete voucher: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update voucher status (Admin)", description = "Activate or deactivate a voucher")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voucher status updated successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Voucher not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateVoucherStatus(
            @Parameter(description = "Voucher ID") @PathVariable Long id,
            @Valid @RequestBody UpdateVoucherStatusRequest request,
            Principal principal) {
        try {
            VoucherResponse voucher = voucherService.updateVoucherStatus(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Voucher status updated successfully", voucher));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update voucher status: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}/usage")
    @Operation(summary = "Get voucher usage (Admin)", description = "Get voucher usage statistics and history")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voucher usage retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Voucher not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getVoucherUsage(
            @Parameter(description = "Voucher ID") @PathVariable Long id,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("usedAt").descending());
            VoucherUsageResponse usage = voucherService.getVoucherUsage(id, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Voucher usage retrieved successfully", usage));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve voucher usage: " + e.getMessage()));
        }
    }
    
    @PostMapping("/bulk-create")
    @Operation(summary = "Bulk create vouchers (Admin)", description = "Create multiple vouchers at once")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vouchers created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid voucher data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> bulkCreateVouchers(
            @Valid @RequestBody BulkCreateVouchersRequest request,
            Principal principal) {
        try {
            List<VoucherResponse> vouchers = voucherService.bulkCreateVouchers(request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Vouchers created successfully", vouchers));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to create vouchers: " + e.getMessage()));
        }
    }
    
    @GetMapping("/statistics")
    @Operation(summary = "Get voucher statistics (Admin)", description = "Get voucher usage and performance statistics")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getVoucherStatistics(
            @Parameter(description = "Start date (YYYY-MM-DD)") @RequestParam(required = false) String startDate,
            @Parameter(description = "End date (YYYY-MM-DD)") @RequestParam(required = false) String endDate) {
        try {
            VoucherStatisticsResponse statistics = voucherService.getVoucherStatistics(startDate, endDate);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Statistics retrieved successfully", statistics));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve statistics: " + e.getMessage()));
        }
    }
}