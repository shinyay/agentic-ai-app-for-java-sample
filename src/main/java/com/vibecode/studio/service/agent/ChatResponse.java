package com.vibecode.studio.service.agent;

public class ChatResponse {
    
    private final ChatResult result;
    
    public ChatResponse(ChatResult result) {
        this.result = result;
    }
    
    public ChatResult getResult() {
        return result;
    }
    
    public static class ChatResult {
        private final ChatOutput output;
        
        public ChatResult(ChatOutput output) {
            this.output = output;
        }
        
        public ChatOutput getOutput() {
            return output;
        }
    }
    
    public static class ChatOutput {
        private final String content;
        
        public ChatOutput(String content) {
            this.content = content;
        }
        
        public String getContent() {
            return content;
        }
    }
}