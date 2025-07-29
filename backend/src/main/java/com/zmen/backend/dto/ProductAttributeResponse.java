package com.zmen.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ProductAttributeResponse {
    private Long id;
    private String attributeName;
    private String attributeType;
    private String description;
    private Boolean required;
    private Integer displayOrder;
    private List<AttributeValueResponse> attributeValues;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public ProductAttributeResponse() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAttributeName() { return attributeName; }
    public void setAttributeName(String attributeName) { this.attributeName = attributeName; }
    
    public String getAttributeType() { return attributeType; }
    public void setAttributeType(String attributeType) { this.attributeType = attributeType; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Boolean getRequired() { return required; }
    public void setRequired(Boolean required) { this.required = required; }
    
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    
    public List<AttributeValueResponse> getAttributeValues() { return attributeValues; }
    public void setAttributeValues(List<AttributeValueResponse> attributeValues) { this.attributeValues = attributeValues; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    // Helper class for attribute values
    public static class AttributeValueResponse {
        private Long id;
        private String value;
        private String displayValue;
        private String color;
        private Integer displayOrder;
        
        public AttributeValueResponse() {}
        
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
        
        public String getDisplayValue() { return displayValue; }
        public void setDisplayValue(String displayValue) { this.displayValue = displayValue; }
        
        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }
        
        public Integer getDisplayOrder() { return displayOrder; }
        public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    }
}