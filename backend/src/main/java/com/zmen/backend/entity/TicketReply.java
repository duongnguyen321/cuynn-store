package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "phan_hoi_yeu_cau")
public class TicketReply {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_yeu_cau_ho_tro", nullable = false)
    private SupportTicket supportTicket;
    
    @Column(name = "id_nguoi_phan_hoi", nullable = false)
    private Long replierId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "loai_nguoi_phan_hoi", nullable = false)
    private ActivityLog.PerformerType replierType;
    
    @Column(name = "noi_dung", columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public TicketReply() {}
    
    public TicketReply(SupportTicket supportTicket, Long replierId, ActivityLog.PerformerType replierType, String content) {
        this.supportTicket = supportTicket;
        this.replierId = replierId;
        this.replierType = replierType;
        this.content = content;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public SupportTicket getSupportTicket() { return supportTicket; }
    public void setSupportTicket(SupportTicket supportTicket) { this.supportTicket = supportTicket; }
    
    public Long getReplierId() { return replierId; }
    public void setReplierId(Long replierId) { this.replierId = replierId; }
    
    public ActivityLog.PerformerType getReplierType() { return replierType; }
    public void setReplierType(ActivityLog.PerformerType replierType) { this.replierType = replierType; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}