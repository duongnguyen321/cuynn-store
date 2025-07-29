package com.zmen.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(max = 100)
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 255)
    private String password;

    @Size(max = 100)
    private String fullName;

    @Size(max = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CUSTOMER;

    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    // Relationships
    @OneToOne(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    private ThongTinNguoiDung thongTinNguoiDung;
    
    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    private Set<DiaChiNguoiDung> diaChis;
    
    @OneToOne(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    private NhanVien nhanVien;
    
    @ManyToMany
    @JoinTable(
        name = "nguoi_dung_vai_tro",
        joinColumns = @JoinColumn(name = "nguoi_dung_id"),
        inverseJoinColumns = @JoinColumn(name = "vai_tro_id")
    )
    private Set<VaiTro> vaiTros;

    public enum Role {
        CUSTOMER, ADMIN, EMPLOYEE
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

    // UserDetails implementation
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public void setAccountNonExpired(boolean accountNonExpired) { this.accountNonExpired = accountNonExpired; }
    public void setAccountNonLocked(boolean accountNonLocked) { this.accountNonLocked = accountNonLocked; }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) { this.credentialsNonExpired = credentialsNonExpired; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    
    // Relationship getters and setters
    public ThongTinNguoiDung getThongTinNguoiDung() { return thongTinNguoiDung; }
    public void setThongTinNguoiDung(ThongTinNguoiDung thongTinNguoiDung) { this.thongTinNguoiDung = thongTinNguoiDung; }
    
    public Set<DiaChiNguoiDung> getDiaChis() { return diaChis; }
    public void setDiaChis(Set<DiaChiNguoiDung> diaChis) { this.diaChis = diaChis; }
    
    public NhanVien getNhanVien() { return nhanVien; }
    public void setNhanVien(NhanVien nhanVien) { this.nhanVien = nhanVien; }
    
    public Set<VaiTro> getVaiTros() { return vaiTros; }
    public void setVaiTros(Set<VaiTro> vaiTros) { this.vaiTros = vaiTros; }
}

