package com.zmen.backend.repository;

import com.zmen.backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findByIdAndDeletedAtIsNull(Long id);
    
    Page<Product> findByDeletedAtIsNull(Pageable pageable);
    
    Page<Product> findByStatusAndDeletedAtIsNull(Product.ProductStatus status, Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.deletedAt IS NULL AND " +
           "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice)")
    Page<Product> searchProducts(@Param("name") String name,
                                @Param("minPrice") BigDecimal minPrice,
                                @Param("maxPrice") BigDecimal maxPrice,
                                Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.deletedAt IS NULL AND p.status = 'DANG_BAN' ORDER BY p.createdAt DESC")
    Page<Product> findNewArrivals(Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.deletedAt IS NULL AND p.status = 'DANG_BAN' ORDER BY p.id DESC")
    Page<Product> findBestSellers(Pageable pageable);
    
    @Query("SELECT COUNT(p) FROM Product p WHERE p.deletedAt IS NULL")
    Long countActiveProducts();
}

