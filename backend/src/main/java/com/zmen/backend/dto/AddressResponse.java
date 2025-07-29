package com.zmen.backend.dto;

public class AddressResponse {
    
    private Long id;
    private String province;
    private String district;
    private String ward;
    private String detailAddress;
    private String fullAddress;
    private Boolean isDefault;
    private String recipientName;
    private String recipientPhone;
    private Long provinceId;
    private Long districtId;
    private Long wardId;
    
    // Constructors
    public AddressResponse() {}
    
    public AddressResponse(Long id, String province, String district, String ward, 
                          String detailAddress, Boolean isDefault) {
        this.id = id;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.detailAddress = detailAddress;
        this.isDefault = isDefault;
        this.fullAddress = buildFullAddress();
    }
    
    public AddressResponse(Long id, String province, String district, String ward, 
                          String detailAddress, Boolean isDefault, String recipientName, 
                          String recipientPhone) {
        this(id, province, district, ward, detailAddress, isDefault);
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
    }
    
    // Helper method to build full address
    private String buildFullAddress() {
        StringBuilder fullAddr = new StringBuilder();
        if (detailAddress != null && !detailAddress.trim().isEmpty()) {
            fullAddr.append(detailAddress);
        }
        if (ward != null && !ward.trim().isEmpty()) {
            if (fullAddr.length() > 0) fullAddr.append(", ");
            fullAddr.append(ward);
        }
        if (district != null && !district.trim().isEmpty()) {
            if (fullAddr.length() > 0) fullAddr.append(", ");
            fullAddr.append(district);
        }
        if (province != null && !province.trim().isEmpty()) {
            if (fullAddr.length() > 0) fullAddr.append(", ");
            fullAddr.append(province);
        }
        return fullAddr.toString();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getProvince() { return province; }
    public void setProvince(String province) { 
        this.province = province;
        this.fullAddress = buildFullAddress();
    }
    
    public String getDistrict() { return district; }
    public void setDistrict(String district) { 
        this.district = district;
        this.fullAddress = buildFullAddress();
    }
    
    public String getWard() { return ward; }
    public void setWard(String ward) { 
        this.ward = ward;
        this.fullAddress = buildFullAddress();
    }
    
    public String getDetailAddress() { return detailAddress; }
    public void setDetailAddress(String detailAddress) { 
        this.detailAddress = detailAddress;
        this.fullAddress = buildFullAddress();
    }
    
    public String getFullAddress() { return fullAddress; }
    public void setFullAddress(String fullAddress) { this.fullAddress = fullAddress; }
    
    public Boolean getIsDefault() { return isDefault; }
    public void setIsDefault(Boolean isDefault) { this.isDefault = isDefault; }
    
    public String getRecipientName() { return recipientName; }
    public void setRecipientName(String recipientName) { this.recipientName = recipientName; }
    
    public String getRecipientPhone() { return recipientPhone; }
    public void setRecipientPhone(String recipientPhone) { this.recipientPhone = recipientPhone; }
    
    public Long getProvinceId() { return provinceId; }
    public void setProvinceId(Long provinceId) { this.provinceId = provinceId; }
    
    public Long getDistrictId() { return districtId; }
    public void setDistrictId(Long districtId) { this.districtId = districtId; }
    
    public Long getWardId() { return wardId; }
    public void setWardId(Long wardId) { this.wardId = wardId; }
}