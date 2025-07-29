package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lich_su_diem_tich_luy")
public class LoyaltyPointHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung", nullable = false)
    private User user;
    
    @Column(name = "diem_thay_doi", nullable = false)
    private Integer pointsChanged;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "loai_giao_dich", nullable = false)
    private TransactionType transactionType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don")
    private Order order;
    
    @Column(name = "ghi_chu", columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    public enum TransactionType {
        mua_hang, su_dung_diem, thang_hang, dieu_chinh_boi_admin, tra_hang
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public LoyaltyPointHistory() {}
    
    public LoyaltyPointHistory(User user, Integer pointsChanged, TransactionType transactionType) {
        this.user = user;
        this.pointsChanged = pointsChanged;
        this.transactionType = transactionType;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Integer getPointsChanged() { return pointsChanged; }
    public void setPointsChanged(Integer pointsChanged) { this.pointsChanged = pointsChanged; }
    
    public TransactionType getTransactionType() { return transactionType; }
    public void setTransactionType(TransactionType transactionType) { this.transactionType = transactionType; }
    
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}