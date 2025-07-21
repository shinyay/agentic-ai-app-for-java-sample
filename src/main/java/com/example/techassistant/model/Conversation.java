package com.example.techassistant.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Conversation {
    
    private String id;
    private String userId;
    private String title;
    private String topic;
    private LocalDateTime startTime;
    private LocalDateTime lastActivity;
    private ConversationStatus status;
    private List<ConversationMessage> messages;
    private ConversationMetadata metadata;

    public Conversation() {
        this.messages = new ArrayList<>();
        this.metadata = new ConversationMetadata();
        this.startTime = LocalDateTime.now();
        this.lastActivity = LocalDateTime.now();
        this.status = ConversationStatus.ACTIVE;
    }

    public Conversation(String userId, String title) {
        this();
        this.userId = userId;
        this.title = title;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    public ConversationStatus getStatus() {
        return status;
    }

    public void setStatus(ConversationStatus status) {
        this.status = status;
    }

    public List<ConversationMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ConversationMessage> messages) {
        this.messages = messages;
    }

    public ConversationMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(ConversationMetadata metadata) {
        this.metadata = metadata;
    }

    // Helper methods
    public void addMessage(ConversationMessage message) {
        this.messages.add(message);
        this.lastActivity = LocalDateTime.now();
        this.metadata.incrementTotalMessages();
    }

    public enum ConversationStatus {
        ACTIVE, ARCHIVED, DELETED
    }
}