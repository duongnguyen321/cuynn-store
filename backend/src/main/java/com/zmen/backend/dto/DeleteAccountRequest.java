package com.zmen.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DeleteAccountRequest {
    
    @NotBlank(message = "Password is required to delete account")
    private String password;
    
    @NotBlank(message = "Reason for deletion is required")
    @Size(max = 500, message = "Reason must not exceed 500 characters")
    private String reason;
    
    @Size(max = 1000, message = "Additional feedback must not exceed 1000 characters")
    private String feedback;
    
    private boolean deleteAllData = true; // Whether to delete all user data
    private boolean unsubscribeFromEmails = true;
    private boolean transferOrderHistory = false; // Whether to keep order history anonymized
    
    // Confirmation fields
    private boolean confirmDeletion = false;
    private boolean understandConsequences = false;
    private String confirmationText; // User must type "DELETE" to confirm
    
    // Constructors
    public DeleteAccountRequest() {}
    
    public DeleteAccountRequest(String password, String reason) {
        this.password = password;
        this.reason = reason;
    }
    
    // Getters and Setters
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    
    public boolean isDeleteAllData() { return deleteAllData; }
    public void setDeleteAllData(boolean deleteAllData) { this.deleteAllData = deleteAllData; }
    
    public boolean isUnsubscribeFromEmails() { return unsubscribeFromEmails; }
    public void setUnsubscribeFromEmails(boolean unsubscribeFromEmails) { this.unsubscribeFromEmails = unsubscribeFromEmails; }
    
    public boolean isTransferOrderHistory() { return transferOrderHistory; }
    public void setTransferOrderHistory(boolean transferOrderHistory) { this.transferOrderHistory = transferOrderHistory; }
    
    public boolean isConfirmDeletion() { return confirmDeletion; }
    public void setConfirmDeletion(boolean confirmDeletion) { this.confirmDeletion = confirmDeletion; }
    
    public boolean isUnderstandConsequences() { return understandConsequences; }
    public void setUnderstandConsequences(boolean understandConsequences) { this.understandConsequences = understandConsequences; }
    
    public String getConfirmationText() { return confirmationText; }
    public void setConfirmationText(String confirmationText) { this.confirmationText = confirmationText; }
    
    // Validation methods
    public boolean isValidConfirmation() {
        return confirmDeletion && understandConsequences && "DELETE".equals(confirmationText);
    }
    
    public boolean hasValidReason() {
        return reason != null && !reason.trim().isEmpty() && reason.length() <= 500;
    }
}