package com.vibecode.studio.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {
    // ChatClient implementations are now auto-configured based on vibecode.ai.provider property
    // - AzureChatClient: when vibecode.ai.provider=azure
    // - MockChatClient: when vibecode.ai.provider=mock (default)
}