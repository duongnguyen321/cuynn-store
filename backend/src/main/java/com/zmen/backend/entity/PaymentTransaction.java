package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lich_su_giao_dich_thanh_toan")
public class PaymentTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don", nullable = false)
    private Order order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phuong_thuc", nullable = false)
    private PaymentMethod paymentMethod;
    
    @Column(name = "so_tien", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "ma_giao_dich", unique = true, nullable = false, length = 100)
    private String transactionCode;
    
    @Column(name = "noi_dung", columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private TransactionStatus status = TransactionStatus.dang_xu_ly;
    
    @Column(name = "thoi_gian_giao_dich", nullable = false)
    private LocalDateTime transactionTime = LocalDateTime.now();
    
    @Column(name = "thong_tin_ngan_hang", columnDefinition = "TEXT")
    private String bankInfo;
    
    @Column(name = "ma_tham_chieu_ngan_hang", length = 100)
    private String bankReferenceCode;
    
    @Column(name = "phi_giao_dich", precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private BigDecimal transactionFee = BigDecimal.ZERO;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    public enum TransactionStatus {
        dang_xu_ly, thanh_cong, that_bai, huy_bo, hoan_tien
    }
    
    // Constructors
    public PaymentTransaction() {}
    
    public PaymentTransaction(Order order, PaymentMethod paymentMethod, BigDecimal amount, String transactionCode) {
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.transactionCode = transactionCode;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    
    public String getTransactionCode() { return transactionCode; }
    public void setTransactionCode(String transactionCode) { this.transactionCode = transactionCode; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }
    
    public LocalDateTime getTransactionTime() { return transactionTime; }
    public void setTransactionTime(LocalDateTime transactionTime) { this.transactionTime = transactionTime; }
    
    public String getBankInfo() { return bankInfo; }
    public void setBankInfo(String bankInfo) { this.bankInfo = bankInfo; }
    
    public String getBankReferenceCode() { return bankReferenceCode; }
    public void setBankReferenceCode(String bankReferenceCode) { this.bankReferenceCode = bankReferenceCode; }
    
    public BigDecimal getTransactionFee() { return transactionFee; }
    public void setTransactionFee(BigDecimal transactionFee) { this.transactionFee = transactionFee; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}