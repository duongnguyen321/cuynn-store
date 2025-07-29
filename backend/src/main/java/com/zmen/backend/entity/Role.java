package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vai_tro")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_vai_tro", nullable = false, unique = true)
    private String roleName;
    
    @Column(name = "ma_vai_tro", nullable = false, unique = true)
    private String roleCode;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String description;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "vai_tro_quyen",
        joinColumns = @JoinColumn(name = "id_vai_tro"),
        inverseJoinColumns = @JoinColumn(name = "id_quyen")
    )
    private Set<Permission> permissions = new HashSet<>();
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private RoleStatus status = RoleStatus.dang_hoat_dong;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDateTime updatedAt;
    
    public enum RoleStatus {
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
    public Role() {}
    
    public Role(String roleName, String roleCode) {
        this.roleName = roleName;
        this.roleCode = roleCode;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    
    public String getRoleCode() { return roleCode; }
    public void setRoleCode(String roleCode) { this.roleCode = roleCode; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Set<Permission> getPermissions() { return permissions; }
    public void setPermissions(Set<Permission> permissions) { this.permissions = permissions; }
    
    public RoleStatus getStatus() { return status; }
    public void setStatus(RoleStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    // Utility methods
    public void addPermission(Permission permission) {
        permissions.add(permission);
    }
    
    public void removePermission(Permission permission) {
        permissions.remove(permission);
    }
}