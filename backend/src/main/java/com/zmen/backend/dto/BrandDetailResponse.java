package com.zmen.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BrandDetailResponse {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String logoUrl;
    private String website;
    private String country;
    private Integer foundedYear;
    private Boolean isActive;
    private Boolean isFeatured;
    private Integer productCount;
    private List<ProductResponse> featuredProducts;
    private List<CategoryResponse> categories;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public BrandDetailResponse() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public Integer getFoundedYear() { return foundedYear; }
    public void setFoundedYear(Integer foundedYear) { this.foundedYear = foundedYear; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public Boolean getIsFeatured() { return isFeatured; }
    public void setIsFeatured(Boolean isFeatured) { this.isFeatured = isFeatured; }
    
    public Integer getProductCount() { return productCount; }
    public void setProductCount(Integer productCount) { this.productCount = productCount; }
    
    public List<ProductResponse> getFeaturedProducts() { return featuredProducts; }
    public void setFeaturedProducts(List<ProductResponse> featuredProducts) { this.featuredProducts = featuredProducts; }
    
    public List<CategoryResponse> getCategories() { return categories; }
    public void setCategories(List<CategoryResponse> categories) { this.categories = categories; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}