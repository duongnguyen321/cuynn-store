package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CartResponse {
    
    private Long id;
    private Long userId;
    private String sessionId;
    private Integer totalItems;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private String voucherCode;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CartItemResponse> items;
    
    // Constructors
    public CartResponse() {}
    
    public CartResponse(Long id, Long userId, String sessionId, Integer totalItems, 
                       BigDecimal totalAmount, BigDecimal discountAmount, String status) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.totalItems = totalItems;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = totalAmount.subtract(discountAmount != null ? discountAmount : BigDecimal.ZERO);
        this.status = status;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }
    
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { 
        this.totalAmount = totalAmount;
        this.finalAmount = totalAmount.subtract(discountAmount != null ? discountAmount : BigDecimal.ZERO);
    }
    
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { 
        this.discountAmount = discountAmount;
        this.finalAmount = totalAmount.subtract(discountAmount != null ? discountAmount : BigDecimal.ZERO);
    }
    
    public BigDecimal getFinalAmount() { return finalAmount; }
    public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
    
    public String getVoucherCode() { return voucherCode; }
    public void setVoucherCode(String voucherCode) { this.voucherCode = voucherCode; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public List<CartItemResponse> getItems() { return items; }
    public void setItems(List<CartItemResponse> items) { this.items = items; }
}