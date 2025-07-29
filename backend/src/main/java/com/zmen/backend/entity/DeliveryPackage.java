package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "goi_giao_hang")
public class DeliveryPackage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don", nullable = false)
    private Order order;
    
    @Column(name = "ma_goi_hang", unique = true, nullable = false)
    private String packageCode;
    
    @Column(name = "don_vi_van_chuyen")
    private String shippingProvider;
    
    @Column(name = "ma_van_don")
    private String trackingNumber;
    
    @Column(name = "chi_phi_van_chuyen_thuc_te", precision = 15, scale = 2)
    private BigDecimal actualShippingCost;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private DeliveryStatus status = DeliveryStatus.cho_lay_hang;
    
    @Column(name = "ngay_gui_hang")
    private LocalDateTime shippedDate;
    
    @Column(name = "ngay_nhan_du_kien")
    private LocalDate expectedDeliveryDate;
    
    @Column(name = "ghi_chu_giao_hang", columnDefinition = "TEXT")
    private String deliveryNotes;
    
    @OneToMany(mappedBy = "deliveryPackage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DeliveryPackageDetail> packageDetails;
    
    public enum DeliveryStatus {
        cho_lay_hang, dang_giao, giao_thanh_cong, that_bai, dang_hoan
    }
    
    // Constructors
    public DeliveryPackage() {}
    
    public DeliveryPackage(Order order, String packageCode) {
        this.order = order;
        this.packageCode = packageCode;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    
    public String getPackageCode() { return packageCode; }
    public void setPackageCode(String packageCode) { this.packageCode = packageCode; }
    
    public String getShippingProvider() { return shippingProvider; }
    public void setShippingProvider(String shippingProvider) { this.shippingProvider = shippingProvider; }
    
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    
    public BigDecimal getActualShippingCost() { return actualShippingCost; }
    public void setActualShippingCost(BigDecimal actualShippingCost) { this.actualShippingCost = actualShippingCost; }
    
    public DeliveryStatus getStatus() { return status; }
    public void setStatus(DeliveryStatus status) { this.status = status; }
    
    public LocalDateTime getShippedDate() { return shippedDate; }
    public void setShippedDate(LocalDateTime shippedDate) { this.shippedDate = shippedDate; }
    
    public LocalDate getExpectedDeliveryDate() { return expectedDeliveryDate; }
    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) { this.expectedDeliveryDate = expectedDeliveryDate; }
    
    public String getDeliveryNotes() { return deliveryNotes; }
    public void setDeliveryNotes(String deliveryNotes) { this.deliveryNotes = deliveryNotes; }
    
    public List<DeliveryPackageDetail> getPackageDetails() { return packageDetails; }
    public void setPackageDetails(List<DeliveryPackageDetail> packageDetails) { this.packageDetails = packageDetails; }
    
    // Utility methods
    public boolean isDelivered() {
        return status == DeliveryStatus.giao_thanh_cong;
    }
    
    public void markAsShipped() {
        this.status = DeliveryStatus.dang_giao;
        this.shippedDate = LocalDateTime.now();
    }
    
    public void markAsDelivered() {
        this.status = DeliveryStatus.giao_thanh_cong;
    }
}