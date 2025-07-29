package com.zmen.backend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "danh_sach_yeu_thich")
public class Wishlist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "id_nguoi_dung", nullable = false)
    private Long userId;
    
    @Column(name = "id_san_pham", nullable = false)
    private Long productId;
    
    @Column(name = "ngay_tao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    // Constructors
    public Wishlist() {
        this.createdAt = new Date();
    }
    
    public Wishlist(Long userId, Long productId) {
        this();
        this.userId = userId;
        this.productId = productId;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}

