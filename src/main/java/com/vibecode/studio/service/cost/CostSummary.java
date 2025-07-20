package com.vibecode.studio.service.cost;

import java.math.BigDecimal;

public class CostSummary {
    
    private final BigDecimal spent;
    private final BigDecimal limit;
    private final BigDecimal remaining;
    private final BigDecimal usagePercentage;
    
    public CostSummary(BigDecimal spent, BigDecimal limit, BigDecimal remaining, BigDecimal usagePercentage) {
        this.spent = spent;
        this.limit = limit;
        this.remaining = remaining;
        this.usagePercentage = usagePercentage;
    }
    
    // Getters
    public BigDecimal getSpent() { return spent; }
    public BigDecimal getLimit() { return limit; }
    public BigDecimal getRemaining() { return remaining; }
    public BigDecimal getUsagePercentage() { return usagePercentage; }
}