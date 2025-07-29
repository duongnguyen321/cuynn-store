package com.zmen.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "dia_chi_nguoi_dung")
public class UserAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung", nullable = false)
    private User user;
    
    @Column(name = "tinh_thanh", nullable = false)
    private String province;
    
    @Column(name = "quan_huyen", nullable = false)
    private String district;
    
    @Column(name = "phuong_xa", nullable = false)
    private String ward;
    
    @Column(name = "chi_tiet", nullable = false)
    private String detailAddress;
    
    @Column(name = "la_mac_dinh", nullable = false)
    private Boolean isDefault = false;
    
    // Constructors
    public UserAddress() {}
    
    public UserAddress(User user, String province, String district, String ward, String detailAddress) {
        this.user = user;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.detailAddress = detailAddress;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
    
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    
    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }
    
    public String getDetailAddress() { return detailAddress; }
    public void setDetailAddress(String detailAddress) { this.detailAddress = detailAddress; }
    
    public Boolean getIsDefault() { return isDefault; }
    public void setIsDefault(Boolean isDefault) { this.isDefault = isDefault; }
    
    public String getFullAddress() {
        return detailAddress + ", " + ward + ", " + district + ", " + province;
    }
}