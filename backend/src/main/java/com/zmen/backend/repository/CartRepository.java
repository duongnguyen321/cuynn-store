package com.zmen.backend.repository;

import com.zmen.backend.entity.Cart;
import com.zmen.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    
    Optional<Cart> findByUserAndStatus(User user, String status);
    
    Optional<Cart> findBySessionIdAndStatus(String sessionId, String status);
    
    Optional<Cart> findByUser(User user);
    
    Optional<Cart> findBySessionId(String sessionId);
    
    void deleteByUser(User user);
    
    void deleteBySessionId(String sessionId);
}

