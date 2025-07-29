package com.zmen.backend.repository;

import com.zmen.backend.entity.Product;
import com.zmen.backend.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    
    Optional<ProductVariant> findBySku(String sku);
    
    List<ProductVariant> findByProductOrderByPositionAsc(Product product);
    
    List<ProductVariant> findByProductAndStatusOrderByPositionAsc(Product product, ProductVariant.VariantStatus status);
    
    @Query("SELECT pv FROM ProductVariant pv WHERE pv.product.id = :productId AND pv.status = :status ORDER BY pv.position ASC")
    List<ProductVariant> findActiveVariantsByProductId(@Param("productId") Long productId, @Param("status") ProductVariant.VariantStatus status);
    
    @Query("SELECT pv FROM ProductVariant pv WHERE pv.stockQuantity > 0 AND pv.status = :status")
    List<ProductVariant> findInStockVariants(@Param("status") ProductVariant.VariantStatus status);
    
    @Query("SELECT SUM(pv.stockQuantity) FROM ProductVariant pv WHERE pv.product.id = :productId AND pv.status = :status")
    Integer getTotalStockByProductId(@Param("productId") Long productId, @Param("status") ProductVariant.VariantStatus status);
    
    boolean existsBySku(String sku);
}