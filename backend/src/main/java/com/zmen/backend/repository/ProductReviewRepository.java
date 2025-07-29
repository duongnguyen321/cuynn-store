package com.zmen.backend.repository;

import com.zmen.backend.entity.Product;
import com.zmen.backend.entity.ProductReview;
import com.zmen.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    
    List<ProductReview> findByProductAndStatusOrderByCreatedAtDesc(Product product, ProductReview.ReviewStatus status);
    
    List<ProductReview> findByUserOrderByCreatedAtDesc(User user);
    
    List<ProductReview> findByStatusOrderByCreatedAtDesc(ProductReview.ReviewStatus status);
    
    Optional<ProductReview> findByProductAndUser(Product product, User user);
    
    @Query("SELECT pr FROM ProductReview pr WHERE pr.product.id = :productId AND pr.status = :status ORDER BY pr.createdAt DESC")
    List<ProductReview> findApprovedReviewsByProductId(@Param("productId") Long productId, @Param("status") ProductReview.ReviewStatus status);
    
    @Query("SELECT pr FROM ProductReview pr WHERE pr.product.id = :productId AND pr.rating = :rating AND pr.status = :status ORDER BY pr.createdAt DESC")
    List<ProductReview> findReviewsByProductIdAndRating(@Param("productId") Long productId, @Param("rating") Integer rating, @Param("status") ProductReview.ReviewStatus status);
    
    @Query("SELECT AVG(pr.rating) FROM ProductReview pr WHERE pr.product.id = :productId AND pr.status = :status")
    Double getAverageRatingByProductId(@Param("productId") Long productId, @Param("status") ProductReview.ReviewStatus status);
    
    @Query("SELECT COUNT(pr) FROM ProductReview pr WHERE pr.product.id = :productId AND pr.status = :status")
    Long getReviewCountByProductId(@Param("productId") Long productId, @Param("status") ProductReview.ReviewStatus status);
    
    @Query("SELECT COUNT(pr) FROM ProductReview pr WHERE pr.product.id = :productId AND pr.rating = :rating AND pr.status = :status")
    Long getReviewCountByProductIdAndRating(@Param("productId") Long productId, @Param("rating") Integer rating, @Param("status") ProductReview.ReviewStatus status);
    
    boolean existsByProductAndUser(Product product, User user);
}