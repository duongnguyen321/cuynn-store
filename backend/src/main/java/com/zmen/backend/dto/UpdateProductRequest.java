package com.zmen.backend.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class UpdateProductRequest {
    
    private String name;
    
    private String slug;
    
    private String description;
    
    private String shortDescription;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;
    
    private BigDecimal originalPrice;
    
    private BigDecimal discountPercentage;
    
    private Long categoryId;
    
    private Long brandId;
    
    private List<String> images;
    
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;
    
    private String status;
    
    private Boolean featured;
    
    private Boolean isNew;
    
    private Boolean bestSeller;
    
    private BigDecimal weight;
    
    private BigDecimal length;
    
    private BigDecimal width;
    
    private BigDecimal height;
    
    private String origin;
    
    private String material;
    
    private String warranty;
    
    private Map<String, Object> attributes;
    
    private List<UpdateProductVariantRequest> variants;
    
    // Constructors
    public UpdateProductRequest() {}
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public BigDecimal getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(BigDecimal originalPrice) { this.originalPrice = originalPrice; }
    
    public BigDecimal getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(BigDecimal discountPercentage) { this.discountPercentage = discountPercentage; }
    
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    
    public Long getBrandId() { return brandId; }
    public void setBrandId(Long brandId) { this.brandId = brandId; }
    
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
    
    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Boolean getFeatured() { return featured; }
    public void setFeatured(Boolean featured) { this.featured = featured; }
    
    public Boolean getIsNew() { return isNew; }
    public void setIsNew(Boolean isNew) { this.isNew = isNew; }
    
    public Boolean getBestSeller() { return bestSeller; }
    public void setBestSeller(Boolean bestSeller) { this.bestSeller = bestSeller; }
    
    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }
    
    public BigDecimal getLength() { return length; }
    public void setLength(BigDecimal length) { this.length = length; }
    
    public BigDecimal getWidth() { return width; }
    public void setWidth(BigDecimal width) { this.width = width; }
    
    public BigDecimal getHeight() { return height; }
    public void setHeight(BigDecimal height) { this.height = height; }
    
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    
    public String getWarranty() { return warranty; }
    public void setWarranty(String warranty) { this.warranty = warranty; }
    
    public Map<String, Object> getAttributes() { return attributes; }
    public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }
    
    public List<UpdateProductVariantRequest> getVariants() { return variants; }
    public void setVariants(List<UpdateProductVariantRequest> variants) { this.variants = variants; }
    
    // Helper class for updating product variants
    public static class UpdateProductVariantRequest {
        private Long id; // null for new variants
        
        private String sku;
        
        private String variantName;
        
        @DecimalMin(value = "0.0", inclusive = false, message = "Variant price must be greater than 0")
        private BigDecimal price;
        
        private BigDecimal originalPrice;
        
        @Min(value = 0, message = "Stock quantity cannot be negative")
        private Integer stockQuantity;
        
        private BigDecimal weight;
        private BigDecimal length;
        private BigDecimal width;
        private BigDecimal height;
        private String image;
        private String position;
        private String status;
        private Map<String, Object> attributes;
        
        public UpdateProductVariantRequest() {}
        
        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        
        public String getSku() { return sku; }
        public void setSku(String sku) { this.sku = sku; }
        
        public String getVariantName() { return variantName; }
        public void setVariantName(String variantName) { this.variantName = variantName; }
        
        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }
        
        public BigDecimal getOriginalPrice() { return originalPrice; }
        public void setOriginalPrice(BigDecimal originalPrice) { this.originalPrice = originalPrice; }
        
        public Integer getStockQuantity() { return stockQuantity; }
        public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
        
        public BigDecimal getWeight() { return weight; }
        public void setWeight(BigDecimal weight) { this.weight = weight; }
        
        public BigDecimal getLength() { return length; }
        public void setLength(BigDecimal length) { this.length = length; }
        
        public BigDecimal getWidth() { return width; }
        public void setWidth(BigDecimal width) { this.width = width; }
        
        public BigDecimal getHeight() { return height; }
        public void setHeight(BigDecimal height) { this.height = height; }
        
        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
        
        public String getPosition() { return position; }
        public void setPosition(String position) { this.position = position; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        public Map<String, Object> getAttributes() { return attributes; }
        public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }
    }
}