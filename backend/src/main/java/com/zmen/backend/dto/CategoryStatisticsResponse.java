package com.zmen.backend.dto;

import java.math.BigDecimal;

public class CategoryStatisticsResponse {
    private Long categoryId;
    private String categoryName;
    private Integer totalProducts;
    private Integer activeProducts;
    private Integer inactiveProducts;
    private BigDecimal totalRevenue;
    private BigDecimal averageProductPrice;
    private Integer totalOrders;
    private Integer totalReviews;
    private Double averageRating;
    private Integer viewCount;
    private Integer subcategoriesCount;
    private Integer level;
    
    // Constructors
    public CategoryStatisticsResponse() {}
    
    // Getters and Setters
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    
    public Integer getTotalProducts() { return totalProducts; }
    public void setTotalProducts(Integer totalProducts) { this.totalProducts = totalProducts; }
    
    public Integer getActiveProducts() { return activeProducts; }
    public void setActiveProducts(Integer activeProducts) { this.activeProducts = activeProducts; }
    
    public Integer getInactiveProducts() { return inactiveProducts; }
    public void setInactiveProducts(Integer inactiveProducts) { this.inactiveProducts = inactiveProducts; }
    
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
    
    public BigDecimal getAverageProductPrice() { return averageProductPrice; }
    public void setAverageProductPrice(BigDecimal averageProductPrice) { this.averageProductPrice = averageProductPrice; }
    
    public Integer getTotalOrders() { return totalOrders; }
    public void setTotalOrders(Integer totalOrders) { this.totalOrders = totalOrders; }
    
    public Integer getTotalReviews() { return totalReviews; }
    public void setTotalReviews(Integer totalReviews) { this.totalReviews = totalReviews; }
    
    public Double getAverageRating() { return averageRating; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
    
    public Integer getViewCount() { return viewCount; }
    public void setViewCount(Integer viewCount) { this.viewCount = viewCount; }
    
    public Integer getSubcategoriesCount() { return subcategoriesCount; }
    public void setSubcategoriesCount(Integer subcategoriesCount) { this.subcategoriesCount = subcategoriesCount; }
    
    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }
}