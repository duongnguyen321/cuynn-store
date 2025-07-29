package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class LoyaltyPointsResponse {
    
    private Long userId;
    private Integer currentPoints;
    private Integer totalEarnedPoints;
    private Integer totalUsedPoints;
    private BigDecimal pointsValue; // Monetary value of current points
    private BigDecimal conversionRate; // Points to currency conversion rate
    private Integer pointsToNextTier;
    private String currentTier;
    private String nextTier;
    private LocalDateTime lastUpdated;
    private List<LoyaltyPointTransaction> recentTransactions;
    
    // Inner class for loyalty point transactions
    public static class LoyaltyPointTransaction {
        private Long id;
        private String type; // EARNED, USED, EXPIRED, BONUS
        private Integer points;
        private String description;
        private String orderId;
        private LocalDateTime transactionDate;
        private LocalDateTime expiryDate;
        
        // Constructors
        public LoyaltyPointTransaction() {}
        
        public LoyaltyPointTransaction(String type, Integer points, String description, LocalDateTime transactionDate) {
            this.type = type;
            this.points = points;
            this.description = description;
            this.transactionDate = transactionDate;
        }
        
        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        
        public Integer getPoints() { return points; }
        public void setPoints(Integer points) { this.points = points; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        
        public String getOrderId() { return orderId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        
        public LocalDateTime getTransactionDate() { return transactionDate; }
        public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
        
        public LocalDateTime getExpiryDate() { return expiryDate; }
        public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    }
    
    // Constructors
    public LoyaltyPointsResponse() {}
    
    public LoyaltyPointsResponse(Long userId, Integer currentPoints, String currentTier) {
        this.userId = userId;
        this.currentPoints = currentPoints;
        this.currentTier = currentTier;
    }
    
    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Integer getCurrentPoints() { return currentPoints; }
    public void setCurrentPoints(Integer currentPoints) { this.currentPoints = currentPoints; }
    
    public Integer getTotalEarnedPoints() { return totalEarnedPoints; }
    public void setTotalEarnedPoints(Integer totalEarnedPoints) { this.totalEarnedPoints = totalEarnedPoints; }
    
    public Integer getTotalUsedPoints() { return totalUsedPoints; }
    public void setTotalUsedPoints(Integer totalUsedPoints) { this.totalUsedPoints = totalUsedPoints; }
    
    public BigDecimal getPointsValue() { return pointsValue; }
    public void setPointsValue(BigDecimal pointsValue) { this.pointsValue = pointsValue; }
    
    public BigDecimal getConversionRate() { return conversionRate; }
    public void setConversionRate(BigDecimal conversionRate) { this.conversionRate = conversionRate; }
    
    public Integer getPointsToNextTier() { return pointsToNextTier; }
    public void setPointsToNextTier(Integer pointsToNextTier) { this.pointsToNextTier = pointsToNextTier; }
    
    public String getCurrentTier() { return currentTier; }
    public void setCurrentTier(String currentTier) { this.currentTier = currentTier; }
    
    public String getNextTier() { return nextTier; }
    public void setNextTier(String nextTier) { this.nextTier = nextTier; }
    
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    
    public List<LoyaltyPointTransaction> getRecentTransactions() { return recentTransactions; }
    public void setRecentTransactions(List<LoyaltyPointTransaction> recentTransactions) { this.recentTransactions = recentTransactions; }
}