package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "thuoc_tinh")
public class ProductAttribute {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_thuoc_tinh", nullable = false)
    private String attributeName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "loai_thuoc_tinh", nullable = false)
    private AttributeType attributeType;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "bat_buoc", nullable = false)
    private Boolean required = false;
    
    @Column(name = "thu_tu_hien_thi")
    private Integer displayOrder = 0;
    
    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttributeValue> attributeValues;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private AttributeStatus status = AttributeStatus.dang_hoat_dong;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDateTime updatedAt;
    
    public enum AttributeType {
        text, number, color, size, select, multi_select
    }
    
    public enum AttributeStatus {
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
    public ProductAttribute() {}
    
    public ProductAttribute(String attributeName, AttributeType attributeType) {
        this.attributeName = attributeName;
        this.attributeType = attributeType;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAttributeName() { return attributeName; }
    public void setAttributeName(String attributeName) { this.attributeName = attributeName; }
    
    public AttributeType getAttributeType() { return attributeType; }
    public void setAttributeType(AttributeType attributeType) { this.attributeType = attributeType; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Boolean getRequired() { return required; }
    public void setRequired(Boolean required) { this.required = required; }
    
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    
    public List<AttributeValue> getAttributeValues() { return attributeValues; }
    public void setAttributeValues(List<AttributeValue> attributeValues) { this.attributeValues = attributeValues; }
    
    public AttributeStatus getStatus() { return status; }
    public void setStatus(AttributeStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}