package com.zmen.backend.dto;

import java.util.List;

public class ProvinceResponse {
    
    private Long id;
    private String name;
    private String code;
    private String nameEn;
    private String fullName;
    private String fullNameEn;
    private String codeName;
    private String administrativeUnitId;
    private String administrativeRegionId;
    private Integer districtCount;
    private List<DistrictResponse> districts;
    
    // Constructors
    public ProvinceResponse() {}
    
    public ProvinceResponse(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
    
    public ProvinceResponse(Long id, String name, String code, String nameEn, 
                           String fullName, String fullNameEn, String codeName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.nameEn = nameEn;
        this.fullName = fullName;
        this.fullNameEn = fullNameEn;
        this.codeName = codeName;
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
    
    public String getAdministrativeUnitId() { return administrativeUnitId; }
    public void setAdministrativeUnitId(String administrativeUnitId) { 
        this.administrativeUnitId = administrativeUnitId; 
    }
    
    public String getAdministrativeRegionId() { return administrativeRegionId; }
    public void setAdministrativeRegionId(String administrativeRegionId) { 
        this.administrativeRegionId = administrativeRegionId; 
    }
    
    public Integer getDistrictCount() { return districtCount; }
    public void setDistrictCount(Integer districtCount) { this.districtCount = districtCount; }
    
    public List<DistrictResponse> getDistricts() { return districts; }
    public void setDistricts(List<DistrictResponse> districts) { this.districts = districts; }
    
    // Helper methods
    public String getDisplayName() {
        return fullName != null ? fullName : name;
    }
    
    public String getDisplayNameEn() {
        return fullNameEn != null ? fullNameEn : nameEn;
    }
    
    @Override
    public String toString() {
        return "ProvinceResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}