package com.vibecode.studio.service.agent;

import com.vibecode.studio.model.*;
import com.vibecode.studio.repository.ChatSessionRepository;
import com.vibecode.studio.repository.ProjectRepository;
import com.vibecode.studio.service.cost.CostSentinel;
import com.vibecode.studio.service.guardrail.GuardRailEngine;
import com.vibecode.studio.service.guardrail.GuardRailValidationResult;
import com.vibecode.studio.service.agent.ChatClient;
import com.vibecode.studio.service.agent.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AgentCore {
    
    @Autowired
    private ChatClient chatClient;
    
    @Autowired
    private GuardRailEngine guardRailEngine;
    
    @Autowired
    private CostSentinel costSentinel;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ChatSessionRepository chatSessionRepository;
    
    @Transactional
    public ChatResponse processConversationalRequest(Long sessionId, String userPrompt, String userId) {
        // Get chat session
        Optional<ChatSession> sessionOpt = chatSessionRepository.findByIdAndUserId(sessionId, userId);
        if (sessionOpt.isEmpty()) {
            throw new IllegalArgumentException("Chat session not found");
        }
        
        ChatSession session = sessionOpt.get();
        
        // Check cost constraints
        if (!costSentinel.isWithinBudget(userId, BigDecimal.valueOf(0.10))) {
            throw new RuntimeException("Monthly cost limit exceeded");
        }
        
        // Validate prompt with GuardRail
        GuardRailValidationResult validation = guardRailEngine.validateCodeGeneration("", userPrompt);
        if (!validation.isOverallPassed()) {
            throw new SecurityException("Prompt failed security validation: " + validation.getSummary());
        }
        
        // Save user message
        ChatMessage userMessage = new ChatMessage(session, ChatMessage.MessageType.USER, userPrompt);
        session.getMessages().add(userMessage);
        
        // Generate AI response
        String enhancedPrompt = buildContextualPrompt(session, userPrompt);
        ChatResponse response = chatClient.call(enhancedPrompt);
        
        String aiResponse = response.getResult().getOutput().getContent();
        
        // Validate AI response
        GuardRailValidationResult responseValidation = guardRailEngine.validateCodeGeneration(aiResponse, userPrompt);
        if (!responseValidation.isOverallPassed()) {
            aiResponse = "I apologize, but I cannot generate that code due to security constraints: " + 
                        responseValidation.getSummary();
        }
        
        // Save assistant message
        ChatMessage assistantMessage = new ChatMessage(session, ChatMessage.MessageType.ASSISTANT, aiResponse);
        session.getMessages().add(assistantMessage);
        
        // Record cost
        int estimatedTokens = (userPrompt.length() + aiResponse.length()) / 4; // Rough estimate
        costSentinel.recordCost(
            session.getProject(), 
            userId, 
            CostEntry.CostType.LLM_CHAT, 
            BigDecimal.valueOf(estimatedTokens * 0.00003), // GPT-4 pricing estimate
            estimatedTokens,
            "Chat conversation"
        );
        
        chatSessionRepository.save(session);
        
        return response;
    }
    
    @Transactional
    public Project createProjectFromPrompt(String projectPrompt, String userId) {
        // Validate prompt
        GuardRailValidationResult validation = guardRailEngine.validateCodeGeneration("", projectPrompt);
        if (!validation.isOverallPassed()) {
            throw new SecurityException("Project prompt failed security validation: " + validation.getSummary());
        }
        
        // Check cost constraints
        if (!costSentinel.isWithinBudget(userId, BigDecimal.valueOf(5.00))) {
            throw new RuntimeException("Insufficient budget for project creation");
        }
        
        // Extract project details using AI
        String systemPrompt = """
            You are a project planning assistant. Based on the user's description, extract:
            1. A concise project name (max 50 chars)
            2. A detailed description explaining what the project will do
            3. Suggest a technology stack
            
            Respond in this format:
            NAME: [project name]
            DESCRIPTION: [description]
            TECH: [technology suggestions]
            """;
        
        ChatResponse response = chatClient.call(systemPrompt + "\n\nUser request: " + projectPrompt);
        String aiResponse = response.getResult().getOutput().getContent();
        
        // Parse AI response
        ProjectDetails details = parseProjectDetails(aiResponse);
        
        // Create project
        Project project = new Project(details.name(), details.description(), userId);
        project.setStatus(Project.ProjectStatus.PLANNING);
        
        Project savedProject = projectRepository.save(project);
        
        // Create initial chat session
        ChatSession initialSession = new ChatSession(savedProject, userId, "Project Creation");
        ChatMessage systemMessage = new ChatMessage(initialSession, ChatMessage.MessageType.SYSTEM, 
            "Project created: " + details.name() + "\\n\\n" + details.description() + "\\n\\nSuggested tech: " + details.tech());
        initialSession.getMessages().add(systemMessage);
        chatSessionRepository.save(initialSession);
        
        // Record cost
        int estimatedTokens = (projectPrompt.length() + aiResponse.length()) / 4;
        costSentinel.recordCost(
            savedProject, 
            userId, 
            CostEntry.CostType.LLM_COMPLETION, 
            BigDecimal.valueOf(estimatedTokens * 0.00006), // GPT-4 completion pricing
            estimatedTokens,
            "Project creation"
        );
        
        return savedProject;
    }
    
    private String buildContextualPrompt(ChatSession session, String userPrompt) {
        StringBuilder context = new StringBuilder();
        context.append("You are VibeCode Studio AI, helping with software development. ");
        context.append("Project: ").append(session.getProject().getName()).append("\\n");
        context.append("Description: ").append(session.getProject().getDescription()).append("\\n\\n");
        
        // Add recent conversation history (last 5 messages)
        session.getMessages().stream()
            .skip(Math.max(0, session.getMessages().size() - 5))
            .forEach(msg -> {
                context.append(msg.getType().name()).append(": ").append(msg.getContent()).append("\\n");
            });
        
        context.append("\\nUser: ").append(userPrompt);
        
        return context.toString();
    }
    
    private ProjectDetails parseProjectDetails(String aiResponse) {
        String[] lines = aiResponse.split("\\n");
        String name = "New Project";
        String description = "Project description";
        String tech = "Spring Boot, Java";
        
        for (String line : lines) {
            if (line.startsWith("NAME:")) {
                name = line.substring(5).trim();
            } else if (line.startsWith("DESCRIPTION:")) {
                description = line.substring(12).trim();
            } else if (line.startsWith("TECH:")) {
                tech = line.substring(5).trim();
            }
        }
        
        return new ProjectDetails(name, description, tech);
    }
    
    private record ProjectDetails(String name, String description, String tech) {}
}