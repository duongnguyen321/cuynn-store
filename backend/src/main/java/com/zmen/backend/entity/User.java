package com.zmen.backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "nguoi_dung")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "username", unique = true)
    private String username;
    
    @Column(name = "mat_khau", nullable = false)
    private String password;
    
    @Column(name = "ho")
    private String firstName;
    
    @Column(name = "ho_ten_day_du")
    private String fullName;
    
    @Column(name = "ten")
    private String lastName;
    
    @Column(name = "so_dien_thoai")
    private String phone;
    
    @Column(name = "so_dien_thoai_chinh")
    private String phoneNumber;
    
    @Column(name = "email_verified")
    private Boolean emailVerified = false;
    
    @Column(name = "enabled")
    private Boolean enabled = true;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role = Role.USER;
    
    @Column(name = "ngay_tao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    @Column(name = "ngay_xoa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
    
    public enum Role {
        USER, ADMIN, MODERATOR
    }
    
    // Constructors
    public User() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    
    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }
    
    // Spring Security methods
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
    
    public boolean isEmailVerified() {
        return emailVerified != null && emailVerified;
    }
    
    public boolean isEnabled() {
        return enabled != null && enabled;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public Boolean getEmailVerified() { return emailVerified; }
    public void setEmailVerified(Boolean emailVerified) { this.emailVerified = emailVerified; }
    
    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
    
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
    
    public Date getDeletedAt() { return deletedAt; }
    public void setDeletedAt(Date deletedAt) { this.deletedAt = deletedAt; }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}

