package com.vibecode.studio.repository;

import com.vibecode.studio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    List<Project> findByOwnerId(String ownerId);
    
    List<Project> findByOwnerIdAndStatus(String ownerId, Project.ProjectStatus status);
    
    Optional<Project> findByIdAndOwnerId(Long id, String ownerId);
    
    @Query("SELECT p FROM Project p WHERE p.ownerId = :ownerId ORDER BY p.updatedAt DESC")
    List<Project> findByOwnerIdOrderByUpdatedAtDesc(@Param("ownerId") String ownerId);
}