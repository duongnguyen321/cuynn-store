package com.zmen.backend.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddToCartRequest {
    
    @NotNull(message = "Product ID is required")
    private Long productId;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    
    private String size;
    private String color;
    private Long variantId;
    private String sessionId; // For guest users
    
    // Constructors
    public AddToCartRequest() {}
    
    public AddToCartRequest(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
    
    public AddToCartRequest(Long productId, Integer quantity, String size, String color) {
        this.productId = productId;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
    }
    
    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public Long getVariantId() { return variantId; }
    public void setVariantId(Long variantId) { this.variantId = variantId; }
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
}