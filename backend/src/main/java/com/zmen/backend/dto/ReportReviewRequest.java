package com.zmen.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReportReviewRequest {
    
    @NotNull(message = "Review ID is required")
    private Long reviewId;
    
    @NotBlank(message = "Report reason is required")
    private String reason;
    
    @NotBlank(message = "Report category is required")
    private String category; // SPAM, INAPPROPRIATE, FAKE, OFFENSIVE, OTHER
    
    private String description;
    
    private String reporterEmail;
    
    private String reporterName;
    
    // Constructors
    public ReportReviewRequest() {}
    
    public ReportReviewRequest(Long reviewId, String reason, String category) {
        this.reviewId = reviewId;
        this.reason = reason;
        this.category = category;
    }
    
    public ReportReviewRequest(Long reviewId, String reason, String category, String description) {
        this.reviewId = reviewId;
        this.reason = reason;
        this.category = category;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getReviewId() { return reviewId; }
    public void setReviewId(Long reviewId) { this.reviewId = reviewId; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getReporterEmail() { return reporterEmail; }
    public void setReporterEmail(String reporterEmail) { this.reporterEmail = reporterEmail; }
    
    public String getReporterName() { return reporterName; }
    public void setReporterName(String reporterName) { this.reporterName = reporterName; }
}