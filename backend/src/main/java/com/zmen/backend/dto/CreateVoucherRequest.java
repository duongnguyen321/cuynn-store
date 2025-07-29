package com.zmen.backend.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateVoucherRequest {
    
    @NotBlank(message = "Voucher code is required")
    @Size(min = 3, max = 50, message = "Voucher code must be between 3 and 50 characters")
    @Pattern(regexp = "^[A-Z0-9_-]+$", message = "Voucher code can only contain uppercase letters, numbers, underscores and hyphens")
    private String voucherCode;
    
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title cannot exceed 200 characters")
    private String title;
    
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
    
    @NotNull(message = "Discount type is required")
    @Pattern(regexp = "^(phan_tram|so_tien)$", message = "Discount type must be either 'phan_tram' or 'so_tien'")
    private String discountType;
    
    @NotNull(message = "Discount value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Discount value must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Discount value is too large")
    private BigDecimal discountValue;
    
    @DecimalMin(value = "0.0", message = "Minimum order amount cannot be negative")
    private BigDecimal minimumOrderAmount = BigDecimal.ZERO;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Maximum discount amount must be greater than 0")
    private BigDecimal maximumDiscountAmount;
    
    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    private LocalDateTime startDate;
    
    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDateTime endDate;
    
    @NotNull(message = "Initial quantity is required")
    @Min(value = 1, message = "Initial quantity must be at least 1")
    @Max(value = 1000000, message = "Initial quantity cannot exceed 1,000,000")
    private Integer initialQuantity;
    
    @Pattern(regexp = "^(hoat_dong|ngung_hoat_dong)$", message = "Status must be either 'hoat_dong' or 'ngung_hoat_dong'")
    private String status = "hoat_dong";
    
    // Constructors
    public CreateVoucherRequest() {}
    
    // Getters and Setters
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
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    // Custom validation method
    @AssertTrue(message = "End date must be after start date")
    public boolean isEndDateAfterStartDate() {
        if (startDate == null || endDate == null) {
            return true; // Let @NotNull handle null validation
        }
        return endDate.isAfter(startDate);
    }
    
    @AssertTrue(message = "For percentage discount, value must be between 0 and 100")
    public boolean isPercentageDiscountValid() {
        if (discountType == null || discountValue == null) {
            return true; // Let other validations handle null values
        }
        if ("phan_tram".equals(discountType)) {
            return discountValue.compareTo(BigDecimal.ZERO) > 0 && 
                   discountValue.compareTo(new BigDecimal("100")) <= 0;
        }
        return true;
    }
    
    @AssertTrue(message = "Maximum discount amount is required for percentage discounts")
    public boolean isMaximumDiscountRequiredForPercentage() {
        if (discountType == null) {
            return true;
        }
        if ("phan_tram".equals(discountType)) {
            return maximumDiscountAmount != null && maximumDiscountAmount.compareTo(BigDecimal.ZERO) > 0;
        }
        return true;
    }
}