package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hoa_don")
public class OrderEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ma_hoa_don", unique = true, nullable = false)
    private String orderCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung", nullable = false)
    private User user;
    
    @Column(name = "tong_tien", precision = 12, scale = 2, nullable = false)
    private BigDecimal totalAmount;
    
    @Column(name = "tien_giam_gia", precision = 12, scale = 2)
    private BigDecimal discountAmount = BigDecimal.ZERO;
    
    @Column(name = "phi_van_chuyen", precision = 12, scale = 2)
    private BigDecimal shippingFee = BigDecimal.ZERO;
    
    @Column(name = "tong_thanh_toan", precision = 12, scale = 2, nullable = false)
    private BigDecimal finalAmount;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai_don_hang", nullable = false)
    private OrderStatus orderStatus = OrderStatus.cho_xac_nhan;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai_thanh_toan", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.chua_thanh_toan;
    
    @Column(name = "phuong_thuc_thanh_toan")
    private String paymentMethod;
    
    @Column(name = "dia_chi_giao_hang", columnDefinition = "TEXT", nullable = false)
    private String shippingAddress;
    
    @Column(name = "so_dien_thoai_nhan_hang")
    private String recipientPhone;
    
    @Column(name = "ten_nguoi_nhan")
    private String recipientName;
    
    @Column(name = "ghi_chu", columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "ngay_dat_hang", nullable = false)
    private LocalDateTime orderDate;
    
    @Column(name = "ngay_xac_nhan")
    private LocalDateTime confirmDate;
    
    @Column(name = "ngay_giao_hang")
    private LocalDateTime deliveryDate;
    
    @Column(name = "ngay_hoan_thanh")
    private LocalDateTime completedDate;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDateTime updatedAt;
    
    public enum OrderStatus {
        cho_xac_nhan, da_xac_nhan, dang_chuan_bi, dang_giao_hang, 
        da_giao_hang, hoan_thanh, da_huy, tra_hang
    }
    
    public enum PaymentStatus {
        chua_thanh_toan, da_thanh_toan, thanh_toan_that_bai, hoan_tien
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        orderDate = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public OrderEntity() {}
    
    public OrderEntity(String orderCode, User user, BigDecimal totalAmount, String shippingAddress) {
        this.orderCode = orderCode;
        this.user = user;
        this.totalAmount = totalAmount;
        this.finalAmount = totalAmount;
        this.shippingAddress = shippingAddress;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getOrderCode() { return orderCode; }
    public void setOrderCode(String orderCode) { this.orderCode = orderCode; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    
    public BigDecimal getShippingFee() { return shippingFee; }
    public void setShippingFee(BigDecimal shippingFee) { this.shippingFee = shippingFee; }
    
    public BigDecimal getFinalAmount() { return finalAmount; }
    public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
    
    public OrderStatus getOrderStatus() { return orderStatus; }
    public void setOrderStatus(OrderStatus orderStatus) { this.orderStatus = orderStatus; }
    
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }
    
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    
    public String getRecipientPhone() { return recipientPhone; }
    public void setRecipientPhone(String recipientPhone) { this.recipientPhone = recipientPhone; }
    
    public String getRecipientName() { return recipientName; }
    public void setRecipientName(String recipientName) { this.recipientName = recipientName; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    
    public LocalDateTime getConfirmDate() { return confirmDate; }
    public void setConfirmDate(LocalDateTime confirmDate) { this.confirmDate = confirmDate; }
    
    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDateTime deliveryDate) { this.deliveryDate = deliveryDate; }
    
    public LocalDateTime getCompletedDate() { return completedDate; }
    public void setCompletedDate(LocalDateTime completedDate) { this.completedDate = completedDate; }
    
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    // Utility methods
    public void calculateFinalAmount() {
        finalAmount = totalAmount.subtract(discountAmount).add(shippingFee);
    }
    
    public boolean isCompleted() {
        return orderStatus == OrderStatus.hoan_thanh;
    }
    
    public boolean isPaid() {
        return paymentStatus == PaymentStatus.da_thanh_toan;
    }
}