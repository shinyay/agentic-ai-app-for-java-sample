package com.example.techassistant.model;

import java.util.List;

public class ConversationMetadata {
    
    private Integer totalMessages;
    private List<String> aiModelsUsed;
    private List<String> primaryTopics;

    public ConversationMetadata() {
        this.totalMessages = 0;
    }

    // Getters and Setters
    public Integer getTotalMessages() {
        return totalMessages;
    }

    public void setTotalMessages(Integer totalMessages) {
        this.totalMessages = totalMessages;
    }

    public List<String> getAiModelsUsed() {
        return aiModelsUsed;
    }

    public void setAiModelsUsed(List<String> aiModelsUsed) {
        this.aiModelsUsed = aiModelsUsed;
    }

    public List<String> getPrimaryTopics() {
        return primaryTopics;
    }

    public void setPrimaryTopics(List<String> primaryTopics) {
        this.primaryTopics = primaryTopics;
    }

    // Helper methods
    public void incrementTotalMessages() {
        this.totalMessages++;
    }
}