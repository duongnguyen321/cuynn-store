package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bien_the_san_pham")
public class ProductVariant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham", nullable = false)
    private Product product;
    
    @Column(name = "sku", unique = true, nullable = false)
    private String sku;
    
    @Column(name = "ten_bien_the")
    private String variantName;
    
    @Column(name = "gia_ban", precision = 12, scale = 2, nullable = false)
    private BigDecimal price;
    
    @Column(name = "gia_goc", precision = 12, scale = 2)
    private BigDecimal originalPrice;
    
    @Column(name = "so_luong_ton", nullable = false)
    private Integer stockQuantity = 0;
    
    @Column(name = "so_luong_da_ban")
    private Integer soldQuantity = 0;
    
    @Column(name = "can_nang")
    private BigDecimal weight;
    
    @Column(name = "chieu_dai")
    private BigDecimal length;
    
    @Column(name = "chieu_rong")
    private BigDecimal width;
    
    @Column(name = "chieu_cao")
    private BigDecimal height;
    
    @Column(name = "hinh_anh")
    private String image;
    
    @Column(name = "vi_tri")
    private String position;
    
    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VariantAttributeValue> variantAttributeValues;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private VariantStatus status = VariantStatus.dang_hoat_dong;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDateTime updatedAt;
    
    public enum VariantStatus {
        dang_hoat_dong, tam_ngung, het_hang
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
    public ProductVariant() {}
    
    public ProductVariant(Product product, String sku, BigDecimal price) {
        this.product = product;
        this.sku = sku;
        this.price = price;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
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
    
    public Integer getSoldQuantity() { return soldQuantity; }
    public void setSoldQuantity(Integer soldQuantity) { this.soldQuantity = soldQuantity; }
    
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
    
    public List<VariantAttributeValue> getVariantAttributeValues() { return variantAttributeValues; }
    public void setVariantAttributeValues(List<VariantAttributeValue> variantAttributeValues) { this.variantAttributeValues = variantAttributeValues; }
    
    public VariantStatus getStatus() { return status; }
    public void setStatus(VariantStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    // Utility methods
    public boolean isInStock() {
        return stockQuantity != null && stockQuantity > 0;
    }
    
    public boolean isActive() {
        return status == VariantStatus.dang_hoat_dong && isInStock();
    }
}