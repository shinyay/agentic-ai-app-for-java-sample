package com.vibecode.studio.config;

import com.vibecode.studio.service.agent.ChatClient;
import com.vibecode.studio.service.agent.MockChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {
    
    @Bean
    public ChatClient chatClient() {
        return new MockChatClient();
    }
}