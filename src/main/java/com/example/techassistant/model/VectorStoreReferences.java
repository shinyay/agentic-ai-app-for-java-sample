package com.example.techassistant.model;

import java.util.List;

public class VectorStoreReferences {
    private List<String> embeddingIds;
    private Integer chunkCount;
    private String indexName;

    // Getters and Setters
    public List<String> getEmbeddingIds() {
        return embeddingIds;
    }

    public void setEmbeddingIds(List<String> embeddingIds) {
        this.embeddingIds = embeddingIds;
    }

    public Integer getChunkCount() {
        return chunkCount;
    }

    public void setChunkCount(Integer chunkCount) {
        this.chunkCount = chunkCount;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
}