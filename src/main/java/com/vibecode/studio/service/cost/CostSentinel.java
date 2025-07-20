package com.vibecode.studio.service.cost;

import com.vibecode.studio.model.CostEntry;
import com.vibecode.studio.model.Project;
import com.vibecode.studio.repository.CostEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Service
public class CostSentinel {
    
    @Autowired
    private CostEntryRepository costEntryRepository;
    
    @Value("${vibecode.cost.default-monthly-limit:100.0}")
    private BigDecimal defaultMonthlyLimit;
    
    @Value("${vibecode.cost.alert-threshold:0.8}")
    private BigDecimal alertThreshold;
    
    public CostEntry recordCost(Project project, String userId, CostEntry.CostType type, 
                               BigDecimal amount, Integer tokenCount, String description) {
        CostEntry costEntry = new CostEntry(project, userId, type, amount);
        costEntry.setTokenCount(tokenCount);
        costEntry.setDescription(description);
        
        CostEntry savedEntry = costEntryRepository.save(costEntry);
        
        // Check if alert threshold is exceeded
        checkAlertThreshold(userId);
        
        return savedEntry;
    }
    
    public BigDecimal getTotalProjectCost(Long projectId) {
        return costEntryRepository.getTotalCostByProject(projectId);
    }
    
    public BigDecimal getMonthlyUserCost(String userId) {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        return costEntryRepository.getTotalCostByUserSince(userId, startOfMonth);
    }
    
    public CostAlert checkAlertThreshold(String userId) {
        BigDecimal monthlySpend = getMonthlyUserCost(userId);
        BigDecimal threshold = defaultMonthlyLimit.multiply(alertThreshold);
        
        if (monthlySpend.compareTo(threshold) >= 0) {
            BigDecimal percentage = monthlySpend.divide(defaultMonthlyLimit, 4, BigDecimal.ROUND_HALF_UP);
            
            return new CostAlert(
                CostAlert.AlertLevel.WARNING,
                String.format("Monthly spend (%.2f) has reached %.0f%% of limit (%.2f)", 
                    monthlySpend, percentage.multiply(BigDecimal.valueOf(100)), defaultMonthlyLimit),
                monthlySpend,
                defaultMonthlyLimit
            );
        }
        
        if (monthlySpend.compareTo(defaultMonthlyLimit) >= 0) {
            return new CostAlert(
                CostAlert.AlertLevel.CRITICAL,
                String.format("Monthly spend (%.2f) has exceeded limit (%.2f)", 
                    monthlySpend, defaultMonthlyLimit),
                monthlySpend,
                defaultMonthlyLimit
            );
        }
        
        return null; // No alert
    }
    
    public boolean isWithinBudget(String userId, BigDecimal additionalCost) {
        BigDecimal currentSpend = getMonthlyUserCost(userId);
        BigDecimal projectedSpend = currentSpend.add(additionalCost);
        return projectedSpend.compareTo(defaultMonthlyLimit) <= 0;
    }
    
    public CostSummary getCostSummary(String userId) {
        BigDecimal monthlySpend = getMonthlyUserCost(userId);
        BigDecimal remaining = defaultMonthlyLimit.subtract(monthlySpend);
        BigDecimal usagePercentage = monthlySpend.divide(defaultMonthlyLimit, 4, BigDecimal.ROUND_HALF_UP)
            .multiply(BigDecimal.valueOf(100));
        
        return new CostSummary(monthlySpend, defaultMonthlyLimit, remaining, usagePercentage);
    }
}