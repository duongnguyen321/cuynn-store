package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VoucherResponse {
    private Long id;
    private String voucherCode;
    private String title;
    private String description;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal minimumOrderAmount;
    private BigDecimal maximumDiscountAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer initialQuantity;
    private Integer usedQuantity;
    private Integer remainingQuantity;
    private String status;
    private Boolean isValid;
    private Boolean canUse;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public VoucherResponse() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getVoucherCode() { return voucherCode; }
    public void setVoucherCode(String voucherCode) { this.voucherCode = voucherCode; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }
    
    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { this.discountValue = discountValue; }
    
    public BigDecimal getMinimumOrderAmount() { return minimumOrderAmount; }
    public void setMinimumOrderAmount(BigDecimal minimumOrderAmount) { this.minimumOrderAmount = minimumOrderAmount; }
    
    public BigDecimal getMaximumDiscountAmount() { return maximumDiscountAmount; }
    public void setMaximumDiscountAmount(BigDecimal maximumDiscountAmount) { this.maximumDiscountAmount = maximumDiscountAmount; }
    
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    
    public Integer getInitialQuantity() { return initialQuantity; }
    public void setInitialQuantity(Integer initialQuantity) { this.initialQuantity = initialQuantity; }
    
    public Integer getUsedQuantity() { return usedQuantity; }
    public void setUsedQuantity(Integer usedQuantity) { this.usedQuantity = usedQuantity; }
    
    public Integer getRemainingQuantity() { return remainingQuantity; }
    public void setRemainingQuantity(Integer remainingQuantity) { this.remainingQuantity = remainingQuantity; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Boolean getIsValid() { return isValid; }
    public void setIsValid(Boolean isValid) { this.isValid = isValid; }
    
    public Boolean getCanUse() { return canUse; }
    public void setCanUse(Boolean canUse) { this.canUse = canUse; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}