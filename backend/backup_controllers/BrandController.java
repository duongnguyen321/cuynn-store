package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.service.BrandService;
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
@RequestMapping("/api/brands")
@Tag(name = "Brand Management", description = "Product brand management APIs")
@CrossOrigin(origins = "*")
public class BrandController {
    
    @Autowired
    private BrandService brandService;
    
    @GetMapping
    @Operation(summary = "Get all brands", description = "Get all product brands")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brands retrieved successfully")
    })
    public ResponseEntity<?> getAllBrands(
            @Parameter(description = "Include inactive brands") @RequestParam(defaultValue = "false") boolean includeInactive,
            @Parameter(description = "Include product count") @RequestParam(defaultValue = "true") boolean includeProductCount) {
        try {
            List<BrandResponse> brands = brandService.getAllBrands(includeInactive, includeProductCount);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Brands retrieved successfully", brands));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve brands: " + e.getMessage()));
        }
    }
    
    @GetMapping("/paginated")
    @Operation(summary = "Get brands with pagination", description = "Get paginated list of brands for admin management")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brands retrieved successfully")
    })
    public ResponseEntity<?> getBrandsPaginated(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "name") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "asc") String sortDir,
            @Parameter(description = "Search by name") @RequestParam(required = false) String search,
            @Parameter(description = "Filter by active status") @RequestParam(required = false) Boolean active) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<BrandResponse> brands = brandService.getBrandsPaginated(search, active, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Brands retrieved successfully", brands));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve brands: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get brand by ID", description = "Get detailed brand information by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brand retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<?> getBrandById(@Parameter(description = "Brand ID") @PathVariable Long id) {
        try {
            BrandDetailResponse brand = brandService.getBrandById(id);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Brand retrieved successfully", brand));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve brand: " + e.getMessage()));
        }
    }
    
    @GetMapping("/slug/{slug}")
    @Operation(summary = "Get brand by slug", description = "Get brand information by URL slug")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brand retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<?> getBrandBySlug(@Parameter(description = "Brand slug") @PathVariable String slug) {
        try {
            BrandDetailResponse brand = brandService.getBrandBySlug(slug);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Brand retrieved successfully", brand));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve brand: " + e.getMessage()));
        }
    }
    
    @PostMapping
    @Operation(summary = "Create brand (Admin)", description = "Create a new product brand")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Brand created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid brand data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createBrand(
            @Valid @RequestBody CreateBrandRequest request,
            Principal principal) {
        try {
            BrandResponse brand = brandService.createBrand(request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Brand created successfully", brand));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to create brand: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update brand (Admin)", description = "Update an existing brand")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brand updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid brand data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateBrand(
            @Parameter(description = "Brand ID") @PathVariable Long id,
            @Valid @RequestBody UpdateBrandRequest request,
            Principal principal) {
        try {
            BrandResponse brand = brandService.updateBrand(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Brand updated successfully", brand));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update brand: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete brand (Admin)", description = "Soft delete a brand")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brand deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Cannot delete brand with products"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteBrand(
            @Parameter(description = "Brand ID") @PathVariable Long id,
            Principal principal) {
        try {
            brandService.deleteBrand(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Brand deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to delete brand: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update brand status (Admin)", description = "Activate or deactivate a brand")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brand status updated successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateBrandStatus(
            @Parameter(description = "Brand ID") @PathVariable Long id,
            @Valid @RequestBody UpdateBrandStatusRequest request,
            Principal principal) {
        try {
            BrandResponse brand = brandService.updateBrandStatus(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Brand status updated successfully", brand));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update brand status: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}/products")
    @Operation(summary = "Get products by brand", description = "Get all products from a specific brand")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<?> getProductsByBrand(
            @Parameter(description = "Brand ID") @PathVariable Long id,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<ProductResponse> products = brandService.getProductsByBrand(id, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve products: " + e.getMessage()));
        }
    }
    
    @GetMapping("/featured")
    @Operation(summary = "Get featured brands", description = "Get list of featured brands for homepage")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Featured brands retrieved successfully")
    })
    public ResponseEntity<?> getFeaturedBrands(
            @Parameter(description = "Maximum number of brands") @RequestParam(defaultValue = "12") int limit) {
        try {
            List<BrandResponse> featuredBrands = brandService.getFeaturedBrands(limit);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Featured brands retrieved successfully", featuredBrands));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve featured brands: " + e.getMessage()));
        }
    }
    
    @GetMapping("/popular")
    @Operation(summary = "Get popular brands", description = "Get brands sorted by product count and sales")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Popular brands retrieved successfully")
    })
    public ResponseEntity<?> getPopularBrands(
            @Parameter(description = "Maximum number of brands") @RequestParam(defaultValue = "20") int limit) {
        try {
            List<BrandResponse> popularBrands = brandService.getPopularBrands(limit);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Popular brands retrieved successfully", popularBrands));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve popular brands: " + e.getMessage()));
        }
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search brands", description = "Search brands by name or description")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search results retrieved successfully")
    })
    public ResponseEntity<?> searchBrands(
            @Parameter(description = "Search query") @RequestParam String query,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<BrandResponse> brands = brandService.searchBrands(query, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Search results retrieved successfully", brands));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to search brands: " + e.getMessage()));
        }
    }
}