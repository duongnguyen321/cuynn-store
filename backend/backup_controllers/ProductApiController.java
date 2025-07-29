package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.entity.Product;
import com.zmen.backend.entity.ProductReview;
import com.zmen.backend.entity.ProductVariant;
import com.zmen.backend.service.ProductService;
import com.zmen.backend.service.ProductReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product catalog and management APIs")
@CrossOrigin(origins = "*")
public class ProductApiController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductReviewService productReviewService;
    
    @GetMapping
    @Operation(summary = "Get all products", description = "Get paginated list of products with filtering and sorting")
    public ResponseEntity<?> getAllProducts(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Search keyword") @RequestParam(required = false) String search,
            @Parameter(description = "Category ID") @RequestParam(required = false) Long categoryId,
            @Parameter(description = "Brand ID") @RequestParam(required = false) Long brandId,
            @Parameter(description = "Minimum price") @RequestParam(required = false) Double minPrice,
            @Parameter(description = "Maximum price") @RequestParam(required = false) Double maxPrice,
            @Parameter(description = "Product status") @RequestParam(required = false) String status,
            @Parameter(description = "Featured products only") @RequestParam(defaultValue = "false") boolean featured,
            @Parameter(description = "New products only") @RequestParam(defaultValue = "false") boolean isNew,
            @Parameter(description = "Best seller products only") @RequestParam(defaultValue = "false") boolean bestSeller
    ) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            ProductSearchRequest searchRequest = new ProductSearchRequest();
            searchRequest.setSearch(search);
            searchRequest.setCategoryId(categoryId);
            searchRequest.setBrandId(brandId);
            searchRequest.setMinPrice(minPrice);
            searchRequest.setMaxPrice(maxPrice);
            searchRequest.setStatus(status);
            searchRequest.setFeatured(featured);
            searchRequest.setIsNew(isNew);
            searchRequest.setBestSeller(bestSeller);
            
            Page<ProductResponse> products = productService.searchProducts(searchRequest, pageable);
            return ResponseEntity.ok(ApiResponse.success("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve products: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Get detailed product information including variants and reviews")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            ProductDetailResponse product = productService.getProductDetail(id);
            return ResponseEntity.ok(ApiResponse.success("Product retrieved successfully", product));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Product not found: " + e.getMessage()));
        }
    }
    
    @GetMapping("/slug/{slug}")
    @Operation(summary = "Get product by slug", description = "Get product information by URL slug")
    public ResponseEntity<?> getProductBySlug(@PathVariable String slug) {
        try {
            ProductDetailResponse product = productService.getProductDetailBySlug(slug);
            return ResponseEntity.ok(ApiResponse.success("Product retrieved successfully", product));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Product not found: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}/variants")
    @Operation(summary = "Get product variants", description = "Get all variants of a specific product")
    public ResponseEntity<?> getProductVariants(@PathVariable Long id) {
        try {
            List<ProductVariantResponse> variants = productService.getProductVariants(id);
            return ResponseEntity.ok(ApiResponse.success("Product variants retrieved successfully", variants));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve variants: " + e.getMessage()));
        }
    }
    
    @GetMapping("/variants/{variantId}")
    @Operation(summary = "Get product variant by ID", description = "Get specific product variant information")
    public ResponseEntity<?> getProductVariant(@PathVariable Long variantId) {
        try {
            ProductVariantResponse variant = productService.getProductVariant(variantId);
            return ResponseEntity.ok(ApiResponse.success("Product variant retrieved successfully", variant));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Variant not found: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}/reviews")
    @Operation(summary = "Get product reviews", description = "Get paginated product reviews and ratings")
    public ResponseEntity<?> getProductReviews(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) Integer rating
    ) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<ProductReviewResponse> reviews = productReviewService.getProductReviews(id, rating, pageable);
            return ResponseEntity.ok(ApiResponse.success("Reviews retrieved successfully", reviews));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve reviews: " + e.getMessage()));
        }
    }
    
    @PostMapping("/{id}/reviews")
    @Operation(summary = "Add product review", description = "Add a review for a product (requires authentication)")
    public ResponseEntity<?> addProductReview(@PathVariable Long id, @Valid @RequestBody AddReviewRequest request) {
        try {
            ProductReviewResponse review = productReviewService.addProductReview(id, request);
            return ResponseEntity.ok(ApiResponse.success("Review added successfully", review));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to add review: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}/rating-summary")
    @Operation(summary = "Get product rating summary", description = "Get product rating statistics")
    public ResponseEntity<?> getProductRatingSummary(@PathVariable Long id) {
        try {
            ProductRatingSummaryResponse summary = productReviewService.getProductRatingSummary(id);
            return ResponseEntity.ok(ApiResponse.success("Rating summary retrieved successfully", summary));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve rating summary: " + e.getMessage()));
        }
    }
    
    @GetMapping("/categories/{categoryId}")
    @Operation(summary = "Get products by category", description = "Get products in a specific category")
    public ResponseEntity<?> getProductsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<ProductResponse> products = productService.getProductsByCategory(categoryId, pageable);
            return ResponseEntity.ok(ApiResponse.success("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve products: " + e.getMessage()));
        }
    }
    
    @GetMapping("/brands/{brandId}")
    @Operation(summary = "Get products by brand", description = "Get products from a specific brand")
    public ResponseEntity<?> getProductsByBrand(
            @PathVariable Long brandId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            Page<ProductResponse> products = productService.getProductsByBrand(brandId, pageable);
            return ResponseEntity.ok(ApiResponse.success("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve products: " + e.getMessage()));
        }
    }
    
    @GetMapping("/featured")
    @Operation(summary = "Get featured products", description = "Get list of featured products")
    public ResponseEntity<?> getFeaturedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ProductResponse> products = productService.getFeaturedProducts(pageable);
            return ResponseEntity.ok(ApiResponse.success("Featured products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve featured products: " + e.getMessage()));
        }
    }
    
    @GetMapping("/new-arrivals")
    @Operation(summary = "Get new arrival products", description = "Get list of newly added products")
    public ResponseEntity<?> getNewArrivals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ProductResponse> products = productService.getNewArrivals(pageable);
            return ResponseEntity.ok(ApiResponse.success("New arrivals retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve new arrivals: " + e.getMessage()));
        }
    }
    
    @GetMapping("/best-sellers")
    @Operation(summary = "Get best seller products", description = "Get list of best selling products")
    public ResponseEntity<?> getBestSellers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ProductResponse> products = productService.getBestSellers(pageable);
            return ResponseEntity.ok(ApiResponse.success("Best sellers retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve best sellers: " + e.getMessage()));
        }
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search products", description = "Advanced product search with filters")
    public ResponseEntity<?> searchProducts(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "relevance") String sortBy,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            
            ProductSearchRequest searchRequest = new ProductSearchRequest();
            searchRequest.setSearch(query);
            searchRequest.setCategoryId(categoryId);
            searchRequest.setBrandId(brandId);
            searchRequest.setMinPrice(minPrice);
            searchRequest.setMaxPrice(maxPrice);
            
            Page<ProductResponse> products = productService.searchProducts(searchRequest, pageable);
            return ResponseEntity.ok(ApiResponse.success("Search results retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Search failed: " + e.getMessage()));
        }
    }
    
    @PostMapping("/{id}/view")
    @Operation(summary = "Track product view", description = "Record product view for analytics")
    public ResponseEntity<?> trackProductView(@PathVariable Long id) {
        try {
            productService.trackProductView(id);
            return ResponseEntity.ok(ApiResponse.success("Product view tracked"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to track view: " + e.getMessage()));
        }
    }
}