package com.zmen.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "phuong_thuc_thanh_toan")
public class PaymentMethod {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_phuong_thuc", unique = true, nullable = false)
    private String methodName;
    
    // Constructors
    public PaymentMethod() {}
    
    public PaymentMethod(String methodName) {
        this.methodName = methodName;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMethodName() { return methodName; }
    public void setMethodName(String methodName) { this.methodName = methodName; }
}