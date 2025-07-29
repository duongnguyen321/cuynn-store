package com.zmen.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CategoryDetailResponse {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String imageUrl;
    private String iconUrl;
    private Long parentId;
    private String parentName;
    private List<CategoryResponse> children;
    private List<CategoryResponse> breadcrumb;
    private Integer sortOrder;
    private Boolean isActive;
    private Boolean isFeatured;
    private Integer productCount;
    private Integer level;
    private String metaTitle;
    private String metaDescription;
    private List<ProductResponse> featuredProducts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public CategoryDetailResponse() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    
    public Long getParentId() { return parentId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }
    
    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }
    
    public List<CategoryResponse> getChildren() { return children; }
    public void setChildren(List<CategoryResponse> children) { this.children = children; }
    
    public List<CategoryResponse> getBreadcrumb() { return breadcrumb; }
    public void setBreadcrumb(List<CategoryResponse> breadcrumb) { this.breadcrumb = breadcrumb; }
    
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public Boolean getIsFeatured() { return isFeatured; }
    public void setIsFeatured(Boolean isFeatured) { this.isFeatured = isFeatured; }
    
    public Integer getProductCount() { return productCount; }
    public void setProductCount(Integer productCount) { this.productCount = productCount; }
    
    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }
    
    public String getMetaTitle() { return metaTitle; }
    public void setMetaTitle(String metaTitle) { this.metaTitle = metaTitle; }
    
    public String getMetaDescription() { return metaDescription; }
    public void setMetaDescription(String metaDescription) { this.metaDescription = metaDescription; }
    
    public List<ProductResponse> getFeaturedProducts() { return featuredProducts; }
    public void setFeaturedProducts(List<ProductResponse> featuredProducts) { this.featuredProducts = featuredProducts; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}