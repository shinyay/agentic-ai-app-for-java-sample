package com.vibecode.studio.service.guardrail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GuardRailEngine {
    
    @Value("${vibecode.guardrail.enabled:true}")
    private boolean enabled;
    
    @Value("${vibecode.guardrail.strict-mode:false}")
    private boolean strictMode;
    
    @Value("${vibecode.guardrail.allowed-packages:}")
    private String allowedPackagesConfig;
    
    private Set<String> allowedPackages;
    
    public GuardRailValidationResult validateCodeGeneration(String code, String prompt) {
        if (!enabled) {
            return GuardRailValidationResult.passed("GuardRail disabled");
        }
        
        GuardRailValidationResult result = new GuardRailValidationResult();
        
        // OWASP-LLM-01: Prompt Injection
        result.addCheck(validatePromptInjection(prompt));
        
        // OWASP-LLM-02: Insecure Output Handling
        result.addCheck(validateOutputSafety(code));
        
        // OWASP-LLM-03: Training Data Poisoning (basic check)
        result.addCheck(validateSuspiciousPatterns(code));
        
        // Package dependency validation
        if (strictMode) {
            result.addCheck(validatePackageDependencies(code));
        }
        
        // Security vulnerability patterns
        result.addCheck(validateSecurityPatterns(code));
        
        return result;
    }
    
    private GuardRailCheck validatePromptInjection(String prompt) {
        List<String> suspiciousPatterns = Arrays.asList(
            "ignore previous instructions",
            "system:",
            "assistant:",
            "\\n\\n",
            "```system",
            "you are now",
            "forget everything"
        );
        
        String lowerPrompt = prompt.toLowerCase();
        for (String pattern : suspiciousPatterns) {
            if (lowerPrompt.contains(pattern)) {
                return GuardRailCheck.failed("PROMPT_INJECTION", 
                    "Potential prompt injection detected: " + pattern);
            }
        }
        
        return GuardRailCheck.passed("PROMPT_INJECTION");
    }
    
    private GuardRailCheck validateOutputSafety(String code) {
        List<String> dangerousPatterns = Arrays.asList(
            "Runtime.getRuntime().exec",
            "ProcessBuilder",
            "System.exit",
            "File.delete",
            "Files.delete",
            "rm -rf",
            "sudo",
            "eval(",
            "exec("
        );
        
        for (String pattern : dangerousPatterns) {
            if (code.contains(pattern)) {
                return GuardRailCheck.failed("UNSAFE_CODE", 
                    "Potentially unsafe code pattern detected: " + pattern);
            }
        }
        
        return GuardRailCheck.passed("UNSAFE_CODE");
    }
    
    private GuardRailCheck validateSuspiciousPatterns(String code) {
        List<String> suspiciousPatterns = Arrays.asList(
            "password",
            "secret",
            "token",
            "api_key",
            "private_key"
        );
        
        String lowerCode = code.toLowerCase();
        for (String pattern : suspiciousPatterns) {
            if (lowerCode.contains(pattern + "\\s*=\\s*[\"'][^\"']{10,}")) {
                return GuardRailCheck.failed("POTENTIAL_SECRET", 
                    "Potential hardcoded secret detected");
            }
        }
        
        return GuardRailCheck.passed("POTENTIAL_SECRET");
    }
    
    private GuardRailCheck validatePackageDependencies(String code) {
        if (allowedPackages == null) {
            initializeAllowedPackages();
        }
        
        // Simple import detection
        String[] lines = code.split("\\n");
        for (String line : lines) {
            if (line.trim().startsWith("import ")) {
                String packageName = extractPackageName(line);
                if (packageName != null && !isPackageAllowed(packageName)) {
                    return GuardRailCheck.failed("UNAUTHORIZED_PACKAGE", 
                        "Unauthorized package detected: " + packageName);
                }
            }
        }
        
        return GuardRailCheck.passed("UNAUTHORIZED_PACKAGE");
    }
    
    private GuardRailCheck validateSecurityPatterns(String code) {
        List<String> securityPatterns = Arrays.asList(
            "SQL\\s+injection",
            "XSS",
            "CSRF",
            "\\.innerHTML\\s*=",
            "document\\.write\\s*\\(",
            "eval\\s*\\("
        );
        
        for (String pattern : securityPatterns) {
            if (code.matches(".*" + pattern + ".*")) {
                return GuardRailCheck.failed("SECURITY_VULNERABILITY", 
                    "Potential security vulnerability pattern detected");
            }
        }
        
        return GuardRailCheck.passed("SECURITY_VULNERABILITY");
    }
    
    private void initializeAllowedPackages() {
        if (allowedPackagesConfig != null && !allowedPackagesConfig.isEmpty()) {
            allowedPackages = Arrays.stream(allowedPackagesConfig.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());
        } else {
            allowedPackages = Set.of("java.", "org.springframework", "jakarta.", "com.fasterxml.jackson");
        }
    }
    
    private String extractPackageName(String importLine) {
        String trimmed = importLine.trim();
        if (trimmed.startsWith("import ")) {
            String packagePart = trimmed.substring(7).trim();
            if (packagePart.endsWith(";")) {
                packagePart = packagePart.substring(0, packagePart.length() - 1);
            }
            return packagePart;
        }
        return null;
    }
    
    private boolean isPackageAllowed(String packageName) {
        return allowedPackages.stream().anyMatch(packageName::startsWith);
    }
}