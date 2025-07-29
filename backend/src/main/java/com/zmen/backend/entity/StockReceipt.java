package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "phieu_nhap_kho")
public class StockReceipt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ma_phieu", unique = true, nullable = false)
    private String receiptCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nha_cung_cap", nullable = false)
    private Supplier supplier;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien_tao", nullable = false)
    private Employee createdByEmployee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cua_hang_nhap", nullable = false)
    private Store store;
    
    @Column(name = "tong_tien", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalAmount;
    
    @Column(name = "ngay_nhap", nullable = false)
    private LocalDateTime receiptDate;
    
    @Column(name = "ghi_chu", columnDefinition = "TEXT")
    private String notes;
    
    @OneToMany(mappedBy = "stockReceipt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StockReceiptDetail> receiptDetails;
    
    @PrePersist
    protected void onCreate() {
        receiptDate = LocalDateTime.now();
    }
    
    // Constructors
    public StockReceipt() {}
    
    public StockReceipt(String receiptCode, Supplier supplier, Employee createdByEmployee, Store store) {
        this.receiptCode = receiptCode;
        this.supplier = supplier;
        this.createdByEmployee = createdByEmployee;
        this.store = store;
        this.totalAmount = BigDecimal.ZERO;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getReceiptCode() { return receiptCode; }
    public void setReceiptCode(String receiptCode) { this.receiptCode = receiptCode; }
    
    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
    
    public Employee getCreatedByEmployee() { return createdByEmployee; }
    public void setCreatedByEmployee(Employee createdByEmployee) { this.createdByEmployee = createdByEmployee; }
    
    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }
    
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    
    public LocalDateTime getReceiptDate() { return receiptDate; }
    public void setReceiptDate(LocalDateTime receiptDate) { this.receiptDate = receiptDate; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public List<StockReceiptDetail> getReceiptDetails() { return receiptDetails; }
    public void setReceiptDetails(List<StockReceiptDetail> receiptDetails) { this.receiptDetails = receiptDetails; }
    
    // Utility methods
    public void calculateTotalAmount() {
        if (receiptDetails != null) {
            totalAmount = receiptDetails.stream()
                .map(StockReceiptDetail::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }
}