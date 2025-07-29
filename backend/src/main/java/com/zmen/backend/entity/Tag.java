package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_tag", unique = true, nullable = false)
    private String tagName;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "ngay_xoa")
    private LocalDateTime deletedAt;
    
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Product> products;
    
    // Constructors
    public Tag() {}
    
    public Tag(String tagName) {
        this.tagName = tagName;
    }
    
    public Tag(String tagName, String description) {
        this.tagName = tagName;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTagName() { return tagName; }
    public void setTagName(String tagName) { this.tagName = tagName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }
    
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
    
    // Utility methods
    public boolean isDeleted() {
        return deletedAt != null;
    }
    
    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}