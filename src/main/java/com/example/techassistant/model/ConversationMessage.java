package com.example.techassistant.model;

import java.time.LocalDateTime;
import java.util.List;

public class ConversationMessage {
    
    private String id;
    private String content;
    private MessageRole role;
    private LocalDateTime timestamp;
    private MessageMetadata metadata;

    public ConversationMessage() {
        this.timestamp = LocalDateTime.now();
        this.metadata = new MessageMetadata();
    }

    public ConversationMessage(String content, MessageRole role) {
        this();
        this.content = content;
        this.role = role;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageRole getRole() {
        return role;
    }

    public void setRole(MessageRole role) {
        this.role = role;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public MessageMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(MessageMetadata metadata) {
        this.metadata = metadata;
    }

    public enum MessageRole {
        USER, ASSISTANT, SYSTEM
    }

    public static class MessageMetadata {
        private String modelUsed;
        private Integer tokens;
        private Double confidenceScore;
        private List<String> sources;

        // Getters and Setters
        public String getModelUsed() {
            return modelUsed;
        }

        public void setModelUsed(String modelUsed) {
            this.modelUsed = modelUsed;
        }

        public Integer getTokens() {
            return tokens;
        }

        public void setTokens(Integer tokens) {
            this.tokens = tokens;
        }

        public Double getConfidenceScore() {
            return confidenceScore;
        }

        public void setConfidenceScore(Double confidenceScore) {
            this.confidenceScore = confidenceScore;
        }

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }
    }
}