package com.zmen.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

public class ValidateAddressRequest {
    
    @NotNull(message = "Province ID is required")
    private Long provinceId;
    
    @NotBlank(message = "Province name is required")
    @Size(max = 100, message = "Province name must not exceed 100 characters")
    private String province;
    
    @NotNull(message = "District ID is required")
    private Long districtId;
    
    @NotBlank(message = "District name is required")
    @Size(max = 100, message = "District name must not exceed 100 characters")
    private String district;
    
    @NotNull(message = "Ward ID is required")
    private Long wardId;
    
    @NotBlank(message = "Ward name is required")
    @Size(max = 100, message = "Ward name must not exceed 100 characters")
    private String ward;
    
    @NotBlank(message = "Detail address is required")
    @Size(max = 500, message = "Detail address must not exceed 500 characters")
    private String detailAddress;
    
    @Pattern(regexp = "^(\\+84|0)[0-9]{9,10}$", message = "Invalid Vietnamese phone number format")
    private String recipientPhone;
    
    private String postalCode;
    
    // Validation options
    private boolean validateCoordinates = false;
    private boolean strictValidation = false;
    private boolean includeSuggestions = true;
    private boolean normalizeAddress = true;
    
    // Optional coordinates for validation
    private Double latitude;
    private Double longitude;
    
    // Constructors
    public ValidateAddressRequest() {}
    
    public ValidateAddressRequest(Long provinceId, String province, Long districtId, String district,
                                Long wardId, String ward, String detailAddress) {
        this.provinceId = provinceId;
        this.province = province;
        this.districtId = districtId;
        this.district = district;
        this.wardId = wardId;
        this.ward = ward;
        this.detailAddress = detailAddress;
    }
    
    public ValidateAddressRequest(Long provinceId, String province, Long districtId, String district,
                                Long wardId, String ward, String detailAddress, 
                                String recipientPhone, String postalCode) {
        this(provinceId, province, districtId, district, wardId, ward, detailAddress);
        this.recipientPhone = recipientPhone;
        this.postalCode = postalCode;
    }
    
    // Getters and Setters
    public Long getProvinceId() { return provinceId; }
    public void setProvinceId(Long provinceId) { this.provinceId = provinceId; }
    
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
    
    public Long getDistrictId() { return districtId; }
    public void setDistrictId(Long districtId) { this.districtId = districtId; }
    
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    
    public Long getWardId() { return wardId; }
    public void setWardId(Long wardId) { this.wardId = wardId; }
    
    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }
    
    public String getDetailAddress() { return detailAddress; }
    public void setDetailAddress(String detailAddress) { this.detailAddress = detailAddress; }
    
    public String getRecipientPhone() { return recipientPhone; }
    public void setRecipientPhone(String recipientPhone) { this.recipientPhone = recipientPhone; }
    
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    
    public boolean isValidateCoordinates() { return validateCoordinates; }
    public void setValidateCoordinates(boolean validateCoordinates) { 
        this.validateCoordinates = validateCoordinates; 
    }
    
    public boolean isStrictValidation() { return strictValidation; }
    public void setStrictValidation(boolean strictValidation) { 
        this.strictValidation = strictValidation; 
    }
    
    public boolean isIncludeSuggestions() { return includeSuggestions; }
    public void setIncludeSuggestions(boolean includeSuggestions) { 
        this.includeSuggestions = includeSuggestions; 
    }
    
    public boolean isNormalizeAddress() { return normalizeAddress; }
    public void setNormalizeAddress(boolean normalizeAddress) { 
        this.normalizeAddress = normalizeAddress; 
    }
    
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    
    // Helper methods
    public String getFullAddress() {
        return detailAddress + ", " + ward + ", " + district + ", " + province;
    }
    
    public boolean hasCoordinates() {
        return latitude != null && longitude != null;
    }
    
    public boolean isBasicValidationOnly() {
        return !validateCoordinates && !strictValidation;
    }
    
    @Override
    public String toString() {
        return "ValidateAddressRequest{" +
                "provinceId=" + provinceId +
                ", province='" + province + '\'' +
                ", districtId=" + districtId +
                ", district='" + district + '\'' +
                ", wardId=" + wardId +
                ", ward='" + ward + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", strictValidation=" + strictValidation +
                '}';
    }
}