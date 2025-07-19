package com.vibecode.studio.service.cost;

import java.math.BigDecimal;

public class CostAlert {
    
    public enum AlertLevel {
        INFO, WARNING, CRITICAL
    }
    
    private final AlertLevel level;
    private final String message;
    private final BigDecimal currentSpend;
    private final BigDecimal limit;
    
    public CostAlert(AlertLevel level, String message, BigDecimal currentSpend, BigDecimal limit) {
        this.level = level;
        this.message = message;
        this.currentSpend = currentSpend;
        this.limit = limit;
    }
    
    // Getters
    public AlertLevel getLevel() { return level; }
    public String getMessage() { return message; }
    public BigDecimal getCurrentSpend() { return currentSpend; }
    public BigDecimal getLimit() { return limit; }
}