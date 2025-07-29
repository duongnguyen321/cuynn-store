package com.zmen.backend.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateCartItemRequest {
    
    @NotNull(message = "Cart item ID is required")
    private Long itemId;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    
    private String size;
    private String color;
    private Long variantId;
    
    // Constructors
    public UpdateCartItemRequest() {}
    
    public UpdateCartItemRequest(Long itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }
    
    public UpdateCartItemRequest(Long itemId, Integer quantity, String size, String color) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
    }
    
    // Getters and Setters
    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public Long getVariantId() { return variantId; }
    public void setVariantId(Long variantId) { this.variantId = variantId; }
}