package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class MembershipInfoResponse {
    
    private Long userId;
    private String membershipTier;
    private String tierDescription;
    private String tierColor;
    private Integer pointsRequired;
    private Integer currentPoints;
    private Integer pointsToNextTier;
    private String nextTier;
    private BigDecimal discountPercentage;
    private boolean freeShipping;
    private Integer prioritySupport;
    private LocalDateTime memberSince;
    private LocalDateTime tierStartDate;
    private LocalDateTime tierExpiryDate;
    private BigDecimal totalSpent;
    private Integer totalOrders;
    private List<MembershipBenefit> benefits;
    private List<MembershipTierInfo> availableTiers;
    
    // Inner class for membership benefits
    public static class MembershipBenefit {
        private String type; // DISCOUNT, FREE_SHIPPING, PRIORITY_SUPPORT, EARLY_ACCESS, etc.
        private String title;
        private String description;
        private String value;
        private boolean active;
        
        // Constructors
        public MembershipBenefit() {}
        
        public MembershipBenefit(String type, String title, String description, boolean active) {
            this.type = type;
            this.title = title;
            this.description = description;
            this.active = active;
        }
        
        // Getters and Setters
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
        
        public boolean isActive() { return active; }
        public void setActive(boolean active) { this.active = active; }
    }
    
    // Inner class for membership tier information
    public static class MembershipTierInfo {
        private String tierName;
        private String description;
        private Integer pointsRequired;
        private BigDecimal discountPercentage;
        private String color;
        private boolean current;
        
        // Constructors
        public MembershipTierInfo() {}
        
        public MembershipTierInfo(String tierName, String description, Integer pointsRequired, boolean current) {
            this.tierName = tierName;
            this.description = description;
            this.pointsRequired = pointsRequired;
            this.current = current;
        }
        
        // Getters and Setters
        public String getTierName() { return tierName; }
        public void setTierName(String tierName) { this.tierName = tierName; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        
        public Integer getPointsRequired() { return pointsRequired; }
        public void setPointsRequired(Integer pointsRequired) { this.pointsRequired = pointsRequired; }
        
        public BigDecimal getDiscountPercentage() { return discountPercentage; }
        public void setDiscountPercentage(BigDecimal discountPercentage) { this.discountPercentage = discountPercentage; }
        
        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }
        
        public boolean isCurrent() { return current; }
        public void setCurrent(boolean current) { this.current = current; }
    }
    
    // Constructors
    public MembershipInfoResponse() {}
    
    public MembershipInfoResponse(Long userId, String membershipTier, Integer currentPoints) {
        this.userId = userId;
        this.membershipTier = membershipTier;
        this.currentPoints = currentPoints;
    }
    
    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getMembershipTier() { return membershipTier; }
    public void setMembershipTier(String membershipTier) { this.membershipTier = membershipTier; }
    
    public String getTierDescription() { return tierDescription; }
    public void setTierDescription(String tierDescription) { this.tierDescription = tierDescription; }
    
    public String getTierColor() { return tierColor; }
    public void setTierColor(String tierColor) { this.tierColor = tierColor; }
    
    public Integer getPointsRequired() { return pointsRequired; }
    public void setPointsRequired(Integer pointsRequired) { this.pointsRequired = pointsRequired; }
    
    public Integer getCurrentPoints() { return currentPoints; }
    public void setCurrentPoints(Integer currentPoints) { this.currentPoints = currentPoints; }
    
    public Integer getPointsToNextTier() { return pointsToNextTier; }
    public void setPointsToNextTier(Integer pointsToNextTier) { this.pointsToNextTier = pointsToNextTier; }
    
    public String getNextTier() { return nextTier; }
    public void setNextTier(String nextTier) { this.nextTier = nextTier; }
    
    public BigDecimal getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(BigDecimal discountPercentage) { this.discountPercentage = discountPercentage; }
    
    public boolean isFreeShipping() { return freeShipping; }
    public void setFreeShipping(boolean freeShipping) { this.freeShipping = freeShipping; }
    
    public Integer getPrioritySupport() { return prioritySupport; }
    public void setPrioritySupport(Integer prioritySupport) { this.prioritySupport = prioritySupport; }
    
    public LocalDateTime getMemberSince() { return memberSince; }
    public void setMemberSince(LocalDateTime memberSince) { this.memberSince = memberSince; }
    
    public LocalDateTime getTierStartDate() { return tierStartDate; }
    public void setTierStartDate(LocalDateTime tierStartDate) { this.tierStartDate = tierStartDate; }
    
    public LocalDateTime getTierExpiryDate() { return tierExpiryDate; }
    public void setTierExpiryDate(LocalDateTime tierExpiryDate) { this.tierExpiryDate = tierExpiryDate; }
    
    public BigDecimal getTotalSpent() { return totalSpent; }
    public void setTotalSpent(BigDecimal totalSpent) { this.totalSpent = totalSpent; }
    
    public Integer getTotalOrders() { return totalOrders; }
    public void setTotalOrders(Integer totalOrders) { this.totalOrders = totalOrders; }
    
    public List<MembershipBenefit> getBenefits() { return benefits; }
    public void setBenefits(List<MembershipBenefit> benefits) { this.benefits = benefits; }
    
    public List<MembershipTierInfo> getAvailableTiers() { return availableTiers; }
    public void setAvailableTiers(List<MembershipTierInfo> availableTiers) { this.availableTiers = availableTiers; }
}