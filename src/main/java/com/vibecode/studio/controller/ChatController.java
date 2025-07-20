package com.vibecode.studio.controller;

import com.vibecode.studio.model.ChatSession;
import com.vibecode.studio.model.Project;
import com.vibecode.studio.repository.ChatSessionRepository;
import com.vibecode.studio.repository.ProjectRepository;
import com.vibecode.studio.service.agent.AgentCore;
import com.vibecode.studio.service.agent.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatController {
    
    @Autowired
    private ChatSessionRepository chatSessionRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private AgentCore agentCore;
    
    @GetMapping("/sessions")
    public ResponseEntity<List<ChatSession>> getUserChatSessions(@RequestParam String userId,
                                                                @RequestParam(required = false) Long projectId) {
        List<ChatSession> sessions;
        if (projectId != null) {
            sessions = chatSessionRepository.findByProjectIdAndUserId(projectId, userId);
        } else {
            // This would need a custom query to get all sessions for a user across projects
            sessions = List.of(); // Simplified for now
        }
        return ResponseEntity.ok(sessions);
    }
    
    @GetMapping("/sessions/{sessionId}")
    public ResponseEntity<ChatSession> getChatSession(@PathVariable Long sessionId, @RequestParam String userId) {
        Optional<ChatSession> session = chatSessionRepository.findByIdAndUserId(sessionId, userId);
        return session.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/sessions")
    public ResponseEntity<ChatSession> createChatSession(@RequestBody CreateChatSessionRequest request) {
        Optional<Project> projectOpt = projectRepository.findByIdAndOwnerId(request.projectId(), request.userId());
        if (projectOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        Project project = projectOpt.get();
        ChatSession session = new ChatSession(project, request.userId(), request.sessionName());
        ChatSession savedSession = chatSessionRepository.save(session);
        
        return ResponseEntity.ok(savedSession);
    }
    
    @PostMapping("/message")
    public ResponseEntity<ChatMessageResponse> sendMessage(@RequestBody SendMessageRequest request) {
        try {
            ChatResponse response = agentCore.processConversationalRequest(
                request.sessionId(), 
                request.message(), 
                request.userId()
            );
            
            String aiResponse = response.getResult().getOutput().getContent();
            
            return ResponseEntity.ok(new ChatMessageResponse(aiResponse, null));
        } catch (SecurityException e) {
            return ResponseEntity.badRequest()
                .body(new ChatMessageResponse(null, "Security validation failed: " + e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ChatMessageResponse(null, e.getMessage()));
        }
    }
    
    @PostMapping("/generate-code")
    public ResponseEntity<CodeGenerationResponse> generateCode(@RequestBody CodeGenerationRequest request) {
        try {
            ChatResponse response = agentCore.processConversationalRequest(
                request.sessionId(), 
                "Generate code for: " + request.prompt(), 
                request.userId()
            );
            
            String generatedCode = response.getResult().getOutput().getContent();
            
            return ResponseEntity.ok(new CodeGenerationResponse(generatedCode, null, null));
        } catch (SecurityException e) {
            return ResponseEntity.badRequest()
                .body(new CodeGenerationResponse(null, null, "Security validation failed: " + e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new CodeGenerationResponse(null, null, e.getMessage()));
        }
    }
    
    // DTOs
    public record CreateChatSessionRequest(Long projectId, String userId, String sessionName) {}
    
    public record SendMessageRequest(Long sessionId, String message, String userId) {}
    
    public record ChatMessageResponse(String response, String error) {}
    
    public record CodeGenerationRequest(Long sessionId, String prompt, String userId) {}
    
    public record CodeGenerationResponse(String code, String diff, String error) {}
}