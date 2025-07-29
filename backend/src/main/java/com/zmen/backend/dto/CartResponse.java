package com.zmen.backend.dto;
import java.math.BigDecimal;
import java.util.List;
public class CartResponse {
    private Long id;
    private Integer totalItems;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private List<CartItemResponse> items;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    public List<CartItemResponse> getItems() { return items; }
    public void setItems(List<CartItemResponse> items) { this.items = items; }
}
