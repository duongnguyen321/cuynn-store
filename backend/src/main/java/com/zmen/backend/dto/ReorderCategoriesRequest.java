package com.zmen.backend.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ReorderCategoriesRequest {
    
    @NotEmpty(message = "Category order list cannot be empty")
    private List<CategoryOrder> categoryOrders;
    
    // Constructors
    public ReorderCategoriesRequest() {}
    
    // Getters and Setters
    public List<CategoryOrder> getCategoryOrders() { return categoryOrders; }
    public void setCategoryOrders(List<CategoryOrder> categoryOrders) { this.categoryOrders = categoryOrders; }
    
    // Inner class for category order
    public static class CategoryOrder {
        @NotNull(message = "Category ID is required")
        private Long categoryId;
        
        @NotNull(message = "Sort order is required")
        private Integer sortOrder;
        
        // Constructors
        public CategoryOrder() {}
        
        public CategoryOrder(Long categoryId, Integer sortOrder) {
            this.categoryId = categoryId;
            this.sortOrder = sortOrder;
        }
        
        // Getters and Setters
        public Long getCategoryId() { return categoryId; }
        public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
        
        public Integer getSortOrder() { return sortOrder; }
        public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    }
}