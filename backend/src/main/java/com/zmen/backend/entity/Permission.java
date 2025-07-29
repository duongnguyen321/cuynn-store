package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quyen")
public class Permission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_quyen", nullable = false, unique = true)
    private String permissionName;
    
    @Column(name = "ma_quyen", nullable = false, unique = true)
    private String permissionCode;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "nhom_quyen")
    private String permissionGroup;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private PermissionStatus status = PermissionStatus.dang_hoat_dong;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDateTime updatedAt;
    
    public enum PermissionStatus {
        dang_hoat_dong, tam_ngung
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
    public Permission() {}
    
    public Permission(String permissionName, String permissionCode) {
        this.permissionName = permissionName;
        this.permissionCode = permissionCode;
    }
    
    public Permission(String permissionName, String permissionCode, String permissionGroup) {
        this.permissionName = permissionName;
        this.permissionCode = permissionCode;
        this.permissionGroup = permissionGroup;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPermissionName() { return permissionName; }
    public void setPermissionName(String permissionName) { this.permissionName = permissionName; }
    
    public String getPermissionCode() { return permissionCode; }
    public void setPermissionCode(String permissionCode) { this.permissionCode = permissionCode; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getPermissionGroup() { return permissionGroup; }
    public void setPermissionGroup(String permissionGroup) { this.permissionGroup = permissionGroup; }
    
    public PermissionStatus getStatus() { return status; }
    public void setStatus(PermissionStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}