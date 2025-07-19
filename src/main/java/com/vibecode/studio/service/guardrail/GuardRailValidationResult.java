package com.vibecode.studio.service.guardrail;

import java.util.ArrayList;
import java.util.List;

public class GuardRailValidationResult {
    
    private final List<GuardRailCheck> checks;
    private boolean overallPassed;
    private String summary;
    
    public GuardRailValidationResult() {
        this.checks = new ArrayList<>();
        this.overallPassed = true;
    }
    
    public static GuardRailValidationResult passed(String summary) {
        GuardRailValidationResult result = new GuardRailValidationResult();
        result.summary = summary;
        result.overallPassed = true;
        return result;
    }
    
    public void addCheck(GuardRailCheck check) {
        checks.add(check);
        if (!check.isPassed()) {
            overallPassed = false;
        }
    }
    
    public boolean isOverallPassed() {
        return overallPassed;
    }
    
    public List<GuardRailCheck> getChecks() {
        return checks;
    }
    
    public List<GuardRailCheck> getFailedChecks() {
        return checks.stream()
            .filter(check -> !check.isPassed())
            .toList();
    }
    
    public String getSummary() {
        if (summary != null) {
            return summary;
        }
        
        long failedCount = getFailedChecks().size();
        if (failedCount == 0) {
            return "All GuardRail checks passed";
        } else {
            return String.format("%d GuardRail check(s) failed", failedCount);
        }
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
}