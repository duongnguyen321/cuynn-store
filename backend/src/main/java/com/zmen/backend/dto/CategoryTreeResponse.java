package com.zmen.backend.dto;

import java.util.List;

public class CategoryTreeResponse {
    private Long id;
    private String name;
    private String slug;
    private String iconUrl;
    private Long parentId;
    private Integer sortOrder;
    private Boolean isActive;
    private Boolean isFeatured;
    private Integer productCount;
    private Integer level;
    private List<CategoryTreeResponse> children;
    
    // Constructors
    public CategoryTreeResponse() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    
    public Long getParentId() { return parentId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }
    
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
    
    public List<CategoryTreeResponse> getChildren() { return children; }
    public void setChildren(List<CategoryTreeResponse> children) { this.children = children; }
}