package com.vibecode.studio.service.guardrail;

public class GuardRailCheck {
    
    private final String checkType;
    private final boolean passed;
    private final String message;
    private final String severity;
    
    private GuardRailCheck(String checkType, boolean passed, String message, String severity) {
        this.checkType = checkType;
        this.passed = passed;
        this.message = message;
        this.severity = severity;
    }
    
    public static GuardRailCheck passed(String checkType) {
        return new GuardRailCheck(checkType, true, "Check passed", "INFO");
    }
    
    public static GuardRailCheck failed(String checkType, String message) {
        return new GuardRailCheck(checkType, false, message, "HIGH");
    }
    
    public static GuardRailCheck warning(String checkType, String message) {
        return new GuardRailCheck(checkType, true, message, "MEDIUM");
    }
    
    // Getters
    public String getCheckType() { return checkType; }
    public boolean isPassed() { return passed; }
    public String getMessage() { return message; }
    public String getSeverity() { return severity; }
}