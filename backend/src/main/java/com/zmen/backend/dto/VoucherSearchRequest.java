package com.zmen.backend.dto;

import java.time.LocalDateTime;

public class VoucherSearchRequest {
    private String search;
    private Boolean active;
    private String type;
    private String status;
    private String discountType;
    private LocalDateTime startDateFrom;
    private LocalDateTime startDateTo;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private Boolean isExpired;
    private Boolean hasUsageLeft;
    private String createdBy;
    private String sortBy;
    private String sortDirection;
    
    // Constructors
    public VoucherSearchRequest() {}
    
    // Getters and Setters
    public String getSearch() { return search; }
    public void setSearch(String search) { this.search = search; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }
    
    public LocalDateTime getStartDateFrom() { return startDateFrom; }
    public void setStartDateFrom(LocalDateTime startDateFrom) { this.startDateFrom = startDateFrom; }
    
    public LocalDateTime getStartDateTo() { return startDateTo; }
    public void setStartDateTo(LocalDateTime startDateTo) { this.startDateTo = startDateTo; }
    
    public LocalDateTime getEndDateFrom() { return endDateFrom; }
    public void setEndDateFrom(LocalDateTime endDateFrom) { this.endDateFrom = endDateFrom; }
    
    public LocalDateTime getEndDateTo() { return endDateTo; }
    public void setEndDateTo(LocalDateTime endDateTo) { this.endDateTo = endDateTo; }
    
    public Boolean getIsExpired() { return isExpired; }
    public void setIsExpired(Boolean isExpired) { this.isExpired = isExpired; }
    
    public Boolean getHasUsageLeft() { return hasUsageLeft; }
    public void setHasUsageLeft(Boolean hasUsageLeft) { this.hasUsageLeft = hasUsageLeft; }
    
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    
    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
    
    public String getSortDirection() { return sortDirection; }
    public void setSortDirection(String sortDirection) { this.sortDirection = sortDirection; }
    
    // Helper methods
    public boolean hasSearchTerm() {
        return search != null && !search.trim().isEmpty();
    }
    
    public boolean hasDateFilter() {
        return startDateFrom != null || startDateTo != null || 
               endDateFrom != null || endDateTo != null;
    }
    
    public boolean hasStatusFilter() {
        return active != null || status != null || isExpired != null || hasUsageLeft != null;
    }
}