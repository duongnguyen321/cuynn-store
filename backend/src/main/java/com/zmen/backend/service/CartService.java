package com.zmen.backend.service;

import com.zmen.backend.entity.Cart;
import com.zmen.backend.entity.CartItem;
import com.zmen.backend.entity.Product;
import com.zmen.backend.entity.User;
import com.zmen.backend.repository.CartItemRepository;
import com.zmen.backend.repository.CartRepository;
import com.zmen.backend.repository.ProductRepository;
import com.zmen.backend.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserService userService;
    
    public Cart getOrCreateCart(User user, String sessionId) {
        Optional<Cart> cartOpt;
        
        if (user != null) {
            cartOpt = cartRepository.findByUserAndStatus(user, "ACTIVE");
        } else {
            cartOpt = cartRepository.findBySessionIdAndStatus(sessionId, "ACTIVE");
        }
        
        if (cartOpt.isPresent()) {
            return cartOpt.get();
        }
        
        // Create new cart
        Cart cart = new Cart();
        if (user != null) {
            cart.setUser(user);
        } else {
            cart.setSessionId(sessionId);
        }
        
        return cartRepository.save(cart);
    }
    
    public Cart addToCart(Cart cart, Long productId, Integer quantity, String size, String color) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (!productOpt.isPresent()) {
            throw new RuntimeException("Product not found");
        }
        
        Product product = productOpt.get();
        
        // Check if item already exists in cart
        Optional<CartItem> existingItemOpt = cartItemRepository
            .findByCartAndProductAndSizeAndColor(cart, product, size, color);
        
        CartItem cartItem;
        if (existingItemOpt.isPresent()) {
            // Update existing item
            cartItem = existingItemOpt.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // Create new item
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setSize(size);
            cartItem.setColor(color);
        }
        
        cartItem.setUnitPrice(product.getPrice());
        cartItemRepository.save(cartItem);
        
        updateCartTotals(cart);
        return cart;
    }
    
    public Cart updateCartItem(Cart cart, Long cartItemId, Integer quantity) {
        Optional<CartItem> itemOpt = cartItemRepository.findById(cartItemId);
        if (!itemOpt.isPresent() || !itemOpt.get().getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Cart item not found");
        }
        
        CartItem cartItem = itemOpt.get();
        if (quantity <= 0) {
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
        
        updateCartTotals(cart);
        return cart;
    }
    
    public Cart removeFromCart(Cart cart, Long cartItemId) {
        Optional<CartItem> itemOpt = cartItemRepository.findById(cartItemId);
        if (itemOpt.isPresent() && itemOpt.get().getCart().getId().equals(cart.getId())) {
            cartItemRepository.delete(itemOpt.get());
            updateCartTotals(cart);
        }
        return cart;
    }
    
    public void clearCart(Cart cart) {
        cartItemRepository.deleteByCart(cart);
        cart.setTotalItems(0);
        cart.setTotalAmount(BigDecimal.ZERO);
        cart.setDiscountAmount(BigDecimal.ZERO);
        cartRepository.save(cart);
    }
    
    private void updateCartTotals(Cart cart) {
        var cartItems = cartItemRepository.findByCart(cart);
        
        int totalItems = cartItems.stream()
            .mapToInt(CartItem::getQuantity)
            .sum();
        
        BigDecimal totalAmount = cartItems.stream()
            .map(CartItem::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        cart.setTotalItems(totalItems);
        cart.setTotalAmount(totalAmount);
        
        cartRepository.save(cart);
    }
    
    public CartResponse getUserCart(String email) {
        User user = userService.findByEmail(email);
        Cart cart = getOrCreateCart(user, null);
        
        CartResponse response = new CartResponse();
        response.setId(cart.getId());
        response.setTotalItems(cart.getTotalItems());
        response.setTotalAmount(cart.getTotalAmount());
        response.setDiscountAmount(cart.getDiscountAmount());
        
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        List<CartItemResponse> itemResponses = cartItems.stream()
            .map(this::convertToCartItemResponse)
            .collect(Collectors.toList());
        
        response.setItems(itemResponses);
        return response;
    }
    
    public CartResponse addToCart(String email, AddToCartRequest request) {
        User user = userService.findByEmail(email);
        Cart cart = getOrCreateCart(user, null);
        
        addToCart(cart, request.getProductId(), request.getQuantity(), 
                 request.getSize(), request.getColor());
        
        return getUserCart(email);
    }
    
    public CartItemResponse updateCartItem(String email, Long itemId, UpdateCartItemRequest request) {
        User user = userService.findByEmail(email);
        Cart cart = getOrCreateCart(user, null);
        
        updateCartItem(cart, itemId, request.getQuantity());
        
        // Return the updated cart item
        CartItem cartItem = cartItemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));
        
        return convertToCartItemResponse(cartItem);
    }
    
    public void removeFromCart(String email, Long itemId) {
        User user = userService.findByEmail(email);
        Cart cart = getOrCreateCart(user, null);
        
        removeFromCart(cart, itemId);
    }
    
    public void clearCart(String email) {
        User user = userService.findByEmail(email);
        Cart cart = getOrCreateCart(user, null);
        
        clearCart(cart);
    }
    
    public CartSummaryResponse getCartSummary(String email) {
        User user = userService.findByEmail(email);
        Cart cart = getOrCreateCart(user, null);
        
        CartSummaryResponse response = new CartSummaryResponse();
        response.setTotalItems(cart.getTotalItems());
        response.setSubtotal(cart.getTotalAmount());
        response.setDiscountAmount(cart.getDiscountAmount());
        response.setTotalAmount(cart.getTotalAmount().subtract(cart.getDiscountAmount()));
        
        return response;
    }
    
    public CartResponse removeVoucher(String email) {
        User user = userService.findByEmail(email);
        Cart cart = getOrCreateCart(user, null);
        
        cart.setVoucherCode(null);
        cart.setDiscountAmount(BigDecimal.ZERO);
        cartRepository.save(cart);
        updateCartTotals(cart);
        
        return getUserCart(email);
    }
    
    public CartValidationResponse validateCart(String email) {
        User user = userService.findByEmail(email);
        Cart cart = getOrCreateCart(user, null);
        
        CartValidationResponse response = new CartValidationResponse();
        response.setValid(true);
        response.setMessage("Cart is valid");
        
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            if (product.getStock() < item.getQuantity()) {
                response.setValid(false);
                response.setMessage("Some items are out of stock");
                break;
            }
        }
        
        return response;
    }
    
    public Integer getCartItemCount(String email) {
        User user = userService.findByEmail(email);
        Cart cart = getOrCreateCart(user, null);
        return cart.getTotalItems();
    }
    
    private CartItemResponse convertToCartItemResponse(CartItem cartItem) {
        CartItemResponse response = new CartItemResponse();
        response.setId(cartItem.getId());
        response.setProductId(cartItem.getProduct().getId());
        response.setProductName(cartItem.getProduct().getName());
        response.setProductImage(cartItem.getProduct().getImageUrl());
        response.setQuantity(cartItem.getQuantity());
        response.setUnitPrice(cartItem.getUnitPrice());
        response.setTotalPrice(cartItem.getTotalPrice());
        response.setSize(cartItem.getSize());
        response.setColor(cartItem.getColor());
        return response;
    }
}

