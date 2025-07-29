package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "hang_thanh_vien")
public class MembershipTier {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_hang", nullable = false, unique = true)
    private String tierName;
    
    @Column(name = "diem_toi_thieu", nullable = false)
    private Integer minimumPoints;
    
    @Column(name = "diem_toi_da")
    private Integer maximumPoints;
    
    @Column(name = "phan_tram_giam_gia", precision = 5, scale = 2, nullable = false)
    private BigDecimal discountPercentage = BigDecimal.ZERO;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "mau_sac")
    private String color;
    
    @Column(name = "icon")
    private String icon;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private TierStatus status = TierStatus.dang_hoat_dong;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDateTime updatedAt;
    
    public enum TierStatus {
        dang_hoat_dong, tam_ngung
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
    public MembershipTier() {}
    
    public MembershipTier(String tierName, Integer minimumPoints, BigDecimal discountPercentage) {
        this.tierName = tierName;
        this.minimumPoints = minimumPoints;
        this.discountPercentage = discountPercentage;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTierName() { return tierName; }
    public void setTierName(String tierName) { this.tierName = tierName; }
    
    public Integer getMinimumPoints() { return minimumPoints; }
    public void setMinimumPoints(Integer minimumPoints) { this.minimumPoints = minimumPoints; }
    
    public Integer getMaximumPoints() { return maximumPoints; }
    public void setMaximumPoints(Integer maximumPoints) { this.maximumPoints = maximumPoints; }
    
    public BigDecimal getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(BigDecimal discountPercentage) { this.discountPercentage = discountPercentage; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    
    public TierStatus getStatus() { return status; }
    public void setStatus(TierStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}