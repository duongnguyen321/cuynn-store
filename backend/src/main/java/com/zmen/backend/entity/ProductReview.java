package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "danh_gia")
public class ProductReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham", nullable = false)
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don")
    private OrderEntity order;
    
    @Column(name = "diem_danh_gia", nullable = false)
    private Integer rating;
    
    @Column(name = "tieu_de")
    private String title;
    
    @Column(name = "noi_dung", columnDefinition = "TEXT")
    private String content;
    
    @Column(name = "uu_diem")
    private String advantages;
    
    @Column(name = "nhuoc_diem")
    private String disadvantages;
    
    @Column(name = "co_mua_hang", nullable = false)
    private Boolean isPurchased = false;
    
    @Column(name = "co_kem_hinh_anh", nullable = false)
    private Boolean hasImages = false;
    
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewImage> reviewImages;
    
    @Column(name = "so_luot_thich")
    private Integer likeCount = 0;
    
    @Column(name = "so_luot_khong_thich")
    private Integer dislikeCount = 0;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private ReviewStatus status = ReviewStatus.cho_duyet;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDateTime updatedAt;
    
    @Column(name = "ngay_duyet")
    private LocalDateTime approvedAt;
    
    public enum ReviewStatus {
        cho_duyet, da_duyet, bi_tu_choi, da_xoa
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public ProductReview() {}
    
    public ProductReview(Product product, User user, Integer rating, String content) {
        this.product = product;
        this.user = user;
        this.rating = rating;
        this.content = content;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public OrderEntity getOrder() { return order; }
    public void setOrder(OrderEntity order) { this.order = order; }
    
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
    
    public Boolean getIsPurchased() { return isPurchased; }
    public void setIsPurchased(Boolean isPurchased) { this.isPurchased = isPurchased; }
    
    public Boolean getHasImages() { return hasImages; }
    public void setHasImages(Boolean hasImages) { this.hasImages = hasImages; }
    
    public List<ReviewImage> getReviewImages() { return reviewImages; }
    public void setReviewImages(List<ReviewImage> reviewImages) { this.reviewImages = reviewImages; }
    
    public Integer getLikeCount() { return likeCount; }
    public void setLikeCount(Integer likeCount) { this.likeCount = likeCount; }
    
    public Integer getDislikeCount() { return dislikeCount; }
    public void setDislikeCount(Integer dislikeCount) { this.dislikeCount = dislikeCount; }
    
    public ReviewStatus getStatus() { return status; }
    public void setStatus(ReviewStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public LocalDateTime getApprovedAt() { return approvedAt; }
    public void setApprovedAt(LocalDateTime approvedAt) { this.approvedAt = approvedAt; }
    
    // Utility methods
    public boolean isApproved() {
        return status == ReviewStatus.da_duyet;
    }
    
    public void approve() {
        this.status = ReviewStatus.da_duyet;
        this.approvedAt = LocalDateTime.now();
    }
}