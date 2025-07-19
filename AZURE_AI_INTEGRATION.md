# Azure AI Foundry Integration Demo

This document demonstrates how to configure and use Azure AI Foundry with VibeCode Studio.

## Setup Azure AI Foundry

1. **Create Azure AI Foundry Resource**
   - Go to [Azure Portal](https://portal.azure.com)
   - Create a new Azure AI Foundry resource
   - Note the endpoint URL and API key

2. **Deploy a Model**
   - In Azure AI Foundry, deploy a model (e.g., GPT-4)
   - Note the deployment name

## Configuration

### Environment Variables (Recommended for Production)
```bash
export AZURE_AI_ENDPOINT=https://your-resource.openai.azure.com/
export AZURE_AI_KEY=your-azure-api-key
export AZURE_AI_DEPLOYMENT=gpt-4
```

### Application Properties
```properties
# application-prod.properties
vibecode.ai.provider=azure
vibecode.ai.azure.endpoint=${AZURE_AI_ENDPOINT}
vibecode.ai.azure.key=${AZURE_AI_KEY}
vibecode.ai.azure.deployment-name=${AZURE_AI_DEPLOYMENT:gpt-4}
vibecode.ai.azure.temperature=0.7
vibecode.ai.azure.max-tokens=2048
```

## Running with Azure AI Foundry

```bash
# Start with production profile
mvn spring-boot:run -Dspring-boot.run.profiles=prod

# Or set environment variables and run
AZURE_AI_ENDPOINT=https://your-resource.openai.azure.com/ \
AZURE_AI_KEY=your-azure-api-key \
AZURE_AI_DEPLOYMENT=gpt-4 \
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## Testing the Integration

### 1. Create a Project with Azure AI
```bash
curl -X POST http://localhost:8080/api/projects/create \
  -H "Content-Type: application/json" \
  -d '{
    "prompt": "Build a microservice for user management with Spring Boot",
    "userId": "demo-user"
  }'
```

### 2. Start a Chat Session
```bash
curl -X POST http://localhost:8080/api/chat/message \
  -H "Content-Type: application/json" \
  -d '{
    "sessionId": 1,
    "message": "Generate a REST controller for user CRUD operations",
    "userId": "demo-user"
  }'
```

## Architecture Benefits

- **Scalable**: Azure AI Foundry provides enterprise-grade scaling
- **Secure**: Built-in security and compliance features
- **Cost-Effective**: Pay-per-token pricing with built-in cost monitoring
- **Reliable**: 99.9% SLA with global availability

## Monitoring and Cost Control

The application automatically tracks:
- Token usage per request
- Cost per project and user
- Response times and error rates
- Budget limits and alerts

## Fallback to Mock Mode

For development and testing without Azure costs:
```properties
vibecode.ai.provider=mock
```

This uses the built-in MockChatClient for offline development.