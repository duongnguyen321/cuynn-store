package com.zmen.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class ValidateVoucherRequest {
    
    @NotBlank(message = "Voucher code is required")
    private String voucherCode;
    
    @NotNull(message = "Order amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Order amount must be greater than 0")
    private BigDecimal orderAmount;
    
    private String userEmail;
    
    private Long userId;
    
    // Constructors
    public ValidateVoucherRequest() {}
    
    public ValidateVoucherRequest(String voucherCode, BigDecimal orderAmount) {
        this.voucherCode = voucherCode;
        this.orderAmount = orderAmount;
    }
    
    // Getters and Setters
    public String getVoucherCode() { return voucherCode; }
    public void setVoucherCode(String voucherCode) { this.voucherCode = voucherCode; }
    
    public BigDecimal getOrderAmount() { return orderAmount; }
    public void setOrderAmount(BigDecimal orderAmount) { this.orderAmount = orderAmount; }
    
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}