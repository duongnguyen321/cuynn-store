package com.zmen.backend.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

public class UpdateReviewRequest {
    
    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private Integer rating;
    
    private String title;
    
    private String content;
    
    private String advantages;
    
    private String disadvantages;
    
    private List<String> reviewImages;
    
    private Boolean isRecommended;
    
    // Constructors
    public UpdateReviewRequest() {}
    
    public UpdateReviewRequest(Integer rating, String title, String content) {
        this.rating = rating;
        this.title = title;
        this.content = content;
    }
    
    // Getters and Setters
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public String getAdvantages() { return advantages; }
    public void setAdvantages(String advantages) { this.advantages = advantages; }
    
    public String getDisadvantages() { return disadvantages; }
    public void setDisadvantages(String disadvantages) { this.disadvantages = disadvantages; }
    
    public List<String> getReviewImages() { return reviewImages; }
    public void setReviewImages(List<String> reviewImages) { this.reviewImages = reviewImages; }
    
    public Boolean getIsRecommended() { return isRecommended; }
    public void setIsRecommended(Boolean isRecommended) { this.isRecommended = isRecommended; }
}