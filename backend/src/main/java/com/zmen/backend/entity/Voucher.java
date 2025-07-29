package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "voucher")
public class Voucher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ma_voucher", unique = true, nullable = false)
    private String voucherCode;
    
    @Column(name = "tieu_de", nullable = false)
    private String title;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "loai_giam_gia", nullable = false)
    private DiscountType discountType;
    
    @Column(name = "gia_tri_giam", precision = 15, scale = 2, nullable = false)
    private BigDecimal discountValue;
    
    @Column(name = "don_hang_toi_thieu", precision = 15, scale = 2)
    private BigDecimal minimumOrderAmount = BigDecimal.ZERO;
    
    @Column(name = "giam_gia_toi_da", precision = 15, scale = 2)
    private BigDecimal maximumDiscountAmount;
    
    @Column(name = "ngay_bat_dau", nullable = false)
    private LocalDateTime startDate;
    
    @Column(name = "ngay_ket_thuc", nullable = false)
    private LocalDateTime endDate;
    
    @Column(name = "so_luong_ban_dau", nullable = false)
    private Integer initialQuantity;
    
    @Column(name = "so_luong_da_dung", nullable = false)
    private Integer usedQuantity = 0;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private ActivityStatus status = ActivityStatus.hoat_dong;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    public enum DiscountType {
        phan_tram, so_tien
    }
    
    public enum ActivityStatus {
        hoat_dong, ngung_hoat_dong
    }
    
    // Constructors
    public Voucher() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public Voucher(String voucherCode, String title, DiscountType discountType, BigDecimal discountValue) {
        this();
        this.voucherCode = voucherCode;
        this.title = title;
        this.discountType = discountType;
        this.discountValue = discountValue;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getVoucherCode() { return voucherCode; }
    public void setVoucherCode(String voucherCode) { this.voucherCode = voucherCode; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public DiscountType getDiscountType() { return discountType; }
    public void setDiscountType(DiscountType discountType) { this.discountType = discountType; }
    
    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { this.discountValue = discountValue; }
    
    public BigDecimal getMinimumOrderAmount() { return minimumOrderAmount; }
    public void setMinimumOrderAmount(BigDecimal minimumOrderAmount) { this.minimumOrderAmount = minimumOrderAmount; }
    
    public BigDecimal getMaximumDiscountAmount() { return maximumDiscountAmount; }
    public void setMaximumDiscountAmount(BigDecimal maximumDiscountAmount) { this.maximumDiscountAmount = maximumDiscountAmount; }
    
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    
    public Integer getInitialQuantity() { return initialQuantity; }
    public void setInitialQuantity(Integer initialQuantity) { this.initialQuantity = initialQuantity; }
    
    public Integer getUsedQuantity() { return usedQuantity; }
    public void setUsedQuantity(Integer usedQuantity) { this.usedQuantity = usedQuantity; }
    
    public ActivityStatus getStatus() { return status; }
    public void setStatus(ActivityStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    // Utility methods
    public boolean isValid() {
        LocalDateTime now = LocalDateTime.now();
        return status == ActivityStatus.hoat_dong && 
               now.isAfter(startDate) && 
               now.isBefore(endDate) && 
               usedQuantity < initialQuantity;
    }
    
    public boolean canUse() {
        return isValid() && usedQuantity < initialQuantity;
    }
    
    public BigDecimal calculateDiscount(BigDecimal orderAmount) {
        if (!isValid() || orderAmount.compareTo(minimumOrderAmount) < 0) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal discount;
        if (discountType == DiscountType.phan_tram) {
            discount = orderAmount.multiply(discountValue).divide(new BigDecimal(100));
            if (maximumDiscountAmount != null && discount.compareTo(maximumDiscountAmount) > 0) {
                discount = maximumDiscountAmount;
            }
        } else {
            discount = discountValue;
        }
        
        return discount;
    }
    
    public void incrementUsedQuantity() {
        this.usedQuantity++;
    }
}