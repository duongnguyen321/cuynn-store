package com.zmen.backend.dto;

import java.util.List;
import java.util.Map;

public class AddressValidationResponse {
    
    private boolean isValid;
    private String message;
    private List<String> errors;
    private Map<String, String> fieldErrors;
    private AddressValidationDetails validationDetails;
    private List<AddressSuggestion> suggestions;
    
    // Constructors
    public AddressValidationResponse() {}
    
    public AddressValidationResponse(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }
    
    public AddressValidationResponse(boolean isValid, String message, List<String> errors) {
        this.isValid = isValid;
        this.message = message;
        this.errors = errors;
    }
    
    // Static factory methods
    public static AddressValidationResponse valid(String message) {
        return new AddressValidationResponse(true, message);
    }
    
    public static AddressValidationResponse invalid(String message, List<String> errors) {
        return new AddressValidationResponse(false, message, errors);
    }
    
    public static AddressValidationResponse invalid(String message) {
        return new AddressValidationResponse(false, message);
    }
    
    // Getters and Setters
    public boolean isValid() { return isValid; }
    public void setValid(boolean valid) { isValid = valid; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
    
    public Map<String, String> getFieldErrors() { return fieldErrors; }
    public void setFieldErrors(Map<String, String> fieldErrors) { this.fieldErrors = fieldErrors; }
    
    public AddressValidationDetails getValidationDetails() { return validationDetails; }
    public void setValidationDetails(AddressValidationDetails validationDetails) { 
        this.validationDetails = validationDetails; 
    }
    
    public List<AddressSuggestion> getSuggestions() { return suggestions; }
    public void setSuggestions(List<AddressSuggestion> suggestions) { this.suggestions = suggestions; }
    
    // Inner class for validation details
    public static class AddressValidationDetails {
        private boolean provinceValid;
        private boolean districtValid;
        private boolean wardValid;
        private boolean detailAddressValid;
        private boolean phoneNumberValid;
        private boolean postalCodeValid;
        private Double latitude;
        private Double longitude;
        private String normalizedAddress;
        private String geocodingProvider;
        private Double confidence;
        
        // Constructors
        public AddressValidationDetails() {}
        
        // Getters and Setters
        public boolean isProvinceValid() { return provinceValid; }
        public void setProvinceValid(boolean provinceValid) { this.provinceValid = provinceValid; }
        
        public boolean isDistrictValid() { return districtValid; }
        public void setDistrictValid(boolean districtValid) { this.districtValid = districtValid; }
        
        public boolean isWardValid() { return wardValid; }
        public void setWardValid(boolean wardValid) { this.wardValid = wardValid; }
        
        public boolean isDetailAddressValid() { return detailAddressValid; }
        public void setDetailAddressValid(boolean detailAddressValid) { 
            this.detailAddressValid = detailAddressValid; 
        }
        
        public boolean isPhoneNumberValid() { return phoneNumberValid; }
        public void setPhoneNumberValid(boolean phoneNumberValid) { 
            this.phoneNumberValid = phoneNumberValid; 
        }
        
        public boolean isPostalCodeValid() { return postalCodeValid; }
        public void setPostalCodeValid(boolean postalCodeValid) { 
            this.postalCodeValid = postalCodeValid; 
        }
        
        public Double getLatitude() { return latitude; }
        public void setLatitude(Double latitude) { this.latitude = latitude; }
        
        public Double getLongitude() { return longitude; }
        public void setLongitude(Double longitude) { this.longitude = longitude; }
        
        public String getNormalizedAddress() { return normalizedAddress; }
        public void setNormalizedAddress(String normalizedAddress) { 
            this.normalizedAddress = normalizedAddress; 
        }
        
        public String getGeocodingProvider() { return geocodingProvider; }
        public void setGeocodingProvider(String geocodingProvider) { 
            this.geocodingProvider = geocodingProvider; 
        }
        
        public Double getConfidence() { return confidence; }
        public void setConfidence(Double confidence) { this.confidence = confidence; }
    }
    
    // Inner class for address suggestions
    public static class AddressSuggestion {
        private String type; // PROVINCE, DISTRICT, WARD, DETAIL
        private String suggestedValue;
        private String originalValue;
        private Double confidence;
        private String reason;
        
        // Constructors
        public AddressSuggestion() {}
        
        public AddressSuggestion(String type, String suggestedValue, String originalValue, 
                               Double confidence, String reason) {
            this.type = type;
            this.suggestedValue = suggestedValue;
            this.originalValue = originalValue;
            this.confidence = confidence;
            this.reason = reason;
        }
        
        // Getters and Setters
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        
        public String getSuggestedValue() { return suggestedValue; }
        public void setSuggestedValue(String suggestedValue) { this.suggestedValue = suggestedValue; }
        
        public String getOriginalValue() { return originalValue; }
        public void setOriginalValue(String originalValue) { this.originalValue = originalValue; }
        
        public Double getConfidence() { return confidence; }
        public void setConfidence(Double confidence) { this.confidence = confidence; }
        
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }
}