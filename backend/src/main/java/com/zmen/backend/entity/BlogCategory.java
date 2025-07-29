package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "danh_muc_bai_viet")
public class BlogCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "ten_danh_muc", unique = true, nullable = false, length = 100)
    private String name;
    
    @Column(name = "slug", unique = true, nullable = false, length = 100)
    private String slug;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "hinh_anh", length = 500)
    private String image;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_danh_muc_cha")
    private BlogCategory parentCategory;
    
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogCategory> subCategories = new ArrayList<>();
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogPost> blogPosts = new ArrayList<>();
    
    @Column(name = "thu_tu_hien_thi", columnDefinition = "INT DEFAULT 0")
    private Integer displayOrder = 0;
    
    @Column(name = "kich_hoat", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active = true;
    
    @Column(name = "meta_title", length = 255)
    private String metaTitle;
    
    @Column(name = "meta_description", length = 500)
    private String metaDescription;
    
    @Column(name = "meta_keywords", length = 500)
    private String metaKeywords;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    // Constructors
    public BlogCategory() {}
    
    public BlogCategory(String name, String description) {
        this.name = name;
        this.description = description;
        this.slug = generateSlug(name);
    }
    
    // Business methods
    private String generateSlug(String name) {
        return name.toLowerCase()
                   .replaceAll("[^a-z0-9\\s-]", "")
                   .replaceAll("\\s+", "-")
                   .replaceAll("-+", "-")
                   .trim();
    }
    
    public boolean isParentOf(BlogCategory category) {
        return category.getParentCategory() != null && category.getParentCategory().getId().equals(this.id);
    }
    
    public boolean isChildOf(BlogCategory category) {
        return this.parentCategory != null && this.parentCategory.getId().equals(category.getId());
    }
    
    public int getPostCount() {
        return blogPosts != null ? blogPosts.size() : 0;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { 
        this.name = name;
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = generateSlug(name);
        }
    }
    
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
    public BlogCategory getParentCategory() { return parentCategory; }
    public void setParentCategory(BlogCategory parentCategory) { this.parentCategory = parentCategory; }
    
    public List<BlogCategory> getSubCategories() { return subCategories; }
    public void setSubCategories(List<BlogCategory> subCategories) { this.subCategories = subCategories; }
    
    public List<BlogPost> getBlogPosts() { return blogPosts; }
    public void setBlogPosts(List<BlogPost> blogPosts) { this.blogPosts = blogPosts; }
    
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public String getMetaTitle() { return metaTitle; }
    public void setMetaTitle(String metaTitle) { this.metaTitle = metaTitle; }
    
    public String getMetaDescription() { return metaDescription; }
    public void setMetaDescription(String metaDescription) { this.metaDescription = metaDescription; }
    
    public String getMetaKeywords() { return metaKeywords; }
    public void setMetaKeywords(String metaKeywords) { this.metaKeywords = metaKeywords; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}