package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartItemResponse {
    
    private Long id;
    private Long cartId;
    private Long productId;
    private String productName;
    private String productImage;
    private String productSlug;
    private Long variantId;
    private String variantSku;
    private String size;
    private String color;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal priceAtTime;
    private BigDecimal totalPrice;
    private boolean inStock;
    private Integer availableQuantity;
    private LocalDateTime addedAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public CartItemResponse() {}
    
    public CartItemResponse(Long id, Long productId, String productName, Integer quantity, 
                           BigDecimal priceAtTime) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
        this.totalPrice = priceAtTime.multiply(BigDecimal.valueOf(quantity));
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getCartId() { return cartId; }
    public void setCartId(Long cartId) { this.cartId = cartId; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }
    
    public String getProductSlug() { return productSlug; }
    public void setProductSlug(String productSlug) { this.productSlug = productSlug; }
    
    public Long getVariantId() { return variantId; }
    public void setVariantId(Long variantId) { this.variantId = variantId; }
    
    public String getVariantSku() { return variantSku; }
    public void setVariantSku(String variantSku) { this.variantSku = variantSku; }
    
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { 
        this.quantity = quantity;
        if (this.priceAtTime != null) {
            this.totalPrice = this.priceAtTime.multiply(BigDecimal.valueOf(quantity));
        }
    }
    
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    
    public BigDecimal getPriceAtTime() { return priceAtTime; }
    public void setPriceAtTime(BigDecimal priceAtTime) { 
        this.priceAtTime = priceAtTime;
        if (this.quantity != null) {
            this.totalPrice = priceAtTime.multiply(BigDecimal.valueOf(quantity));
        }
    }
    
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    
    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }
    
    public Integer getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(Integer availableQuantity) { this.availableQuantity = availableQuantity; }
    
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}