package com.zmen.backend.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddReviewRequest {
    
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private Integer rating;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Comment is required")
    private String comment;
    
    // Constructors
    public AddReviewRequest() {}
    
    public AddReviewRequest(Integer rating, String title, String comment) {
        this.rating = rating;
        this.title = title;
        this.comment = comment;
    }
    
    // Getters and Setters
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}