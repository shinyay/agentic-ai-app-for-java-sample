package com.vibecode.studio.service.cost;

import com.vibecode.studio.model.CostEntry;
import com.vibecode.studio.model.Project;
import com.vibecode.studio.repository.CostEntryRepository;
import com.vibecode.studio.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CostSentinelTest {
    
    @Autowired
    private CostSentinel costSentinel;
    
    @Autowired
    private CostEntryRepository costEntryRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Test
    void testRecordCost() {
        Project project = new Project("Test Project", "Test Description", "user123");
        Project savedProject = projectRepository.save(project);
        
        CostEntry entry = costSentinel.recordCost(
            savedProject, 
            "user123", 
            CostEntry.CostType.LLM_CHAT, 
            BigDecimal.valueOf(0.05), 
            100, 
            "Test chat"
        );
        
        assertNotNull(entry);
        assertNotNull(entry.getId());
        assertEquals(BigDecimal.valueOf(0.05), entry.getAmount());
        assertEquals(100, entry.getTokenCount());
    }
    
    @Test
    void testBudgetCheck() {
        assertTrue(costSentinel.isWithinBudget("newuser", BigDecimal.valueOf(50.0)));
        assertTrue(costSentinel.isWithinBudget("newuser", BigDecimal.valueOf(99.99)));
        assertFalse(costSentinel.isWithinBudget("newuser", BigDecimal.valueOf(150.0)));
    }
    
    @Test
    void testCostSummary() {
        CostSummary summary = costSentinel.getCostSummary("testuser");
        
        assertNotNull(summary);
        assertNotNull(summary.getSpent());
        assertNotNull(summary.getLimit());
        assertNotNull(summary.getRemaining());
        assertNotNull(summary.getUsagePercentage());
    }
}