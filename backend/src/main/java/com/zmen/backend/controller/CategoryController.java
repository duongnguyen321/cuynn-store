package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.service.CategoryService;
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
@RequestMapping("/api/categories")
@Tag(name = "Category Management", description = "Product category management APIs")
@CrossOrigin(origins = "*")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    @Operation(summary = "Get all categories", description = "Get all product categories with hierarchical structure")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categories retrieved successfully")
    })
    public ResponseEntity<?> getAllCategories(
            @Parameter(description = "Include inactive categories") @RequestParam(defaultValue = "false") boolean includeInactive,
            @Parameter(description = "Include product count") @RequestParam(defaultValue = "true") boolean includeProductCount) {
        try {
            List<CategoryResponse> categories = categoryService.getAllCategories(includeInactive, includeProductCount);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Categories retrieved successfully", categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve categories: " + e.getMessage()));
        }
    }
    
    @GetMapping("/tree")
    @Operation(summary = "Get category tree", description = "Get categories in hierarchical tree structure")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category tree retrieved successfully")
    })
    public ResponseEntity<?> getCategoryTree(
            @Parameter(description = "Include inactive categories") @RequestParam(defaultValue = "false") boolean includeInactive) {
        try {
            List<CategoryTreeResponse> categoryTree = categoryService.getCategoryTree(includeInactive);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Category tree retrieved successfully", categoryTree));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve category tree: " + e.getMessage()));
        }
    }
    
    @GetMapping("/paginated")
    @Operation(summary = "Get categories with pagination", description = "Get paginated list of categories for admin management")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categories retrieved successfully")
    })
    public ResponseEntity<?> getCategoriesPaginated(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "displayOrder") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "asc") String sortDir,
            @Parameter(description = "Search by name") @RequestParam(required = false) String search,
            @Parameter(description = "Filter by active status") @RequestParam(required = false) Boolean active) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<CategoryResponse> categories = categoryService.getCategoriesPaginated(search, active, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Categories retrieved successfully", categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve categories: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID", description = "Get detailed category information by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<?> getCategoryById(@Parameter(description = "Category ID") @PathVariable Long id) {
        try {
            CategoryDetailResponse category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Category retrieved successfully", category));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve category: " + e.getMessage()));
        }
    }
    
    @GetMapping("/slug/{slug}")
    @Operation(summary = "Get category by slug", description = "Get category information by URL slug")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<?> getCategoryBySlug(@Parameter(description = "Category slug") @PathVariable String slug) {
        try {
            CategoryDetailResponse category = categoryService.getCategoryBySlug(slug);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Category retrieved successfully", category));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve category: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}/children")
    @Operation(summary = "Get child categories", description = "Get all child categories of a parent category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Child categories retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Parent category not found")
    })
    public ResponseEntity<?> getChildCategories(
            @Parameter(description = "Parent category ID") @PathVariable Long id,
            @Parameter(description = "Include inactive categories") @RequestParam(defaultValue = "false") boolean includeInactive) {
        try {
            List<CategoryResponse> childCategories = categoryService.getChildCategories(id, includeInactive);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Child categories retrieved successfully", childCategories));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve child categories: " + e.getMessage()));
        }
    }
    
    @PostMapping
    @Operation(summary = "Create category (Admin)", description = "Create a new product category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Category created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid category data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CreateCategoryRequest request,
            Principal principal) {
        try {
            CategoryResponse category = categoryService.createCategory(request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Category created successfully", category));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to create category: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update category (Admin)", description = "Update an existing category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid category data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateCategory(
            @Parameter(description = "Category ID") @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryRequest request,
            Principal principal) {
        try {
            CategoryResponse category = categoryService.updateCategory(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Category updated successfully", category));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update category: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category (Admin)", description = "Soft delete a category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Cannot delete category with products or subcategories"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteCategory(
            @Parameter(description = "Category ID") @PathVariable Long id,
            Principal principal) {
        try {
            categoryService.deleteCategory(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Category deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to delete category: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update category status (Admin)", description = "Activate or deactivate a category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category status updated successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateCategoryStatus(
            @Parameter(description = "Category ID") @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryStatusRequest request,
            Principal principal) {
        try {
            CategoryResponse category = categoryService.updateCategoryStatus(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Category status updated successfully", category));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update category status: " + e.getMessage()));
        }
    }
    
    @PutMapping("/reorder")
    @Operation(summary = "Reorder categories (Admin)", description = "Update the display order of categories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categories reordered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid reorder data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> reorderCategories(
            @Valid @RequestBody ReorderCategoriesRequest request,
            Principal principal) {
        try {
            categoryService.reorderCategories(request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Categories reordered successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to reorder categories: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}/products")
    @Operation(summary = "Get products by category", description = "Get all products in a specific category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<?> getProductsByCategory(
            @Parameter(description = "Category ID") @PathVariable Long id,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Include subcategory products") @RequestParam(defaultValue = "true") boolean includeSubcategories) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<ProductResponse> products = categoryService.getProductsByCategory(id, includeSubcategories, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve products: " + e.getMessage()));
        }
    }
    
    @GetMapping("/featured")
    @Operation(summary = "Get featured categories", description = "Get list of featured categories for homepage")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Featured categories retrieved successfully")
    })
    public ResponseEntity<?> getFeaturedCategories(
            @Parameter(description = "Maximum number of categories") @RequestParam(defaultValue = "8") int limit) {
        try {
            List<CategoryResponse> featuredCategories = categoryService.getFeaturedCategories(limit);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Featured categories retrieved successfully", featuredCategories));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve featured categories: " + e.getMessage()));
        }
    }
}