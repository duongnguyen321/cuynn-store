package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bai_viet")
public class BlogPost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "tieu_de", nullable = false, length = 255)
    private String title;
    
    @Column(name = "slug", unique = true, nullable = false, length = 255)
    private String slug;
    
    @Column(name = "tom_tat", columnDefinition = "TEXT")
    private String summary;
    
    @Column(name = "noi_dung", columnDefinition = "LONGTEXT", nullable = false)
    private String content;
    
    @Column(name = "hinh_anh_dai_dien", length = 500)
    private String featuredImage;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tac_gia", nullable = false)
    private User author;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_danh_muc")
    private BlogCategory category;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private PostStatus status = PostStatus.ban_thao;
    
    @Column(name = "luot_xem", columnDefinition = "INT DEFAULT 0")
    private Integer viewCount = 0;
    
    @Column(name = "co_binh_luan", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean allowComments = true;
    
    @Column(name = "noi_bat", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean featured = false;
    
    @Column(name = "thu_tu_hien_thi", columnDefinition = "INT DEFAULT 0")
    private Integer displayOrder = 0;
    
    @Column(name = "meta_title", length = 255)
    private String metaTitle;
    
    @Column(name = "meta_description", length = 500)
    private String metaDescription;
    
    @Column(name = "meta_keywords", length = 500)
    private String metaKeywords;
    
    @Column(name = "thoi_gian_xuat_ban")
    private LocalDateTime publishedAt;
    
    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogComment> comments = new ArrayList<>();
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    public enum PostStatus {
        ban_thao, da_xuat_ban, tam_ngung, da_xoa
    }
    
    // Constructors
    public BlogPost() {}
    
    public BlogPost(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.slug = generateSlug(title);
    }
    
    // Business methods
    private String generateSlug(String title) {
        return title.toLowerCase()
                   .replaceAll("[^a-z0-9\\s-]", "")
                   .replaceAll("\\s+", "-")
                   .replaceAll("-+", "-")
                   .trim();
    }
    
    public void publish() {
        this.status = PostStatus.da_xuat_ban;
        this.publishedAt = LocalDateTime.now();
    }
    
    public void incrementViewCount() {
        this.viewCount = (this.viewCount == null ? 0 : this.viewCount) + 1;
    }
    
    public boolean isPublished() {
        return this.status == PostStatus.da_xuat_ban && this.publishedAt != null;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { 
        this.title = title;
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = generateSlug(title);
        }
    }
    
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public String getFeaturedImage() { return featuredImage; }
    public void setFeaturedImage(String featuredImage) { this.featuredImage = featuredImage; }
    
    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }
    
    public BlogCategory getCategory() { return category; }
    public void setCategory(BlogCategory category) { this.category = category; }
    
    public PostStatus getStatus() { return status; }
    public void setStatus(PostStatus status) { this.status = status; }
    
    public Integer getViewCount() { return viewCount; }
    public void setViewCount(Integer viewCount) { this.viewCount = viewCount; }
    
    public Boolean getAllowComments() { return allowComments; }
    public void setAllowComments(Boolean allowComments) { this.allowComments = allowComments; }
    
    public Boolean getFeatured() { return featured; }
    public void setFeatured(Boolean featured) { this.featured = featured; }
    
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    
    public String getMetaTitle() { return metaTitle; }
    public void setMetaTitle(String metaTitle) { this.metaTitle = metaTitle; }
    
    public String getMetaDescription() { return metaDescription; }
    public void setMetaDescription(String metaDescription) { this.metaDescription = metaDescription; }
    
    public String getMetaKeywords() { return metaKeywords; }
    public void setMetaKeywords(String metaKeywords) { this.metaKeywords = metaKeywords; }
    
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDateTime publishedAt) { this.publishedAt = publishedAt; }
    
    public List<BlogComment> getComments() { return comments; }
    public void setComments(List<BlogComment> comments) { this.comments = comments; }
    
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