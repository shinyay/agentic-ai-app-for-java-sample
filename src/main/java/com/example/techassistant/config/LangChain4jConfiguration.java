package com.example.techassistant.config;

import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import dev.langchain4j.model.azure.AzureOpenAiEmbeddingModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class LangChain4jConfiguration {

    @Value("${langchain4j.azure.openai.api-key}")
    private String apiKey;

    @Value("${langchain4j.azure.openai.endpoint}")
    private String endpoint;

    @Value("${langchain4j.azure.openai.deployment-name}")
    private String deploymentName;

    @Value("${langchain4j.embedding-model.azure-openai.deployment-name}")
    private String embeddingDeploymentName;

    @Value("${techknowledge.ai.max-tokens:4000}")
    private Integer maxTokens;

    @Value("${techknowledge.ai.temperature:0.7}")
    private Double temperature;

    @Value("${techknowledge.ai.timeout:30000}")
    private Long timeoutMs;

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return AzureOpenAiChatModel.builder()
                .apiKey(apiKey)
                .endpoint(endpoint)
                .deploymentName(deploymentName)
                .maxTokens(maxTokens)
                .temperature(temperature)
                .timeout(Duration.ofMillis(timeoutMs))
                .logRequestsAndResponses(true)
                .build();
    }

    @Bean
    public EmbeddingModel embeddingModel() {
        return AzureOpenAiEmbeddingModel.builder()
                .apiKey(apiKey)
                .endpoint(endpoint)
                .deploymentName(embeddingDeploymentName)
                .timeout(Duration.ofMillis(timeoutMs))
                .logRequestsAndResponses(true)
                .build();
    }

    // Specialized models for different purposes
    @Bean("codeReviewModel")
    public ChatLanguageModel codeReviewModel() {
        return AzureOpenAiChatModel.builder()
                .apiKey(apiKey)
                .endpoint(endpoint)
                .deploymentName(deploymentName)
                .maxTokens(maxTokens)
                .temperature(0.3) // Lower temperature for more focused code analysis
                .timeout(Duration.ofMillis(timeoutMs))
                .logRequestsAndResponses(true)
                .build();
    }

    @Bean("architectureModel")
    public ChatLanguageModel architectureModel() {
        return AzureOpenAiChatModel.builder()
                .apiKey(apiKey)
                .endpoint(endpoint)
                .deploymentName(deploymentName)
                .maxTokens(maxTokens)
                .temperature(0.5) // Balanced for architectural discussions
                .timeout(Duration.ofMillis(timeoutMs))
                .logRequestsAndResponses(true)
                .build();
    }
}