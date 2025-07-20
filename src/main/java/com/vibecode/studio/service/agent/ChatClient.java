package com.vibecode.studio.service.agent;

public interface ChatClient {
    ChatResponse call(String prompt);
}