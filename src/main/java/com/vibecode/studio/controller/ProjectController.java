package com.vibecode.studio.controller;

import com.vibecode.studio.model.Project;
import com.vibecode.studio.repository.ProjectRepository;
import com.vibecode.studio.service.agent.AgentCore;
import com.vibecode.studio.service.cost.CostSentinel;
import com.vibecode.studio.service.cost.CostSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class ProjectController {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private AgentCore agentCore;
    
    @Autowired
    private CostSentinel costSentinel;
    
    @GetMapping
    public ResponseEntity<List<Project>> getUserProjects(@RequestParam String userId) {
        List<Project> projects = projectRepository.findByOwnerIdOrderByUpdatedAtDesc(userId);
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id, @RequestParam String userId) {
        Optional<Project> project = projectRepository.findByIdAndOwnerId(id, userId);
        return project.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/create")
    public ResponseEntity<ProjectCreationResponse> createProject(@RequestBody CreateProjectRequest request) {
        try {
            Project project = agentCore.createProjectFromPrompt(request.prompt(), request.userId());
            CostSummary costSummary = costSentinel.getCostSummary(request.userId());
            
            return ResponseEntity.ok(new ProjectCreationResponse(project, costSummary));
        } catch (SecurityException e) {
            return ResponseEntity.badRequest()
                .body(new ProjectCreationResponse(null, null, "Security validation failed: " + e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ProjectCreationResponse(null, null, e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Project> updateProjectStatus(@PathVariable Long id, 
                                                      @RequestParam String userId,
                                                      @RequestParam Project.ProjectStatus status) {
        Optional<Project> projectOpt = projectRepository.findByIdAndOwnerId(id, userId);
        if (projectOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Project project = projectOpt.get();
        project.setStatus(status);
        Project savedProject = projectRepository.save(project);
        
        return ResponseEntity.ok(savedProject);
    }
    
    @GetMapping("/{id}/cost")
    public ResponseEntity<ProjectCostResponse> getProjectCost(@PathVariable Long id, @RequestParam String userId) {
        Optional<Project> projectOpt = projectRepository.findByIdAndOwnerId(id, userId);
        if (projectOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        var totalCost = costSentinel.getTotalProjectCost(id);
        var userSummary = costSentinel.getCostSummary(userId);
        
        return ResponseEntity.ok(new ProjectCostResponse(totalCost, userSummary));
    }
    
    // DTOs
    public record CreateProjectRequest(String prompt, String userId) {}
    
    public record ProjectCreationResponse(Project project, CostSummary costSummary, String error) {
        public ProjectCreationResponse(Project project, CostSummary costSummary) {
            this(project, costSummary, null);
        }
    }
    
    public record ProjectCostResponse(java.math.BigDecimal projectCost, CostSummary userCostSummary) {}
}