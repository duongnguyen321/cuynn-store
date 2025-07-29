package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "khuyen_mai")
public class Promotion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_khuyen_mai", nullable = false)
    private String promotionName;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "loai_khuyen_mai", nullable = false)
    private PromotionType promotionType;
    
    @Column(name = "dieu_kien", columnDefinition = "jsonb", nullable = false)
    private String conditions;
    
    @Column(name = "hanh_dong", columnDefinition = "jsonb", nullable = false)
    private String actions;
    
    @Column(name = "ngay_bat_dau", nullable = false)
    private LocalDateTime startDate;
    
    @Column(name = "ngay_ket_thuc", nullable = false)
    private LocalDateTime endDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private ActivityStatus status = ActivityStatus.hoat_dong;
    
    public enum PromotionType {
        GIAM_GIA_DON_HANG, MUA_X_TANG_Y, GIAM_GIA_SAN_PHAM, MIEN_PHI_VAN_CHUYEN
    }
    
    public enum ActivityStatus {
        hoat_dong, ngung_hoat_dong
    }
    
    // Constructors
    public Promotion() {}
    
    public Promotion(String promotionName, PromotionType promotionType, String conditions, String actions) {
        this.promotionName = promotionName;
        this.promotionType = promotionType;
        this.conditions = conditions;
        this.actions = actions;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPromotionName() { return promotionName; }
    public void setPromotionName(String promotionName) { this.promotionName = promotionName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public PromotionType getPromotionType() { return promotionType; }
    public void setPromotionType(PromotionType promotionType) { this.promotionType = promotionType; }
    
    public String getConditions() { return conditions; }
    public void setConditions(String conditions) { this.conditions = conditions; }
    
    public String getActions() { return actions; }
    public void setActions(String actions) { this.actions = actions; }
    
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    
    public ActivityStatus getStatus() { return status; }
    public void setStatus(ActivityStatus status) { this.status = status; }
    
    // Utility methods
    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        return status == ActivityStatus.hoat_dong && 
               now.isAfter(startDate) && 
               now.isBefore(endDate);
    }
}