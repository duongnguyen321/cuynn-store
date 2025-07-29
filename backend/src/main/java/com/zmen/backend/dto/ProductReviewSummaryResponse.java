package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ProductReviewSummaryResponse {
    private Long productId;
    private String productName;
    private String productSlug;
    private String productImage;
    private Integer totalReviews;
    private Double averageRating;
    private BigDecimal overallRating;
    private Map<Integer, Integer> ratingBreakdown; // Rating (1-5) -> Count
    private List<ProductReviewResponse> recentReviews;
    private List<ProductReviewResponse> featuredReviews;
    private List<ProductReviewResponse> mostHelpfulReviews;
    private ReviewSummaryStats summaryStats;
    private LocalDateTime lastReviewDate;
    private Boolean canUserReview;
    private Boolean hasUserReviewed;
    
    // Constructors
    public ProductReviewSummaryResponse() {}
    
    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public String getProductSlug() { return productSlug; }
    public void setProductSlug(String productSlug) { this.productSlug = productSlug; }
    
    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }
    
    public Integer getTotalReviews() { return totalReviews; }
    public void setTotalReviews(Integer totalReviews) { this.totalReviews = totalReviews; }
    
    public Double getAverageRating() { return averageRating; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
    
    public BigDecimal getOverallRating() { return overallRating; }
    public void setOverallRating(BigDecimal overallRating) { this.overallRating = overallRating; }
    
    public Map<Integer, Integer> getRatingBreakdown() { return ratingBreakdown; }
    public void setRatingBreakdown(Map<Integer, Integer> ratingBreakdown) { this.ratingBreakdown = ratingBreakdown; }
    
    public List<ProductReviewResponse> getRecentReviews() { return recentReviews; }
    public void setRecentReviews(List<ProductReviewResponse> recentReviews) { this.recentReviews = recentReviews; }
    
    public List<ProductReviewResponse> getFeaturedReviews() { return featuredReviews; }
    public void setFeaturedReviews(List<ProductReviewResponse> featuredReviews) { this.featuredReviews = featuredReviews; }
    
    public List<ProductReviewResponse> getMostHelpfulReviews() { return mostHelpfulReviews; }
    public void setMostHelpfulReviews(List<ProductReviewResponse> mostHelpfulReviews) { this.mostHelpfulReviews = mostHelpfulReviews; }
    
    public ReviewSummaryStats getSummaryStats() { return summaryStats; }
    public void setSummaryStats(ReviewSummaryStats summaryStats) { this.summaryStats = summaryStats; }
    
    public LocalDateTime getLastReviewDate() { return lastReviewDate; }
    public void setLastReviewDate(LocalDateTime lastReviewDate) { this.lastReviewDate = lastReviewDate; }
    
    public Boolean getCanUserReview() { return canUserReview; }
    public void setCanUserReview(Boolean canUserReview) { this.canUserReview = canUserReview; }
    
    public Boolean getHasUserReviewed() { return hasUserReviewed; }
    public void setHasUserReviewed(Boolean hasUserReviewed) { this.hasUserReviewed = hasUserReviewed; }
    
    // Helper class for review summary statistics
    public static class ReviewSummaryStats {
        private Integer reviewsWithImages;
        private Integer verifiedPurchaseReviews;
        private Integer recommendedCount;
        private Double recommendationPercentage;
        private Map<String, Integer> reviewKeywords; // Common words/phrases mentioned
        private Map<String, Double> sentimentAnalysis; // Positive, Negative, Neutral percentages
        
        public ReviewSummaryStats() {}
        
        public Integer getReviewsWithImages() { return reviewsWithImages; }
        public void setReviewsWithImages(Integer reviewsWithImages) { this.reviewsWithImages = reviewsWithImages; }
        
        public Integer getVerifiedPurchaseReviews() { return verifiedPurchaseReviews; }
        public void setVerifiedPurchaseReviews(Integer verifiedPurchaseReviews) { this.verifiedPurchaseReviews = verifiedPurchaseReviews; }
        
        public Integer getRecommendedCount() { return recommendedCount; }
        public void setRecommendedCount(Integer recommendedCount) { this.recommendedCount = recommendedCount; }
        
        public Double getRecommendationPercentage() { return recommendationPercentage; }
        public void setRecommendationPercentage(Double recommendationPercentage) { this.recommendationPercentage = recommendationPercentage; }
        
        public Map<String, Integer> getReviewKeywords() { return reviewKeywords; }
        public void setReviewKeywords(Map<String, Integer> reviewKeywords) { this.reviewKeywords = reviewKeywords; }
        
        public Map<String, Double> getSentimentAnalysis() { return sentimentAnalysis; }
        public void setSentimentAnalysis(Map<String, Double> sentimentAnalysis) { this.sentimentAnalysis = sentimentAnalysis; }
    }
}