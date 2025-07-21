package com.example.techassistant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "langchain4j.azure.openai.api-key=test-key",
    "langchain4j.azure.openai.endpoint=https://test.openai.azure.com",
    "langchain4j.azure.openai.deployment-name=test-deployment",
    "langchain4j.embedding-model.azure-openai.deployment-name=test-embedding",
    "techknowledge.vector-store.azure.search.service-name=test-search",
    "techknowledge.vector-store.azure.search.api-key=test-search-key",
    "techknowledge.vector-store.azure.search.index-name=test-index",
    "techknowledge.storage.azure.blob.connection-string=DefaultEndpointsProtocol=https;AccountName=test;AccountKey=testkey;EndpointSuffix=core.windows.net",
    "techknowledge.storage.azure.blob.container-name=test-container"
})
class TechKnowledgeAssistantApplicationTests {

	@Test
	void contextLoads() {
		// This test verifies that the Spring Boot application context loads successfully
	}
}