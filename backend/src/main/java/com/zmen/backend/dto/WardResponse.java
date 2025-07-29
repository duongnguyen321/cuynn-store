package com.zmen.backend.dto;

public class WardResponse {
    
    private Long id;
    private String name;
    private String code;
    private String nameEn;
    private String fullName;
    private String fullNameEn;
    private String codeName;
    private Long districtId;
    private String districtName;
    private Long provinceId;
    private String provinceName;
    private String administrativeUnitId;
    private String postalCode;
    
    // Constructors
    public WardResponse() {}
    
    public WardResponse(Long id, String name, String code, Long districtId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.districtId = districtId;
    }
    
    public WardResponse(Long id, String name, String code, String nameEn, 
                       String fullName, String fullNameEn, String codeName, 
                       Long districtId, String districtName, Long provinceId, String provinceName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.nameEn = nameEn;
        this.fullName = fullName;
        this.fullNameEn = fullNameEn;
        this.codeName = codeName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getFullNameEn() { return fullNameEn; }
    public void setFullNameEn(String fullNameEn) { this.fullNameEn = fullNameEn; }
    
    public String getCodeName() { return codeName; }
    public void setCodeName(String codeName) { this.codeName = codeName; }
    
    public Long getDistrictId() { return districtId; }
    public void setDistrictId(Long districtId) { this.districtId = districtId; }
    
    public String getDistrictName() { return districtName; }
    public void setDistrictName(String districtName) { this.districtName = districtName; }
    
    public Long getProvinceId() { return provinceId; }
    public void setProvinceId(Long provinceId) { this.provinceId = provinceId; }
    
    public String getProvinceName() { return provinceName; }
    public void setProvinceName(String provinceName) { this.provinceName = provinceName; }
    
    public String getAdministrativeUnitId() { return administrativeUnitId; }
    public void setAdministrativeUnitId(String administrativeUnitId) { 
        this.administrativeUnitId = administrativeUnitId; 
    }
    
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    
    // Helper methods
    public String getDisplayName() {
        return fullName != null ? fullName : name;
    }
    
    public String getDisplayNameEn() {
        return fullNameEn != null ? fullNameEn : nameEn;
    }
    
    public String getFullAddressName() {
        StringBuilder fullAddress = new StringBuilder(getDisplayName());
        if (districtName != null) {
            fullAddress.append(", ").append(districtName);
        }
        if (provinceName != null) {
            fullAddress.append(", ").append(provinceName);
        }
        return fullAddress.toString();
    }
    
    public String getShortAddressName() {
        return getDisplayName() + (districtName != null ? ", " + districtName : "");
    }
    
    @Override
    public String toString() {
        return "WardResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", fullName='" + fullName + '\'' +
                ", districtId=" + districtId +
                ", provinceId=" + provinceId +
                '}';
    }
}