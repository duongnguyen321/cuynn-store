package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "binh_luan_bai_viet")
public class BlogComment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bai_viet", nullable = false)
    private BlogPost blogPost;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_binh_luan_cha")
    private BlogComment parentComment;
    
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogComment> replies = new ArrayList<>();
    
    @Column(name = "ho_ten", length = 100)
    private String fullName;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "noi_dung", columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private CommentStatus status = CommentStatus.cho_duyet;
    
    @Column(name = "dia_chi_ip", length = 45)
    private String ipAddress;
    
    @Column(name = "user_agent", length = 500)
    private String userAgent;
    
    @Column(name = "like_count", columnDefinition = "INT DEFAULT 0")
    private Integer likeCount = 0;
    
    @Column(name = "dislike_count", columnDefinition = "INT DEFAULT 0")
    private Integer dislikeCount = 0;
    
    @Column(name = "da_bao_cao", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean reported = false;
    
    @Column(name = "ly_do_tu_choi", columnDefinition = "TEXT")
    private String rejectionReason;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_duyet")
    private User approvedBy;
    
    @Column(name = "thoi_gian_duyet")
    private LocalDateTime approvedAt;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    public enum CommentStatus {
        cho_duyet, da_duyet, tu_choi, da_xoa, spam
    }
    
    // Constructors
    public BlogComment() {}
    
    public BlogComment(BlogPost blogPost, String content) {
        this.blogPost = blogPost;
        this.content = content;
    }
    
    public BlogComment(BlogPost blogPost, User user, String content) {
        this.blogPost = blogPost;
        this.user = user;
        this.content = content;
        if (user != null) {
            this.fullName = user.getFullName();
            this.email = user.getEmail();
        }
    }
    
    // Business methods
    public void approve(User approver) {
        this.status = CommentStatus.da_duyet;
        this.approvedBy = approver;
        this.approvedAt = LocalDateTime.now();
    }
    
    public void reject(User rejector, String reason) {
        this.status = CommentStatus.tu_choi;
        this.approvedBy = rejector;
        this.approvedAt = LocalDateTime.now();
        this.rejectionReason = reason;
    }
    
    public void markAsSpam() {
        this.status = CommentStatus.spam;
        this.reported = true;
    }
    
    public boolean isReply() {
        return this.parentComment != null;
    }
    
    public boolean canBeRepliedTo() {
        return this.status == CommentStatus.da_duyet && this.deletedAt == null;
    }
    
    public void incrementLike() {
        this.likeCount = (this.likeCount == null ? 0 : this.likeCount) + 1;
    }
    
    public void incrementDislike() {
        this.dislikeCount = (this.dislikeCount == null ? 0 : this.dislikeCount) + 1;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public BlogPost getBlogPost() { return blogPost; }
    public void setBlogPost(BlogPost blogPost) { this.blogPost = blogPost; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public BlogComment getParentComment() { return parentComment; }
    public void setParentComment(BlogComment parentComment) { this.parentComment = parentComment; }
    
    public List<BlogComment> getReplies() { return replies; }
    public void setReplies(List<BlogComment> replies) { this.replies = replies; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public CommentStatus getStatus() { return status; }
    public void setStatus(CommentStatus status) { this.status = status; }
    
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    
    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
    
    public Integer getLikeCount() { return likeCount; }
    public void setLikeCount(Integer likeCount) { this.likeCount = likeCount; }
    
    public Integer getDislikeCount() { return dislikeCount; }
    public void setDislikeCount(Integer dislikeCount) { this.dislikeCount = dislikeCount; }
    
    public Boolean getReported() { return reported; }
    public void setReported(Boolean reported) { this.reported = reported; }
    
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
    
    public User getApprovedBy() { return approvedBy; }
    public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }
    
    public LocalDateTime getApprovedAt() { return approvedAt; }
    public void setApprovedAt(LocalDateTime approvedAt) { this.approvedAt = approvedAt; }
    
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