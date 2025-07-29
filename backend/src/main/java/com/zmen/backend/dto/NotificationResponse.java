package com.zmen.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationResponse {
    
    private Long id;
    private Long userId;
    private String type; // ORDER, PROMOTION, SYSTEM, ACCOUNT, PRODUCT, PAYMENT
    private String title;
    private String message;
    private String description;
    private String imageUrl;
    private String actionUrl;
    private String actionText;
    private boolean isRead;
    private String priority; // LOW, MEDIUM, HIGH, URGENT
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime readAt;
    private LocalDateTime expiresAt;
    private NotificationData data; // Additional structured data
    
    // Inner class for additional notification data
    public static class NotificationData {
        private String orderId;
        private String productId;
        private String promotionId;
        private String amount;
        private String status;
        private String trackingNumber;
        private String couponCode;
        
        // Constructors
        public NotificationData() {}
        
        // Getters and Setters
        public String getOrderId() { return orderId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        
        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        
        public String getPromotionId() { return promotionId; }
        public void setPromotionId(String promotionId) { this.promotionId = promotionId; }
        
        public String getAmount() { return amount; }
        public void setAmount(String amount) { this.amount = amount; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        public String getTrackingNumber() { return trackingNumber; }
        public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
        
        public String getCouponCode() { return couponCode; }
        public void setCouponCode(String couponCode) { this.couponCode = couponCode; }
    }
    
    // Constructors
    public NotificationResponse() {}
    
    public NotificationResponse(Long id, String type, String title, String message, boolean isRead, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.message = message;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public String getActionUrl() { return actionUrl; }
    public void setActionUrl(String actionUrl) { this.actionUrl = actionUrl; }
    
    public String getActionText() { return actionText; }
    public void setActionText(String actionText) { this.actionText = actionText; }
    
    public boolean isRead() { return isRead; }
    public void setRead(boolean isRead) { this.isRead = isRead; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getReadAt() { return readAt; }
    public void setReadAt(LocalDateTime readAt) { this.readAt = readAt; }
    
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
    
    public NotificationData getData() { return data; }
    public void setData(NotificationData data) { this.data = data; }
    
    // Utility methods
    public boolean isExpired() {
        return expiresAt != null && LocalDateTime.now().isAfter(expiresAt);
    }
    
    public boolean isUrgent() {
        return "URGENT".equals(priority);
    }
    
    public boolean isHighPriority() {
        return "HIGH".equals(priority) || "URGENT".equals(priority);
    }
}