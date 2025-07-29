package com.zmen.backend.repository;

import com.zmen.backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findBySlug(String slug);
    
    List<Product> findByFeaturedTrueAndStatus(String status);
    
    List<Product> findByBestSellerTrueAndStatus(String status);
    
    List<Product> findByIsNewTrueAndStatus(String status);
    
    Page<Product> findByStatus(String status, Pageable pageable);
    
    Page<Product> findByCategory(String category, Pageable pageable);
    
    Page<Product> findByBrand(String brand, Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.status = 'ACTIVE'")
    List<String> findAllCategories();
    
    @Query("SELECT DISTINCT p.brand FROM Product p WHERE p.status = 'ACTIVE'")
    List<String> findAllBrands();
}

