package com.zmen.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bao_cao_doanh_thu_theo_ngay")
public class DailyRevenue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ngay_bao_cao", nullable = false)
    private LocalDate reportDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cua_hang")
    private Store store;
    
    @Column(name = "tong_doanh_thu", precision = 18, scale = 2, nullable = false)
    private BigDecimal totalRevenue;
    
    @Column(name = "so_don_hang", nullable = false)
    private Integer orderCount;
    
    @Column(name = "so_san_pham_ban_ra", nullable = false)
    private Integer productsSold;
    
    @Column(name = "khach_hang_moi", nullable = false)
    private Integer newCustomers;
    
    // Constructors
    public DailyRevenue() {}
    
    public DailyRevenue(LocalDate reportDate, Store store) {
        this.reportDate = reportDate;
        this.store = store;
        this.totalRevenue = BigDecimal.ZERO;
        this.orderCount = 0;
        this.productsSold = 0;
        this.newCustomers = 0;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDate getReportDate() { return reportDate; }
    public void setReportDate(LocalDate reportDate) { this.reportDate = reportDate; }
    
    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }
    
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
    
    public Integer getOrderCount() { return orderCount; }
    public void setOrderCount(Integer orderCount) { this.orderCount = orderCount; }
    
    public Integer getProductsSold() { return productsSold; }
    public void setProductsSold(Integer productsSold) { this.productsSold = productsSold; }
    
    public Integer getNewCustomers() { return newCustomers; }
    public void setNewCustomers(Integer newCustomers) { this.newCustomers = newCustomers; }
}