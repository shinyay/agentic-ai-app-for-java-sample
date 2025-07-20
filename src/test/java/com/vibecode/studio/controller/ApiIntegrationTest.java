package com.vibecode.studio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ApiIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testGuardRailStatus() throws Exception {
        mockMvc.perform(get("/guardrail/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.enabled").value(true))
                .andExpect(jsonPath("$.message").exists());
    }
    
    @Test
    void testCreateProject() throws Exception {
        String requestBody = """
            {
                "prompt": "Build a simple todo application with REST API",
                "userId": "test-user"
            }
            """;
        
        mockMvc.perform(post("/projects/create")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.project").exists())
                .andExpect(jsonPath("$.project.name").exists());
    }
    
    @Test
    void testValidateCode() throws Exception {
        String requestBody = """
            {
                "code": "@RestController public class TestController {}",
                "prompt": "Create a simple controller"
            }
            """;
        
        mockMvc.perform(post("/guardrail/validate")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.overallPassed").value(true));
    }
}