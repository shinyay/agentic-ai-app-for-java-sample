# Azure Deployment Guide for TechKnowledge Assistant

This comprehensive guide covers how to deploy the TechKnowledge Assistant application and its related services on Microsoft Azure.

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Azure OpenAI Service Setup](#azure-openai-service-setup)
3. [Container Deployment Options](#container-deployment-options)
   - [Azure Container Apps](#azure-container-apps)
   - [Azure App Service](#azure-app-service)
   - [Azure Kubernetes Service (AKS)](#azure-kubernetes-service-aks)
4. [Environment Configuration](#environment-configuration)
5. [CI/CD Pipeline Setup](#cicd-pipeline-setup)
6. [Monitoring and Logging](#monitoring-and-logging)
7. [Security Best Practices](#security-best-practices)
8. [Troubleshooting](#troubleshooting)

## Prerequisites

Before deploying to Azure, ensure you have:

1. **Azure Account**: Active Azure subscription
2. **Azure CLI**: Installed and configured (available in Dev Container)
3. **Azure Developer CLI (azd)**: For rapid deployment (available in Dev Container)
4. **Docker**: For containerization
5. **Java 17+**: For local development and testing
6. **Maven 3.9+**: For building the application

### Authentication Setup

```bash
# Login to Azure CLI
az login

# Set your preferred subscription
az account set --subscription "<your-subscription-id>"

# Login to Azure Developer CLI
azd auth login
```

## Azure OpenAI Service Setup

The TechKnowledge Assistant requires Azure OpenAI Service for AI capabilities.

### 1. Create Azure OpenAI Resource

```bash
# Set variables
RESOURCE_GROUP="rg-techknowledge-assistant"
LOCATION="eastus"  # Choose a region that supports Azure OpenAI
OPENAI_RESOURCE_NAME="openai-techknowledge-assistant"

# Create resource group
az group create --name $RESOURCE_GROUP --location $LOCATION

# Create Azure OpenAI resource
az cognitiveservices account create \
  --name $OPENAI_RESOURCE_NAME \
  --resource-group $RESOURCE_GROUP \
  --location $LOCATION \
  --kind OpenAI \
  --sku S0 \
  --custom-domain $OPENAI_RESOURCE_NAME
```

### 2. Deploy Required Models

The application uses multiple models for different purposes:

```bash
# Deploy GPT-4 for general assistance
az cognitiveservices account deployment create \
  --name $OPENAI_RESOURCE_NAME \
  --resource-group $RESOURCE_GROUP \
  --deployment-name "gpt-4" \
  --model-name "gpt-4" \
  --model-version "0613" \
  --model-format OpenAI \
  --scale-type "Standard"

# Deploy GPT-3.5-turbo for code analysis
az cognitiveservices account deployment create \
  --name $OPENAI_RESOURCE_NAME \
  --resource-group $RESOURCE_GROUP \
  --deployment-name "gpt-35-turbo" \
  --model-name "gpt-35-turbo" \
  --model-version "0613" \
  --model-format OpenAI \
  --scale-type "Standard"

# Deploy text-embedding-ada-002 for future RAG capabilities
az cognitiveservices account deployment create \
  --name $OPENAI_RESOURCE_NAME \
  --resource-group $RESOURCE_GROUP \
  --deployment-name "text-embedding-ada-002" \
  --model-name "text-embedding-ada-002" \
  --model-version "2" \
  --model-format OpenAI \
  --scale-type "Standard"
```

### 3. Get Connection Details

```bash
# Get the endpoint
OPENAI_ENDPOINT=$(az cognitiveservices account show \
  --name $OPENAI_RESOURCE_NAME \
  --resource-group $RESOURCE_GROUP \
  --query "properties.endpoint" \
  --output tsv)

# Get the API key
OPENAI_API_KEY=$(az cognitiveservices account keys list \
  --name $OPENAI_RESOURCE_NAME \
  --resource-group $RESOURCE_GROUP \
  --query "key1" \
  --output tsv)

echo "Endpoint: $OPENAI_ENDPOINT"
echo "API Key: $OPENAI_API_KEY"
```

## Container Deployment Options

### Azure Container Apps

Azure Container Apps provides a serverless container platform ideal for microservices.

#### 1. Create Container Apps Environment

```bash
# Set variables
CONTAINERAPPS_RESOURCE_GROUP="rg-techknowledge-containerapps"
CONTAINERAPPS_ENVIRONMENT="env-techknowledge"
APP_NAME="techknowledge-assistant"

# Create resource group
az group create --name $CONTAINERAPPS_RESOURCE_GROUP --location $LOCATION

# Create Container Apps environment
az containerapp env create \
  --name $CONTAINERAPPS_ENVIRONMENT \
  --resource-group $CONTAINERAPPS_RESOURCE_GROUP \
  --location $LOCATION
```

#### 2. Deploy the Application

```bash
# Build and push container image
az acr create --resource-group $CONTAINERAPPS_RESOURCE_GROUP --name "acrtechknowledge" --sku Basic
az acr login --name "acrtechknowledge"

docker build -t acrtechknowledge.azurecr.io/techknowledge-assistant:latest .
docker push acrtechknowledge.azurecr.io/techknowledge-assistant:latest

# Deploy to Container Apps
az containerapp create \
  --name $APP_NAME \
  --resource-group $CONTAINERAPPS_RESOURCE_GROUP \
  --environment $CONTAINERAPPS_ENVIRONMENT \
  --image acrtechknowledge.azurecr.io/techknowledge-assistant:latest \
  --target-port 8080 \
  --ingress 'external' \
  --registry-server acrtechknowledge.azurecr.io \
  --env-vars \
    OPENAI_ENDPOINT="$OPENAI_ENDPOINT" \
    OPENAI_API_KEY="$OPENAI_API_KEY" \
    SPRING_PROFILES_ACTIVE="prod"
```

### Azure App Service

Azure App Service provides a fully managed platform for web applications.

#### 1. Create App Service Plan

```bash
# Set variables
APPSERVICE_RESOURCE_GROUP="rg-techknowledge-appservice"
APPSERVICE_PLAN="plan-techknowledge"
WEBAPP_NAME="webapp-techknowledge-assistant"

# Create resource group
az group create --name $APPSERVICE_RESOURCE_GROUP --location $LOCATION

# Create App Service plan
az appservice plan create \
  --name $APPSERVICE_PLAN \
  --resource-group $APPSERVICE_RESOURCE_GROUP \
  --location $LOCATION \
  --is-linux \
  --sku P1V3
```

#### 2. Deploy Using Container

```bash
# Create web app
az webapp create \
  --resource-group $APPSERVICE_RESOURCE_GROUP \
  --plan $APPSERVICE_PLAN \
  --name $WEBAPP_NAME \
  --deployment-container-image-name acrtechknowledge.azurecr.io/techknowledge-assistant:latest

# Configure app settings
az webapp config appsettings set \
  --resource-group $APPSERVICE_RESOURCE_GROUP \
  --name $WEBAPP_NAME \
  --settings \
    OPENAI_ENDPOINT="$OPENAI_ENDPOINT" \
    OPENAI_API_KEY="$OPENAI_API_KEY" \
    SPRING_PROFILES_ACTIVE="prod" \
    WEBSITES_PORT=8080
```

#### 3. Deploy Using JAR (Alternative)

```bash
# Build the application
mvn clean package -DskipTests

# Create web app for Java
az webapp create \
  --resource-group $APPSERVICE_RESOURCE_GROUP \
  --plan $APPSERVICE_PLAN \
  --name $WEBAPP_NAME \
  --runtime "JAVA:17-java17"

# Deploy JAR file
az webapp deploy \
  --resource-group $APPSERVICE_RESOURCE_GROUP \
  --name $WEBAPP_NAME \
  --src-path target/tech-knowledge-assistant-1.0.0.jar \
  --type jar
```

### Azure Kubernetes Service (AKS)

For more complex orchestration and scaling requirements.

#### 1. Create AKS Cluster

```bash
# Set variables
AKS_RESOURCE_GROUP="rg-techknowledge-aks"
AKS_CLUSTER_NAME="aks-techknowledge"

# Create resource group
az group create --name $AKS_RESOURCE_GROUP --location $LOCATION

# Create AKS cluster
az aks create \
  --resource-group $AKS_RESOURCE_GROUP \
  --name $AKS_CLUSTER_NAME \
  --node-count 2 \
  --node-vm-size Standard_D2s_v3 \
  --enable-addons monitoring \
  --generate-ssh-keys
```

#### 2. Deploy Application

```bash
# Get AKS credentials
az aks get-credentials --resource-group $AKS_RESOURCE_GROUP --name $AKS_CLUSTER_NAME

# Create Kubernetes manifests
cat << EOF > k8s-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: techknowledge-assistant
spec:
  replicas: 3
  selector:
    matchLabels:
      app: techknowledge-assistant
  template:
    metadata:
      labels:
        app: techknowledge-assistant
    spec:
      containers:
      - name: techknowledge-assistant
        image: acrtechknowledge.azurecr.io/techknowledge-assistant:latest
        ports:
        - containerPort: 8080
        env:
        - name: OPENAI_ENDPOINT
          value: "$OPENAI_ENDPOINT"
        - name: OPENAI_API_KEY
          valueFrom:
            secretKeyRef:
              name: openai-secret
              key: api-key
        - name: SPRING_PROFILES_ACTIVE
          value: "prod"
---
apiVersion: v1
kind: Service
metadata:
  name: techknowledge-assistant-service
spec:
  selector:
    app: techknowledge-assistant
  ports:
  - port: 80
    targetPort: 8080
  type: LoadBalancer
EOF

# Create secret for OpenAI API key
kubectl create secret generic openai-secret --from-literal=api-key="$OPENAI_API_KEY"

# Apply deployment
kubectl apply -f k8s-deployment.yaml
```

## Environment Configuration

### Application Properties for Azure

Create `src/main/resources/application-prod.yml`:

```yaml
spring:
  profiles:
    active: prod
  
server:
  port: ${PORT:8080}

langchain4j:
  open-ai:
    chat-model:
      base-url: ${OPENAI_ENDPOINT:}
      api-key: ${OPENAI_API_KEY:}
      model-name: ${OPENAI_MODEL_NAME:gpt-4}
      temperature: 0.7
      max-tokens: 4000
      timeout: 60s
    
    embedding-model:
      base-url: ${OPENAI_ENDPOINT:}
      api-key: ${OPENAI_API_KEY:}
      model-name: ${OPENAI_EMBEDDING_MODEL:text-embedding-ada-002}

logging:
  level:
    com.example.techassistant: INFO
    org.springframework.security: INFO
  pattern:
    console: "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
  endpoint:
    health:
      show-details: always
```

### Environment Variables

Set these environment variables in your deployment:

```bash
# Required
OPENAI_ENDPOINT=https://your-openai-resource.openai.azure.com/
OPENAI_API_KEY=your-api-key-here
SPRING_PROFILES_ACTIVE=prod

# Optional model overrides
OPENAI_MODEL_NAME=gpt-4
OPENAI_CODE_MODEL_NAME=gpt-35-turbo
OPENAI_ARCHITECTURE_MODEL_NAME=gpt-4
OPENAI_EMBEDDING_MODEL=text-embedding-ada-002

# Server configuration
PORT=8080
```

## CI/CD Pipeline Setup

### GitHub Actions Workflow

Create `.github/workflows/azure-deploy.yml`:

```yaml
name: Deploy to Azure

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

env:
  AZURE_WEBAPP_NAME: your-webapp-name
  AZURE_WEBAPP_PACKAGE_PATH: '.'
  JAVA_VERSION: '17'

jobs:
  build:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v4
    
    - name: Set up Java version
      uses: actions/setup-java@v3
      with:
        java-version: ${{ env.JAVA_VERSION }}
        distribution: 'temurin'
    
    - name: Build with Maven
      run: mvn clean package -DskipTests
    
    - name: Upload artifact for deployment job
      uses: actions/upload-artifact@v3
      with:
        name: java-app
        path: '${{ env.AZURE_WEBAPP_PACKAGE_PATH }}/target/*.jar'

  deploy:
    runs-on: ubuntu-latest
    needs: build
    environment:
      name: 'Production'
      url: ${{ steps.deploy-to-webapp.outputs.webapp-url }}
    
    steps:
    - name: Download artifact from build job
      uses: actions/download-artifact@v3
      with:
        name: java-app
    
    - name: Deploy to Azure Web App
      id: deploy-to-webapp
      uses: azure/webapps-deploy@v2
      with:
        app-name: ${{ env.AZURE_WEBAPP_NAME }}
        publish-profile: ${{ secrets.AZURE_WEBAPP_PUBLISH_PROFILE }}
        package: '*.jar'
```

### Azure DevOps Pipeline

Create `azure-pipelines.yml`:

```yaml
trigger:
- main

variables:
  azureSubscription: 'your-service-connection'
  webAppName: 'your-webapp-name'
  vmImageName: 'ubuntu-latest'

stages:
- stage: Build
  displayName: Build stage
  jobs:
  - job: MavenPackageAndPublishArtifacts
    displayName: Maven Package and Publish Artifacts
    pool:
      vmImage: $(vmImageName)
    
    steps:
    - task: Maven@3
      displayName: 'Maven Package'
      inputs:
        mavenPomFile: 'pom.xml'
        goals: 'clean package'
        options: '-DskipTests'
    
    - task: CopyFiles@2
      displayName: 'Copy Files to artifact staging directory'
      inputs:
        SourceFolder: '$(System.DefaultWorkingDirectory)'
        Contents: '**/target/*.?(war|jar)'
        TargetFolder: $(Build.ArtifactStagingDirectory)
    
    - upload: $(Build.ArtifactStagingDirectory)
      artifact: drop

- stage: Deploy
  displayName: Deploy stage
  dependsOn: Build
  condition: succeeded()
  jobs:
  - deployment: DeploymentJob
    pool:
      vmImage: $(vmImageName)
    environment: production
    strategy:
      runOnce:
        deploy:
          steps:
          - task: AzureWebApp@1
            displayName: 'Azure Web App Deploy'
            inputs:
              azureSubscription: $(azureSubscription)
              appType: webAppLinux
              appName: $(webAppName)
              package: '$(Pipeline.Workspace)/drop/**/target/*.?(war|jar)'
```

## Monitoring and Logging

### Application Insights Integration

1. **Create Application Insights Resource**:

```bash
# Create Application Insights
az monitor app-insights component create \
  --app "appinsights-techknowledge" \
  --location $LOCATION \
  --resource-group $RESOURCE_GROUP \
  --application-type web

# Get instrumentation key
INSTRUMENTATION_KEY=$(az monitor app-insights component show \
  --app "appinsights-techknowledge" \
  --resource-group $RESOURCE_GROUP \
  --query "instrumentationKey" \
  --output tsv)
```

2. **Add to Application Configuration**:

```yaml
# application-prod.yml
azure:
  application-insights:
    instrumentation-key: ${APPLICATIONINSIGHTS_INSTRUMENTATION_KEY:}
```

### Log Analytics Workspace

```bash
# Create Log Analytics workspace
az monitor log-analytics workspace create \
  --resource-group $RESOURCE_GROUP \
  --workspace-name "law-techknowledge"
```

## Security Best Practices

### 1. Use Azure Key Vault for Secrets

```bash
# Create Key Vault
az keyvault create \
  --name "kv-techknowledge" \
  --resource-group $RESOURCE_GROUP \
  --location $LOCATION

# Store OpenAI API key
az keyvault secret set \
  --vault-name "kv-techknowledge" \
  --name "openai-api-key" \
  --value "$OPENAI_API_KEY"
```

### 2. Configure Managed Identity

```bash
# Enable system-assigned managed identity for App Service
az webapp identity assign \
  --resource-group $APPSERVICE_RESOURCE_GROUP \
  --name $WEBAPP_NAME

# Grant access to Key Vault
az keyvault set-policy \
  --name "kv-techknowledge" \
  --object-id $(az webapp identity show --resource-group $APPSERVICE_RESOURCE_GROUP --name $WEBAPP_NAME --query principalId --output tsv) \
  --secret-permissions get
```

### 3. Network Security

```bash
# Configure firewall rules for Azure OpenAI
az cognitiveservices account network-rule add \
  --name $OPENAI_RESOURCE_NAME \
  --resource-group $RESOURCE_GROUP \
  --subnet "/subscriptions/{subscription-id}/resourceGroups/{resource-group}/providers/Microsoft.Network/virtualNetworks/{vnet-name}/subnets/{subnet-name}"
```

## Troubleshooting

### Common Issues

1. **OpenAI Connection Issues**:
   - Verify endpoint URL format
   - Check API key validity
   - Ensure model deployments are active

2. **Container Startup Problems**:
   - Check port configuration (8080)
   - Verify environment variables
   - Review application logs

3. **Authentication Failures**:
   - Validate Azure CLI login
   - Check service principal permissions
   - Verify subscription access

### Diagnostic Commands

```bash
# Check application health
curl https://your-app-url/actuator/health

# View container logs (Container Apps)
az containerapp logs show \
  --name $APP_NAME \
  --resource-group $CONTAINERAPPS_RESOURCE_GROUP

# View App Service logs
az webapp log tail \
  --name $WEBAPP_NAME \
  --resource-group $APPSERVICE_RESOURCE_GROUP

# Test OpenAI connectivity
curl -X POST \
  -H "Content-Type: application/json" \
  -H "api-key: $OPENAI_API_KEY" \
  -d '{"messages":[{"role":"user","content":"test"}]}' \
  "$OPENAI_ENDPOINT/openai/deployments/gpt-4/chat/completions?api-version=2024-02-15-preview"
```

### Performance Optimization

1. **Scaling Configuration**:

```bash
# Auto-scale App Service
az monitor autoscale create \
  --resource-group $APPSERVICE_RESOURCE_GROUP \
  --resource "/subscriptions/{subscription-id}/resourceGroups/$APPSERVICE_RESOURCE_GROUP/providers/Microsoft.Web/serverfarms/$APPSERVICE_PLAN" \
  --name "autoscale-techknowledge" \
  --min-count 1 \
  --max-count 10 \
  --count 2
```

2. **Container Apps Scaling**:

```bash
# Update Container App with scaling rules
az containerapp update \
  --name $APP_NAME \
  --resource-group $CONTAINERAPPS_RESOURCE_GROUP \
  --min-replicas 1 \
  --max-replicas 10
```

## Cost Optimization

### 1. Use Appropriate Service Tiers

- **Development**: Use B1 App Service Plan or Container Apps consumption
- **Production**: Use P1V3 App Service Plan or Container Apps dedicated

### 2. Monitor OpenAI Usage

```bash
# Set up cost alerts for Azure OpenAI
az consumption budget create \
  --budget-name "openai-budget" \
  --amount 100 \
  --category cost \
  --time-grain monthly \
  --time-period start-date=2024-01-01 \
  --resource-group $RESOURCE_GROUP
```

### 3. Implement Caching

Configure Redis Cache for frequent requests:

```bash
# Create Redis Cache
az redis create \
  --location $LOCATION \
  --name "redis-techknowledge" \
  --resource-group $RESOURCE_GROUP \
  --sku Basic \
  --vm-size c0
```

## Next Steps

After successful deployment, consider implementing:

1. **Vector Store Integration**: Add Azure AI Search for RAG capabilities
2. **Real-time Features**: Implement WebSocket support for chat functionality
3. **Frontend Development**: Create React-based user interface
4. **Advanced Monitoring**: Set up custom metrics and dashboards
5. **Load Testing**: Validate performance under expected load

For additional support or questions, refer to the [Azure documentation](https://docs.microsoft.com/azure/) or open an issue in the repository.