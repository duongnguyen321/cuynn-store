package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "thong_tin_nguoi_dung")
public class UserProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_nguoi_dung", unique = true, nullable = false)
    private User user;
    
    @Column(name = "ho_ten", nullable = false)
    private String fullName;
    
    @Column(name = "avatar")
    private String avatar;
    
    @Column(name = "so_dien_thoai", unique = true)
    private String phoneNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "gioi_tinh")
    private Gender gender;
    
    @Column(name = "ngay_sinh")
    private LocalDate dateOfBirth;
    
    @Column(name = "diem_tich_luy", nullable = false)
    private Integer loyaltyPoints = 0;
    
    @ManyToOne
    @JoinColumn(name = "id_hang_thanh_vien")
    private MembershipTier membershipTier;
    
    @ManyToOne
    @JoinColumn(name = "id_cong_ty")
    private Company company;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDateTime updatedAt;
    
    public enum Gender {
        nam, nu, khac
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public UserProfile() {}
    
    public UserProfile(User user, String fullName) {
        this.user = user;
        this.fullName = fullName;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public Integer getLoyaltyPoints() { return loyaltyPoints; }
    public void setLoyaltyPoints(Integer loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }
    
    public MembershipTier getMembershipTier() { return membershipTier; }
    public void setMembershipTier(MembershipTier membershipTier) { this.membershipTier = membershipTier; }
    
    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}