package com.zmen.backend.dto;

import java.math.BigDecimal;

public class CartSummaryResponse {
    
    private Integer totalItems;
    private BigDecimal subtotal;
    private BigDecimal discountAmount;
    private BigDecimal shippingCost;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;
    private String voucherCode;
    private String voucherDescription;
    private BigDecimal voucherDiscount;
    private boolean freeShipping;
    private BigDecimal minimumOrderAmount;
    private BigDecimal amountToFreeShipping;
    private Integer loyaltyPointsEarned;
    private Integer loyaltyPointsUsed;
    private BigDecimal loyaltyPointsValue;
    
    // Constructors
    public CartSummaryResponse() {}
    
    public CartSummaryResponse(Integer totalItems, BigDecimal subtotal, BigDecimal discountAmount, 
                              BigDecimal shippingCost, BigDecimal totalAmount) {
        this.totalItems = totalItems;
        this.subtotal = subtotal;
        this.discountAmount = discountAmount;
        this.shippingCost = shippingCost;
        this.totalAmount = totalAmount;
    }
    
    // Getters and Setters
    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }
    
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    
    public BigDecimal getShippingCost() { return shippingCost; }
    public void setShippingCost(BigDecimal shippingCost) { this.shippingCost = shippingCost; }
    
    public BigDecimal getTaxAmount() { return taxAmount; }
    public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }
    
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    
    public String getVoucherCode() { return voucherCode; }
    public void setVoucherCode(String voucherCode) { this.voucherCode = voucherCode; }
    
    public String getVoucherDescription() { return voucherDescription; }
    public void setVoucherDescription(String voucherDescription) { this.voucherDescription = voucherDescription; }
    
    public BigDecimal getVoucherDiscount() { return voucherDiscount; }
    public void setVoucherDiscount(BigDecimal voucherDiscount) { this.voucherDiscount = voucherDiscount; }
    
    public boolean isFreeShipping() { return freeShipping; }
    public void setFreeShipping(boolean freeShipping) { this.freeShipping = freeShipping; }
    
    public BigDecimal getMinimumOrderAmount() { return minimumOrderAmount; }
    public void setMinimumOrderAmount(BigDecimal minimumOrderAmount) { this.minimumOrderAmount = minimumOrderAmount; }
    
    public BigDecimal getAmountToFreeShipping() { return amountToFreeShipping; }
    public void setAmountToFreeShipping(BigDecimal amountToFreeShipping) { this.amountToFreeShipping = amountToFreeShipping; }
    
    public Integer getLoyaltyPointsEarned() { return loyaltyPointsEarned; }
    public void setLoyaltyPointsEarned(Integer loyaltyPointsEarned) { this.loyaltyPointsEarned = loyaltyPointsEarned; }
    
    public Integer getLoyaltyPointsUsed() { return loyaltyPointsUsed; }
    public void setLoyaltyPointsUsed(Integer loyaltyPointsUsed) { this.loyaltyPointsUsed = loyaltyPointsUsed; }
    
    public BigDecimal getLoyaltyPointsValue() { return loyaltyPointsValue; }
    public void setLoyaltyPointsValue(BigDecimal loyaltyPointsValue) { this.loyaltyPointsValue = loyaltyPointsValue; }
}