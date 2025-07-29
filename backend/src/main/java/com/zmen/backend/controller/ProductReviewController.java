package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.service.ProductReviewService;
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
@RequestMapping("/api/reviews")
@Tag(name = "Product Reviews", description = "Product review and rating management APIs")
@CrossOrigin(origins = "*")
public class ProductReviewController {
    
    @Autowired
    private ProductReviewService reviewService;
    
    @GetMapping("/product/{productId}")
    @Operation(summary = "Get product reviews", description = "Get paginated reviews for a specific product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reviews retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<?> getProductReviews(
            @Parameter(description = "Product ID") @PathVariable Long productId,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Filter by rating") @RequestParam(required = false) Integer rating,
            @Parameter(description = "Include only verified purchases") @RequestParam(defaultValue = "false") boolean verifiedOnly) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<ProductReviewResponse> reviews = reviewService.getProductReviews(productId, rating, verifiedOnly, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Reviews retrieved successfully", reviews));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve reviews: " + e.getMessage()));
        }
    }
    
    @GetMapping("/product/{productId}/summary")
    @Operation(summary = "Get review summary", description = "Get rating summary and statistics for a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review summary retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<?> getReviewSummary(
            @Parameter(description = "Product ID") @PathVariable Long productId) {
        try {
            ProductReviewSummaryResponse summary = reviewService.getReviewSummary(productId);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Review summary retrieved successfully", summary));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve review summary: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get review by ID", description = "Get detailed review information by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    public ResponseEntity<?> getReviewById(@Parameter(description = "Review ID") @PathVariable Long id) {
        try {
            ProductReviewDetailResponse review = reviewService.getReviewById(id);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Review retrieved successfully", review));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve review: " + e.getMessage()));
        }
    }
    
    @PostMapping("/product/{productId}")
    @Operation(summary = "Create product review", description = "Create a new review for a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Review created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid review data or user hasn't purchased the product"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "409", description = "User has already reviewed this product")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createReview(
            @Parameter(description = "Product ID") @PathVariable Long productId,
            @Valid @RequestBody CreateProductReviewRequest request,
            Principal principal) {
        try {
            ProductReviewResponse review = reviewService.createReview(productId, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Review created successfully", review));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to create review: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update review", description = "Update an existing review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid review data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your review"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateReview(
            @Parameter(description = "Review ID") @PathVariable Long id,
            @Valid @RequestBody UpdateProductReviewRequest request,
            Principal principal) {
        try {
            ProductReviewResponse review = reviewService.updateReview(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Review updated successfully", review));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update review: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete review", description = "Delete a review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review deleted successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your review or not admin"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteReview(
            @Parameter(description = "Review ID") @PathVariable Long id,
            Principal principal) {
        try {
            reviewService.deleteReview(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Review deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to delete review: " + e.getMessage()));
        }
    }
    
    @PostMapping("/{id}/helpful")
    @Operation(summary = "Mark review as helpful", description = "Mark a review as helpful")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review marked as helpful successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "409", description = "User has already marked this review")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> markReviewHelpful(
            @Parameter(description = "Review ID") @PathVariable Long id,
            Principal principal) {
        try {
            reviewService.markReviewHelpful(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Review marked as helpful successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to mark review as helpful: " + e.getMessage()));
        }
    }
    
    @PostMapping("/{id}/report")
    @Operation(summary = "Report review", description = "Report a review for inappropriate content")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review reported successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "409", description = "User has already reported this review")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> reportReview(
            @Parameter(description = "Review ID") @PathVariable Long id,
            @Valid @RequestBody ReportReviewRequest request,
            Principal principal) {
        try {
            reviewService.reportReview(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Review reported successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to report review: " + e.getMessage()));
        }
    }
    
    @GetMapping("/user")
    @Operation(summary = "Get user reviews", description = "Get all reviews by the current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User reviews retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getUserReviews(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            Principal principal) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<ProductReviewResponse> reviews = reviewService.getUserReviews(principal.getName(), pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("User reviews retrieved successfully", reviews));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve user reviews: " + e.getMessage()));
        }
    }
    
    @GetMapping("/admin/pending")
    @Operation(summary = "Get pending reviews (Admin)", description = "Get reviews pending moderation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pending reviews retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getPendingReviews(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
            Page<ProductReviewResponse> reviews = reviewService.getPendingReviews(pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Pending reviews retrieved successfully", reviews));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve pending reviews: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/approve")
    @Operation(summary = "Approve review (Admin)", description = "Approve a pending review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review approved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> approveReview(
            @Parameter(description = "Review ID") @PathVariable Long id,
            Principal principal) {
        try {
            ProductReviewResponse review = reviewService.approveReview(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Review approved successfully", review));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to approve review: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/reject")
    @Operation(summary = "Reject review (Admin)", description = "Reject a pending review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review rejected successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> rejectReview(
            @Parameter(description = "Review ID") @PathVariable Long id,
            @Valid @RequestBody RejectReviewRequest request,
            Principal principal) {
        try {
            reviewService.rejectReview(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Review rejected successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to reject review: " + e.getMessage()));
        }
    }
    
    @GetMapping("/statistics")
    @Operation(summary = "Get review statistics", description = "Get overall review statistics")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully")
    })
    public ResponseEntity<?> getReviewStatistics() {
        try {
            ReviewStatisticsResponse statistics = reviewService.getReviewStatistics();
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Statistics retrieved successfully", statistics));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve statistics: " + e.getMessage()));
        }
    }
}