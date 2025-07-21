package com.example.techassistant.controller;

import com.example.techassistant.service.agent.DevOpsAgent;
import com.example.techassistant.service.agent.DevOpsAgent.AgentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/agents")
@CrossOrigin(origins = "*")
public class AgentController {

    private final DevOpsAgent devOpsAgent;

    public AgentController(DevOpsAgent devOpsAgent) {
        this.devOpsAgent = devOpsAgent;
    }

    @GetMapping("/devops/tools")
    public ResponseEntity<List<String>> getDevOpsTools() {
        return ResponseEntity.ok(devOpsAgent.getAvailableTools());
    }

    @PostMapping("/devops/health-check")
    public ResponseEntity<AgentResponse> checkHealth(@Valid @RequestBody HealthCheckRequest request) {
        try {
            AgentResponse response = devOpsAgent.checkApplicationHealth(request.getAppName());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(createErrorResponse("check_health", e.getMessage()));
        }
    }

    @PostMapping("/devops/analyze-infrastructure")
    public ResponseEntity<AgentResponse> analyzeInfrastructure(@Valid @RequestBody InfrastructureAnalysisRequest request) {
        try {
            AgentResponse response = devOpsAgent.analyzeInfrastructure(request.getInfrastructure());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(createErrorResponse("analyze_infrastructure", e.getMessage()));
        }
    }

    @PostMapping("/devops/generate-deployment")
    public ResponseEntity<AgentResponse> generateDeployment(@Valid @RequestBody DeploymentRequest request) {
        try {
            AgentResponse response = devOpsAgent.generateDeploymentScript(
                    request.getAppName(), 
                    request.getEnvironment(), 
                    request.getPlatform()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(createErrorResponse("generate_deployment", e.getMessage()));
        }
    }

    private AgentResponse createErrorResponse(String toolName, String errorMessage) {
        return AgentResponse.builder()
                .toolName(toolName)
                .result("Error occurred during agent execution")
                .success(false)
                .errorMessage(errorMessage)
                .build();
    }

    // Request DTOs
    public static class HealthCheckRequest {
        @NotBlank(message = "Application name is required")
        private String appName;

        public String getAppName() { return appName; }
        public void setAppName(String appName) { this.appName = appName; }
    }

    public static class InfrastructureAnalysisRequest {
        @NotBlank(message = "Infrastructure description is required")
        private String infrastructure;

        public String getInfrastructure() { return infrastructure; }
        public void setInfrastructure(String infrastructure) { this.infrastructure = infrastructure; }
    }

    public static class DeploymentRequest {
        @NotBlank(message = "Application name is required")
        private String appName;
        
        @NotBlank(message = "Environment is required")
        private String environment;
        
        @NotBlank(message = "Platform is required")
        private String platform;

        public String getAppName() { return appName; }
        public void setAppName(String appName) { this.appName = appName; }

        public String getEnvironment() { return environment; }
        public void setEnvironment(String environment) { this.environment = environment; }

        public String getPlatform() { return platform; }
        public void setPlatform(String platform) { this.platform = platform; }
    }
}