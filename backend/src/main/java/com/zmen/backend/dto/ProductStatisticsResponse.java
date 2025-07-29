package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductStatisticsResponse {
    private Long productId;
    private String productName;
    private Integer totalViews;
    private Integer totalSales;
    private Integer totalReviews;
    private Double averageRating;
    private BigDecimal totalRevenue;
    private Integer stockQuantity;
    private Integer soldQuantity;
    private Integer cartAdditions;
    private Integer wishlistAdditions;
    private Integer returnedQuantity;
    private BigDecimal returnRate;
    private BigDecimal conversionRate;
    private Integer popularityRank;
    private String performanceCategory;
    private LocalDateTime lastSaleDate;
    private LocalDateTime statisticsDate;
    private String periodType; // DAILY, WEEKLY, MONTHLY, YEARLY
    
    // Constructors
    public ProductStatisticsResponse() {}
    
    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public Integer getTotalViews() { return totalViews; }
    public void setTotalViews(Integer totalViews) { this.totalViews = totalViews; }
    
    public Integer getTotalSales() { return totalSales; }
    public void setTotalSales(Integer totalSales) { this.totalSales = totalSales; }
    
    public Integer getTotalReviews() { return totalReviews; }
    public void setTotalReviews(Integer totalReviews) { this.totalReviews = totalReviews; }
    
    public Double getAverageRating() { return averageRating; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
    
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
    
    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
    
    public Integer getSoldQuantity() { return soldQuantity; }
    public void setSoldQuantity(Integer soldQuantity) { this.soldQuantity = soldQuantity; }
    
    public Integer getCartAdditions() { return cartAdditions; }
    public void setCartAdditions(Integer cartAdditions) { this.cartAdditions = cartAdditions; }
    
    public Integer getWishlistAdditions() { return wishlistAdditions; }
    public void setWishlistAdditions(Integer wishlistAdditions) { this.wishlistAdditions = wishlistAdditions; }
    
    public Integer getReturnedQuantity() { return returnedQuantity; }
    public void setReturnedQuantity(Integer returnedQuantity) { this.returnedQuantity = returnedQuantity; }
    
    public BigDecimal getReturnRate() { return returnRate; }
    public void setReturnRate(BigDecimal returnRate) { this.returnRate = returnRate; }
    
    public BigDecimal getConversionRate() { return conversionRate; }
    public void setConversionRate(BigDecimal conversionRate) { this.conversionRate = conversionRate; }
    
    public Integer getPopularityRank() { return popularityRank; }
    public void setPopularityRank(Integer popularityRank) { this.popularityRank = popularityRank; }
    
    public String getPerformanceCategory() { return performanceCategory; }
    public void setPerformanceCategory(String performanceCategory) { this.performanceCategory = performanceCategory; }
    
    public LocalDateTime getLastSaleDate() { return lastSaleDate; }
    public void setLastSaleDate(LocalDateTime lastSaleDate) { this.lastSaleDate = lastSaleDate; }
    
    public LocalDateTime getStatisticsDate() { return statisticsDate; }
    public void setStatisticsDate(LocalDateTime statisticsDate) { this.statisticsDate = statisticsDate; }
    
    public String getPeriodType() { return periodType; }
    public void setPeriodType(String periodType) { this.periodType = periodType; }
}