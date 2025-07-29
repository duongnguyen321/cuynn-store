package com.zmen.backend.dto;

import javax.validation.constraints.NotNull;

public class UpdateCategoryStatusRequest {
    
    @NotNull(message = "Active status is required")
    private Boolean isActive;
    
    private Boolean isFeatured;
    
    // Constructors
    public UpdateCategoryStatusRequest() {}
    
    // Getters and Setters
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public Boolean getIsFeatured() { return isFeatured; }
    public void setIsFeatured(Boolean isFeatured) { this.isFeatured = isFeatured; }
}