package com.zmen.backend.repository;

import com.zmen.backend.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    
    Optional<Wishlist> findByUserIdAndProductId(Long userId, Long productId);
    
    List<Wishlist> findByUserId(Long userId);
    
    @Modifying
    @Query("DELETE FROM Wishlist w WHERE w.userId = :userId")
    void deleteByUserId(@Param("userId") Long userId);
    
    boolean existsByUserIdAndProductId(Long userId, Long productId);
    
    Long countByUserId(Long userId);
}

