package com.zmen.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class CartValidationResponse {
    
    private boolean valid;
    private List<String> errors;
    private List<String> warnings;
    private List<CartItemValidation> itemValidations;
    private String message;
    
    // Inner class for individual item validation
    public static class CartItemValidation {
        private Long itemId;
        private Long productId;
        private String productName;
        private boolean valid;
        private String errorMessage;
        private Integer requestedQuantity;
        private Integer availableQuantity;
        private boolean inStock;
        private boolean priceChanged;
        private String oldPrice;
        private String newPrice;
        
        // Constructors
        public CartItemValidation() {}
        
        public CartItemValidation(Long itemId, Long productId, String productName, boolean valid) {
            this.itemId = itemId;
            this.productId = productId;
            this.productName = productName;
            this.valid = valid;
        }
        
        // Getters and Setters
        public Long getItemId() { return itemId; }
        public void setItemId(Long itemId) { this.itemId = itemId; }
        
        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }
        
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        
        public boolean isValid() { return valid; }
        public void setValid(boolean valid) { this.valid = valid; }
        
        public String getErrorMessage() { return errorMessage; }
        public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
        
        public Integer getRequestedQuantity() { return requestedQuantity; }
        public void setRequestedQuantity(Integer requestedQuantity) { this.requestedQuantity = requestedQuantity; }
        
        public Integer getAvailableQuantity() { return availableQuantity; }
        public void setAvailableQuantity(Integer availableQuantity) { this.availableQuantity = availableQuantity; }
        
        public boolean isInStock() { return inStock; }
        public void setInStock(boolean inStock) { this.inStock = inStock; }
        
        public boolean isPriceChanged() { return priceChanged; }
        public void setPriceChanged(boolean priceChanged) { this.priceChanged = priceChanged; }
        
        public String getOldPrice() { return oldPrice; }
        public void setOldPrice(String oldPrice) { this.oldPrice = oldPrice; }
        
        public String getNewPrice() { return newPrice; }
        public void setNewPrice(String newPrice) { this.newPrice = newPrice; }
    }
    
    // Constructors
    public CartValidationResponse() {
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
        this.itemValidations = new ArrayList<>();
    }
    
    public CartValidationResponse(boolean valid, String message) {
        this();
        this.valid = valid;
        this.message = message;
    }
    
    // Getters and Setters
    public boolean isValid() { return valid; }
    public void setValid(boolean valid) { this.valid = valid; }
    
    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
    
    public List<String> getWarnings() { return warnings; }
    public void setWarnings(List<String> warnings) { this.warnings = warnings; }
    
    public List<CartItemValidation> getItemValidations() { return itemValidations; }
    public void setItemValidations(List<CartItemValidation> itemValidations) { this.itemValidations = itemValidations; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    // Utility methods
    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
        this.valid = false;
    }
    
    public void addWarning(String warning) {
        if (this.warnings == null) {
            this.warnings = new ArrayList<>();
        }
        this.warnings.add(warning);
    }
    
    public void addItemValidation(CartItemValidation itemValidation) {
        if (this.itemValidations == null) {
            this.itemValidations = new ArrayList<>();
        }
        this.itemValidations.add(itemValidation);
        if (!itemValidation.isValid()) {
            this.valid = false;
        }
    }
}