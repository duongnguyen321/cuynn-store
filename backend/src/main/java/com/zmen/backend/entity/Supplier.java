package com.zmen.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "nha_cung_cap")
public class Supplier {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_nha_cung_cap", unique = true, nullable = false)
    private String supplierName;
    
    @Column(name = "nguoi_lien_he")
    private String contactPerson;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "so_dien_thoai")
    private String phoneNumber;
    
    @Column(name = "dia_chi", columnDefinition = "TEXT")
    private String address;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private CooperationStatus status = CooperationStatus.dang_hop_tac;
    
    public enum CooperationStatus {
        dang_hop_tac, ngung_hop_tac
    }
    
    // Constructors
    public Supplier() {}
    
    public Supplier(String supplierName, String contactPerson) {
        this.supplierName = supplierName;
        this.contactPerson = contactPerson;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public CooperationStatus getStatus() { return status; }
    public void setStatus(CooperationStatus status) { this.status = status; }
    
    // Utility methods
    public boolean isActive() {
        return status == CooperationStatus.dang_hop_tac;
    }
}