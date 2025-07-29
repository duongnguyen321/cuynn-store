package com.zmen.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class RefundRequest {
    
    @NotNull(message = "Payment ID is required")
    private Long paymentId;
    
    @NotNull(message = "Refund amount is required")
    @DecimalMin(value = "0.01", message = "Refund amount must be greater than 0")
    private BigDecimal amount;
    
    @NotBlank(message = "Refund reason is required")
    private String reason;
    
    private String description;
    
    private Boolean notifyCustomer = true;
    
    // Constructors
    public RefundRequest() {}
    
    // Getters and Setters
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Boolean getNotifyCustomer() { return notifyCustomer; }
    public void setNotifyCustomer(Boolean notifyCustomer) { this.notifyCustomer = notifyCustomer; }
}