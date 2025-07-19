package com.vibecode.studio.controller;

import com.vibecode.studio.service.guardrail.GuardRailEngine;
import com.vibecode.studio.service.guardrail.GuardRailValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guardrail")
@CrossOrigin(origins = "*")
public class GuardRailController {
    
    @Autowired
    private GuardRailEngine guardRailEngine;
    
    @PostMapping("/validate")
    public ResponseEntity<GuardRailValidationResult> validateCode(@RequestBody ValidationRequest request) {
        GuardRailValidationResult result = guardRailEngine.validateCodeGeneration(
            request.code(), 
            request.prompt()
        );
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/status")
    public ResponseEntity<GuardRailStatus> getStatus() {
        return ResponseEntity.ok(new GuardRailStatus(true, "GuardRail engine operational"));
    }
    
    // DTOs
    public record ValidationRequest(String code, String prompt) {}
    
    public record GuardRailStatus(boolean enabled, String message) {}
}