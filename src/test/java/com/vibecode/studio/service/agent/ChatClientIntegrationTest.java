package com.vibecode.studio.service.agent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "vibecode.ai.provider=mock"
})
class ChatClientIntegrationTest {

    @Autowired
    private ChatClient chatClient;

    @Test
    void testChatClientIsInjected() {
        assertThat(chatClient).isNotNull();
        assertThat(chatClient).isInstanceOf(MockChatClient.class);
    }

    @Test
    void testBasicChatResponse() {
        ChatResponse response = chatClient.call("Hello, can you help me?");
        
        assertThat(response).isNotNull();
        assertThat(response.getResult()).isNotNull();
        assertThat(response.getResult().getOutput()).isNotNull();
        assertThat(response.getResult().getOutput().getContent()).isNotEmpty();
        assertThat(response.getResult().getOutput().getContent()).contains("VibeCode Studio AI");
    }

    @Test
    void testProjectExtractionPrompt() {
        String prompt = "extract project: Build a todo app with real-time updates";
        ChatResponse response = chatClient.call(prompt);
        
        String content = response.getResult().getOutput().getContent();
        assertThat(content).contains("NAME:");
        assertThat(content).contains("DESCRIPTION:");
        assertThat(content).contains("TECH:");
        assertThat(content).contains("Todo App");
    }

    @Test
    void testCodeGenerationPrompt() {
        String prompt = "generate code for a REST controller";
        ChatResponse response = chatClient.call(prompt);
        
        String content = response.getResult().getOutput().getContent();
        assertThat(content).contains("@RestController");
        assertThat(content).contains("java");
    }
}