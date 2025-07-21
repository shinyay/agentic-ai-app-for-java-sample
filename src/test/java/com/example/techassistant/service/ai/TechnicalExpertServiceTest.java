package com.example.techassistant.service.ai;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TechnicalExpertServiceTest {

    @Test
    void testExpertiseAreaEnum() {
        // Test that all expertise areas are available
        TechnicalExpertService.ExpertiseArea[] areas = TechnicalExpertService.ExpertiseArea.values();
        
        assertTrue(areas.length >= 6);
        assertNotNull(TechnicalExpertService.ExpertiseArea.CLOUD);
        assertNotNull(TechnicalExpertService.ExpertiseArea.SECURITY);
        assertNotNull(TechnicalExpertService.ExpertiseArea.PERFORMANCE);
        assertNotNull(TechnicalExpertService.ExpertiseArea.ARCHITECTURE);
        assertNotNull(TechnicalExpertService.ExpertiseArea.CODE);
        assertNotNull(TechnicalExpertService.ExpertiseArea.GENERAL);
    }

    @Test
    void testTechnicalResponseBuilder() {
        // Test the builder pattern
        TechnicalExpertService.TechnicalResponse response = 
            TechnicalExpertService.TechnicalResponse.builder()
                .answer("Test answer")
                .confidenceScore(0.95)
                .expertiseArea(TechnicalExpertService.ExpertiseArea.CLOUD)
                .hasContext(true)
                .build();
        
        assertEquals("Test answer", response.getAnswer());
        assertEquals(0.95, response.getConfidenceScore());
        assertEquals(TechnicalExpertService.ExpertiseArea.CLOUD, response.getExpertiseArea());
        assertTrue(response.isHasContext());
    }
}