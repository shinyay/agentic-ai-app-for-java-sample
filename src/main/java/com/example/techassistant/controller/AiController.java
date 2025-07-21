package com.example.techassistant.controller;

import com.example.techassistant.service.ai.TechnicalExpertService;
import com.example.techassistant.service.ai.TechnicalExpertService.ExpertiseArea;
import com.example.techassistant.service.ai.TechnicalExpertService.TechnicalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")
public class AiController {

    private final TechnicalExpertService technicalExpertService;

    public AiController(TechnicalExpertService technicalExpertService) {
        this.technicalExpertService = technicalExpertService;
    }

    @PostMapping("/technical-consultation")
    public ResponseEntity<TechnicalConsultationResponse> askTechnicalQuestion(
            @Valid @RequestBody TechnicalConsultationRequest request) {
        
        try {
            TechnicalResponse response;
            
            if (request.getContext() != null && !request.getContext().trim().isEmpty()) {
                response = technicalExpertService.askTechnicalQuestionWithContext(
                    request.getQuestion(), 
                    request.getExpertiseArea(), 
                    request.getContext()
                );
            } else {
                String answer = technicalExpertService.askTechnicalQuestion(
                    request.getQuestion(), 
                    request.getExpertiseArea()
                );
                
                response = TechnicalResponse.builder()
                    .answer(answer)
                    .confidenceScore(0.8)
                    .expertiseArea(request.getExpertiseArea())
                    .hasContext(false)
                    .build();
            }
            
            TechnicalConsultationResponse apiResponse = new TechnicalConsultationResponse();
            apiResponse.setAnswer(response.getAnswer());
            apiResponse.setConfidenceScore(response.getConfidenceScore());
            apiResponse.setExpertiseArea(response.getExpertiseArea().toString());
            apiResponse.setHasContext(response.isHasContext());
            
            return ResponseEntity.ok(apiResponse);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(createErrorResponse("Error processing technical consultation: " + e.getMessage()));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("AI Service is healthy");
    }

    private TechnicalConsultationResponse createErrorResponse(String errorMessage) {
        TechnicalConsultationResponse response = new TechnicalConsultationResponse();
        response.setAnswer("Sorry, I encountered an error while processing your request: " + errorMessage);
        response.setConfidenceScore(0.0);
        response.setExpertiseArea("ERROR");
        response.setHasContext(false);
        return response;
    }

    // Request/Response DTOs
    public static class TechnicalConsultationRequest {
        @NotBlank(message = "Question is required")
        private String question;
        
        @NotNull(message = "Expertise area is required")
        private ExpertiseArea expertiseArea;
        
        private String context;

        // Getters and Setters
        public String getQuestion() { return question; }
        public void setQuestion(String question) { this.question = question; }

        public ExpertiseArea getExpertiseArea() { return expertiseArea; }
        public void setExpertiseArea(ExpertiseArea expertiseArea) { this.expertiseArea = expertiseArea; }

        public String getContext() { return context; }
        public void setContext(String context) { this.context = context; }
    }

    public static class TechnicalConsultationResponse {
        private String answer;
        private double confidenceScore;
        private String expertiseArea;
        private boolean hasContext;

        // Getters and Setters
        public String getAnswer() { return answer; }
        public void setAnswer(String answer) { this.answer = answer; }

        public double getConfidenceScore() { return confidenceScore; }
        public void setConfidenceScore(double confidenceScore) { this.confidenceScore = confidenceScore; }

        public String getExpertiseArea() { return expertiseArea; }
        public void setExpertiseArea(String expertiseArea) { this.expertiseArea = expertiseArea; }

        public boolean isHasContext() { return hasContext; }
        public void setHasContext(boolean hasContext) { this.hasContext = hasContext; }
    }
}