package com.zmen.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

public class UpdateProductStockRequest {
    
    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;
    
    private String operation; // SET, ADD, SUBTRACT
    
    private String reason;
    
    private Long variantId; // Optional, for variant-specific stock updates
    
    private String location; // Warehouse or location identifier
    
    private String batchNumber;
    
    private String notes;
    
    // Constructors
    public UpdateProductStockRequest() {}
    
    public UpdateProductStockRequest(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
        this.operation = "SET";
    }
    
    public UpdateProductStockRequest(Integer stockQuantity, String operation) {
        this.stockQuantity = stockQuantity;
        this.operation = operation;
    }
    
    public UpdateProductStockRequest(Integer stockQuantity, String operation, String reason) {
        this.stockQuantity = stockQuantity;
        this.operation = operation;
        this.reason = reason;
    }
    
    // Getters and Setters
    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
    
    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public Long getVariantId() { return variantId; }
    public void setVariantId(Long variantId) { this.variantId = variantId; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}