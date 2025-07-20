# Azure Configuration with Azure CLI

This guide shows how to configure Azure settings for VibeCode Studio using Azure CLI commands.

## 🚀 Quick Start

### Automated Setup (Recommended)
```bash
# Complete Azure setup with one command
bash .devcontainer/setup-azure.sh

# Start the application with Azure configuration
mvn spring-boot:run -Dspring-boot.run.profiles=azure
```

### Step-by-Step Setup

1. **Authenticate with Azure CLI**
```bash
# Interactive login
bash .devcontainer/azure-auth.sh login

# Check authentication status
bash .devcontainer/azure-auth.sh status
```

2. **Create Azure Resources**
```bash
# Create resource group
az group create --name vibecode-studio-rg --location eastus

# Create Azure OpenAI service
az cognitiveservices account create \
    --name vibecode-ai-service \
    --resource-group vibecode-studio-rg \
    --location eastus \
    --kind OpenAI \
    --sku S0

# Deploy GPT-4 model
az cognitiveservices account deployment create \
    --name vibecode-ai-service \
    --resource-group vibecode-studio-rg \
    --deployment-name gpt-4 \
    --model-name gpt-4 \
    --model-version "0613" \
    --model-format OpenAI \
    --scale-settings-scale-type "Standard" \
    --scale-settings-capacity 10
```

3. **Get Configuration**
```bash
# Get endpoint URL
ENDPOINT=$(az cognitiveservices account show \
    --name vibecode-ai-service \
    --resource-group vibecode-studio-rg \
    --query properties.endpoint -o tsv)

# Get API key
API_KEY=$(az cognitiveservices account keys list \
    --name vibecode-ai-service \
    --resource-group vibecode-studio-rg \
    --query key1 -o tsv)

echo "Endpoint: $ENDPOINT"
echo "API Key: $API_KEY"
```

## 🔧 Authentication Methods

### Option 1: Azure CLI Authentication (Recommended)
```bash
# Login with browser
az login

# Login with device code (for Codespaces)
az login --use-device-code

# Verify login
az account show
```

### Option 2: Service Principal (CI/CD)
```bash
# Create service principal
az ad sp create-for-rbac --name "vibecode-studio-sp" \
    --role contributor \
    --scopes /subscriptions/{subscription-id}

# Login with service principal
az login --service-principal \
    --username {client-id} \
    --password {client-secret} \
    --tenant {tenant-id}
```

## 🛠️ Configuration Scripts

### Azure Authentication Helper
```bash
# Show authentication status
bash .devcontainer/azure-auth.sh status

# Interactive login
bash .devcontainer/azure-auth.sh login

# Device code login (Codespaces)
bash .devcontainer/azure-auth.sh device

# Configure MCP Azure integration
bash .devcontainer/azure-auth.sh mcp

# Set up environment variables
bash .devcontainer/azure-auth.sh env

# Test authentication and permissions
bash .devcontainer/azure-auth.sh test
```

### Azure Resource Setup
```bash
# Complete setup (creates resources + configures app)
bash .devcontainer/setup-azure.sh

# Only retrieve configuration from existing resources
bash .devcontainer/setup-azure.sh config-only

# Clean up all Azure resources
bash .devcontainer/setup-azure.sh clean
```

## 🌍 Environment Configuration

### Automatic Configuration
The setup scripts create environment files automatically:

1. **`.env`** - Environment variables for the application
2. **`azure-env.sh`** - Shell script to set Azure variables
3. **`application-azure.properties`** - Spring Boot configuration

### Manual Configuration
```bash
# Set Azure subscription
az account set --subscription "Your Subscription Name"

# Get subscription info
SUBSCRIPTION_ID=$(az account show --query id -o tsv)
TENANT_ID=$(az account show --query tenantId -o tsv)

# Export for application use
export AZURE_SUBSCRIPTION_ID="$SUBSCRIPTION_ID"
export AZURE_TENANT_ID="$TENANT_ID"
export AZURE_CLI_AUTH="true"
```

## 🚀 Running the Application

### With Azure Profile
```bash
# After running setup-azure.sh
mvn spring-boot:run -Dspring-boot.run.profiles=azure
```

### With Environment Variables
```bash
# Load Azure environment
source azure-env.sh

# Start application
mvn spring-boot:run
```

### Manual Environment Setup
```bash
# Set Azure AI configuration
export AZURE_AI_ENDPOINT="https://your-service.openai.azure.com/"
export AZURE_AI_KEY="your-api-key"
export AZURE_AI_DEPLOYMENT="gpt-4"

# Start application
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## 🧪 Testing the Configuration

### Test API Endpoints
```bash
# Test project creation with Azure AI
curl -X POST http://localhost:8080/api/projects/create \
  -H "Content-Type: application/json" \
  -d '{
    "prompt": "Create a Spring Boot microservice for user management",
    "userId": "test-user"
  }'

# Test chat with Azure AI
curl -X POST http://localhost:8080/api/chat/message \
  -H "Content-Type: application/json" \
  -d '{
    "sessionId": 1,
    "message": "Add input validation to the user controller",
    "userId": "test-user"
  }'
```

### Test Azure CLI Integration
```bash
# Test authentication
bash .devcontainer/azure-auth.sh test

# List Azure resources
az resource list --resource-group vibecode-studio-rg

# Check AI service status
az cognitiveservices account show \
    --name vibecode-ai-service \
    --resource-group vibecode-studio-rg
```

## 🔒 Security Best Practices

### Authentication
- Use Azure CLI authentication for development
- Use service principal for CI/CD pipelines
- Enable MFA for interactive logins
- Rotate API keys regularly

### Resource Management
```bash
# List your resources
az resource list --query "[?resourceGroup=='vibecode-studio-rg']"

# Monitor costs
az consumption usage list --start-date $(date -d '30 days ago' '+%Y-%m-%d')

# Set up budget alerts
az consumption budget create \
    --budget-name "vibecode-studio-budget" \
    --amount 100 \
    --time-grain Monthly \
    --category Cost
```

## 🧹 Cleanup

### Remove Resources
```bash
# Delete specific resource group
bash .devcontainer/setup-azure.sh clean

# Or manually
az group delete --name vibecode-studio-rg --yes --no-wait
```

### Logout
```bash
# Logout from Azure CLI
bash .devcontainer/azure-auth.sh logout
```

## 🔍 Troubleshooting

### Common Issues
1. **Not logged in**: Run `az login` first
2. **Insufficient permissions**: Check your Azure role assignments
3. **Resource already exists**: Use `config-only` option
4. **API quota exceeded**: Check your Azure AI service limits

### Debugging Commands
```bash
# Check Azure CLI version
az --version

# Verify account access
az account show

# List available locations
az account list-locations --query "[].name" -o tsv

# Check cognitive services availability
az cognitiveservices account list-kinds --location eastus
```

This approach provides a seamless Azure configuration experience using Azure CLI, eliminating the need for manual credential management while maintaining security best practices.

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