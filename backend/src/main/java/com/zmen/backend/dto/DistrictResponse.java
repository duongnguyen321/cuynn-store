package com.zmen.backend.dto;

import java.util.List;

public class DistrictResponse {
    
    private Long id;
    private String name;
    private String code;
    private String nameEn;
    private String fullName;
    private String fullNameEn;
    private String codeName;
    private Long provinceId;
    private String provinceName;
    private String administrativeUnitId;
    private Integer wardCount;
    private List<WardResponse> wards;
    
    // Constructors
    public DistrictResponse() {}
    
    public DistrictResponse(Long id, String name, String code, Long provinceId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.provinceId = provinceId;
    }
    
    public DistrictResponse(Long id, String name, String code, String nameEn, 
                           String fullName, String fullNameEn, String codeName, 
                           Long provinceId, String provinceName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.nameEn = nameEn;
        this.fullName = fullName;
        this.fullNameEn = fullNameEn;
        this.codeName = codeName;
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
    
    public Long getProvinceId() { return provinceId; }
    public void setProvinceId(Long provinceId) { this.provinceId = provinceId; }
    
    public String getProvinceName() { return provinceName; }
    public void setProvinceName(String provinceName) { this.provinceName = provinceName; }
    
    public String getAdministrativeUnitId() { return administrativeUnitId; }
    public void setAdministrativeUnitId(String administrativeUnitId) { 
        this.administrativeUnitId = administrativeUnitId; 
    }
    
    public Integer getWardCount() { return wardCount; }
    public void setWardCount(Integer wardCount) { this.wardCount = wardCount; }
    
    public List<WardResponse> getWards() { return wards; }
    public void setWards(List<WardResponse> wards) { this.wards = wards; }
    
    // Helper methods
    public String getDisplayName() {
        return fullName != null ? fullName : name;
    }
    
    public String getDisplayNameEn() {
        return fullNameEn != null ? fullNameEn : nameEn;
    }
    
    public String getFullAddressName() {
        if (provinceName != null) {
            return getDisplayName() + ", " + provinceName;
        }
        return getDisplayName();
    }
    
    @Override
    public String toString() {
        return "DistrictResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", fullName='" + fullName + '\'' +
                ", provinceId=" + provinceId +
                '}';
    }
}