package com.vibecode.studio.repository;

import com.vibecode.studio.model.CostEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CostEntryRepository extends JpaRepository<CostEntry, Long> {
    
    List<CostEntry> findByProjectId(Long projectId);
    
    List<CostEntry> findByUserId(String userId);
    
    @Query("SELECT COALESCE(SUM(c.amount), 0) FROM CostEntry c WHERE c.project.id = :projectId")
    BigDecimal getTotalCostByProject(@Param("projectId") Long projectId);
    
    @Query("SELECT COALESCE(SUM(c.amount), 0) FROM CostEntry c WHERE c.userId = :userId AND c.createdAt >= :startDate")
    BigDecimal getTotalCostByUserSince(@Param("userId") String userId, @Param("startDate") LocalDateTime startDate);
    
    @Query("SELECT c FROM CostEntry c WHERE c.project.id = :projectId ORDER BY c.createdAt DESC")
    List<CostEntry> findByProjectIdOrderByCreatedAtDesc(@Param("projectId") Long projectId);
}