package com.zmen.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "yeu_cau_ho_tro")
public class SupportTicket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ma_yeu_cau", unique = true, nullable = false)
    private String ticketCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don")
    private Order relatedOrder;
    
    @Column(name = "tieu_de", nullable = false)
    private String title;
    
    @Column(name = "noi_dung", columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien_phu_trach")
    private Employee assignedEmployee;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private TicketStatus status = TicketStatus.moi;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "do_uu_tien", nullable = false)
    private Priority priority = Priority.trung_binh;
    
    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "ngay_dong")
    private LocalDateTime closedAt;
    
    @OneToMany(mappedBy = "supportTicket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TicketReply> replies;
    
    public enum TicketStatus {
        moi, dang_xu_ly, cho_phan_hoi, da_dong
    }
    
    public enum Priority {
        thap, trung_binh, cao, khan_cap
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public SupportTicket() {}
    
    public SupportTicket(String ticketCode, User user, String title, String content) {
        this.ticketCode = ticketCode;
        this.user = user;
        this.title = title;
        this.content = content;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTicketCode() { return ticketCode; }
    public void setTicketCode(String ticketCode) { this.ticketCode = ticketCode; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Order getRelatedOrder() { return relatedOrder; }
    public void setRelatedOrder(Order relatedOrder) { this.relatedOrder = relatedOrder; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public Employee getAssignedEmployee() { return assignedEmployee; }
    public void setAssignedEmployee(Employee assignedEmployee) { this.assignedEmployee = assignedEmployee; }
    
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
    
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getClosedAt() { return closedAt; }
    public void setClosedAt(LocalDateTime closedAt) { this.closedAt = closedAt; }
    
    public List<TicketReply> getReplies() { return replies; }
    public void setReplies(List<TicketReply> replies) { this.replies = replies; }
    
    // Utility methods
    public boolean isOpen() {
        return status != TicketStatus.da_dong;
    }
    
    public void close() {
        this.status = TicketStatus.da_dong;
        this.closedAt = LocalDateTime.now();
    }
    
    public boolean isHighPriority() {
        return priority == Priority.cao || priority == Priority.khan_cap;
    }
}