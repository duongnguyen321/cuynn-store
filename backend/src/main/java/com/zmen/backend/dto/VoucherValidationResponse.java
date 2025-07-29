package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VoucherValidationResponse {
    private Boolean isValid;
    private String message;
    private String voucherCode;
    private String title;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private BigDecimal originalAmount;
    private BigDecimal minimumOrderAmount;
    private BigDecimal maximumDiscountAmount;
    private LocalDateTime expiryDate;
    private Integer remainingUses;
    private String validationStatus;
    
    // Validation error details
    private String errorCode;
    private String errorDetail;
    
    // Constructors
    public VoucherValidationResponse() {}
    
    public VoucherValidationResponse(Boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }
    
    // Static factory methods for common responses
    public static VoucherValidationResponse valid(String voucherCode, BigDecimal discountAmount, BigDecimal finalAmount) {
        VoucherValidationResponse response = new VoucherValidationResponse();
        response.setIsValid(true);
        response.setMessage("Voucher is valid and can be applied");
        response.setVoucherCode(voucherCode);
        response.setDiscountAmount(discountAmount);
        response.setFinalAmount(finalAmount);
        response.setValidationStatus("VALID");
        return response;
    }
    
    public static VoucherValidationResponse invalid(String message, String errorCode) {
        VoucherValidationResponse response = new VoucherValidationResponse();
        response.setIsValid(false);
        response.setMessage(message);
        response.setErrorCode(errorCode);
        response.setValidationStatus("INVALID");
        return response;
    }
    
    public static VoucherValidationResponse expired(String voucherCode, LocalDateTime expiryDate) {
        VoucherValidationResponse response = new VoucherValidationResponse();
        response.setIsValid(false);
        response.setMessage("Voucher has expired");
        response.setVoucherCode(voucherCode);
        response.setExpiryDate(expiryDate);
        response.setErrorCode("EXPIRED");
        response.setValidationStatus("EXPIRED");
        return response;
    }
    
    public static VoucherValidationResponse minimumAmountNotMet(String voucherCode, BigDecimal minimumAmount) {
        VoucherValidationResponse response = new VoucherValidationResponse();
        response.setIsValid(false);
        response.setMessage("Order amount does not meet minimum requirement");
        response.setVoucherCode(voucherCode);
        response.setMinimumOrderAmount(minimumAmount);
        response.setErrorCode("MINIMUM_AMOUNT_NOT_MET");
        response.setValidationStatus("INVALID");
        return response;
    }
    
    // Getters and Setters
    public Boolean getIsValid() { return isValid; }
    public void setIsValid(Boolean isValid) { this.isValid = isValid; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public String getVoucherCode() { return voucherCode; }
    public void setVoucherCode(String voucherCode) { this.voucherCode = voucherCode; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }
    
    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { this.discountValue = discountValue; }
    
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    
    public BigDecimal getFinalAmount() { return finalAmount; }
    public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
    
    public BigDecimal getOriginalAmount() { return originalAmount; }
    public void setOriginalAmount(BigDecimal originalAmount) { this.originalAmount = originalAmount; }
    
    public BigDecimal getMinimumOrderAmount() { return minimumOrderAmount; }
    public void setMinimumOrderAmount(BigDecimal minimumOrderAmount) { this.minimumOrderAmount = minimumOrderAmount; }
    
    public BigDecimal getMaximumDiscountAmount() { return maximumDiscountAmount; }
    public void setMaximumDiscountAmount(BigDecimal maximumDiscountAmount) { this.maximumDiscountAmount = maximumDiscountAmount; }
    
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    
    public Integer getRemainingUses() { return remainingUses; }
    public void setRemainingUses(Integer remainingUses) { this.remainingUses = remainingUses; }
    
    public String getValidationStatus() { return validationStatus; }
    public void setValidationStatus(String validationStatus) { this.validationStatus = validationStatus; }
    
    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
    
    public String getErrorDetail() { return errorDetail; }
    public void setErrorDetail(String errorDetail) { this.errorDetail = errorDetail; }
}