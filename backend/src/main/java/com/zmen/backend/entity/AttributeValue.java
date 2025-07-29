package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "gia_tri_thuoc_tinh")
public class AttributeValue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_thuoc_tinh", nullable = false)
    private ProductAttribute attribute;
    
    @Column(name = "gia_tri", nullable = false)
    private String value;
    
    @Column(name = "ma_mau")
    private String colorCode;
    
    @Column(name = "hinh_anh")
    private String image;
    
    @Column(name = "thu_tu_hien_thi")
    private Integer displayOrder = 0;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private ValueStatus status = ValueStatus.dang_hoat_dong;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDateTime updatedAt;
    
    public enum ValueStatus {
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
    public AttributeValue() {}
    
    public AttributeValue(ProductAttribute attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }
    
    public AttributeValue(ProductAttribute attribute, String value, String colorCode) {
        this.attribute = attribute;
        this.value = value;
        this.colorCode = colorCode;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public ProductAttribute getAttribute() { return attribute; }
    public void setAttribute(ProductAttribute attribute) { this.attribute = attribute; }
    
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    
    public String getColorCode() { return colorCode; }
    public void setColorCode(String colorCode) { this.colorCode = colorCode; }
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    
    public ValueStatus getStatus() { return status; }
    public void setStatus(ValueStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}