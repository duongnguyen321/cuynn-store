package com.zmen.backend.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UpdateVoucherRequest {
    
    @Size(max = 200, message = "Title cannot exceed 200 characters")
    private String title;
    
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Discount value must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Discount value is too large")
    private BigDecimal discountValue;
    
    @DecimalMin(value = "0.0", message = "Minimum order amount cannot be negative")
    private BigDecimal minimumOrderAmount;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Maximum discount amount must be greater than 0")
    private BigDecimal maximumDiscountAmount;
    
    private LocalDateTime startDate;
    
    private LocalDateTime endDate;
    
    @Min(value = 1, message = "Initial quantity must be at least 1")
    @Max(value = 1000000, message = "Initial quantity cannot exceed 1,000,000")
    private Integer initialQuantity;
    
    @Pattern(regexp = "^(hoat_dong|ngung_hoat_dong)$", message = "Status must be either 'hoat_dong' or 'ngung_hoat_dong'")
    private String status;
    
    // Fields that indicate what should be updated
    private Boolean updateTitle = false;
    private Boolean updateDescription = false;
    private Boolean updateDiscountValue = false;
    private Boolean updateMinimumOrderAmount = false;
    private Boolean updateMaximumDiscountAmount = false;
    private Boolean updateStartDate = false;
    private Boolean updateEndDate = false;
    private Boolean updateInitialQuantity = false;
    private Boolean updateStatus = false;
    
    // Constructors
    public UpdateVoucherRequest() {}
    
    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { 
        this.title = title; 
        this.updateTitle = true;
    }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { 
        this.description = description; 
        this.updateDescription = true;
    }
    
    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { 
        this.discountValue = discountValue; 
        this.updateDiscountValue = true;
    }
    
    public BigDecimal getMinimumOrderAmount() { return minimumOrderAmount; }
    public void setMinimumOrderAmount(BigDecimal minimumOrderAmount) { 
        this.minimumOrderAmount = minimumOrderAmount; 
        this.updateMinimumOrderAmount = true;
    }
    
    public BigDecimal getMaximumDiscountAmount() { return maximumDiscountAmount; }
    public void setMaximumDiscountAmount(BigDecimal maximumDiscountAmount) { 
        this.maximumDiscountAmount = maximumDiscountAmount; 
        this.updateMaximumDiscountAmount = true;
    }
    
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { 
        this.startDate = startDate; 
        this.updateStartDate = true;
    }
    
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { 
        this.endDate = endDate; 
        this.updateEndDate = true;
    }
    
    public Integer getInitialQuantity() { return initialQuantity; }
    public void setInitialQuantity(Integer initialQuantity) { 
        this.initialQuantity = initialQuantity; 
        this.updateInitialQuantity = true;
    }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { 
        this.status = status; 
        this.updateStatus = true;
    }
    
    // Update flags getters
    public Boolean getUpdateTitle() { return updateTitle; }
    public Boolean getUpdateDescription() { return updateDescription; }
    public Boolean getUpdateDiscountValue() { return updateDiscountValue; }
    public Boolean getUpdateMinimumOrderAmount() { return updateMinimumOrderAmount; }
    public Boolean getUpdateMaximumDiscountAmount() { return updateMaximumDiscountAmount; }
    public Boolean getUpdateStartDate() { return updateStartDate; }
    public Boolean getUpdateEndDate() { return updateEndDate; }
    public Boolean getUpdateInitialQuantity() { return updateInitialQuantity; }
    public Boolean getUpdateStatus() { return updateStatus; }
    
    // Helper methods
    public boolean hasUpdates() {
        return updateTitle || updateDescription || updateDiscountValue || 
               updateMinimumOrderAmount || updateMaximumDiscountAmount || 
               updateStartDate || updateEndDate || updateInitialQuantity || updateStatus;
    }
    
    // Custom validation method
    @AssertTrue(message = "End date must be after start date")
    public boolean isEndDateAfterStartDate() {
        if (!updateStartDate && !updateEndDate) {
            return true; // No date updates, skip validation
        }
        if (startDate == null || endDate == null) {
            return true; // Let other validations handle null values
        }
        return endDate.isAfter(startDate);
    }
    
    @AssertTrue(message = "At least one field must be updated")
    public boolean hasAtLeastOneUpdate() {
        return hasUpdates();
    }
}