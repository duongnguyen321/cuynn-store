package com.zmen.backend.repository;

import com.zmen.backend.entity.ShoppingCart;
import com.zmen.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    
    Optional<ShoppingCart> findByUser(User user);
    
    Optional<ShoppingCart> findBySessionId(String sessionId);
    
    @Query("SELECT sc FROM ShoppingCart sc WHERE sc.user.id = :userId")
    Optional<ShoppingCart> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT sc FROM ShoppingCart sc WHERE sc.updatedAt < :cutoffDate AND sc.user IS NULL")
    List<ShoppingCart> findAbandonedGuestCarts(@Param("cutoffDate") LocalDateTime cutoffDate);
    
    void deleteBySessionId(String sessionId);
}