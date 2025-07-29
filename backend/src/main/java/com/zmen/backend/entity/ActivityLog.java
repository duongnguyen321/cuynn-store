package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "nhat_ky_hoat_dong")
public class ActivityLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "id_nguoi_thuc_hien")
    private Long performerId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "loai_nguoi_thuc_hien", nullable = false)
    private PerformerType performerType;
    
    @Column(name = "hanh_dong", nullable = false)
    private String action;
    
    @Column(name = "doi_tuong", nullable = false)
    private String targetObject;
    
    @Column(name = "id_doi_tuong", nullable = false)
    private String targetObjectId;
    
    @Column(name = "du_lieu_thay_doi", columnDefinition = "jsonb")
    private String changeData;
    
    @Column(name = "ghi_chu", columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    public enum PerformerType {
        NHAN_VIEN, NGUOI_DUNG, HE_THONG
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public ActivityLog() {}
    
    public ActivityLog(Long performerId, PerformerType performerType, String action, String targetObject, String targetObjectId) {
        this.performerId = performerId;
        this.performerType = performerType;
        this.action = action;
        this.targetObject = targetObject;
        this.targetObjectId = targetObjectId;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getPerformerId() { return performerId; }
    public void setPerformerId(Long performerId) { this.performerId = performerId; }
    
    public PerformerType getPerformerType() { return performerType; }
    public void setPerformerType(PerformerType performerType) { this.performerType = performerType; }
    
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    
    public String getTargetObject() { return targetObject; }
    public void setTargetObject(String targetObject) { this.targetObject = targetObject; }
    
    public String getTargetObjectId() { return targetObjectId; }
    public void setTargetObjectId(String targetObjectId) { this.targetObjectId = targetObjectId; }
    
    public String getChangeData() { return changeData; }
    public void setChangeData(String changeData) { this.changeData = changeData; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}