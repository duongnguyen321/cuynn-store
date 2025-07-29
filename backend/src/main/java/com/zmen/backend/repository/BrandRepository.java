package com.zmen.backend.repository;

import com.zmen.backend.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
    Optional<Brand> findBySlug(String slug);
    
    Optional<Brand> findByBrandName(String brandName);
    
    List<Brand> findByStatusOrderByDisplayOrderAsc(Brand.BrandStatus status);
    
    List<Brand> findByCountryOrderByBrandNameAsc(String country);
    
    @Query("SELECT b FROM Brand b WHERE b.status = :status ORDER BY b.displayOrder ASC, b.brandName ASC")
    List<Brand> findActiveBrands(@Param("status") Brand.BrandStatus status);
    
    boolean existsBySlug(String slug);
    
    boolean existsByBrandName(String brandName);
}