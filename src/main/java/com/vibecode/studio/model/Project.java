package com.vibecode.studio.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "owner_id", nullable = false)
    private String ownerId;
    
    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.DRAFT;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "git_repository_url")
    private String gitRepositoryUrl;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChatSession> chatSessions = new ArrayList<>();
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CostEntry> costEntries = new ArrayList<>();
    
    public enum ProjectStatus {
        DRAFT, PLANNING, BUILDING, TESTING, DEPLOYED, FAILED
    }
    
    // Constructors
    public Project() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public Project(String name, String description, String ownerId) {
        this();
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
    
    public ProjectStatus getStatus() { return status; }
    public void setStatus(ProjectStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public String getGitRepositoryUrl() { return gitRepositoryUrl; }
    public void setGitRepositoryUrl(String gitRepositoryUrl) { this.gitRepositoryUrl = gitRepositoryUrl; }
    
    public List<ChatSession> getChatSessions() { return chatSessions; }
    public void setChatSessions(List<ChatSession> chatSessions) { this.chatSessions = chatSessions; }
    
    public List<CostEntry> getCostEntries() { return costEntries; }
    public void setCostEntries(List<CostEntry> costEntries) { this.costEntries = costEntries; }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}