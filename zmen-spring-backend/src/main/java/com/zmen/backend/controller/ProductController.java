package com.zmen.backend.controller;

import com.zmen.backend.dto.ProductResponse;
import com.zmen.backend.entity.Product;
import com.zmen.backend.repository.ProductRepository;
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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product management APIs")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieve paginated list of products with filtering and sorting")
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "12") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Search keyword") @RequestParam(required = false) String keyword,
            @Parameter(description = "Filter by category") @RequestParam(required = false) String category,
            @Parameter(description = "Filter by brand") @RequestParam(required = false) String brand,
            @Parameter(description = "Minimum price") @RequestParam(required = false) BigDecimal minPrice,
            @Parameter(description = "Maximum price") @RequestParam(required = false) BigDecimal maxPrice,
            @Parameter(description = "Minimum rating") @RequestParam(required = false) BigDecimal minRating) {

        Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productPage;

        if (keyword != null || category != null || brand != null || minPrice != null || maxPrice != null || minRating != null) {
            productPage = productRepository.findWithFilters(category, brand, minPrice, maxPrice, minRating, keyword, pageable);
        } else {
            productPage = productRepository.findByIsActiveTrue(pageable);
        }

        List<ProductResponse> products = productPage.getContent().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());
        response.put("hasNext", productPage.hasNext());
        response.put("hasPrevious", productPage.hasPrevious());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieve a single product by its ID and increment view count")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            if (product.isActive()) {
                // Increment view count
                productRepository.incrementViewCount(id);
                product.setViewCount(product.getViewCount() + 1);
                
                return ResponseEntity.ok(new ProductResponse(product));
            }
        }
        
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "Get product by slug", description = "Retrieve a single product by its slug and increment view count")
    public ResponseEntity<ProductResponse> getProductBySlug(@PathVariable String slug) {
        Optional<Product> productOpt = productRepository.findBySlug(slug);
        
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            if (product.isActive()) {
                // Increment view count
                productRepository.incrementViewCount(product.getId());
                product.setViewCount(product.getViewCount() + 1);
                
                return ResponseEntity.ok(new ProductResponse(product));
            }
        }
        
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/featured")
    @Operation(summary = "Get featured products", description = "Retrieve featured products")
    public ResponseEntity<List<ProductResponse>> getFeaturedProducts(
            @Parameter(description = "Limit number of products") @RequestParam(defaultValue = "8") int limit) {
        
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Product> productPage = productRepository.findByIsFeaturedTrueAndIsActiveTrue(pageable);
        
        List<ProductResponse> products = productPage.getContent().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(products);
    }

    @GetMapping("/best-sellers")
    @Operation(summary = "Get best selling products", description = "Retrieve best selling products")
    public ResponseEntity<List<ProductResponse>> getBestSellingProducts(
            @Parameter(description = "Limit number of products") @RequestParam(defaultValue = "8") int limit) {
        
        Pageable pageable = PageRequest.of(0, limit);
        Page<Product> productPage = productRepository.findBestSellingProducts(pageable);
        
        List<ProductResponse> products = productPage.getContent().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(products);
    }

    @GetMapping("/new-arrivals")
    @Operation(summary = "Get new arrival products", description = "Retrieve new arrival products")
    public ResponseEntity<List<ProductResponse>> getNewArrivals(
            @Parameter(description = "Limit number of products") @RequestParam(defaultValue = "8") int limit) {
        
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Product> productPage = productRepository.findByIsNewTrueAndIsActiveTrue(pageable);
        
        List<ProductResponse> products = productPage.getContent().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(products);
    }

    @GetMapping("/categories")
    @Operation(summary = "Get all categories", description = "Retrieve all distinct product categories")
    public ResponseEntity<List<String>> getCategories() {
        List<String> categories = productRepository.findDistinctCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/brands")
    @Operation(summary = "Get all brands", description = "Retrieve all distinct product brands")
    public ResponseEntity<List<String>> getBrands() {
        List<String> brands = productRepository.findDistinctBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/search")
    @Operation(summary = "Search products", description = "Search products by keyword")
    public ResponseEntity<Map<String, Object>> searchProducts(
            @Parameter(description = "Search keyword") @RequestParam String keyword,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "12") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Product> productPage = productRepository.searchByKeyword(keyword, pageable);
        
        List<ProductResponse> products = productPage.getContent().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());
        response.put("keyword", keyword);

        return ResponseEntity.ok(response);
    }
}

