package com.zmen.backend.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ResendVerificationRequest {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    // Constructors
    public ResendVerificationRequest() {}
    
    public ResendVerificationRequest(String email) {
        this.email = email;
    }
    
    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}