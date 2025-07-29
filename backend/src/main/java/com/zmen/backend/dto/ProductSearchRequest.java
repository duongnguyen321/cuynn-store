package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductSearchRequest {
    private String query;
    private List<Long> categoryIds;
    private List<Long> brandIds;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer minRating;
    private Boolean inStock;
    private Boolean onSale;
    private Boolean featured;
    private String sortBy = "name";
    private String sortDirection = "asc";
    
    // Constructors
    public ProductSearchRequest() {}
    
    // Getters and Setters
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }
    
    public List<Long> getCategoryIds() { return categoryIds; }
    public void setCategoryIds(List<Long> categoryIds) { this.categoryIds = categoryIds; }
    
    public List<Long> getBrandIds() { return brandIds; }
    public void setBrandIds(List<Long> brandIds) { this.brandIds = brandIds; }
    
    public BigDecimal getMinPrice() { return minPrice; }
    public void setMinPrice(BigDecimal minPrice) { this.minPrice = minPrice; }
    
    public BigDecimal getMaxPrice() { return maxPrice; }
    public void setMaxPrice(BigDecimal maxPrice) { this.maxPrice = maxPrice; }
    
    public Integer getMinRating() { return minRating; }
    public void setMinRating(Integer minRating) { this.minRating = minRating; }
    
    public Boolean getInStock() { return inStock; }
    public void setInStock(Boolean inStock) { this.inStock = inStock; }
    
    public Boolean getOnSale() { return onSale; }
    public void setOnSale(Boolean onSale) { this.onSale = onSale; }
    
    public Boolean getFeatured() { return featured; }
    public void setFeatured(Boolean featured) { this.featured = featured; }
    
    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
    
    public String getSortDirection() { return sortDirection; }
    public void setSortDirection(String sortDirection) { this.sortDirection = sortDirection; }
}