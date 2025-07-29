package com.zmen.backend.controller;

import com.zmen.backend.dto.CartRequest;
import com.zmen.backend.entity.Cart;
import com.zmen.backend.entity.User;
import com.zmen.backend.repository.UserRepository;
import com.zmen.backend.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@Api(tags = "Shopping Cart Management")
@CrossOrigin(origins = "*")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    @ApiOperation("Get current cart")
    public ResponseEntity<Cart> getCart(HttpServletRequest request) {
        try {
            String sessionId = request.getSession().getId();
            User user = getCurrentUser(); // For now, we'll use session-based cart
            
            Cart cart = cartService.getOrCreateCart(user, sessionId);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/add")
    @ApiOperation("Add item to cart")
    public ResponseEntity<Map<String, Object>> addToCart(
            @RequestBody CartRequest request,
            HttpServletRequest httpRequest) {
        try {
            String sessionId = httpRequest.getSession().getId();
            User user = getCurrentUser();
            
            Cart cart = cartService.getOrCreateCart(user, sessionId);
            cart = cartService.addToCart(cart, request.getProductId(), 
                request.getQuantity(), request.getSize(), request.getColor());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Item added to cart successfully");
            response.put("cart", cart);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PutMapping("/update/{itemId}")
    @ApiOperation("Update cart item quantity")
    public ResponseEntity<Map<String, Object>> updateCartItem(
            @PathVariable Long itemId,
            @RequestParam Integer quantity,
            HttpServletRequest request) {
        try {
            String sessionId = request.getSession().getId();
            User user = getCurrentUser();
            
            Cart cart = cartService.getOrCreateCart(user, sessionId);
            cart = cartService.updateCartItem(cart, itemId, quantity);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cart updated successfully");
            response.put("cart", cart);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @DeleteMapping("/remove/{itemId}")
    @ApiOperation("Remove item from cart")
    public ResponseEntity<Map<String, Object>> removeFromCart(
            @PathVariable Long itemId,
            HttpServletRequest request) {
        try {
            String sessionId = request.getSession().getId();
            User user = getCurrentUser();
            
            Cart cart = cartService.getOrCreateCart(user, sessionId);
            cart = cartService.removeFromCart(cart, itemId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Item removed from cart");
            response.put("cart", cart);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @DeleteMapping("/clear")
    @ApiOperation("Clear cart")
    public ResponseEntity<Map<String, Object>> clearCart(HttpServletRequest request) {
        try {
            String sessionId = request.getSession().getId();
            User user = getCurrentUser();
            
            Cart cart = cartService.getOrCreateCart(user, sessionId);
            cartService.clearCart(cart);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cart cleared successfully");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    // Helper method to get current user (placeholder for authentication)
    private User getCurrentUser() {
        // For now, return null to use session-based cart
        // In a real app, this would get the authenticated user
        return null;
    }
}

