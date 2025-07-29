package com.zmen.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "trang_thai_thanh_toan")
public class PaymentStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_trang_thai", unique = true, nullable = false)
    private String statusName;
    
    // Constructors
    public PaymentStatus() {}
    
    public PaymentStatus(String statusName) {
        this.statusName = statusName;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
}