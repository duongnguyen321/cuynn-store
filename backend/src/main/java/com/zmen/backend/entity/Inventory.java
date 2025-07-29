package com.zmen.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "ton_kho")
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bien_the", nullable = false)
    private ProductVariant productVariant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cua_hang", nullable = false)
    private Store store;
    
    @Column(name = "so_luong", nullable = false)
    private Integer quantity = 0;
    
    // Constructors
    public Inventory() {}
    
    public Inventory(ProductVariant productVariant, Store store, Integer quantity) {
        this.productVariant = productVariant;
        this.store = store;
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public ProductVariant getProductVariant() { return productVariant; }
    public void setProductVariant(ProductVariant productVariant) { this.productVariant = productVariant; }
    
    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    // Utility methods
    public boolean isInStock() {
        return quantity != null && quantity > 0;
    }
    
    public void addQuantity(Integer amount) {
        this.quantity += amount;
    }
    
    public void subtractQuantity(Integer amount) {
        this.quantity = Math.max(0, this.quantity - amount);
    }
}