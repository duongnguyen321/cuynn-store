package com.zmen.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "chi_tiet_don_tra_hang")
public class ReturnOrderDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_don_tra_hang", nullable = false)
    private ReturnOrder returnOrder;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chi_tiet_hoa_don", nullable = false)
    private OrderItem orderItem;
    
    @Column(name = "so_luong_tra", nullable = false)
    private Integer returnQuantity;
    
    // Constructors
    public ReturnOrderDetail() {}
    
    public ReturnOrderDetail(ReturnOrder returnOrder, OrderItem orderItem, Integer returnQuantity) {
        this.returnOrder = returnOrder;
        this.orderItem = orderItem;
        this.returnQuantity = returnQuantity;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public ReturnOrder getReturnOrder() { return returnOrder; }
    public void setReturnOrder(ReturnOrder returnOrder) { this.returnOrder = returnOrder; }
    
    public OrderItem getOrderItem() { return orderItem; }
    public void setOrderItem(OrderItem orderItem) { this.orderItem = orderItem; }
    
    public Integer getReturnQuantity() { return returnQuantity; }
    public void setReturnQuantity(Integer returnQuantity) { this.returnQuantity = returnQuantity; }
}