package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lich_su_email")
public class EmailHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email_nguoi_nhan", nullable = false)
    private String recipientEmail;
    
    @Column(name = "tieu_de", nullable = false)
    private String subject;
    
    @Column(name = "noi_dung_html", columnDefinition = "TEXT", nullable = false)
    private String htmlContent;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "loai_email", nullable = false)
    private EmailType emailType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private EmailStatus status = EmailStatus.cho_gui;
    
    @Column(name = "thong_bao_loi", columnDefinition = "TEXT")
    private String errorMessage;
    
    @Column(name = "ngay_gui")
    private LocalDateTime sentAt;
    
    @Column(name = "ngay_mo")
    private LocalDateTime openedAt;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    public enum EmailType {
        XAC_NHAN_DON_HANG, CAP_NHAT_GIAO_HANG, QUEN_MAT_KHAU, MARKETING, THONG_BAO_KHAC
    }
    
    public enum EmailStatus {
        cho_gui, dang_gui, da_gui, that_bai
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public EmailHistory() {}
    
    public EmailHistory(String recipientEmail, String subject, String htmlContent, EmailType emailType) {
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.htmlContent = htmlContent;
        this.emailType = emailType;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getRecipientEmail() { return recipientEmail; }
    public void setRecipientEmail(String recipientEmail) { this.recipientEmail = recipientEmail; }
    
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    
    public String getHtmlContent() { return htmlContent; }
    public void setHtmlContent(String htmlContent) { this.htmlContent = htmlContent; }
    
    public EmailType getEmailType() { return emailType; }
    public void setEmailType(EmailType emailType) { this.emailType = emailType; }
    
    public EmailStatus getStatus() { return status; }
    public void setStatus(EmailStatus status) { this.status = status; }
    
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
    
    public LocalDateTime getOpenedAt() { return openedAt; }
    public void setOpenedAt(LocalDateTime openedAt) { this.openedAt = openedAt; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    // Utility methods
    public void markAsSent() {
        this.status = EmailStatus.da_gui;
        this.sentAt = LocalDateTime.now();
    }
    
    public void markAsOpened() {
        this.openedAt = LocalDateTime.now();
    }
    
    public void markAsFailed(String errorMessage) {
        this.status = EmailStatus.that_bai;
        this.errorMessage = errorMessage;
    }
}