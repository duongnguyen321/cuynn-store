package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "giao_dich_hoan_tien")
public class RefundTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_giao_dich_goc", nullable = false)
    private PaymentTransaction originalTransaction;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don", nullable = false)
    private Order order;
    
    @Column(name = "so_tien_hoan", nullable = false, precision = 15, scale = 2)
    private BigDecimal refundAmount;
    
    @Column(name = "ma_giao_dich_hoan", unique = true, nullable = false, length = 100)
    private String refundTransactionCode;
    
    @Column(name = "ly_do_hoan", columnDefinition = "TEXT")
    private String refundReason;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private RefundStatus status = RefundStatus.dang_xu_ly;
    
    @Column(name = "thoi_gian_yeu_cau", nullable = false)
    private LocalDateTime requestTime = LocalDateTime.now();
    
    @Column(name = "thoi_gian_xu_ly")
    private LocalDateTime processTime;
    
    @Column(name = "thong_tin_ngan_hang", columnDefinition = "TEXT")
    private String bankInfo;
    
    @Column(name = "ma_tham_chieu_hoan_tien", length = 100)
    private String refundReferenceCode;
    
    @Column(name = "phi_hoan_tien", precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private BigDecimal refundFee = BigDecimal.ZERO;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_xu_ly")
    private User processedBy;
    
    @Column(name = "ghi_chu_noi_bo", columnDefinition = "TEXT")
    private String internalNotes;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    public enum RefundStatus {
        dang_xu_ly, da_duyet, da_hoan_tien, tu_choi, huy_bo
    }
    
    // Constructors
    public RefundTransaction() {}
    
    public RefundTransaction(PaymentTransaction originalTransaction, Order order, BigDecimal refundAmount, String refundReason) {
        this.originalTransaction = originalTransaction;
        this.order = order;
        this.refundAmount = refundAmount;
        this.refundReason = refundReason;
        this.refundTransactionCode = generateRefundCode();
    }
    
    // Business methods
    private String generateRefundCode() {
        return "RF" + System.currentTimeMillis();
    }
    
    public void approve(User approver) {
        this.status = RefundStatus.da_duyet;
        this.processedBy = approver;
        this.processTime = LocalDateTime.now();
    }
    
    public void complete() {
        this.status = RefundStatus.da_hoan_tien;
        this.processTime = LocalDateTime.now();
    }
    
    public void reject(User rejector, String reason) {
        this.status = RefundStatus.tu_choi;
        this.processedBy = rejector;
        this.processTime = LocalDateTime.now();
        this.internalNotes = reason;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public PaymentTransaction getOriginalTransaction() { return originalTransaction; }
    public void setOriginalTransaction(PaymentTransaction originalTransaction) { this.originalTransaction = originalTransaction; }
    
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    
    public BigDecimal getRefundAmount() { return refundAmount; }
    public void setRefundAmount(BigDecimal refundAmount) { this.refundAmount = refundAmount; }
    
    public String getRefundTransactionCode() { return refundTransactionCode; }
    public void setRefundTransactionCode(String refundTransactionCode) { this.refundTransactionCode = refundTransactionCode; }
    
    public String getRefundReason() { return refundReason; }
    public void setRefundReason(String refundReason) { this.refundReason = refundReason; }
    
    public RefundStatus getStatus() { return status; }
    public void setStatus(RefundStatus status) { this.status = status; }
    
    public LocalDateTime getRequestTime() { return requestTime; }
    public void setRequestTime(LocalDateTime requestTime) { this.requestTime = requestTime; }
    
    public LocalDateTime getProcessTime() { return processTime; }
    public void setProcessTime(LocalDateTime processTime) { this.processTime = processTime; }
    
    public String getBankInfo() { return bankInfo; }
    public void setBankInfo(String bankInfo) { this.bankInfo = bankInfo; }
    
    public String getRefundReferenceCode() { return refundReferenceCode; }
    public void setRefundReferenceCode(String refundReferenceCode) { this.refundReferenceCode = refundReferenceCode; }
    
    public BigDecimal getRefundFee() { return refundFee; }
    public void setRefundFee(BigDecimal refundFee) { this.refundFee = refundFee; }
    
    public User getProcessedBy() { return processedBy; }
    public void setProcessedBy(User processedBy) { this.processedBy = processedBy; }
    
    public String getInternalNotes() { return internalNotes; }
    public void setInternalNotes(String internalNotes) { this.internalNotes = internalNotes; }
    
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