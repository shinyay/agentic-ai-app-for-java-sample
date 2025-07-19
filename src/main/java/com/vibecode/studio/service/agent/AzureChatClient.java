package com.vibecode.studio.service.agent;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.exception.AzureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnProperty(name = "vibecode.ai.provider", havingValue = "azure")
public class AzureChatClient implements ChatClient {
    
    private static final Logger logger = LoggerFactory.getLogger(AzureChatClient.class);
    
    private final OpenAIClient client;
    private final String deploymentName;
    private final double temperature;
    private final int maxTokens;
    
    public AzureChatClient(
            @Value("${vibecode.ai.azure.endpoint}") String endpoint,
            @Value("${vibecode.ai.azure.key}") String apiKey,
            @Value("${vibecode.ai.azure.deployment-name}") String deploymentName,
            @Value("${vibecode.ai.azure.temperature:0.7}") double temperature,
            @Value("${vibecode.ai.azure.max-tokens:2048}") int maxTokens) {
        
        this.deploymentName = deploymentName;
        this.temperature = temperature;
        this.maxTokens = maxTokens;
        
        this.client = new OpenAIClientBuilder()
                .endpoint(endpoint)
                .credential(new AzureKeyCredential(apiKey))
                .buildClient();
        
        logger.info("Azure AI Foundry client initialized with deployment: {}", deploymentName);
    }
    
    @Override
    public ChatResponse call(String prompt) {
        try {
            List<ChatRequestMessage> chatMessages = new ArrayList<>();
            chatMessages.add(new ChatRequestSystemMessage(getSystemPrompt()));
            chatMessages.add(new ChatRequestUserMessage(prompt));
            
            ChatCompletionsOptions options = new ChatCompletionsOptions(chatMessages)
                    .setTemperature(temperature)
                    .setMaxTokens(maxTokens);
            
            ChatCompletions chatCompletions = client.getChatCompletions(deploymentName, options);
            
            if (chatCompletions.getChoices() != null && !chatCompletions.getChoices().isEmpty()) {
                ChatChoice choice = chatCompletions.getChoices().get(0);
                String content = choice.getMessage().getContent();
                
                logger.debug("Azure AI response received with {} tokens", 
                    chatCompletions.getUsage() != null ? chatCompletions.getUsage().getTotalTokens() : "unknown");
                
                return new ChatResponse(
                    new ChatResponse.ChatResult(
                        new ChatResponse.ChatOutput(content)
                    )
                );
            } else {
                logger.warn("No response received from Azure AI Foundry");
                return createErrorResponse("No response received from Azure AI service");
            }
            
        } catch (AzureException e) {
            logger.error("Azure AI Foundry API error: {}", e.getMessage(), e);
            return createErrorResponse("Azure AI service error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error calling Azure AI Foundry: {}", e.getMessage(), e);
            return createErrorResponse("Unexpected error occurred while processing your request");
        }
    }
    
    private String getSystemPrompt() {
        return """
            You are VibeCode Studio AI, an expert software development assistant specializing in collaborative, chat-first development workflows.
            
            Your role is to help users:
            - Create new projects from natural language descriptions
            - Generate high-quality, secure code snippets and complete components
            - Refactor existing code with best practices
            - Add comprehensive tests and documentation
            - Suggest security improvements and performance optimizations
            - Guide users through iterative development processes
            
            Always provide:
            - Clean, well-documented code
            - Security-conscious implementations
            - Best practice recommendations
            - Clear explanations of your suggestions
            
            For project creation requests, extract NAME, DESCRIPTION, and TECH stack clearly.
            For code generation, provide production-ready code with proper error handling.
            Be conversational, helpful, and focused on practical software development.
            """;
    }
    
    private ChatResponse createErrorResponse(String errorMessage) {
        return new ChatResponse(
            new ChatResponse.ChatResult(
                new ChatResponse.ChatOutput(
                    "I apologize, but I'm experiencing technical difficulties. " + 
                    errorMessage + 
                    " Please try again in a moment or contact support if the issue persists."
                )
            )
        );
    }
}