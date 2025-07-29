package com.zmen.backend.repository;

import com.zmen.backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findBySlug(String slug);
    
    Page<Product> findByIsActiveTrue(Pageable pageable);
    
    Page<Product> findByIsFeaturedTrueAndIsActiveTrue(Pageable pageable);
    
    Page<Product> findByIsBestSellerTrueAndIsActiveTrue(Pageable pageable);
    
    Page<Product> findByIsNewTrueAndIsActiveTrue(Pageable pageable);
    
    Page<Product> findByCategoryAndIsActiveTrue(String category, Pageable pageable);
    
    Page<Product> findByBrandAndIsActiveTrue(String brand, Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE " +
           "p.isActive = true AND " +
           "(:keyword IS NULL OR " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE " +
           "p.isActive = true AND " +
           "(:category IS NULL OR p.category = :category) AND " +
           "(:brand IS NULL OR p.brand = :brand) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
           "(:minRating IS NULL OR p.rating >= :minRating) AND " +
           "(:keyword IS NULL OR " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Product> findWithFilters(@Param("category") String category,
                                 @Param("brand") String brand,
                                 @Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice,
                                 @Param("minRating") BigDecimal minRating,
                                 @Param("keyword") String keyword,
                                 Pageable pageable);
    
    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.isActive = true ORDER BY p.category")
    List<String> findDistinctCategories();
    
    @Query("SELECT DISTINCT p.brand FROM Product p WHERE p.isActive = true ORDER BY p.brand")
    List<String> findDistinctBrands();
    
    @Modifying
    @Query("UPDATE Product p SET p.viewCount = p.viewCount + 1 WHERE p.id = :id")
    void incrementViewCount(@Param("id") Long id);
    
    @Query("SELECT p FROM Product p WHERE p.stockQuantity <= :threshold AND p.isActive = true")
    List<Product> findLowStockProducts(@Param("threshold") Integer threshold);
    
    @Query("SELECT p FROM Product p WHERE p.stockQuantity = 0 AND p.isActive = true")
    List<Product> findOutOfStockProducts();
    
    // Statistics queries
    @Query("SELECT COUNT(p) FROM Product p WHERE p.isActive = true")
    long countActiveProducts();
    
    @Query("SELECT COUNT(p) FROM Product p WHERE p.stockQuantity = 0 AND p.isActive = true")
    long countOutOfStockProducts();
    
    @Query("SELECT COUNT(p) FROM Product p WHERE p.stockQuantity <= :threshold AND p.isActive = true")
    long countLowStockProducts(@Param("threshold") Integer threshold);
    
    @Query("SELECT SUM(p.stockQuantity) FROM Product p WHERE p.isActive = true")
    Long getTotalStockQuantity();
    
    @Query("SELECT p FROM Product p WHERE p.isActive = true ORDER BY p.soldQuantity DESC")
    Page<Product> findBestSellingProducts(Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.isActive = true ORDER BY p.viewCount DESC")
    Page<Product> findMostViewedProducts(Pageable pageable);
}

