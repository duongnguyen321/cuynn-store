package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "chi_tiet_hoa_don")
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don", nullable = false)
    private OrderEntity order;
    
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
    
    @Column(name = "tong_gia", precision = 12, scale = 2, nullable = false)
    private BigDecimal totalPrice;
    
    // Constructors
    public OrderItem() {}
    
    public OrderItem(OrderEntity order, Product product, Integer quantity, BigDecimal priceAtTime) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
        this.totalPrice = priceAtTime.multiply(new BigDecimal(quantity));
    }
    
    public OrderItem(OrderEntity order, Product product, ProductVariant productVariant, Integer quantity, BigDecimal priceAtTime) {
        this.order = order;
        this.product = product;
        this.productVariant = productVariant;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
        this.totalPrice = priceAtTime.multiply(new BigDecimal(quantity));
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public OrderEntity getOrder() { return order; }
    public void setOrder(OrderEntity order) { this.order = order; }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public ProductVariant getProductVariant() { return productVariant; }
    public void setProductVariant(ProductVariant productVariant) { this.productVariant = productVariant; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { 
        this.quantity = quantity;
        if (priceAtTime != null) {
            this.totalPrice = priceAtTime.multiply(new BigDecimal(quantity));
        }
    }
    
    public BigDecimal getPriceAtTime() { return priceAtTime; }
    public void setPriceAtTime(BigDecimal priceAtTime) { 
        this.priceAtTime = priceAtTime;
        if (quantity != null) {
            this.totalPrice = priceAtTime.multiply(new BigDecimal(quantity));
        }
    }
    
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}

