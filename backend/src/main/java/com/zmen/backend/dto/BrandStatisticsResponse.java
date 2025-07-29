package com.zmen.backend.dto;

import java.math.BigDecimal;

public class BrandStatisticsResponse {
    private Long brandId;
    private String brandName;
    private Integer totalProducts;
    private Integer activeProducts;
    private Integer inactiveProducts;
    private BigDecimal totalRevenue;
    private BigDecimal averageProductPrice;
    private Integer totalOrders;
    private Integer totalReviews;
    private Double averageRating;
    private Integer viewCount;
    private Integer favoriteCount;
    
    // Constructors
    public BrandStatisticsResponse() {}
    
    // Getters and Setters
    public Long getBrandId() { return brandId; }
    public void setBrandId(Long brandId) { this.brandId = brandId; }
    
    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }
    
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
    
    public Integer getFavoriteCount() { return favoriteCount; }
    public void setFavoriteCount(Integer favoriteCount) { this.favoriteCount = favoriteCount; }
}