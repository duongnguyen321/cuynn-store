package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "don_tra_hang")
public class ReturnOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ma_don_tra", unique = true, nullable = false)
    private String returnCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don_goc", nullable = false)
    private OrderEntity originalOrder;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien_xu_ly", nullable = false)
    private Employee handledByEmployee;
    
    @Column(name = "ly_do_tra_hang", columnDefinition = "TEXT", nullable = false)
    private String returnReason;
    
    @Column(name = "tong_tien_hoan", precision = 15, scale = 2, nullable = false)
    private BigDecimal refundAmount;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private ReturnStatus status;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "returnOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReturnOrderDetail> returnDetails;
    
    public enum ReturnStatus {
        dang_cho_xu_ly, da_chap_nhan, da_tu_choi, da_hoan_thanh
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public ReturnOrder() {}
    
    public ReturnOrder(String returnCode, OrderEntity originalOrder, Employee handledByEmployee, String returnReason) {
        this.returnCode = returnCode;
        this.originalOrder = originalOrder;
        this.handledByEmployee = handledByEmployee;
        this.returnReason = returnReason;
        this.status = ReturnStatus.dang_cho_xu_ly;
        this.refundAmount = BigDecimal.ZERO;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getReturnCode() { return returnCode; }
    public void setReturnCode(String returnCode) { this.returnCode = returnCode; }
    
    public OrderEntity getOriginalOrder() { return originalOrder; }
    public void setOriginalOrder(OrderEntity originalOrder) { this.originalOrder = originalOrder; }
    
    public Employee getHandledByEmployee() { return handledByEmployee; }
    public void setHandledByEmployee(Employee handledByEmployee) { this.handledByEmployee = handledByEmployee; }
    
    public String getReturnReason() { return returnReason; }
    public void setReturnReason(String returnReason) { this.returnReason = returnReason; }
    
    public BigDecimal getRefundAmount() { return refundAmount; }
    public void setRefundAmount(BigDecimal refundAmount) { this.refundAmount = refundAmount; }
    
    public ReturnStatus getStatus() { return status; }
    public void setStatus(ReturnStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public List<ReturnOrderDetail> getReturnDetails() { return returnDetails; }
    public void setReturnDetails(List<ReturnOrderDetail> returnDetails) { this.returnDetails = returnDetails; }
    
    // Utility methods
    public boolean isPending() {
        return status == ReturnStatus.dang_cho_xu_ly;
    }
    
    public boolean isApproved() {
        return status == ReturnStatus.da_chap_nhan;
    }
    
    public boolean isCompleted() {
        return status == ReturnStatus.da_hoan_thanh;
    }
    
    public void approve() {
        this.status = ReturnStatus.da_chap_nhan;
    }
    
    public void reject() {
        this.status = ReturnStatus.da_tu_choi;
    }
    
    public void complete() {
        this.status = ReturnStatus.da_hoan_thanh;
    }
}