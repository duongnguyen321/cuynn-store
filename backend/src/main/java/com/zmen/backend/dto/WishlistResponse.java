package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class WishlistResponse {
    
    private Long userId;
    private Integer totalItems;
    private LocalDateTime lastUpdated;
    private List<WishlistItem> items;
    
    // Inner class for wishlist items
    public static class WishlistItem {
        private Long id;
        private Long productId;
        private String productName;
        private String productSlug;
        private String productDescription;
        private String mainImage;
        private List<String> images;
        private BigDecimal currentPrice;
        private BigDecimal originalPrice;
        private BigDecimal discountAmount;
        private Integer discountPercentage;
        private String brandName;
        private String categoryName;
        private boolean inStock;
        private Integer availableQuantity;
        private boolean onSale;
        private boolean isNew;
        private Double rating;
        private Integer reviewCount;
        private LocalDateTime addedAt;
        private List<String> availableSizes;
        private List<String> availableColors;
        
        // Constructors
        public WishlistItem() {}
        
        public WishlistItem(Long id, Long productId, String productName, BigDecimal currentPrice, LocalDateTime addedAt) {
            this.id = id;
            this.productId = productId;
            this.productName = productName;
            this.currentPrice = currentPrice;
            this.addedAt = addedAt;
        }
        
        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        
        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }
        
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        
        public String getProductSlug() { return productSlug; }
        public void setProductSlug(String productSlug) { this.productSlug = productSlug; }
        
        public String getProductDescription() { return productDescription; }
        public void setProductDescription(String productDescription) { this.productDescription = productDescription; }
        
        public String getMainImage() { return mainImage; }
        public void setMainImage(String mainImage) { this.mainImage = mainImage; }
        
        public List<String> getImages() { return images; }
        public void setImages(List<String> images) { this.images = images; }
        
        public BigDecimal getCurrentPrice() { return currentPrice; }
        public void setCurrentPrice(BigDecimal currentPrice) { this.currentPrice = currentPrice; }
        
        public BigDecimal getOriginalPrice() { return originalPrice; }
        public void setOriginalPrice(BigDecimal originalPrice) { this.originalPrice = originalPrice; }
        
        public BigDecimal getDiscountAmount() { return discountAmount; }
        public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
        
        public Integer getDiscountPercentage() { return discountPercentage; }
        public void setDiscountPercentage(Integer discountPercentage) { this.discountPercentage = discountPercentage; }
        
        public String getBrandName() { return brandName; }
        public void setBrandName(String brandName) { this.brandName = brandName; }
        
        public String getCategoryName() { return categoryName; }
        public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
        
        public boolean isInStock() { return inStock; }
        public void setInStock(boolean inStock) { this.inStock = inStock; }
        
        public Integer getAvailableQuantity() { return availableQuantity; }
        public void setAvailableQuantity(Integer availableQuantity) { this.availableQuantity = availableQuantity; }
        
        public boolean isOnSale() { return onSale; }
        public void setOnSale(boolean onSale) { this.onSale = onSale; }
        
        public boolean isNew() { return isNew; }
        public void setNew(boolean isNew) { this.isNew = isNew; }
        
        public Double getRating() { return rating; }
        public void setRating(Double rating) { this.rating = rating; }
        
        public Integer getReviewCount() { return reviewCount; }
        public void setReviewCount(Integer reviewCount) { this.reviewCount = reviewCount; }
        
        public LocalDateTime getAddedAt() { return addedAt; }
        public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
        
        public List<String> getAvailableSizes() { return availableSizes; }
        public void setAvailableSizes(List<String> availableSizes) { this.availableSizes = availableSizes; }
        
        public List<String> getAvailableColors() { return availableColors; }
        public void setAvailableColors(List<String> availableColors) { this.availableColors = availableColors; }
    }
    
    // Constructors
    public WishlistResponse() {}
    
    public WishlistResponse(Long userId, Integer totalItems) {
        this.userId = userId;
        this.totalItems = totalItems;
    }
    
    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }
    
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    
    public List<WishlistItem> getItems() { return items; }
    public void setItems(List<WishlistItem> items) { this.items = items; }
}