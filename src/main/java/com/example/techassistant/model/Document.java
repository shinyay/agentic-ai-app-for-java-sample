package com.example.techassistant.model;

import java.time.LocalDateTime;
import java.util.List;

public class Document {
    
    private String id;
    private String category;
    private String title;
    private String fileName;
    private String fileType;
    private String contentHash;
    private LocalDateTime uploadDate;
    private LocalDateTime lastModified;
    private String uploadedBy;
    private List<String> tags;
    private AccessLevel accessLevel;
    private DocumentMetadata metadata;
    private VectorStoreReferences vectorStoreReferences;
    private ProcessingStatus processingStatus;

    public Document() {
        this.uploadDate = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.processingStatus = ProcessingStatus.PENDING;
        this.accessLevel = AccessLevel.PRIVATE;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getContentHash() {
        return contentHash;
    }

    public void setContentHash(String contentHash) {
        this.contentHash = contentHash;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public DocumentMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(DocumentMetadata metadata) {
        this.metadata = metadata;
    }

    public VectorStoreReferences getVectorStoreReferences() {
        return vectorStoreReferences;
    }

    public void setVectorStoreReferences(VectorStoreReferences vectorStoreReferences) {
        this.vectorStoreReferences = vectorStoreReferences;
    }

    public ProcessingStatus getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(ProcessingStatus processingStatus) {
        this.processingStatus = processingStatus;
    }

    public enum AccessLevel {
        PUBLIC, RESTRICTED, PRIVATE
    }

    public enum ProcessingStatus {
        PENDING, PROCESSING, COMPLETED, FAILED
    }
}