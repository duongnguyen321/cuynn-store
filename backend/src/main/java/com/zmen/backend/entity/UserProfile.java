package com.zmen.backend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "thong_tin_nguoi_dung")
public class UserProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "id_nguoi_dung", unique = true, nullable = false)
    private Long userId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung", insertable = false, updatable = false)
    private User user;
    
    @Column(name = "ho_ten_day_du")
    private String fullName;
    
    @Column(name = "so_dien_thoai")
    private String phoneNumber;
    
    @Column(name = "avatar")
    private String avatar;
    
    @Column(name = "gioi_tinh")
    private String gender;
    
    @Column(name = "ngay_sinh")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @Column(name = "diem_tich_luy")
    private Integer loyaltyPoints = 0;
    
    @Column(name = "id_hang_thanh_vien")
    private Long membershipLevelId;
    
    @Column(name = "id_cong_ty")
    private Long companyId;
    
    // Constructors
    public UserProfile() {}
    
    public UserProfile(Long userId) {
        this.userId = userId;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public User getUser() { return user; }
    public void setUser(User user) { 
        this.user = user; 
        if (user != null) {
            this.userId = user.getId();
        }
    }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public Integer getLoyaltyPoints() { return loyaltyPoints; }
    public void setLoyaltyPoints(Integer loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }
    
    public Long getMembershipLevelId() { return membershipLevelId; }
    public void setMembershipLevelId(Long membershipLevelId) { this.membershipLevelId = membershipLevelId; }
    
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
}

