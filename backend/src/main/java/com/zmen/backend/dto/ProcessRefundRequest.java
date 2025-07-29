package com.zmen.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProcessRefundRequest {
    
    @NotNull(message = "Refund ID is required")
    private Long refundId;
    
    @NotBlank(message = "Action is required")
    private String action; // APPROVE, REJECT
    
    private String adminNotes;
    
    private Boolean notifyCustomer = true;
    
    // Constructors
    public ProcessRefundRequest() {}
    
    // Getters and Setters
    public Long getRefundId() { return refundId; }
    public void setRefundId(Long refundId) { this.refundId = refundId; }
    
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    
    public String getAdminNotes() { return adminNotes; }
    public void setAdminNotes(String adminNotes) { this.adminNotes = adminNotes; }
    
    public Boolean getNotifyCustomer() { return notifyCustomer; }
    public void setNotifyCustomer(Boolean notifyCustomer) { this.notifyCustomer = notifyCustomer; }
}