package com.zmen.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ProductReviewResponse {
    private Long id;
    private Long productId;
    private String productName;
    private Long userId;
    private String userName;
    private String userAvatar;
    private Long orderId;
    private String orderNumber;
    private Integer rating;
    private String title;
    private String content;
    private String advantages;
    private String disadvantages;
    private Boolean isPurchased;
    private Boolean hasImages;
    private List<String> reviewImages;
    private Integer likeCount;
    private Integer dislikeCount;
    private String status;
    private Boolean isVerified;
    private Boolean isRecommended;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime approvedAt;
    
    // Constructors
    public ProductReviewResponse() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public String getUserAvatar() { return userAvatar; }
    public void setUserAvatar(String userAvatar) { this.userAvatar = userAvatar; }
    
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public String getAdvantages() { return advantages; }
    public void setAdvantages(String advantages) { this.advantages = advantages; }
    
    public String getDisadvantages() { return disadvantages; }
    public void setDisadvantages(String disadvantages) { this.disadvantages = disadvantages; }
    
    public Boolean getIsPurchased() { return isPurchased; }
    public void setIsPurchased(Boolean isPurchased) { this.isPurchased = isPurchased; }
    
    public Boolean getHasImages() { return hasImages; }
    public void setHasImages(Boolean hasImages) { this.hasImages = hasImages; }
    
    public List<String> getReviewImages() { return reviewImages; }
    public void setReviewImages(List<String> reviewImages) { this.reviewImages = reviewImages; }
    
    public Integer getLikeCount() { return likeCount; }
    public void setLikeCount(Integer likeCount) { this.likeCount = likeCount; }
    
    public Integer getDislikeCount() { return dislikeCount; }
    public void setDislikeCount(Integer dislikeCount) { this.dislikeCount = dislikeCount; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
    
    public Boolean getIsRecommended() { return isRecommended; }
    public void setIsRecommended(Boolean isRecommended) { this.isRecommended = isRecommended; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public LocalDateTime getApprovedAt() { return approvedAt; }
    public void setApprovedAt(LocalDateTime approvedAt) { this.approvedAt = approvedAt; }
}