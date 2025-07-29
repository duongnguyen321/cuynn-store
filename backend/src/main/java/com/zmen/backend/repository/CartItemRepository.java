package com.zmen.backend.repository;

import com.zmen.backend.entity.Cart;
import com.zmen.backend.entity.CartItem;
import com.zmen.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    List<CartItem> findByCart(Cart cart);
    
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
    
    Optional<CartItem> findByCartAndProductAndSizeAndColor(Cart cart, Product product, String size, String color);
    
    void deleteByCart(Cart cart);
}

