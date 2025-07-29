package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "chi_tiet_gio_hang")
public class CartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gio_hang", nullable = false)
    private ShoppingCart cart;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham", nullable = false)
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bien_the")
    private ProductVariant productVariant;
    
    @Column(name = "so_luong", nullable = false)
    private Integer quantity;
    
    @Column(name = "gia_tai_thoi_diem", precision = 12, scale = 2, nullable = false)
    private BigDecimal priceAtTime;
    
    @Column(name = "ngay_them", nullable = false)
    private LocalDateTime addedAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        addedAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public CartItem() {}
    
    public CartItem(ShoppingCart cart, Product product, Integer quantity, BigDecimal priceAtTime) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
    }
    
    public CartItem(ShoppingCart cart, Product product, ProductVariant productVariant, Integer quantity, BigDecimal priceAtTime) {
        this.cart = cart;
        this.product = product;
        this.productVariant = productVariant;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public ShoppingCart getCart() { return cart; }
    public void setCart(ShoppingCart cart) { this.cart = cart; }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public ProductVariant getProductVariant() { return productVariant; }
    public void setProductVariant(ProductVariant productVariant) { this.productVariant = productVariant; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public BigDecimal getPriceAtTime() { return priceAtTime; }
    public void setPriceAtTime(BigDecimal priceAtTime) { this.priceAtTime = priceAtTime; }
    
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    // Utility methods
    public BigDecimal getTotalPrice() {
        return priceAtTime.multiply(new BigDecimal(quantity));
    }
}

