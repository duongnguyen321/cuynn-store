package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hinh_anh_danh_gia")
public class ReviewImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_danh_gia", nullable = false)
    private ProductReview review;
    
    @Column(name = "duong_dan", nullable = false)
    private String imagePath;
    
    @Column(name = "ten_file")
    private String fileName;
    
    @Column(name = "loai_file")
    private String fileType;
    
    @Column(name = "kich_thuoc")
    private Long fileSize;
    
    @Column(name = "thu_tu_hien_thi")
    private Integer displayOrder = 0;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public ReviewImage() {}
    
    public ReviewImage(ProductReview review, String imagePath) {
        this.review = review;
        this.imagePath = imagePath;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public ProductReview getReview() { return review; }
    public void setReview(ProductReview review) { this.review = review; }
    
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}