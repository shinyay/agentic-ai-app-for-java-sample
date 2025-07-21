# 🐳 Dev Container Setup for TechKnowledge Assistant

> **Complete Development Environment for GitHub Codespaces & VS Code**  
> Zero-configuration setup with Java 17, Maven, Azure CLI, and AI development tools

[![Codespaces](https://img.shields.io/badge/GitHub-Codespaces-black.svg)](https://github.com/features/codespaces)
[![VS Code](https://img.shields.io/badge/VS%20Code-DevContainer-blue.svg)](https://code.visualstudio.com/docs/remote/containers)
[![Java 17](https://img.shields.io/badge/Java-17+-orange.svg)](https://openjdk.java.net/projects/jdk/17/)
[![Azure CLI](https://img.shields.io/badge/Azure-CLI-blue.svg)](https://docs.microsoft.com/en-us/cli/azure/)

## 🎯 What This Dev Container Provides

This directory contains a **complete development environment** that enables instant development of the TechKnowledge Assistant in GitHub Codespaces or VS Code Dev Containers, with all necessary tools pre-configured.

### 🚀 Instant Development Environment

```
┌─────────────────────────────────────────────────────────────────────┐
│                    🚀 Complete Dev Environment                     │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  💻 Development Tools                                               │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Java 17 JDK (Eclipse Temurin)                            │   │
│  │ • Maven 3.9.6 (Latest stable)                              │   │
│  │ • Git, curl, vim, nano                                     │   │
│  │ • Node.js 18+ (for future frontend development)            │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  ☁️ Azure Integration                                               │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Azure CLI (az) - Resource management                     │   │
│  │ • Azure Developer CLI (azd) - Rapid deployment             │   │
│  │ • Azure Functions Core Tools                               │   │
│  │ • Azure Storage Explorer tools                             │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🧩 VS Code Extensions                                              │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Java Extension Pack (IntelliSense, debugging)            │   │
│  │ • Spring Boot Tools (application.yml support)              │   │
│  │ • Azure Tools (deployment & monitoring)                    │   │
│  │ • Docker & Kubernetes support                              │   │
│  │ • GitLens (Git integration)                                │   │
│  │ • REST Client (API testing)                                │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🐛 Debug & Development                                             │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Remote debugging (port 5005)                             │   │
│  │ • Application port forwarding (8080)                       │   │
│  │ • Live reload support                                      │   │
│  │ • Integrated terminal                                      │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

## 📁 Dev Container Files Structure

```
.devcontainer/
├── 🐳 Dockerfile                    # Development environment image
├── 🔧 docker-compose.yml            # Development services orchestration
├── ⚙️ devcontainer.json             # VS Code Dev Container configuration
└── 📖 README.md                     # This documentation
```

### 🐳 **Dockerfile** - Development Environment

Creates a complete development environment with:
- **Java 17** (Eclipse Temurin OpenJDK)
- **Maven 3.9.6** (Latest stable release)
- **Azure CLI & Azure Developer CLI** (Latest versions)
- **Essential development tools** (git, curl, vim, nano, etc.)
- **Node.js 18+** (For future frontend development)

### 🔧 **docker-compose.yml** - Service Orchestration

Configures:
- **Volume mounting** for source code persistence
- **Port forwarding** (8080 for app, 5005 for debugging)
- **Environment variable** setup
- **Development service** dependencies

### ⚙️ **devcontainer.json** - VS Code Integration

Provides:
- **Extension management** (Java, Spring Boot, Azure tools)
- **Port forwarding** configuration
- **Environment setup** and workspace configuration
- **Debug configuration** for Java applications

## 🚀 Getting Started

### Option 1: GitHub Codespaces (Recommended)

```
┌─────────────────────────────────────────────────────────────────────┐
│                  🌐 GitHub Codespaces Workflow                     │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  Step 1: Open Repository                                           │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. Go to the GitHub repository                              │   │
│  │ 2. Click the green "Code" button                            │   │
│  │ 3. Select "Codespaces" tab                                  │   │
│  │ 4. Click "Create codespace on main"                         │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                            ⬇                                        │
│  Step 2: Environment Setup (Automatic)                             │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Container builds (~2-3 minutes)                          │   │
│  │ • VS Code opens in browser                                  │   │
│  │ • All tools are pre-installed                              │   │
│  │ • Extensions are automatically loaded                       │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                            ⬇                                        │
│  Step 3: Start Development                                          │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Open integrated terminal                                  │   │
│  │ • Run: mvn clean compile                                    │   │
│  │ • Run: mvn spring-boot:run                                  │   │
│  │ • Access app at forwarded localhost:8080                    │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

**⏱️ Timeline:**
- **0-2 minutes**: Click "Create codespace"
- **2-3 minutes**: Container builds and VS Code loads
- **3 minutes**: Ready to develop!

### Option 2: VS Code Dev Containers

```
┌─────────────────────────────────────────────────────────────────────┐
│                   💻 VS Code Dev Container Setup                   │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  Prerequisites:                                                     │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • VS Code installed                                         │   │
│  │ • Dev Containers extension installed                        │   │
│  │ • Docker Desktop running                                    │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  Setup Process:                                                     │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. Clone repository locally                                 │   │
│  │    git clone <repository-url>                               │   │
│  │                                                             │   │
│  │ 2. Open in VS Code                                          │   │
│  │    code agentic-ai-app-for-java-sample                      │   │
│  │                                                             │   │
│  │ 3. Reopen in Container                                      │   │
│  │    Click notification "Reopen in Container"                 │   │
│  │    OR: Ctrl+Shift+P → "Dev Containers: Reopen in Container"│   │
│  │                                                             │   │
│  │ 4. Wait for build (first time: ~5-7 minutes)               │   │
│  │                                                             │   │
│  │ 5. Start developing!                                        │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

## 🛠️ Available Tools & Commands

### 📋 Verification Commands

Once your Dev Container is running, verify everything is working:

```bash
# ✅ Verify Java installation
java -version
# Expected: openjdk version "17.0.x" 2024-xx-xx

# ✅ Verify Maven installation  
mvn -version
# Expected: Apache Maven 3.9.6

# ✅ Verify Azure CLI
az version
# Expected: azure-cli 2.x.x

# ✅ Verify Azure Developer CLI
azd version
# Expected: azd version 1.x.x

# ✅ Verify Git
git --version
# Expected: git version 2.x.x
```

### 🏗️ Development Workflow Commands

```bash
# 🔨 Build the application
mvn clean compile

# 🧪 Run tests
mvn test

# 📦 Package application
mvn package

# 🚀 Start the application
mvn spring-boot:run

# 🔄 Clean and restart
mvn clean compile spring-boot:run

# 🐛 Debug mode (with remote debugging enabled)
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

### ☁️ Azure Development Commands

```bash
# 🔐 Login to Azure
az login

# 👤 Show current account
az account show

# 🏗️ Create resource group
az group create --name rg-techknowledge --location eastus

# 🤖 Create Azure OpenAI service
az cognitiveservices account create \
  --name techknowledge-openai \
  --resource-group rg-techknowledge \
  --location eastus \
  --kind OpenAI \
  --sku S0

# 🚀 Quick deployment with azd
azd auth login
azd init
azd up
```

### 🧪 Application Testing Commands

```bash
# 🩺 Health check
curl http://localhost:8080/api/dev/health

# 📊 Application info
curl http://localhost:8080/api/dev/info

# 🔄 Echo test
curl -X POST http://localhost:8080/api/dev/echo \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello Dev Container!"}'

# 🧠 AI consultation test
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{
    "question": "What is Spring Boot?",
    "expertiseArea": "GENERAL"
  }'
```

## ⚙️ Environment Configuration

### 🔐 Required Environment Variables

Set up your Azure OpenAI credentials:

#### **In GitHub Codespaces:**
1. Go to repository **Settings** → **Secrets** → **Codespaces**
2. Add these secrets:
   ```
   AZURE_OPENAI_API_KEY          # Your Azure OpenAI API key
   AZURE_OPENAI_ENDPOINT         # https://your-instance.openai.azure.com
   AZURE_OPENAI_DEPLOYMENT_NAME  # Your GPT-4 deployment name
   AZURE_OPENAI_EMBEDDING_DEPLOYMENT # Your embedding deployment
   ```

#### **In VS Code Dev Containers:**
Create `.env` file in the project root:
```bash
# .env (add to .gitignore)
AZURE_OPENAI_API_KEY=your-api-key-here
AZURE_OPENAI_ENDPOINT=https://your-instance.openai.azure.com
AZURE_OPENAI_DEPLOYMENT_NAME=gpt-4
AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-ada-002
```

### 🔧 Optional Configuration

```bash
# Spring profile (development settings)
export SPRING_PROFILES_ACTIVE=dev

# Server port (default: 8080)
export SERVER_PORT=8080

# Debug port (default: 5005)  
export DEBUG_PORT=5005

# Maven options
export MAVEN_OPTS="-Xmx1024m"
```

## 🐛 Debugging Setup

### 🔍 Remote Debugging Configuration

The Dev Container includes pre-configured remote debugging:

```
┌─────────────────────────────────────────────────────────────────────┐
│                      🐛 Debug Configuration                        │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  Java Remote Debugging:                                             │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Debug Port: 5005                                          │   │
│  │ • Transport: dt_socket                                      │   │
│  │ • Suspend: false (non-blocking startup)                     │   │
│  │ • Address: 0.0.0.0:5005 (accessible from VS Code)          │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  VS Code Debug Integration:                                         │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Pre-configured launch.json                                │   │
│  │ • Automatic port forwarding                                 │   │
│  │ • Breakpoint support                                        │   │
│  │ • Variable inspection                                       │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

#### **Debug Workflow:**
1. Start application in debug mode:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
   ```

2. In VS Code:
   - Press **F5** or go to **Run and Debug**
   - Select **"Attach to Java Application"**
   - Set breakpoints in your code
   - Debug normally with step-through, variable inspection, etc.

## 🎯 Development Workflow

### 🔄 Typical Development Session

```bash
# 1. Start dev container (automatic in Codespaces)
# 2. Open integrated terminal

# 3. First-time setup
mvn clean compile

# 4. Run tests to ensure everything works
mvn test

# 5. Start application
mvn spring-boot:run

# 6. In another terminal, test the API
curl http://localhost:8080/api/dev/health

# 7. Make code changes
# 8. Quick restart (in same terminal, Ctrl+C then):
mvn compile spring-boot:run

# 9. Test your changes
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{"question": "Test question", "expertiseArea": "GENERAL"}'

# 10. Commit and push when ready
git add .
git commit -m "Your changes"
git push
```

## 🎨 VS Code Extensions Included

### 🧩 Pre-installed Extensions

```
📦 Java Development:
├── Extension Pack for Java (vscjava.vscode-java-pack)
├── Spring Boot Extension Pack (vmware.vscode-spring-boot)
├── Spring Boot Tools (vmware.vscode-spring-boot)
└── Maven for Java (vscjava.vscode-maven)

☁️ Azure Development:
├── Azure Tools (ms-vscode.vscode-node-azure-pack)
├── Azure CLI Tools (ms-vscode.azurecli)
├── Azure Functions (ms-azuretools.vscode-azurefunctions)
└── Azure Resources (ms-azuretools.vscode-azureresourcegroups)

🐳 Container Development:
├── Docker (ms-azuretools.vscode-docker)
├── Kubernetes (ms-kubernetes-tools.vscode-kubernetes-tools)
└── Remote - Containers (ms-vscode-remote.remote-containers)

🛠️ General Development:
├── GitLens (eamodio.gitlens)
├── REST Client (humao.rest-client)
├── YAML (redhat.vscode-yaml)
└── Thunder Client (rangav.vscode-thunder-client)
```

### 🔧 Extension Configuration

Pre-configured settings for optimal development experience:
- **Java**: Proper classpath detection, auto-import, formatting
- **Spring Boot**: application.yml support, dependency management
- **Azure**: Integrated login, resource management
- **Git**: Enhanced Git integration with history and blame

## 🚨 Troubleshooting

### ❓ Common Issues & Solutions

#### **Issue: Container build fails**
```bash
# Solution: Rebuild container
# In VS Code: Ctrl+Shift+P → "Dev Containers: Rebuild Container"
# In Codespaces: Delete and create new codespace
```

#### **Issue: Azure CLI not working**
```bash
# Solution: Re-login to Azure
az logout
az login
```

#### **Issue: Application won't start**
```bash
# Solution: Check environment variables
env | grep AZURE_OPENAI

# Verify Maven dependencies
mvn dependency:tree

# Check Java version
java -version
```

#### **Issue: Port forwarding not working**
```bash
# Solution: Check VS Code port forwarding
# 1. Open VS Code port panel (usually bottom panel)
# 2. Ensure ports 8080 and 5005 are forwarded
# 3. Try accessing via forwarded URL
```

#### **Issue: Extensions not loading**
```bash
# Solution: Reload VS Code window
# Ctrl+Shift+P → "Developer: Reload Window"
```

## 🎉 Ready to Develop!

Once your Dev Container is running, you have a **complete enterprise development environment** with:

✅ **Zero configuration required**  
✅ **All tools pre-installed and configured**  
✅ **Azure integration ready**  
✅ **Debug environment setup**  
✅ **VS Code extensions loaded**  
✅ **Port forwarding configured**  

**🚀 Start developing immediately with `mvn spring-boot:run`!**

---

For more information about the application itself, see the [main README.md](../README.md) and [Azure deployment guide](../docs/azure-deployment.md).