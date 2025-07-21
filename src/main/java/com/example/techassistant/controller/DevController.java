package com.example.techassistant.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/dev")
@CrossOrigin(origins = "*")
public class DevController {

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of(
            "status", "UP",
            "timestamp", LocalDateTime.now(),
            "service", "TechKnowledge Assistant",
            "version", "0.0.1-SNAPSHOT"
        );
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        return Map.of(
            "application", Map.of(
                "name", "TechKnowledge Assistant",
                "description", "Enterprise AI-Powered Technical Knowledge Management Platform",
                "version", "0.0.1-SNAPSHOT"
            ),
            "features", Map.of(
                "ai_consultation", true,
                "code_review", true,
                "document_processing", false,
                "intelligent_agents", false,
                "vector_search", false
            ),
            "technology_stack", Map.of(
                "java_version", System.getProperty("java.version"),
                "spring_boot", "3.2.1",
                "langchain4j", "0.25.0"
            )
        );
    }

    @PostMapping("/echo")
    public Map<String, Object> echo(@RequestBody Map<String, Object> payload) {
        return Map.of(
            "timestamp", LocalDateTime.now(),
            "received", payload,
            "message", "Echo successful"
        );
    }
}