package com.example.techassistant.controller;

import com.example.techassistant.service.ai.CodeReviewService;
import com.example.techassistant.service.ai.CodeReviewService.AnalysisType;
import com.example.techassistant.service.ai.CodeReviewService.CodeReviewResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")
public class CodeReviewController {

    private final CodeReviewService codeReviewService;

    public CodeReviewController(CodeReviewService codeReviewService) {
        this.codeReviewService = codeReviewService;
    }

    @PostMapping("/code-review")
    public ResponseEntity<CodeReviewResponse> reviewCode(
            @Valid @RequestBody CodeReviewRequest request) {
        
        try {
            CodeReviewResult result = codeReviewService.reviewCode(
                request.getCode(), 
                request.getLanguage(), 
                request.getAnalysisType()
            );
            
            CodeReviewResponse response = new CodeReviewResponse();
            response.setOverallScore(result.getOverallScore());
            response.setReviewSummary(result.getReviewSummary());
            response.setSecurityIssues(result.getSecurityIssues());
            response.setPerformanceSuggestions(result.getPerformanceSuggestions());
            response.setLanguage(result.getLanguage());
            response.setAnalysisType(result.getAnalysisType().toString());
            
            if (result.getQualityMetrics() != null) {
                QualityMetricsResponse metrics = new QualityMetricsResponse();
                metrics.setReadability(result.getQualityMetrics().getReadability());
                metrics.setMaintainability(result.getQualityMetrics().getMaintainability());
                metrics.setComplexity(result.getQualityMetrics().getComplexity());
                response.setQualityMetrics(metrics);
            }
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(createErrorResponse("Error during code review: " + e.getMessage()));
        }
    }

    @PostMapping("/code-review/with-improvements")
    public ResponseEntity<CodeReviewWithImprovementsResponse> reviewCodeWithSuggestions(
            @Valid @RequestBody CodeImprovementRequest request) {
        
        try {
            CodeReviewResult result = codeReviewService.reviewCodeWithSuggestions(
                request.getCode(), 
                request.getLanguage()
            );
            
            CodeReviewWithImprovementsResponse response = new CodeReviewWithImprovementsResponse();
            response.setOriginalCode(result.getOriginalCode());
            response.setImprovedCode(result.getImprovedCode());
            response.setOverallScore(result.getOverallScore());
            response.setReviewSummary(result.getReviewSummary());
            response.setSecurityIssues(result.getSecurityIssues());
            response.setPerformanceSuggestions(result.getPerformanceSuggestions());
            response.setLanguage(result.getLanguage());
            
            if (result.getQualityMetrics() != null) {
                QualityMetricsResponse metrics = new QualityMetricsResponse();
                metrics.setReadability(result.getQualityMetrics().getReadability());
                metrics.setMaintainability(result.getQualityMetrics().getMaintainability());
                metrics.setComplexity(result.getQualityMetrics().getComplexity());
                response.setQualityMetrics(metrics);
            }
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            CodeReviewWithImprovementsResponse errorResponse = new CodeReviewWithImprovementsResponse();
            errorResponse.setReviewSummary("Error during code review: " + e.getMessage());
            errorResponse.setOverallScore(0.0);
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    private CodeReviewResponse createErrorResponse(String errorMessage) {
        CodeReviewResponse response = new CodeReviewResponse();
        response.setReviewSummary(errorMessage);
        response.setOverallScore(0.0);
        response.setAnalysisType("ERROR");
        return response;
    }

    // Request/Response DTOs
    public static class CodeReviewRequest {
        @NotBlank(message = "Code is required")
        private String code;
        
        @NotBlank(message = "Language is required")
        private String language;
        
        @NotNull(message = "Analysis type is required")
        private AnalysisType analysisType;

        // Getters and Setters
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }

        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }

        public AnalysisType getAnalysisType() { return analysisType; }
        public void setAnalysisType(AnalysisType analysisType) { this.analysisType = analysisType; }
    }

    public static class CodeImprovementRequest {
        @NotBlank(message = "Code is required")
        private String code;
        
        @NotBlank(message = "Language is required")
        private String language;

        // Getters and Setters
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }

        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }
    }

    public static class CodeReviewResponse {
        private double overallScore;
        private String reviewSummary;
        private java.util.List<String> securityIssues;
        private java.util.List<String> performanceSuggestions;
        private QualityMetricsResponse qualityMetrics;
        private String language;
        private String analysisType;

        // Getters and Setters
        public double getOverallScore() { return overallScore; }
        public void setOverallScore(double overallScore) { this.overallScore = overallScore; }

        public String getReviewSummary() { return reviewSummary; }
        public void setReviewSummary(String reviewSummary) { this.reviewSummary = reviewSummary; }

        public java.util.List<String> getSecurityIssues() { return securityIssues; }
        public void setSecurityIssues(java.util.List<String> securityIssues) { this.securityIssues = securityIssues; }

        public java.util.List<String> getPerformanceSuggestions() { return performanceSuggestions; }
        public void setPerformanceSuggestions(java.util.List<String> performanceSuggestions) { this.performanceSuggestions = performanceSuggestions; }

        public QualityMetricsResponse getQualityMetrics() { return qualityMetrics; }
        public void setQualityMetrics(QualityMetricsResponse qualityMetrics) { this.qualityMetrics = qualityMetrics; }

        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }

        public String getAnalysisType() { return analysisType; }
        public void setAnalysisType(String analysisType) { this.analysisType = analysisType; }
    }

    public static class CodeReviewWithImprovementsResponse extends CodeReviewResponse {
        private String originalCode;
        private String improvedCode;

        // Getters and Setters
        public String getOriginalCode() { return originalCode; }
        public void setOriginalCode(String originalCode) { this.originalCode = originalCode; }

        public String getImprovedCode() { return improvedCode; }
        public void setImprovedCode(String improvedCode) { this.improvedCode = improvedCode; }
    }

    public static class QualityMetricsResponse {
        private double readability;
        private double maintainability;
        private double complexity;

        // Getters and Setters
        public double getReadability() { return readability; }
        public void setReadability(double readability) { this.readability = readability; }

        public double getMaintainability() { return maintainability; }
        public void setMaintainability(double maintainability) { this.maintainability = maintainability; }

        public double getComplexity() { return complexity; }
        public void setComplexity(double complexity) { this.complexity = complexity; }
    }
}