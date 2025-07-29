package com.zmen.backend.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    public void sendWelcomeEmail(String email, String fullName) {
        // Implementation for sending welcome email
        System.out.println("Sending welcome email to: " + email);
    }
    
    public void sendPasswordResetEmail(String email, String fullName, String resetToken) {
        // Implementation for sending password reset email
        System.out.println("Sending password reset email to: " + email);
    }
    
    public void sendPasswordChangedConfirmation(String email, String fullName) {
        // Implementation for sending password changed confirmation
        System.out.println("Sending password changed confirmation to: " + email);
    }
    
    public void sendEmailVerification(String email, String fullName, String verificationToken) {
        // Implementation for sending email verification
        System.out.println("Sending email verification to: " + email);
    }
}