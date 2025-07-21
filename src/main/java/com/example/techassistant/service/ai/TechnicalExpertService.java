package com.example.techassistant.service.ai;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TechnicalExpertService {

    private final ChatModel generalModel;
    private final ChatModel codeReviewModel;
    private final ChatModel architectureModel;

    public TechnicalExpertService(
            ChatModel generalModel,
            @Qualifier("codeReviewModel") ChatModel codeReviewModel,
            @Qualifier("architectureModel") ChatModel architectureModel) {
        this.generalModel = generalModel;
        this.codeReviewModel = codeReviewModel;
        this.architectureModel = architectureModel;
    }

    public String askTechnicalQuestion(String question, ExpertiseArea expertiseArea) {
        ChatModel selectedModel = selectModelForExpertise(expertiseArea);
        String systemPrompt = buildSystemPrompt(expertiseArea);
        String fullPrompt = systemPrompt + "\n\nUser Question: " + question;
        
        return selectedModel.chat(fullPrompt);
    }

    public TechnicalResponse askTechnicalQuestionWithContext(String question, 
                                                           ExpertiseArea expertiseArea, 
                                                           String context) {
        ChatModel selectedModel = selectModelForExpertise(expertiseArea);
        String systemPrompt = buildSystemPrompt(expertiseArea);
        String contextualPrompt = systemPrompt + 
                "\n\nContext Information:\n" + context +
                "\n\nUser Question: " + question +
                "\n\nPlease provide a comprehensive answer based on the context provided and your expertise.";
        
        String response = selectedModel.chat(contextualPrompt);
        
        return TechnicalResponse.builder()
                .answer(response)
                .confidenceScore(calculateConfidenceScore(response, context))
                .expertiseArea(expertiseArea)
                .hasContext(true)
                .build();
    }

    private ChatModel selectModelForExpertise(ExpertiseArea expertiseArea) {
        return switch (expertiseArea) {
            case CODE, SECURITY -> codeReviewModel;
            case ARCHITECTURE, PERFORMANCE -> architectureModel;
            default -> generalModel;
        };
    }

    private String buildSystemPrompt(ExpertiseArea expertiseArea) {
        String basePrompt = "You are a senior technical expert and consultant with extensive experience in enterprise software development. ";
        
        return switch (expertiseArea) {
            case CLOUD -> basePrompt + 
                "You specialize in cloud architecture, particularly Microsoft Azure, AWS, and cloud-native technologies. " +
                "Provide practical guidance on cloud migration, scalability, security, and best practices.";
            
            case SECURITY -> basePrompt + 
                "You specialize in cybersecurity, secure coding practices, and enterprise security architecture. " +
                "Focus on practical security implementations, threat modeling, and security best practices.";
            
            case PERFORMANCE -> basePrompt + 
                "You specialize in application performance optimization, system tuning, and scalability solutions. " +
                "Provide actionable advice on performance bottlenecks, monitoring, and optimization strategies.";
            
            case ARCHITECTURE -> basePrompt + 
                "You specialize in software architecture design, design patterns, and system design. " +
                "Focus on scalable, maintainable, and robust architectural solutions for enterprise applications.";
            
            case CODE -> basePrompt + 
                "You specialize in code review, best practices, and software craftsmanship. " +
                "Provide specific, actionable feedback on code quality, design patterns, and implementation approaches.";
            
            default -> basePrompt + 
                "You provide general technical consultation across various domains. " +
                "Offer comprehensive, practical advice tailored to enterprise development needs.";
        };
    }

    private double calculateConfidenceScore(String response, String context) {
        // Simple confidence calculation based on response length and context relevance
        // In a real implementation, this could be more sophisticated
        double baseScore = 0.7;
        
        if (response.length() > 100) baseScore += 0.1;
        if (response.length() > 500) baseScore += 0.1;
        if (context != null && !context.trim().isEmpty()) baseScore += 0.1;
        
        return Math.min(1.0, baseScore);
    }

    public enum ExpertiseArea {
        CLOUD, SECURITY, PERFORMANCE, ARCHITECTURE, CODE, GENERAL
    }

    public static class TechnicalResponse {
        private String answer;
        private double confidenceScore;
        private ExpertiseArea expertiseArea;
        private boolean hasContext;

        // Builder pattern
        public static TechnicalResponseBuilder builder() {
            return new TechnicalResponseBuilder();
        }

        // Getters
        public String getAnswer() { return answer; }
        public double getConfidenceScore() { return confidenceScore; }
        public ExpertiseArea getExpertiseArea() { return expertiseArea; }
        public boolean isHasContext() { return hasContext; }

        // Builder class
        public static class TechnicalResponseBuilder {
            private String answer;
            private double confidenceScore;
            private ExpertiseArea expertiseArea;
            private boolean hasContext;

            public TechnicalResponseBuilder answer(String answer) {
                this.answer = answer;
                return this;
            }

            public TechnicalResponseBuilder confidenceScore(double confidenceScore) {
                this.confidenceScore = confidenceScore;
                return this;
            }

            public TechnicalResponseBuilder expertiseArea(ExpertiseArea expertiseArea) {
                this.expertiseArea = expertiseArea;
                return this;
            }

            public TechnicalResponseBuilder hasContext(boolean hasContext) {
                this.hasContext = hasContext;
                return this;
            }

            public TechnicalResponse build() {
                TechnicalResponse response = new TechnicalResponse();
                response.answer = this.answer;
                response.confidenceScore = this.confidenceScore;
                response.expertiseArea = this.expertiseArea;
                response.hasContext = this.hasContext;
                return response;
            }
        }
    }
}