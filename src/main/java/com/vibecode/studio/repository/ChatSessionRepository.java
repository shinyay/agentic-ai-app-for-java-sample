package com.vibecode.studio.repository;

import com.vibecode.studio.model.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Long> {
    
    List<ChatSession> findByProjectId(Long projectId);
    
    List<ChatSession> findByProjectIdAndUserId(Long projectId, String userId);
    
    Optional<ChatSession> findByIdAndUserId(Long id, String userId);
}