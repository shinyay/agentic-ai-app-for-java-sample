package com.vibecode.studio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "vibecode.ai")
public class AIProperties {
    
    private String provider = "mock";
    private Azure azure = new Azure();
    
    public String getProvider() {
        return provider;
    }
    
    public void setProvider(String provider) {
        this.provider = provider;
    }
    
    public Azure getAzure() {
        return azure;
    }
    
    public void setAzure(Azure azure) {
        this.azure = azure;
    }
    
    public static class Azure {
        private String endpoint;
        private String key;
        private String deploymentName = "gpt-4";
        private double temperature = 0.7;
        private int maxTokens = 2048;
        
        public String getEndpoint() {
            return endpoint;
        }
        
        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }
        
        public String getKey() {
            return key;
        }
        
        public void setKey(String key) {
            this.key = key;
        }
        
        public String getDeploymentName() {
            return deploymentName;
        }
        
        public void setDeploymentName(String deploymentName) {
            this.deploymentName = deploymentName;
        }
        
        public double getTemperature() {
            return temperature;
        }
        
        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
        
        public int getMaxTokens() {
            return maxTokens;
        }
        
        public void setMaxTokens(int maxTokens) {
            this.maxTokens = maxTokens;
        }
    }
}