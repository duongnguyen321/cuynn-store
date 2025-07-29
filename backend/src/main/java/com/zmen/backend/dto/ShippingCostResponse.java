package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ShippingCostResponse {
    
    private BigDecimal totalCost;
    private BigDecimal baseCost;
    private BigDecimal weightCost;
    private BigDecimal distanceCost;
    private BigDecimal valueCost;
    private BigDecimal serviceFee;
    private List<ShippingFeeBreakdown> feeBreakdown;
    private List<ShippingOption> availableOptions;
    private ShippingOption recommendedOption;
    private String currency;
    private boolean calculationSuccessful;
    private String message;
    private List<String> errors;
    private Map<String, Object> metadata;
    
    // Constructors
    public ShippingCostResponse() {
        this.currency = "VND";
        this.calculationSuccessful = false;
    }
    
    public ShippingCostResponse(BigDecimal totalCost, boolean calculationSuccessful, String message) {
        this();
        this.totalCost = totalCost;
        this.calculationSuccessful = calculationSuccessful;
        this.message = message;
    }
    
    // Static factory methods
    public static ShippingCostResponse success(BigDecimal totalCost, String message) {
        return new ShippingCostResponse(totalCost, true, message);
    }
    
    public static ShippingCostResponse error(String message, List<String> errors) {
        ShippingCostResponse response = new ShippingCostResponse(BigDecimal.ZERO, false, message);
        response.setErrors(errors);
        return response;
    }
    
    // Getters and Setters
    public BigDecimal getTotalCost() { return totalCost; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }
    
    public BigDecimal getBaseCost() { return baseCost; }
    public void setBaseCost(BigDecimal baseCost) { this.baseCost = baseCost; }
    
    public BigDecimal getWeightCost() { return weightCost; }
    public void setWeightCost(BigDecimal weightCost) { this.weightCost = weightCost; }
    
    public BigDecimal getDistanceCost() { return distanceCost; }
    public void setDistanceCost(BigDecimal distanceCost) { this.distanceCost = distanceCost; }
    
    public BigDecimal getValueCost() { return valueCost; }
    public void setValueCost(BigDecimal valueCost) { this.valueCost = valueCost; }
    
    public BigDecimal getServiceFee() { return serviceFee; }
    public void setServiceFee(BigDecimal serviceFee) { this.serviceFee = serviceFee; }
    
    public List<ShippingFeeBreakdown> getFeeBreakdown() { return feeBreakdown; }
    public void setFeeBreakdown(List<ShippingFeeBreakdown> feeBreakdown) { 
        this.feeBreakdown = feeBreakdown; 
    }
    
    public List<ShippingOption> getAvailableOptions() { return availableOptions; }
    public void setAvailableOptions(List<ShippingOption> availableOptions) { 
        this.availableOptions = availableOptions; 
    }
    
    public ShippingOption getRecommendedOption() { return recommendedOption; }
    public void setRecommendedOption(ShippingOption recommendedOption) { 
        this.recommendedOption = recommendedOption; 
    }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public boolean isCalculationSuccessful() { return calculationSuccessful; }
    public void setCalculationSuccessful(boolean calculationSuccessful) { 
        this.calculationSuccessful = calculationSuccessful; 
    }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
    
    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
    
    // Helper methods
    public String getFormattedTotalCost() {
        if (totalCost == null) return "0 " + currency;
        return String.format("%,.0f %s", totalCost.doubleValue(), currency);
    }
    
    public boolean hasMultipleOptions() {
        return availableOptions != null && availableOptions.size() > 1;
    }
    
    // Inner class for fee breakdown
    public static class ShippingFeeBreakdown {
        private String feeType;
        private String description;
        private BigDecimal amount;
        private String calculation;
        
        public ShippingFeeBreakdown() {}
        
        public ShippingFeeBreakdown(String feeType, String description, BigDecimal amount) {
            this.feeType = feeType;
            this.description = description;
            this.amount = amount;
        }
        
        // Getters and Setters
        public String getFeeType() { return feeType; }
        public void setFeeType(String feeType) { this.feeType = feeType; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        
        public String getCalculation() { return calculation; }
        public void setCalculation(String calculation) { this.calculation = calculation; }
    }
    
    // Inner class for shipping options
    public static class ShippingOption {
        private String id;
        private String name;
        private String provider;
        private BigDecimal cost;
        private Integer estimatedDays;
        private LocalDateTime estimatedDeliveryDate;
        private String serviceType; // STANDARD, EXPRESS, OVERNIGHT
        private boolean trackingAvailable;
        private boolean insuranceAvailable;
        private Map<String, Object> providerDetails;
        
        public ShippingOption() {}
        
        public ShippingOption(String id, String name, String provider, BigDecimal cost, 
                            Integer estimatedDays, String serviceType) {
            this.id = id;
            this.name = name;
            this.provider = provider;
            this.cost = cost;
            this.estimatedDays = estimatedDays;
            this.serviceType = serviceType;
        }
        
        // Getters and Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getProvider() { return provider; }
        public void setProvider(String provider) { this.provider = provider; }
        
        public BigDecimal getCost() { return cost; }
        public void setCost(BigDecimal cost) { this.cost = cost; }
        
        public Integer getEstimatedDays() { return estimatedDays; }
        public void setEstimatedDays(Integer estimatedDays) { this.estimatedDays = estimatedDays; }
        
        public LocalDateTime getEstimatedDeliveryDate() { return estimatedDeliveryDate; }
        public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) { 
            this.estimatedDeliveryDate = estimatedDeliveryDate; 
        }
        
        public String getServiceType() { return serviceType; }
        public void setServiceType(String serviceType) { this.serviceType = serviceType; }
        
        public boolean isTrackingAvailable() { return trackingAvailable; }
        public void setTrackingAvailable(boolean trackingAvailable) { 
            this.trackingAvailable = trackingAvailable; 
        }
        
        public boolean isInsuranceAvailable() { return insuranceAvailable; }
        public void setInsuranceAvailable(boolean insuranceAvailable) { 
            this.insuranceAvailable = insuranceAvailable; 
        }
        
        public Map<String, Object> getProviderDetails() { return providerDetails; }
        public void setProviderDetails(Map<String, Object> providerDetails) { 
            this.providerDetails = providerDetails; 
        }
        
        // Helper methods
        public String getFormattedCost() {
            if (cost == null) return "0 VND";
            return String.format("%,.0f VND", cost.doubleValue());
        }
        
        public String getEstimatedDeliveryText() {
            if (estimatedDays == null) return "Unknown";
            if (estimatedDays <= 1) return "1 day";
            return estimatedDays + " days";
        }
    }
}