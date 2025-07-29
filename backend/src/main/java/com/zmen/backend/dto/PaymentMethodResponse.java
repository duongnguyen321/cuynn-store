package com.zmen.backend.dto;

import java.math.BigDecimal;

public class PaymentMethodResponse {
    private Long id;
    private String name;
    private String code;
    private String description;
    private String type;
    private String iconUrl;
    private Boolean isActive;
    private Boolean isDefault;
    private BigDecimal minimumAmount;
    private BigDecimal maximumAmount;
    private BigDecimal processingFee;
    private String processingFeeType;
    private String gatewayProvider;
    private String configuration;
    private Integer sortOrder;
    
    // Constructors
    public PaymentMethodResponse() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public Boolean getIsDefault() { return isDefault; }
    public void setIsDefault(Boolean isDefault) { this.isDefault = isDefault; }
    
    public BigDecimal getMinimumAmount() { return minimumAmount; }
    public void setMinimumAmount(BigDecimal minimumAmount) { this.minimumAmount = minimumAmount; }
    
    public BigDecimal getMaximumAmount() { return maximumAmount; }
    public void setMaximumAmount(BigDecimal maximumAmount) { this.maximumAmount = maximumAmount; }
    
    public BigDecimal getProcessingFee() { return processingFee; }
    public void setProcessingFee(BigDecimal processingFee) { this.processingFee = processingFee; }
    
    public String getProcessingFeeType() { return processingFeeType; }
    public void setProcessingFeeType(String processingFeeType) { this.processingFeeType = processingFeeType; }
    
    public String getGatewayProvider() { return gatewayProvider; }
    public void setGatewayProvider(String gatewayProvider) { this.gatewayProvider = gatewayProvider; }
    
    public String getConfiguration() { return configuration; }
    public void setConfiguration(String configuration) { this.configuration = configuration; }
    
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}