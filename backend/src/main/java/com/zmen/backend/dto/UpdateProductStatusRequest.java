package com.zmen.backend.dto;

import javax.validation.constraints.NotBlank;

public class UpdateProductStatusRequest {
    
    @NotBlank(message = "Status is required")
    private String status;
    
    private String reason;
    
    private Boolean featured;
    
    private Boolean isNew;
    
    private Boolean bestSeller;
    
    // Constructors
    public UpdateProductStatusRequest() {}
    
    public UpdateProductStatusRequest(String status) {
        this.status = status;
    }
    
    public UpdateProductStatusRequest(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }
    
    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public Boolean getFeatured() { return featured; }
    public void setFeatured(Boolean featured) { this.featured = featured; }
    
    public Boolean getIsNew() { return isNew; }
    public void setIsNew(Boolean isNew) { this.isNew = isNew; }
    
    public Boolean getBestSeller() { return bestSeller; }
    public void setBestSeller(Boolean bestSeller) { this.bestSeller = bestSeller; }
}