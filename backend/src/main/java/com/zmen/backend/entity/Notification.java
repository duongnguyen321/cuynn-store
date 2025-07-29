package com.zmen.backend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "thong_bao")
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "id_nguoi_dung", nullable = false)
    private Long userId;
    
    @Column(name = "tieu_de", nullable = false)
    private String title;
    
    @Column(name = "noi_dung", columnDefinition = "TEXT")
    private String message;
    
    @Column(name = "loai")
    private String type;
    
    @Column(name = "da_doc")
    private Boolean isRead = false;
    
    @Column(name = "ngay_tao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    // Constructors
    public Notification() {
        this.createdAt = new Date();
    }
    
    public Notification(Long userId, String title, String message, String type) {
        this();
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.type = type;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public Boolean getIsRead() { return isRead; }
    public void setIsRead(Boolean isRead) { this.isRead = isRead; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}

