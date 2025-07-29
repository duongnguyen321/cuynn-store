package com.zmen.backend.dto;

import java.time.LocalDateTime;

public class ProductStockResponse {
    private Long id;
    private Long productId;
    private String productName;
    private String sku;
    private Long variantId;
    private String variantName;
    private Integer currentStock;
    private Integer reservedStock;
    private Integer availableStock;
    private Integer minimumStock;
    private Integer maximumStock;
    private Integer reorderLevel;
    private Integer reorderQuantity;
    private String stockStatus;
    private String location;
    private String warehouseName;
    private LocalDateTime lastStockUpdate;
    private LocalDateTime lastRestock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public ProductStockResponse() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    
    public Long getVariantId() { return variantId; }
    public void setVariantId(Long variantId) { this.variantId = variantId; }
    
    public String getVariantName() { return variantName; }
    public void setVariantName(String variantName) { this.variantName = variantName; }
    
    public Integer getCurrentStock() { return currentStock; }
    public void setCurrentStock(Integer currentStock) { this.currentStock = currentStock; }
    
    public Integer getReservedStock() { return reservedStock; }
    public void setReservedStock(Integer reservedStock) { this.reservedStock = reservedStock; }
    
    public Integer getAvailableStock() { return availableStock; }
    public void setAvailableStock(Integer availableStock) { this.availableStock = availableStock; }
    
    public Integer getMinimumStock() { return minimumStock; }
    public void setMinimumStock(Integer minimumStock) { this.minimumStock = minimumStock; }
    
    public Integer getMaximumStock() { return maximumStock; }
    public void setMaximumStock(Integer maximumStock) { this.maximumStock = maximumStock; }
    
    public Integer getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(Integer reorderLevel) { this.reorderLevel = reorderLevel; }
    
    public Integer getReorderQuantity() { return reorderQuantity; }
    public void setReorderQuantity(Integer reorderQuantity) { this.reorderQuantity = reorderQuantity; }
    
    public String getStockStatus() { return stockStatus; }
    public void setStockStatus(String stockStatus) { this.stockStatus = stockStatus; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getWarehouseName() { return warehouseName; }
    public void setWarehouseName(String warehouseName) { this.warehouseName = warehouseName; }
    
    public LocalDateTime getLastStockUpdate() { return lastStockUpdate; }
    public void setLastStockUpdate(LocalDateTime lastStockUpdate) { this.lastStockUpdate = lastStockUpdate; }
    
    public LocalDateTime getLastRestock() { return lastRestock; }
    public void setLastRestock(LocalDateTime lastRestock) { this.lastRestock = lastRestock; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}