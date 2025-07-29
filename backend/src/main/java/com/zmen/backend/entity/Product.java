package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "san_pham")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_san_pham", nullable = false)
    private String name;
    
    @Column(name = "slug", unique = true)
    private String slug;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "mo_ta_ngan")
    private String shortDescription;
    
    @Column(name = "gia_ban", precision = 12, scale = 2, nullable = false)
    private BigDecimal price;
    
    @Column(name = "gia_goc", precision = 12, scale = 2)
    private BigDecimal originalPrice;
    
    @Column(name = "phan_tram_giam_gia", precision = 5, scale = 2)
    private BigDecimal discountPercentage;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_danh_muc")
    private Category category;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_thuong_hieu")
    private Brand brand;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productImages;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductVariant> productVariants;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductReview> productReviews;
    
    @Column(name = "diem_danh_gia", precision = 3, scale = 1)
    private BigDecimal rating = BigDecimal.ZERO;
    
    @Column(name = "so_luot_danh_gia")
    private Integer reviewsCount = 0;
    
    @Column(name = "so_luong_ton")
    private Integer stockQuantity = 0;
    
    @Column(name = "so_luong_da_ban")
    private Integer soldQuantity = 0;
    
    @Column(name = "so_luot_xem")
    private Integer viewCount = 0;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private ProductStatus status = ProductStatus.dang_hoat_dong;
    
    @Column(name = "la_noi_bat", nullable = false)
    private Boolean featured = false;
    
    @Column(name = "la_san_pham_moi", nullable = false)
    private Boolean isNew = false;
    
    @Column(name = "la_ban_chay", nullable = false)
    private Boolean bestSeller = false;
    
    @Column(name = "can_nang")
    private BigDecimal weight;
    
    @Column(name = "chieu_dai")
    private BigDecimal length;
    
    @Column(name = "chieu_rong")
    private BigDecimal width;
    
    @Column(name = "chieu_cao")
    private BigDecimal height;
    
    @Column(name = "xuat_xu")
    private String origin;
    
    @Column(name = "chat_lieu")
    private String material;
    
    @Column(name = "bao_hanh")
    private String warranty;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDateTime updatedAt;
    
    public enum ProductStatus {
        dang_hoat_dong, tam_ngung, het_hang, ngung_kinh_doanh
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (slug == null || slug.isEmpty()) {
            slug = generateSlug(name);
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    private String generateSlug(String name) {
        if (name == null) return "";
        return name.toLowerCase()
                  .replaceAll("[^a-z0-9\\s-]", "")
                  .replaceAll("\\s+", "-")
                  .replaceAll("-+", "-")
                  .replaceAll("^-|-$", "");
    }
    
    // Constructors
    public Product() {}
    
    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public BigDecimal getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(BigDecimal originalPrice) { this.originalPrice = originalPrice; }
    
    public BigDecimal getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(BigDecimal discountPercentage) { this.discountPercentage = discountPercentage; }
    
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
    
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    
    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }
    
    public List<ProductImage> getProductImages() { return productImages; }
    public void setProductImages(List<ProductImage> productImages) { this.productImages = productImages; }
    
    public List<ProductVariant> getProductVariants() { return productVariants; }
    public void setProductVariants(List<ProductVariant> productVariants) { this.productVariants = productVariants; }
    
    public List<ProductReview> getProductReviews() { return productReviews; }
    public void setProductReviews(List<ProductReview> productReviews) { this.productReviews = productReviews; }
    
    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }
    
    public Integer getReviewsCount() { return reviewsCount; }
    public void setReviewsCount(Integer reviewsCount) { this.reviewsCount = reviewsCount; }
    
    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
    
    public Integer getSoldQuantity() { return soldQuantity; }
    public void setSoldQuantity(Integer soldQuantity) { this.soldQuantity = soldQuantity; }
    
    public Integer getViewCount() { return viewCount; }
    public void setViewCount(Integer viewCount) { this.viewCount = viewCount; }
    
    public ProductStatus getStatus() { return status; }
    public void setStatus(ProductStatus status) { this.status = status; }
    
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
    
    public Boolean getFeatured() { return featured; }
    public void setFeatured(Boolean featured) { this.featured = featured; }
    
    public Boolean getIsNew() { return isNew; }
    public void setIsNew(Boolean isNew) { this.isNew = isNew; }
    
    public Boolean getBestSeller() { return bestSeller; }
    public void setBestSeller(Boolean bestSeller) { this.bestSeller = bestSeller; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

