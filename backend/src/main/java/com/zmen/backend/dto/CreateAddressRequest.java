package com.zmen.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

public class CreateAddressRequest {
    
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
    
    @NotBlank(message = "Recipient name is required")
    @Size(max = 100, message = "Recipient name must not exceed 100 characters")
    private String recipientName;
    
    @NotBlank(message = "Recipient phone is required")
    @Pattern(regexp = "^(\\+84|0)[0-9]{9,10}$", message = "Invalid Vietnamese phone number format")
    private String recipientPhone;
    
    private Boolean isDefault = false;
    
    // Optional fields for address validation
    private String postalCode;
    private Double latitude;
    private Double longitude;
    
    // Constructors
    public CreateAddressRequest() {}
    
    public CreateAddressRequest(Long provinceId, String province, Long districtId, String district,
                              Long wardId, String ward, String detailAddress, 
                              String recipientName, String recipientPhone) {
        this.provinceId = provinceId;
        this.province = province;
        this.districtId = districtId;
        this.district = district;
        this.wardId = wardId;
        this.ward = ward;
        this.detailAddress = detailAddress;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
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
    
    public String getRecipientName() { return recipientName; }
    public void setRecipientName(String recipientName) { this.recipientName = recipientName; }
    
    public String getRecipientPhone() { return recipientPhone; }
    public void setRecipientPhone(String recipientPhone) { this.recipientPhone = recipientPhone; }
    
    public Boolean getIsDefault() { return isDefault; }
    public void setIsDefault(Boolean isDefault) { this.isDefault = isDefault; }
    
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    
    // Helper method to get full address
    public String getFullAddress() {
        return detailAddress + ", " + ward + ", " + district + ", " + province;
    }
}