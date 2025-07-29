package com.zmen.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "chi_tiet_goi_giao_hang")
public class DeliveryPackageDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_goi_giao_hang", nullable = false)
    private DeliveryPackage deliveryPackage;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chi_tiet_hoa_don", nullable = false)
    private OrderItem orderItem;
    
    @Column(name = "so_luong", nullable = false)
    private Integer quantity;
    
    // Constructors
    public DeliveryPackageDetail() {}
    
    public DeliveryPackageDetail(DeliveryPackage deliveryPackage, OrderItem orderItem, Integer quantity) {
        this.deliveryPackage = deliveryPackage;
        this.orderItem = orderItem;
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public DeliveryPackage getDeliveryPackage() { return deliveryPackage; }
    public void setDeliveryPackage(DeliveryPackage deliveryPackage) { this.deliveryPackage = deliveryPackage; }
    
    public OrderItem getOrderItem() { return orderItem; }
    public void setOrderItem(OrderItem orderItem) { this.orderItem = orderItem; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}