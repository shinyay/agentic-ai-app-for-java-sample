package com.example.techassistant.service.agent;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class DevOpsAgent {

    private final ChatModel chatModel;

    public DevOpsAgent(@Qualifier("architectureModel") ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public AgentResponse checkApplicationHealth(String appName) {
        String prompt = buildHealthCheckPrompt(appName);
        String response = chatModel.chat(prompt);
        
        return AgentResponse.builder()
                .toolName("check_health")
                .result(response)
                .success(true)
                .build();
    }

    public AgentResponse analyzeInfrastructure(String infrastructure) {
        String prompt = buildInfrastructureAnalysisPrompt(infrastructure);
        String response = chatModel.chat(prompt);
        
        return AgentResponse.builder()
                .toolName("analyze_infrastructure")
                .result(response)
                .success(true)
                .build();
    }

    public AgentResponse generateDeploymentScript(String appName, String environment, String platform) {
        String prompt = buildDeploymentScriptPrompt(appName, environment, platform);
        String response = chatModel.chat(prompt);
        
        return AgentResponse.builder()
                .toolName("generate_deployment")
                .result(response)
                .success(true)
                .build();
    }

    public List<String> getAvailableTools() {
        return List.of(
            "check_health",
            "analyze_infrastructure", 
            "generate_deployment",
            "scale_application",
            "monitor_performance"
        );
    }

    private String buildHealthCheckPrompt(String appName) {
        return "You are a DevOps expert. Generate a comprehensive health check strategy for the application '" + 
               appName + "'. Include:\n" +
               "1. Key health indicators to monitor\n" +
               "2. Monitoring endpoints to implement\n" +
               "3. Alert thresholds and criteria\n" +
               "4. Automated remediation suggestions\n" +
               "Provide practical, actionable recommendations.";
    }

    private String buildInfrastructureAnalysisPrompt(String infrastructure) {
        return "You are a cloud infrastructure expert. Analyze the following infrastructure setup and provide recommendations:\n\n" +
               infrastructure + "\n\n" +
               "Focus on:\n" +
               "1. Security best practices\n" +
               "2. Cost optimization opportunities\n" +
               "3. Scalability improvements\n" +
               "4. Reliability and disaster recovery\n" +
               "5. Performance optimization\n" +
               "Provide specific, actionable recommendations.";
    }

    private String buildDeploymentScriptPrompt(String appName, String environment, String platform) {
        return "You are a deployment automation expert. Generate a deployment script for:\n" +
               "- Application: " + appName + "\n" +
               "- Environment: " + environment + "\n" +
               "- Platform: " + platform + "\n\n" +
               "Include:\n" +
               "1. Pre-deployment checks\n" +
               "2. Deployment steps with error handling\n" +
               "3. Post-deployment validation\n" +
               "4. Rollback procedures\n" +
               "Provide a complete, production-ready script with best practices.";
    }

    public static class AgentResponse {
        private String toolName;
        private String result;
        private boolean success;
        private String errorMessage;
        private long executionTimeMs;

        public static AgentResponseBuilder builder() {
            return new AgentResponseBuilder();
        }

        // Getters
        public String getToolName() { return toolName; }
        public String getResult() { return result; }
        public boolean isSuccess() { return success; }
        public String getErrorMessage() { return errorMessage; }
        public long getExecutionTimeMs() { return executionTimeMs; }

        public static class AgentResponseBuilder {
            private String toolName;
            private String result;
            private boolean success;
            private String errorMessage;
            private long executionTimeMs;

            public AgentResponseBuilder toolName(String toolName) {
                this.toolName = toolName;
                return this;
            }

            public AgentResponseBuilder result(String result) {
                this.result = result;
                return this;
            }

            public AgentResponseBuilder success(boolean success) {
                this.success = success;
                return this;
            }

            public AgentResponseBuilder errorMessage(String errorMessage) {
                this.errorMessage = errorMessage;
                return this;
            }

            public AgentResponseBuilder executionTimeMs(long executionTimeMs) {
                this.executionTimeMs = executionTimeMs;
                return this;
            }

            public AgentResponse build() {
                AgentResponse response = new AgentResponse();
                response.toolName = this.toolName;
                response.result = this.result;
                response.success = this.success;
                response.errorMessage = this.errorMessage;
                response.executionTimeMs = this.executionTimeMs;
                return response;
            }
        }
    }
}