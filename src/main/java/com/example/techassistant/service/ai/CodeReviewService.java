package com.example.techassistant.service.ai;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class CodeReviewService {

    private final ChatLanguageModel codeReviewModel;

    public CodeReviewService(@Qualifier("codeReviewModel") ChatLanguageModel codeReviewModel) {
        this.codeReviewModel = codeReviewModel;
    }

    public CodeReviewResult reviewCode(String code, String language, AnalysisType analysisType) {
        String systemPrompt = buildCodeReviewPrompt(language, analysisType);
        String fullPrompt = systemPrompt + "\n\nCode to review:\n```" + language + "\n" + code + "\n```";
        
        String reviewResponse = codeReviewModel.generate(fullPrompt);
        
        return parseCodeReviewResponse(reviewResponse, code, language, analysisType);
    }

    public CodeReviewResult reviewCodeWithSuggestions(String code, String language) {
        String systemPrompt = buildImprovementPrompt(language);
        String fullPrompt = systemPrompt + "\n\nCode to improve:\n```" + language + "\n" + code + "\n```";
        
        String reviewResponse = codeReviewModel.generate(fullPrompt);
        String improvedCode = generateImprovedCode(code, language);
        
        CodeReviewResult result = parseCodeReviewResponse(reviewResponse, code, language, AnalysisType.COMPREHENSIVE);
        result.setImprovedCode(improvedCode);
        
        return result;
    }

    private String buildCodeReviewPrompt(String language, AnalysisType analysisType) {
        String basePrompt = "You are an expert code reviewer with extensive experience in " + language + 
                " and software engineering best practices. ";

        return switch (analysisType) {
            case SECURITY -> basePrompt + 
                "Focus specifically on security vulnerabilities, potential attack vectors, and secure coding practices. " +
                "Identify any SQL injection, XSS, authentication bypass, or other security issues. " +
                "Provide specific recommendations for security improvements.";
            
            case PERFORMANCE -> basePrompt + 
                "Focus on performance optimization opportunities, algorithmic efficiency, and resource usage. " +
                "Identify potential bottlenecks, inefficient operations, and suggest optimizations. " +
                "Consider memory usage, CPU efficiency, and scalability implications.";
            
            case QUALITY -> basePrompt + 
                "Focus on code quality, maintainability, readability, and adherence to best practices. " +
                "Review naming conventions, code structure, design patterns, and documentation. " +
                "Suggest improvements for code clarity and maintainability.";
            
            case COMPREHENSIVE -> basePrompt + 
                "Provide a comprehensive review covering security, performance, quality, and best practices. " +
                "Identify issues across all categories and prioritize them by severity. " +
                "Provide actionable recommendations for improvements.";
        };
    }

    private String buildImprovementPrompt(String language) {
        return "You are a senior software engineer helping to improve code quality. " +
                "Review the provided " + language + " code and provide an improved version that addresses: " +
                "1. Security vulnerabilities\n" +
                "2. Performance optimizations\n" +
                "3. Code quality and readability\n" +
                "4. Best practices for " + language + "\n" +
                "Return the improved code with clear comments explaining the changes.";
    }

    private String generateImprovedCode(String originalCode, String language) {
        String prompt = "Please provide an improved version of this " + language + " code with better " +
                "security, performance, and maintainability:\n\n```" + language + "\n" + originalCode + "\n```\n\n" +
                "Return only the improved code without explanation.";
        
        return codeReviewModel.generate(prompt);
    }

    private CodeReviewResult parseCodeReviewResponse(String response, String originalCode, 
                                                   String language, AnalysisType analysisType) {
        // In a real implementation, this would parse the AI response more sophisticatedly
        // For now, we'll create a structured result based on the response
        
        CodeReviewResult result = new CodeReviewResult();
        result.setOriginalCode(originalCode);
        result.setLanguage(language);
        result.setAnalysisType(analysisType);
        result.setOverallScore(calculateOverallScore(response));
        result.setReviewSummary(response);
        
        // Parse issues from response (simplified)
        result.setSecurityIssues(extractIssues(response, "security"));
        result.setPerformanceSuggestions(extractSuggestions(response, "performance"));
        result.setQualityMetrics(extractQualityMetrics(response));
        
        return result;
    }

    private double calculateOverallScore(String response) {
        // Simple scoring based on response content
        double score = 8.0; // Default good score
        
        if (response.toLowerCase().contains("critical") || response.toLowerCase().contains("severe")) {
            score -= 3.0;
        } else if (response.toLowerCase().contains("major") || response.toLowerCase().contains("important")) {
            score -= 2.0;
        } else if (response.toLowerCase().contains("minor") || response.toLowerCase().contains("suggestion")) {
            score -= 1.0;
        }
        
        return Math.max(1.0, Math.min(10.0, score));
    }

    private List<String> extractIssues(String response, String category) {
        List<String> issues = new ArrayList<>();
        // Simple extraction logic - in real implementation, this would be more sophisticated
        String[] lines = response.split("\n");
        for (String line : lines) {
            if (line.toLowerCase().contains(category) && 
                (line.contains("issue") || line.contains("problem") || line.contains("vulnerability"))) {
                issues.add(line.trim());
            }
        }
        return issues;
    }

    private List<String> extractSuggestions(String response, String category) {
        List<String> suggestions = new ArrayList<>();
        String[] lines = response.split("\n");
        for (String line : lines) {
            if (line.toLowerCase().contains(category) && 
                (line.contains("suggest") || line.contains("recommend") || line.contains("improve"))) {
                suggestions.add(line.trim());
            }
        }
        return suggestions;
    }

    private QualityMetrics extractQualityMetrics(String response) {
        QualityMetrics metrics = new QualityMetrics();
        
        // Simple metrics extraction
        if (response.toLowerCase().contains("readable") || response.toLowerCase().contains("clear")) {
            metrics.setReadability(8.0);
        } else {
            metrics.setReadability(6.0);
        }
        
        if (response.toLowerCase().contains("maintainable") || response.toLowerCase().contains("well-structured")) {
            metrics.setMaintainability(8.0);
        } else {
            metrics.setMaintainability(6.0);
        }
        
        metrics.setComplexity(7.0); // Default
        
        return metrics;
    }

    public enum AnalysisType {
        SECURITY, PERFORMANCE, QUALITY, COMPREHENSIVE
    }

    public static class CodeReviewResult {
        private String originalCode;
        private String improvedCode;
        private String language;
        private AnalysisType analysisType;
        private double overallScore;
        private String reviewSummary;
        private List<String> securityIssues;
        private List<String> performanceSuggestions;
        private QualityMetrics qualityMetrics;

        // Getters and Setters
        public String getOriginalCode() { return originalCode; }
        public void setOriginalCode(String originalCode) { this.originalCode = originalCode; }

        public String getImprovedCode() { return improvedCode; }
        public void setImprovedCode(String improvedCode) { this.improvedCode = improvedCode; }

        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }

        public AnalysisType getAnalysisType() { return analysisType; }
        public void setAnalysisType(AnalysisType analysisType) { this.analysisType = analysisType; }

        public double getOverallScore() { return overallScore; }
        public void setOverallScore(double overallScore) { this.overallScore = overallScore; }

        public String getReviewSummary() { return reviewSummary; }
        public void setReviewSummary(String reviewSummary) { this.reviewSummary = reviewSummary; }

        public List<String> getSecurityIssues() { return securityIssues; }
        public void setSecurityIssues(List<String> securityIssues) { this.securityIssues = securityIssues; }

        public List<String> getPerformanceSuggestions() { return performanceSuggestions; }
        public void setPerformanceSuggestions(List<String> performanceSuggestions) { this.performanceSuggestions = performanceSuggestions; }

        public QualityMetrics getQualityMetrics() { return qualityMetrics; }
        public void setQualityMetrics(QualityMetrics qualityMetrics) { this.qualityMetrics = qualityMetrics; }
    }

    public static class QualityMetrics {
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