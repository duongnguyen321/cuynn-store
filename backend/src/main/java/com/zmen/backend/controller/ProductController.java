package com.zmen.backend.controller;

import com.zmen.backend.entity.Product;
import com.zmen.backend.repository.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Api(tags = "Product Management")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    @ApiOperation("Get all products with pagination")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> products = productRepository.findByStatus("ACTIVE", pageable);
        
        return ResponseEntity.ok(products);
    }

    @GetMapping("/featured")
    @ApiOperation("Get featured products")
    public ResponseEntity<List<Product>> getFeaturedProducts() {
        List<Product> products = productRepository.findByFeaturedTrueAndStatus("ACTIVE");
        return ResponseEntity.ok(products);
    }

    @GetMapping("/bestsellers")
    @ApiOperation("Get best seller products")
    public ResponseEntity<List<Product>> getBestSellerProducts() {
        List<Product> products = productRepository.findByBestSellerTrueAndStatus("ACTIVE");
        return ResponseEntity.ok(products);
    }

    @GetMapping("/new")
    @ApiOperation("Get new products")
    public ResponseEntity<List<Product>> getNewProducts() {
        List<Product> products = productRepository.findByIsNewTrueAndStatus("ACTIVE");
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get product by ID")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/slug/{slug}")
    @ApiOperation("Get product by slug")
    public ResponseEntity<Product> getProductBySlug(@PathVariable String slug) {
        Optional<Product> product = productRepository.findBySlug(slug);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @ApiOperation("Search products by keyword")
    public ResponseEntity<Page<Product>> searchProducts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.searchByKeyword(keyword, pageable);
        
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{category}")
    @ApiOperation("Get products by category")
    public ResponseEntity<Page<Product>> getProductsByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByCategory(category, pageable);
        
        return ResponseEntity.ok(products);
    }

    @GetMapping("/brand/{brand}")
    @ApiOperation("Get products by brand")
    public ResponseEntity<Page<Product>> getProductsByBrand(
            @PathVariable String brand,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByBrand(brand, pageable);
        
        return ResponseEntity.ok(products);
    }

    @GetMapping("/categories")
    @ApiOperation("Get all categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = productRepository.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/brands")
    @ApiOperation("Get all brands")
    public ResponseEntity<List<String>> getAllBrands() {
        List<String> brands = productRepository.findAllBrands();
        return ResponseEntity.ok(brands);
    }
}

