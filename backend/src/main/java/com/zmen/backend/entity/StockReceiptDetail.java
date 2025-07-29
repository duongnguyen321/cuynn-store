package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "chi_tiet_phieu_nhap")
public class StockReceiptDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phieu_nhap", nullable = false)
    private StockReceipt stockReceipt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bien_the", nullable = false)
    private ProductVariant productVariant;
    
    @Column(name = "so_luong_nhap", nullable = false)
    private Integer quantity;
    
    @Column(name = "don_gia_nhap", precision = 15, scale = 2, nullable = false)
    private BigDecimal unitPrice;
    
    @Column(name = "thanh_tien", precision = 15, scale = 2, nullable = false)
    private BigDecimal subtotal;
    
    @PrePersist
    @PreUpdate
    protected void calculateSubtotal() {
        if (quantity != null && unitPrice != null) {
            subtotal = unitPrice.multiply(new BigDecimal(quantity));
        }
    }
    
    // Constructors
    public StockReceiptDetail() {}
    
    public StockReceiptDetail(StockReceipt stockReceipt, ProductVariant productVariant, Integer quantity, BigDecimal unitPrice) {
        this.stockReceipt = stockReceipt;
        this.productVariant = productVariant;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        calculateSubtotal();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public StockReceipt getStockReceipt() { return stockReceipt; }
    public void setStockReceipt(StockReceipt stockReceipt) { this.stockReceipt = stockReceipt; }
    
    public ProductVariant getProductVariant() { return productVariant; }
    public void setProductVariant(ProductVariant productVariant) { this.productVariant = productVariant; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { 
        this.quantity = quantity;
        calculateSubtotal();
    }
    
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { 
        this.unitPrice = unitPrice;
        calculateSubtotal();
    }
    
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}