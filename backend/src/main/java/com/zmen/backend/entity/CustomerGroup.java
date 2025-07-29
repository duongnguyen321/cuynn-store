package com.zmen.backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nhom_khach_hang")
public class CustomerGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_nhom", unique = true, nullable = false)
    private String groupName;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String description;
    
    @ManyToMany(mappedBy = "customerGroups", fetch = FetchType.LAZY)
    private List<User> users;
    
    // Constructors
    public CustomerGroup() {}
    
    public CustomerGroup(String groupName) {
        this.groupName = groupName;
    }
    
    public CustomerGroup(String groupName, String description) {
        this.groupName = groupName;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }
}