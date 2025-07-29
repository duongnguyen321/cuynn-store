package com.zmen.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateVoucherStatusRequest {
    
    @NotNull(message = "Status is required")
    @Pattern(regexp = "^(hoat_dong|ngung_hoat_dong)$", message = "Status must be either 'hoat_dong' or 'ngung_hoat_dong'")
    private String status;
    
    @Size(max = 500, message = "Reason cannot exceed 500 characters")
    private String reason;
    
    private Boolean notifyUsers = false;
    
    // Constructors
    public UpdateVoucherStatusRequest() {}
    
    public UpdateVoucherStatusRequest(String status) {
        this.status = status;
    }
    
    public UpdateVoucherStatusRequest(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }
    
    // Static factory methods
    public static UpdateVoucherStatusRequest activate() {
        return new UpdateVoucherStatusRequest("hoat_dong");
    }
    
    public static UpdateVoucherStatusRequest deactivate() {
        return new UpdateVoucherStatusRequest("ngung_hoat_dong");
    }
    
    public static UpdateVoucherStatusRequest activate(String reason) {
        return new UpdateVoucherStatusRequest("hoat_dong", reason);
    }
    
    public static UpdateVoucherStatusRequest deactivate(String reason) {
        return new UpdateVoucherStatusRequest("ngung_hoat_dong", reason);
    }
    
    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public Boolean getNotifyUsers() { return notifyUsers; }
    public void setNotifyUsers(Boolean notifyUsers) { this.notifyUsers = notifyUsers; }
    
    // Helper methods
    public boolean isActivating() {
        return "hoat_dong".equals(status);
    }
    
    public boolean isDeactivating() {
        return "ngung_hoat_dong".equals(status);
    }
    
    public boolean hasReason() {
        return reason != null && !reason.trim().isEmpty();
    }
}