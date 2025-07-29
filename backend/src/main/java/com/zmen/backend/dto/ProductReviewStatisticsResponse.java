package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.util.Map;

public class ProductReviewStatisticsResponse {
    private Long productId;
    private String productName;
    private Integer totalReviews;
    private Double averageRating;
    private BigDecimal overallRating;
    private Map<Integer, Integer> ratingDistribution; // Rating (1-5) -> Count
    private Map<Integer, Double> ratingPercentages; // Rating (1-5) -> Percentage
    private Integer totalReviewsWithImages;
    private Integer totalVerifiedReviews;
    private Integer totalRecommendations;
    private Double recommendationRate;
    private Integer totalHelpfulVotes;
    private Integer pendingReviewsCount;
    private Integer approvedReviewsCount;
    private Integer rejectedReviewsCount;
    private ReviewTrends trends;
    
    // Constructors
    public ProductReviewStatisticsResponse() {}
    
    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public Integer getTotalReviews() { return totalReviews; }
    public void setTotalReviews(Integer totalReviews) { this.totalReviews = totalReviews; }
    
    public Double getAverageRating() { return averageRating; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
    
    public BigDecimal getOverallRating() { return overallRating; }
    public void setOverallRating(BigDecimal overallRating) { this.overallRating = overallRating; }
    
    public Map<Integer, Integer> getRatingDistribution() { return ratingDistribution; }
    public void setRatingDistribution(Map<Integer, Integer> ratingDistribution) { this.ratingDistribution = ratingDistribution; }
    
    public Map<Integer, Double> getRatingPercentages() { return ratingPercentages; }
    public void setRatingPercentages(Map<Integer, Double> ratingPercentages) { this.ratingPercentages = ratingPercentages; }
    
    public Integer getTotalReviewsWithImages() { return totalReviewsWithImages; }
    public void setTotalReviewsWithImages(Integer totalReviewsWithImages) { this.totalReviewsWithImages = totalReviewsWithImages; }
    
    public Integer getTotalVerifiedReviews() { return totalVerifiedReviews; }
    public void setTotalVerifiedReviews(Integer totalVerifiedReviews) { this.totalVerifiedReviews = totalVerifiedReviews; }
    
    public Integer getTotalRecommendations() { return totalRecommendations; }
    public void setTotalRecommendations(Integer totalRecommendations) { this.totalRecommendations = totalRecommendations; }
    
    public Double getRecommendationRate() { return recommendationRate; }
    public void setRecommendationRate(Double recommendationRate) { this.recommendationRate = recommendationRate; }
    
    public Integer getTotalHelpfulVotes() { return totalHelpfulVotes; }
    public void setTotalHelpfulVotes(Integer totalHelpfulVotes) { this.totalHelpfulVotes = totalHelpfulVotes; }
    
    public Integer getPendingReviewsCount() { return pendingReviewsCount; }
    public void setPendingReviewsCount(Integer pendingReviewsCount) { this.pendingReviewsCount = pendingReviewsCount; }
    
    public Integer getApprovedReviewsCount() { return approvedReviewsCount; }
    public void setApprovedReviewsCount(Integer approvedReviewsCount) { this.approvedReviewsCount = approvedReviewsCount; }
    
    public Integer getRejectedReviewsCount() { return rejectedReviewsCount; }
    public void setRejectedReviewsCount(Integer rejectedReviewsCount) { this.rejectedReviewsCount = rejectedReviewsCount; }
    
    public ReviewTrends getTrends() { return trends; }
    public void setTrends(ReviewTrends trends) { this.trends = trends; }
    
    // Helper class for review trends
    public static class ReviewTrends {
        private Double monthlyGrowthRate;
        private Integer reviewsThisMonth;
        private Integer reviewsLastMonth;
        private Double averageRatingTrend;
        private String trendDirection; // IMPROVING, DECLINING, STABLE
        
        public ReviewTrends() {}
        
        public Double getMonthlyGrowthRate() { return monthlyGrowthRate; }
        public void setMonthlyGrowthRate(Double monthlyGrowthRate) { this.monthlyGrowthRate = monthlyGrowthRate; }
        
        public Integer getReviewsThisMonth() { return reviewsThisMonth; }
        public void setReviewsThisMonth(Integer reviewsThisMonth) { this.reviewsThisMonth = reviewsThisMonth; }
        
        public Integer getReviewsLastMonth() { return reviewsLastMonth; }
        public void setReviewsLastMonth(Integer reviewsLastMonth) { this.reviewsLastMonth = reviewsLastMonth; }
        
        public Double getAverageRatingTrend() { return averageRatingTrend; }
        public void setAverageRatingTrend(Double averageRatingTrend) { this.averageRatingTrend = averageRatingTrend; }
        
        public String getTrendDirection() { return trendDirection; }
        public void setTrendDirection(String trendDirection) { this.trendDirection = trendDirection; }
    }
}