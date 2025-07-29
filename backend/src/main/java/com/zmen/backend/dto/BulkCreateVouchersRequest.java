package com.zmen.backend.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BulkCreateVouchersRequest {
    
    @NotBlank(message = "Base voucher code is required")
    @Size(min = 3, max = 30, message = "Base voucher code must be between 3 and 30 characters")
    @Pattern(regexp = "^[A-Z0-9_-]+$", message = "Base voucher code can only contain uppercase letters, numbers, underscores and hyphens")
    private String baseVoucherCode;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 10000, message = "Cannot create more than 10,000 vouchers at once")
    private Integer quantity;
    
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
    
    @NotNull(message = "Initial quantity per voucher is required")
    @Min(value = 1, message = "Initial quantity per voucher must be at least 1")
    @Max(value = 1000000, message = "Initial quantity per voucher cannot exceed 1,000,000")
    private Integer initialQuantityPerVoucher;
    
    @Pattern(regexp = "^(hoat_dong|ngung_hoat_dong)$", message = "Status must be either 'hoat_dong' or 'ngung_hoat_dong'")
    private String status = "hoat_dong";
    
    @NotNull(message = "Code generation strategy is required")
    @Pattern(regexp = "^(sequential|random|prefix_sequential|prefix_random)$", 
             message = "Code generation strategy must be one of: sequential, random, prefix_sequential, prefix_random")
    private String codeGenerationStrategy = "sequential";
    
    @Min(value = 4, message = "Random code length must be at least 4")
    @Max(value = 20, message = "Random code length cannot exceed 20")
    private Integer randomCodeLength = 8;
    
    private String codePrefix;
    private String codeSuffix;
    
    private Boolean generateUniqueCodesOnly = true;
    private Boolean allowDuplicateValidation = false;
    
    // Constructors
    public BulkCreateVouchersRequest() {}
    
    // Getters and Setters
    public String getBaseVoucherCode() { return baseVoucherCode; }
    public void setBaseVoucherCode(String baseVoucherCode) { this.baseVoucherCode = baseVoucherCode; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
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
    
    public Integer getInitialQuantityPerVoucher() { return initialQuantityPerVoucher; }
    public void setInitialQuantityPerVoucher(Integer initialQuantityPerVoucher) { this.initialQuantityPerVoucher = initialQuantityPerVoucher; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getCodeGenerationStrategy() { return codeGenerationStrategy; }
    public void setCodeGenerationStrategy(String codeGenerationStrategy) { this.codeGenerationStrategy = codeGenerationStrategy; }
    
    public Integer getRandomCodeLength() { return randomCodeLength; }
    public void setRandomCodeLength(Integer randomCodeLength) { this.randomCodeLength = randomCodeLength; }
    
    public String getCodePrefix() { return codePrefix; }
    public void setCodePrefix(String codePrefix) { this.codePrefix = codePrefix; }
    
    public String getCodeSuffix() { return codeSuffix; }
    public void setCodeSuffix(String codeSuffix) { this.codeSuffix = codeSuffix; }
    
    public Boolean getGenerateUniqueCodesOnly() { return generateUniqueCodesOnly; }
    public void setGenerateUniqueCodesOnly(Boolean generateUniqueCodesOnly) { this.generateUniqueCodesOnly = generateUniqueCodesOnly; }
    
    public Boolean getAllowDuplicateValidation() { return allowDuplicateValidation; }
    public void setAllowDuplicateValidation(Boolean allowDuplicateValidation) { this.allowDuplicateValidation = allowDuplicateValidation; }
    
    // Custom validation methods
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
    
    @AssertTrue(message = "Random code length is required for random generation strategies")
    public boolean isRandomCodeLengthValidForStrategy() {
        if (codeGenerationStrategy == null) {
            return true;
        }
        if (codeGenerationStrategy.contains("random")) {
            return randomCodeLength != null && randomCodeLength >= 4 && randomCodeLength <= 20;
        }
        return true;
    }
}