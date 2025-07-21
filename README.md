# 🚀 TechKnowledge Assistant

> **Enterprise AI-Powered Technical Knowledge Management Platform**  
> Demonstrating comprehensive LangChain4j 1.1.0 integration with Azure OpenAI services

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://openjdk.java.net/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2+-green.svg)](https://spring.io/projects/spring-boot)
[![LangChain4j](https://img.shields.io/badge/LangChain4j-1.1.0-orange.svg)](https://docs.langchain4j.dev/)
[![Azure OpenAI](https://img.shields.io/badge/Azure-OpenAI-blue.svg)](https://azure.microsoft.com/en-us/products/ai-services/openai-service)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)
[![Codespaces](https://img.shields.io/badge/GitHub-Codespaces-black.svg)](https://github.com/features/codespaces)

## 🎯 What This Application Does

TechKnowledge Assistant is a **comprehensive enterprise-grade AI platform** that serves as an intelligent technical consultation hub for development teams. It provides:

### 🧠 **AI-Powered Technical Expertise**
- **Multi-Domain Consultation**: Expert-level guidance across Cloud, Security, Performance, Architecture, and Code domains
- **Intelligent Code Review**: Automated code analysis with security, performance, and quality assessments
- **Multiple AI Models**: Specialized models optimized for different technical expertise areas

### 🤖 **Intelligent Agent System** 
- **DevOps Agent**: Automated application health checking and infrastructure analysis
- **Code Analysis Agent**: Deep code review with improvement suggestions
- **Extensible Framework**: Ready for additional specialized agents

### 🏢 **Enterprise-Ready Architecture**
- **Production-Grade Spring Boot**: Built with Spring Boot 3.2+ and Java 17
- **Azure Cloud Integration**: Seamless integration with Azure OpenAI services
- **Security First**: OAuth2 authentication and comprehensive security configurations
- **Scalable Design**: Containerized with Docker and ready for cloud deployment

## 🏗️ System Architecture

### High-Level Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                          🌐 TechKnowledge Assistant                             │
│                        Enterprise AI Platform                                  │
└─────────────────────────────────────────────────────────────────────────────────┘
                                        │
                                        ▼
┌─────────────────────────────────────────────────────────────────────────────────┐
│                           🔌 REST API Layer                                    │
│  ┌─────────────────┐ ┌─────────────────┐ ┌─────────────────┐                   │
│  │   AI Controller │ │ Code Controller │ │  Dev Controller │                   │
│  │                 │ │                 │ │                 │                   │
│  │ • Consultation  │ │ • Code Review   │ │ • Health Check  │                   │
│  │ • Expert Areas  │ │ • Improvements  │ │ • Echo & Info   │                   │
│  └─────────────────┘ └─────────────────┘ └─────────────────┘                   │
└─────────────────────────────────────────────────────────────────────────────────┘
                                        │
                                        ▼
┌─────────────────────────────────────────────────────────────────────────────────┐
│                          💼 Business Logic Layer                                │
│  ┌─────────────────┐ ┌─────────────────┐ ┌─────────────────┐                   │
│  │Technical Expert │ │  Code Review    │ │   AI Agent      │                   │
│  │    Service      │ │     Service     │ │   Framework     │                   │
│  │                 │ │                 │ │                 │                   │
│  │ • Multi-domain  │ │ • Security      │ │ • DevOps Agent  │                   │
│  │   consultation  │ │ • Performance   │ │ • Tool Executor │                   │
│  │ • Context-aware │ │ • Quality       │ │ • Extensible    │                   │
│  └─────────────────┘ └─────────────────┘ └─────────────────┘                   │
└─────────────────────────────────────────────────────────────────────────────────┘
                                        │
                                        ▼
┌─────────────────────────────────────────────────────────────────────────────────┐
│                         🧠 LangChain4j AI Layer                                │
│  ┌─────────────────┐ ┌─────────────────┐ ┌─────────────────┐                   │
│  │  General Chat   │ │   Code-Focused  │ │  Architecture   │                   │
│  │     Model       │ │      Model      │ │     Model       │                   │
│  │                 │ │                 │ │                 │                   │
│  │ • GPT-4 based   │ │ • Code analysis │ │ • System design │                   │
│  │ • Multi-purpose │ │ • Programming   │ │ • Best practices│                   │
│  └─────────────────┘ └─────────────────┘ └─────────────────┘                   │
└─────────────────────────────────────────────────────────────────────────────────┘
                                        │
                                        ▼
┌─────────────────────────────────────────────────────────────────────────────────┐
│                          ☁️ Azure OpenAI Services                              │
│  ┌─────────────────┐ ┌─────────────────┐ ┌─────────────────┐                   │
│  │   Chat Models   │ │  Embedding      │ │   Future        │                   │
│  │                 │ │   Models        │ │   Services      │                   │
│  │ • GPT-4         │ │ • text-embed..  │ │ • Azure AI      │                   │
│  │ • GPT-3.5-turbo │ │ • Semantic      │ │   Search        │                   │
│  │ • Custom models │ │   Search        │ │ • Cosmos DB     │                   │
│  └─────────────────┘ └─────────────────┘ └─────────────────┘                   │
└─────────────────────────────────────────────────────────────────────────────────┘
```

### Component Flow Diagram

```
📱 Client Request
     │
     ▼
🛡️ Security Layer (Spring Security + OAuth2)
     │
     ▼
🔌 REST Controller
     │
     ▼
💼 Service Layer
     │
     ├─► 🧠 LangChain4j AI Models
     │    │
     │    ▼
     │   ☁️ Azure OpenAI API
     │    │
     │    ▼
     │   🤖 AI Response
     │
     ▼
📊 Response Processing
     │
     ▼
📱 JSON Response to Client
```

### 🛠️ Technology Stack & Architecture

```
┌─────────────────────────────────────────────────────────────────────┐
│                      Technology Stack                              │
├─────────────────────────────────────────────────────────────────────┤
│ 🔧 Framework     │ Spring Boot 3.2+                               │
│ ☕ Language      │ Java 17+ (Latest LTS)                         │
│ 🤖 AI Framework  │ LangChain4j 1.1.0 (Latest)                    │
│ ☁️ Cloud         │ Microsoft Azure                                │
│ 🔨 Build Tool    │ Maven 3.9+                                     │
│ 🛡️ Security      │ Spring Security + OAuth2                       │
│ 🐳 Container     │ Docker + Docker Compose                        │
│ 🧪 Testing       │ JUnit 5 + Mockito                             │
│ 📊 Monitoring    │ Spring Boot Actuator                          │
└─────────────────────────────────────────────────────────────────────┘
```

### 🏛️ Project Structure Visualization

```
📁 agentic-ai-app-for-java-sample/
├── 📁 .devcontainer/                    # 🚀 Codespaces Ready
│   ├── 🐳 Dockerfile                    # Development environment
│   ├── 🔧 docker-compose.yml            # Development services  
│   ├── ⚙️ devcontainer.json             # VS Code configuration
│   └── 📖 README.md                     # Dev setup guide
├── 📁 docs/                             # 📚 Documentation Hub
│   ├── 📖 README.md                     # Documentation index
│   └── ☁️ azure-deployment.md           # Azure setup guide
├── 📁 src/
│   ├── 📁 main/java/com/example/techassistant/
│   │   ├── 📁 config/                   # ⚙️ Configuration
│   │   │   ├── LangChain4jConfiguration.java
│   │   │   ├── AzureIntegrationConfiguration.java
│   │   │   └── SecurityConfiguration.java
│   │   ├── 📁 controller/               # 🔌 REST API Layer
│   │   │   ├── AiController.java
│   │   │   ├── CodeReviewController.java
│   │   │   └── DevController.java
│   │   ├── 📁 model/                    # 📊 Domain Models
│   │   │   ├── TechnicalConsultationRequest.java
│   │   │   ├── CodeReviewRequest.java
│   │   │   └── ExpertiseArea.java
│   │   ├── 📁 service/                  # 💼 Business Logic
│   │   │   ├── 📁 ai/                   # 🧠 AI Services
│   │   │   │   ├── TechnicalExpertService.java
│   │   │   │   └── CodeReviewService.java
│   │   │   └── 📁 agent/                # 🤖 Intelligent Agents
│   │   │       └── DevOpsAgent.java
│   │   └── 🚀 TechKnowledgeAssistantApplication.java
│   └── 📁 test/                         # 🧪 Test Suite
├── 🐳 Dockerfile                        # Production container
├── 🔧 docker-compose.yml                # Production deployment
├── 📦 pom.xml                           # Maven configuration
└── 📖 README.md                         # Main documentation
```

### 🎯 Core Features Implemented

```
┌─────────────────────────────────────────────────────────────────────┐
│                        🚀 Current Features                         │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  🧠 Technical Expert Service                                        │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Multi-Domain AI Consultation                             │   │
│  │   - CLOUD       (Azure, AWS, GCP strategies)               │   │
│  │   - SECURITY    (Cybersecurity best practices)             │   │
│  │   - PERFORMANCE (Optimization techniques)                  │   │
│  │   - ARCHITECTURE (System design patterns)                  │   │
│  │   - CODE        (Programming best practices)               │   │
│  │   - GENERAL     (Technology guidance)                      │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🔍 Code Review Service                                             │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Automated Code Analysis                                   │   │
│  │   - SECURITY     (Vulnerability detection)                 │   │
│  │   - PERFORMANCE  (Optimization opportunities)              │   │
│  │   - QUALITY      (Code style & maintainability)           │   │
│  │   - COMPREHENSIVE (All-in-one analysis)                    │   │
│  │ • Multi-Language Support (Java, Python, JS, etc.)         │   │
│  │ • Improvement Suggestions                                  │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🤖 Multiple AI Models                                              │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • General Chat Model    (Multi-purpose consultation)       │   │
│  │ • Code-Focused Model    (Programming expertise)            │   │
│  │ • Architecture Model    (System design focus)              │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🌐 RESTful API                                                     │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Comprehensive endpoints for all AI services              │   │
│  │ • JSON request/response format                             │   │
│  │ • OpenAPI/Swagger documentation ready                      │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🛡️ Security Configuration                                          │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Spring Security with OAuth2 resource server              │   │
│  │ • CORS configuration for cross-origin requests             │   │
│  │ • Rate limiting and input validation                       │   │
│  │ • Secure configuration management                          │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🩺 Development & Health Endpoints                                  │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Application health monitoring                            │   │
│  │ • Echo service for connectivity testing                    │   │
│  │ • Application info and metrics                             │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### 🔮 Planned Features Roadmap

```
┌─────────────────────────────────────────────────────────────────────┐
│                      🚧 Development Roadmap                        │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  📄 Document Processing Pipeline (Phase 2)                         │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • PDF, Word, Text file ingestion                           │   │
│  │ • Intelligent content extraction                           │   │
│  │ • Document metadata indexing                               │   │
│  │ • Multi-format support                                     │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🔍 Vector Store Integration (Phase 2)                             │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Azure AI Search integration                              │   │
│  │ • Semantic search capabilities                             │   │
│  │ • Vector embeddings storage                                │   │
│  │ • Similarity-based retrieval                               │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🧠 RAG Implementation (Phase 3)                                   │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Retrieval-Augmented Generation                           │   │
│  │ • Context-aware AI responses                               │   │
│  │ • Knowledge base integration                               │   │
│  │ • Dynamic context retrieval                                │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🤖 Advanced Intelligent Agents (Phase 3)                         │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Enhanced DevOps Agent with tool integration              │   │
│  │ • Code Analysis Agent with Git integration                 │   │
│  │ • Infrastructure Management Agent                          │   │
│  │ • Multi-agent collaboration framework                      │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  💬 Real-time Chat Interface (Phase 4)                             │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • WebSocket support for real-time communication            │   │
│  │ • Chat history and conversation management                 │   │
│  │ • Multi-user support                                       │   │
│  │ • Real-time collaboration features                         │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🎨 React Frontend Interface (Phase 4)                             │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ • Modern React-based user interface                        │   │
│  │ • Interactive chat components                              │   │
│  │ • Code editor integration                                  │   │
│  │ • Real-time updates and notifications                      │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### 🎯 Current Development Status

```
Phase 1: ✅ COMPLETED - Core AI Services & API Foundation
├── ✅ LangChain4j 1.1.0 Integration
├── ✅ Azure OpenAI Service Integration  
├── ✅ Multi-domain Technical Consultation
├── ✅ Code Review & Analysis Service
├── ✅ RESTful API with Security
├── ✅ Docker & Dev Container Support
└── ✅ Comprehensive Documentation

Phase 2: 🔄 READY - Document Processing & Search
├── 🔄 Document ingestion pipeline
├── 🔄 Vector store with Azure AI Search
├── 🔄 Semantic search implementation
└── 🔄 Enhanced knowledge management

Phase 3: 📋 PLANNED - Advanced AI & Agents
├── 📋 RAG implementation
├── 📋 Intelligent agent framework
├── 📋 Tool integration
└── 📋 Multi-agent collaboration

Phase 4: 📋 PLANNED - Frontend & Real-time Features  
├── 📋 React frontend application
├── 📋 WebSocket real-time chat
├── 📋 Interactive code editor
└── 📋 User management system
```

## 🚀 Getting Started

### 📋 Prerequisites Checklist

```
✅ Requirements Check:
├── ☕ Java 17 or higher (OpenJDK recommended)
├── 🔨 Maven 3.9+ (Build management)
├── ☁️ Azure Account (For OpenAI services)
├── 🔑 Azure OpenAI Service access
└── 🐳 Docker (Optional, for containerized development)
```

### ⚙️ Environment Setup Flow

```
┌─────────────────────────────────────────────────────────────────────┐
│                    Environment Setup Process                       │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  1️⃣ Azure OpenAI Setup                                             │
│     ┌─────────────────────────────────────────────────────────┐     │
│     │ • Create Azure OpenAI resource                         │     │
│     │ • Deploy GPT-4 model                                   │     │
│     │ • Deploy text-embedding-ada-002 model                  │     │
│     │ • Note endpoint URL and API key                        │     │
│     └─────────────────────────────────────────────────────────┘     │
│                            ⬇                                        │
│  2️⃣ Environment Variables                                           │
│     ┌─────────────────────────────────────────────────────────┐     │
│     │ export AZURE_OPENAI_API_KEY=your-api-key              │     │
│     │ export AZURE_OPENAI_ENDPOINT=https://...azure.com     │     │
│     │ export AZURE_OPENAI_DEPLOYMENT_NAME=gpt-4             │     │
│     │ export AZURE_OPENAI_EMBEDDING_DEPLOYMENT=ada-002      │     │
│     └─────────────────────────────────────────────────────────┘     │
│                            ⬇                                        │
│  3️⃣ Build & Run                                                     │
│     ┌─────────────────────────────────────────────────────────┐     │
│     │ mvn clean compile                                      │     │
│     │ mvn test                                               │     │
│     │ mvn spring-boot:run                                    │     │
│     └─────────────────────────────────────────────────────────┘     │
│                            ⬇                                        │
│  4️⃣ Verify Setup                                                    │
│     ┌─────────────────────────────────────────────────────────┐     │
│     │ curl http://localhost:8080/api/dev/health              │     │
│     │ Expected: {"status":"UP","timestamp":"..."}            │     │
│     └─────────────────────────────────────────────────────────┘     │
└─────────────────────────────────────────────────────────────────────┘
```

### 🔧 Configuration Guide

The application supports multiple configuration approaches:

#### **Option 1: Environment Variables (Recommended)**
```bash
# Required Azure OpenAI Configuration
export AZURE_OPENAI_API_KEY="your-api-key-here"
export AZURE_OPENAI_ENDPOINT="https://your-instance.openai.azure.com"
export AZURE_OPENAI_DEPLOYMENT_NAME="gpt-4"
export AZURE_OPENAI_EMBEDDING_DEPLOYMENT="text-embedding-ada-002"

# Optional Configuration
export SPRING_PROFILES_ACTIVE="dev"  # Use development profile
export SERVER_PORT="8080"            # Default application port
```

#### **Option 2: Application Properties File**
Create `src/main/resources/application-local.yml`:
```yaml
azure:
  openai:
    api-key: "your-api-key-here"
    endpoint: "https://your-instance.openai.azure.com"
    deployment-name: "gpt-4"
    embedding-deployment: "text-embedding-ada-002"
```

Then run with: `mvn spring-boot:run -Dspring.profiles.active=local`

### 🏗️ Build and Run Instructions

#### **Quick Start (5 minutes)**
```bash
# 1. Clone the repository
git clone https://github.com/shinyay/agentic-ai-app-for-java-sample.git
cd agentic-ai-app-for-java-sample

# 2. Set environment variables (required)
export AZURE_OPENAI_API_KEY="your-api-key"
export AZURE_OPENAI_ENDPOINT="https://your-instance.openai.azure.com"
export AZURE_OPENAI_DEPLOYMENT_NAME="gpt-4"

# 3. Build and test
mvn clean compile
mvn test

# 4. Run the application
mvn spring-boot:run

# 5. Verify it's working
curl http://localhost:8080/api/dev/health
```

#### **Development Workflow**
```bash
# Full development cycle
mvn clean                    # Clean previous builds
mvn compile                  # Compile source code
mvn test                     # Run all tests
mvn package                  # Create JAR package
mvn spring-boot:run          # Run application

# Quick development restart
mvn compile spring-boot:run  # Compile and run in one command

# Production build
mvn clean package -DskipTests # Create production-ready JAR
java -jar target/tech-knowledge-assistant-*.jar  # Run JAR directly
```

The application will start on **`http://localhost:8080`** 🌐

### 🐳 Development with Codespaces/Dev Containers

```
┌─────────────────────────────────────────────────────────────────────┐
│              🚀 Instant Development Environment                     │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  GitHub Codespaces (Recommended) 🌐                                │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. Go to GitHub repository                                  │   │
│  │ 2. Click "Code" button                                     │   │
│  │ 3. Select "Open with Codespaces"                           │   │
│  │ 4. Wait for container to build (~2-3 minutes)              │   │
│  │ 5. Everything is pre-configured and ready! ✨              │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  VS Code Dev Container 💻                                          │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ 1. Clone repository locally                                 │   │
│  │ 2. Open in VS Code                                          │   │
│  │ 3. Click "Reopen in Container" notification                 │   │
│  │ 4. Container builds automatically                           │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

#### **🛠️ What's Pre-Installed in Dev Container**

```
📦 Development Environment Includes:
├── ☕ Java 17 JDK (Eclipse Temurin)
├── 🔨 Maven 3.9.6 (Latest)
├── 🧩 VS Code Java Extension Pack
├── 🎨 Spring Boot Extension Pack  
├── ☁️ Azure CLI (az command)
├── 🚀 Azure Developer CLI (azd command)
├── 🔧 Essential tools (Git, curl, vim, nano)
├── 🐛 Debug configuration (port 5005)
├── 📡 Application port forwarding (8080)
└── 🌐 Azure VS Code extensions
```

#### **⚡ Quick Commands in Dev Container**

```bash
# Verify environment is ready
java -version    # ☕ Java 17.x.x
mvn -version     # 🔨 Maven 3.9.6
az version       # ☁️ Azure CLI ready
azd version      # 🚀 Azure Developer CLI ready

# Build and run immediately
mvn clean compile                    # 🏗️ Build application
mvn test                            # 🧪 Run all tests  
mvn spring-boot:run                 # 🚀 Start application

# Azure operations (if configured)
az login                            # 🔐 Login to Azure
az account show                     # 👤 Show current account
azd init                            # 🏗️ Initialize Azure deployment
```

#### **🔐 Environment Variables in Codespaces**

Set up your Azure credentials securely:

1. **In GitHub Codespaces**: Go to repository Settings → Secrets → Codespaces
2. **Add these secrets**:
   ```
   AZURE_OPENAI_API_KEY          (Your Azure OpenAI API key)
   AZURE_OPENAI_ENDPOINT         (Your Azure OpenAI endpoint)
   AZURE_OPENAI_DEPLOYMENT_NAME  (Your GPT-4 deployment name)
   ```
3. **Secrets are automatically available** in your Codespace as environment variables

#### **🎯 Zero-Config Development Experience**

```
Minutes 0-2:   🚀 Click "Open with Codespaces"
Minutes 2-3:   🔨 Container builds (Java, Maven, tools installed)
Minutes 3-4:   ⚙️ Set environment variables in secrets
Minute 4:      ✅ Ready to code! Run: mvn spring-boot:run
Minute 5:      🌐 Access your app at forwarded localhost:8080
```

🎉 **No local setup required!** Everything works in your browser.

For detailed Dev Container setup information, see [.devcontainer/README.md](.devcontainer/README.md)

## 📋 API Documentation

### 🔍 API Endpoints Overview

```
┌─────────────────────────────────────────────────────────────────────┐
│                        🌐 REST API Endpoints                       │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  🩺 Health & Development                                            │
│  ├── GET  /api/dev/health              → Health check              │
│  ├── GET  /api/dev/info                → App information           │
│  └── POST /api/dev/echo                → Echo test service         │
│                                                                     │
│  🧠 AI Services                                                     │
│  ├── POST /api/ai/technical-consultation → Expert consultation     │
│  ├── POST /api/ai/code-review          → Code analysis             │
│  └── POST /api/ai/code-review/with-improvements → Code + fixes     │
│                                                                     │
│  🤖 Agent Services (Future)                                        │
│  ├── POST /api/agents/devops/health-check → Infrastructure check   │
│  └── POST /api/agents/devops/analyze      → System analysis        │
└─────────────────────────────────────────────────────────────────────┘
```

### 🩺 Health and Information Endpoints

#### **Health Check**
```bash
GET /api/dev/health
```
**Response:**
```json
{
  "status": "UP",
  "timestamp": "2024-01-15T10:30:00Z",
  "uptime": "2h 45m 30s"
}
```

#### **Application Information**
```bash
GET /api/dev/info
```
**Response:**
```json
{
  "app": "TechKnowledge Assistant",
  "version": "1.0.0",
  "description": "Enterprise AI-Powered Technical Knowledge Platform",
  "langchain4j-version": "1.1.0",
  "java-version": "17.0.2",
  "profiles": ["dev"]
}
```

#### **Echo Test Service**
```bash
POST /api/dev/echo
Content-Type: application/json

{
  "message": "Hello, World!",
  "timestamp": "2024-01-15T10:30:00Z"
}
```

### 🧠 AI Services Documentation

#### **🎯 Technical Consultation Service**

**Endpoint:** `POST /api/ai/technical-consultation`

**Request Format:**
```json
{
  "question": "How do I implement microservices architecture with Spring Boot?",
  "expertiseArea": "ARCHITECTURE",
  "context": "We have a monolithic application with 100k+ users"
}
```

**Expertise Areas Available:**
```
┌─────────────────────────────────────────────────────────────────┐
│ CLOUD        │ Azure, AWS, GCP strategies and best practices  │
│ SECURITY     │ Cybersecurity, vulnerabilities, compliance     │
│ PERFORMANCE  │ Optimization, scaling, monitoring              │
│ ARCHITECTURE │ System design, patterns, microservices        │
│ CODE         │ Programming best practices, code quality       │
│ GENERAL      │ Technology guidance, tool recommendations      │
└─────────────────────────────────────────────────────────────────┘
```

**Response Example:**
```json
{
  "response": "To implement microservices architecture with Spring Boot...",
  "expertiseArea": "ARCHITECTURE",
  "confidence": 0.95,
  "recommendations": [
    "Start with domain-driven design",
    "Implement API gateway pattern",
    "Use Spring Cloud for service discovery"
  ],
  "timestamp": "2024-01-15T10:30:00Z"
}
```

#### **🔍 Code Review Service**

**Endpoint:** `POST /api/ai/code-review`

**Request Format:**
```json
{
  "code": "public class UserService {\n    public User getUser(String id) {\n        return database.query(\"SELECT * FROM users WHERE id = \" + id);\n    }\n}",
  "language": "java",
  "analysisType": "COMPREHENSIVE"
}
```

**Analysis Types:**
```
┌─────────────────────────────────────────────────────────────────┐
│ SECURITY     │ Focus on security vulnerabilities & fixes      │
│ PERFORMANCE  │ Performance optimization opportunities          │
│ QUALITY      │ Code quality, maintainability, best practices  │
│ COMPREHENSIVE│ All-in-one analysis (recommended)              │
└─────────────────────────────────────────────────────────────────┘
```

**Supported Languages:**
`java`, `python`, `javascript`, `typescript`, `go`, `rust`, `c++`, `c#`, `php`, `ruby`

**Response Example:**
```json
{
  "analysis": "The code has a critical SQL injection vulnerability...",
  "issues": [
    {
      "type": "SECURITY",
      "severity": "HIGH",
      "line": 3,
      "description": "SQL injection vulnerability in user query",
      "recommendation": "Use parameterized queries or JPA"
    }
  ],
  "overallScore": 3.2,
  "language": "java",
  "timestamp": "2024-01-15T10:30:00Z"
}
```

#### **🛠️ Code Review with Improvements**

**Endpoint:** `POST /api/ai/code-review/with-improvements`

**Request Format:**
```json
{
  "code": "public class UserService {\n    public User getUser(String id) {\n        return database.query(\"SELECT * FROM users WHERE id = \" + id);\n    }\n}",
  "language": "java"
}
```

**Response Example:**
```json
{
  "originalCode": "public class UserService {...}",
  "improvedCode": "@Service\npublic class UserService {\n    @Autowired\n    private UserRepository userRepository;\n    \n    public Optional<User> getUser(UUID id) {\n        return userRepository.findById(id);\n    }\n}",
  "improvements": [
    "Added @Service annotation for proper Spring component",
    "Used UUID instead of String for better type safety",
    "Implemented parameterized queries via repository",
    "Added Optional return type for null safety"
  ],
  "securityFixes": 1,
  "performanceImprovements": 2,
  "timestamp": "2024-01-15T10:30:00Z"
}
```

## 🔧 Development Guide

### 📁 Project Structure Deep Dive

```
🏗️ Architecture Layers Explained:

📁 src/main/java/com/example/techassistant/
│
├── 📁 config/                          🔧 Configuration Layer
│   ├── LangChain4jConfiguration.java   ├─ AI model setup & Azure connection
│   ├── AzureIntegrationConfiguration.java ├─ Azure services integration  
│   └── SecurityConfiguration.java      └─ OAuth2, CORS, security rules
│
├── 📁 controller/                      🌐 API Layer (REST Endpoints)
│   ├── AiController.java              ├─ /api/ai/* - AI consultation endpoints
│   ├── CodeReviewController.java      ├─ /api/ai/code-review* - Code analysis
│   └── DevController.java             └─ /api/dev/* - Health & development
│
├── 📁 model/                           📊 Data Models & DTOs
│   ├── TechnicalConsultationRequest.java ├─ Request/response models
│   ├── CodeReviewRequest.java         ├─ API data structures
│   ├── ExpertiseArea.java             ├─ Enums and constants
│   └── ...                            └─ Domain entities
│
├── 📁 service/                         💼 Business Logic Layer
│   ├── 📁 ai/                          🧠 AI Service Implementation
│   │   ├── TechnicalExpertService.java ├─ Multi-domain AI consultation
│   │   └── CodeReviewService.java      └─ Automated code analysis
│   └── 📁 agent/                       🤖 Intelligent Agents (Future)
│       └── DevOpsAgent.java           └─ Infrastructure management agent
│
└── TechKnowledgeAssistantApplication.java 🚀 Spring Boot Application Entry Point
```

### 🧩 Key Components Explained

#### **🔧 Configuration Classes**

```java
┌─────────────────────────────────────────────────────────────────┐
│ LangChain4jConfiguration.java                                  │
├─────────────────────────────────────────────────────────────────┤
│ • Azure OpenAI model configuration                             │
│ • Chat model setup (generalChatModel, codeFocusedModel, etc.)  │
│ • Embedding model configuration                                │
│ • Connection settings and API endpoints                        │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│ SecurityConfiguration.java                                     │
├─────────────────────────────────────────────────────────────────┤
│ • OAuth2 resource server setup                                 │
│ • CORS configuration for frontend integration                  │
│ • Endpoint security rules (/api/dev/* public, /api/ai/* auth)  │
│ • Rate limiting and input validation                           │
└─────────────────────────────────────────────────────────────────┘
```

#### **🌐 Controller Classes**

```java
┌─────────────────────────────────────────────────────────────────┐
│ AiController.java                                              │
├─────────────────────────────────────────────────────────────────┤
│ • POST /api/ai/technical-consultation                          │
│ • Multi-domain technical expertise                             │
│ • Request validation and response formatting                   │
│ • Error handling and fallback responses                        │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│ CodeReviewController.java                                      │
├─────────────────────────────────────────────────────────────────┤
│ • POST /api/ai/code-review                                     │
│ • POST /api/ai/code-review/with-improvements                   │
│ • Multi-language code analysis                                 │
│ • Security, performance, quality assessments                   │
└─────────────────────────────────────────────────────────────────┘
```

#### **💼 Service Classes**

```java
┌─────────────────────────────────────────────────────────────────┐
│ TechnicalExpertService.java                                    │
├─────────────────────────────────────────────────────────────────┤
│ • consultWithExpert() - Main consultation method               │
│ • Model selection based on expertise area                      │
│ • Context-aware prompt engineering                             │
│ • Response processing and validation                           │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│ CodeReviewService.java                                         │
├─────────────────────────────────────────────────────────────────┤
│ • reviewCode() - Code analysis with issues detection           │
│ • improveCode() - Code improvement suggestions                  │
│ • Language-specific analysis patterns                          │
│ • Security vulnerability scanning                              │
└─────────────────────────────────────────────────────────────────┘
```

### 🧪 Testing Strategy

#### **Testing Architecture**

```
📁 src/test/java/
├── 📁 unit/                            🔬 Unit Tests
│   ├── service/                        ├─ Service layer testing
│   ├── controller/                     ├─ Controller testing with MockMvc
│   └── config/                         └─ Configuration testing
├── 📁 integration/                     🔗 Integration Tests (Planned)
│   ├── api/                            ├─ Full API endpoint testing
│   └── azure/                          └─ Azure service integration tests
└── 📁 resources/
    ├── application-test.yml            🔧 Test configuration
    └── test-data/                      📊 Test data files
```

#### **Running Tests**

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=TechnicalExpertServiceTest

# Run tests with coverage report
mvn test jacoco:report

# Run only unit tests (exclude integration)
mvn test -Dtest=**/unit/**/*Test

# Run tests with specific profile
mvn test -Dspring.profiles.active=test

# Continuous testing during development
mvn test-compile spring-boot:run -Dspring-boot.run.fork=false
```

#### **Test Configuration**

```yaml
# src/test/resources/application-test.yml
spring:
  profiles:
    active: test
  
azure:
  openai:
    api-key: "test-key"
    endpoint: "https://test.openai.azure.com"
    deployment-name: "test-gpt-4"

# Mock configuration for testing without Azure dependency
langchain4j:
  test-mode: true
  mock-responses: true
```

## 🌐 Azure Integration & Deployment

### ☁️ Azure Services Architecture

```
┌─────────────────────────────────────────────────────────────────────┐
│                     🌐 Azure Cloud Integration                     │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  🚀 Current Integration (Phase 1)                                  │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │                                                             │   │
│  │  ┌─────────────────┐    ┌─────────────────┐                 │   │
│  │  │  TechKnowledge  │    │ Azure OpenAI    │                 │   │
│  │  │   Assistant     │◄──►│    Service      │                 │   │
│  │  │                 │    │                 │                 │   │
│  │  │ • Spring Boot   │    │ • GPT-4 Models  │                 │   │
│  │  │ • LangChain4j   │    │ • Embeddings    │                 │   │
│  │  │ • REST APIs     │    │ • Chat APIs     │                 │   │
│  │  └─────────────────┘    └─────────────────┘                 │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🔮 Planned Integration (Phase 2-4)                                │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │                                                             │   │
│  │  ┌───────────────┐  ┌───────────────┐  ┌───────────────┐   │   │
│  │  │ Azure AI      │  │ Azure Cosmos  │  │ Azure Blob    │   │   │
│  │  │ Search        │  │ DB            │  │ Storage       │   │   │
│  │  │               │  │               │  │               │   │   │
│  │  │ • Vector DB   │  │ • Documents   │  │ • File Store  │   │   │
│  │  │ • Semantic    │  │ • Metadata    │  │ • Binary Data │   │   │
│  │  │   Search      │  │ • Chat Logs   │  │ • Backups     │   │   │
│  │  └───────────────┘  └───────────────┘  └───────────────┘   │   │
│  │            ▲                ▲                ▲             │   │
│  │            └────────────────┼────────────────┘             │   │
│  │                             ▼                              │   │
│  │                 ┌───────────────┐                         │   │
│  │                 │ Azure Key     │                         │   │
│  │                 │ Vault         │                         │   │
│  │                 │               │                         │   │
│  │                 │ • API Keys    │                         │   │
│  │                 │ • Secrets     │                         │   │
│  │                 │ • Certificates│                         │   │
│  │                 └───────────────┘                         │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### 🚀 Deployment Options Overview

```
┌─────────────────────────────────────────────────────────────────────┐
│                    Deployment Target Comparison                    │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  🔥 Azure Container Apps (Recommended)                             │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ ✅ Serverless container hosting                            │   │
│  │ ✅ Auto-scaling (0 to N instances)                         │   │
│  │ ✅ Built-in load balancing                                 │   │
│  │ ✅ HTTPS termination                                       │   │
│  │ ✅ Simple deployment with azd                              │   │
│  │ ⚡ Best for: Production workloads, variable traffic        │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  🌐 Azure App Service                                              │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ ✅ Platform-as-a-Service (PaaS)                            │   │
│  │ ✅ Integrated CI/CD                                        │   │
│  │ ✅ Custom domains & SSL                                    │   │
│  │ ✅ Application insights integration                        │   │
│  │ ⚡ Best for: Traditional web applications                   │   │
│  └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
│  ⚙️ Azure Kubernetes Service (AKS)                                 │
│  ┌─────────────────────────────────────────────────────────────┐   │
│  │ ✅ Full Kubernetes orchestration                           │   │
│  │ ✅ Advanced networking & security                          │   │
│  │ ✅ Multi-container deployments                             │   │
│  │ ⚠️ Requires Kubernetes expertise                           │   │
│  │ ⚡ Best for: Complex microservices, enterprise scale       │   │
│  └─────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

### 📖 Comprehensive Deployment Documentation

For detailed Azure deployment instructions, including:

```
📚 Complete Deployment Guide Includes:
├── 🔧 Azure OpenAI Service setup (step-by-step)
├── 🐳 Container Apps deployment with azd
├── 🌐 App Service deployment options
├── ⚙️ AKS deployment for enterprise scale
├── 🔄 CI/CD pipeline configuration (GitHub Actions)
├── 🛡️ Security best practices & compliance
├── 📊 Monitoring, logging, and troubleshooting
├── 💰 Cost optimization strategies
└── 🚨 Incident response procedures
```

**👉 See the comprehensive [Azure Deployment Guide](docs/azure-deployment.md)**

### 🔧 Quick Azure Setup

#### **With Azure Developer CLI (azd) - Fastest Option**

```bash
# 1. Login to Azure (in Dev Container)
az login
azd auth login

# 2. Initialize project for Azure deployment  
azd init

# 3. Deploy to Azure (creates all resources)
azd up

# 4. Monitor deployment
azd monitor

# 5. Clean up resources when done
azd down
```

#### **Manual Setup Process**

```bash
# 1. Create resource group
az group create --name rg-techknowledge --location eastus

# 2. Create Azure OpenAI resource
az cognitiveservices account create \
  --name "techknowledge-openai" \
  --resource-group "rg-techknowledge" \
  --location "eastus" \
  --kind "OpenAI" \
  --sku "S0"

# 3. Deploy GPT-4 model
az cognitiveservices account deployment create \
  --name "techknowledge-openai" \
  --resource-group "rg-techknowledge" \
  --deployment-name "gpt-4" \
  --model-name "gpt-4" \
  --model-version "0613"

# 4. Get connection details
az cognitiveservices account show \
  --name "techknowledge-openai" \
  --resource-group "rg-techknowledge" \
  --query "properties.endpoint"

az cognitiveservices account keys list \
  --name "techknowledge-openai" \
  --resource-group "rg-techknowledge"
```

### 🔧 Dev Container with Azure CLI Tools

The project includes a pre-configured Dev Container with:

```
🛠️ Azure Tools Pre-Installed:
├── ☁️ Azure CLI (az) - Resource management
├── 🚀 Azure Developer CLI (azd) - Rapid deployment  
├── 🧩 Azure VS Code extensions - Integrated experience
├── 🐳 Docker - Container operations
└── 📊 Monitoring tools - Application insights
```

**🎯 Zero-Setup Azure Development:**
1. Click "Code" → "Open with Codespaces"
2. All Azure tools are ready immediately
3. Login: `az login` and `azd auth login`
4. Deploy: `azd up`

This application is designed to integrate seamlessly with Azure's enterprise services and provides a foundation for building production-scale AI applications.

## 🔐 Security

- Spring Security with OAuth2 resource server support
- CORS configuration for cross-origin requests
- Rate limiting and input validation
- Secure configuration management

## 📊 Monitoring

- Spring Boot Actuator endpoints
- Health checks and metrics
- Structured logging with configurable levels

## 🧪 Testing Strategy

- Unit tests for core business logic
- Integration tests for API endpoints (planned)
- Test profiles for different environments

## 📝 Example Usage & Demonstrations

### 🎯 Real-World Usage Examples

#### **🧠 Technical Consultation Examples**

##### **Example 1: Architecture Design Consultation**
```bash
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{
    "question": "What are the best practices for implementing caching in a microservices architecture with high availability requirements?",
    "expertiseArea": "ARCHITECTURE",
    "context": "E-commerce platform with 500k+ daily users, currently experiencing 2-3 second response times"
  }'
```

**Expected Response:**
```json
{
  "response": "For a high-availability e-commerce microservices architecture, I recommend implementing a multi-tier caching strategy:\n\n1. **Application-Level Caching**: Use Redis Cluster for session management and frequently accessed product data\n2. **Database Caching**: Implement read replicas with cache-aside pattern\n3. **CDN Layer**: CloudFront/Azure CDN for static assets and API responses\n4. **Circuit Breakers**: Implement Hystrix or Resilience4j for cache fallback strategies...",
  "expertiseArea": "ARCHITECTURE",
  "confidence": 0.94,
  "recommendations": [
    "Implement Redis Cluster for distributed caching",
    "Use cache-aside pattern for database queries",
    "Configure proper TTL strategies per data type",
    "Monitor cache hit ratios and adjust accordingly"
  ]
}
```

##### **Example 2: Security Best Practices**
```bash
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{
    "question": "How should I secure REST APIs in a Spring Boot application with sensitive financial data?",
    "expertiseArea": "SECURITY",
    "context": "Financial services application handling PCI DSS compliance requirements"
  }'
```

##### **Example 3: Performance Optimization**
```bash
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{
    "question": "My Java application has memory leaks and high GC pressure. How can I optimize it?",
    "expertiseArea": "PERFORMANCE",
    "context": "Spring Boot app with 8GB heap, handling 1000 requests/second"
  }'
```

#### **🔍 Code Review Examples**

##### **Example 1: Security Vulnerability Detection**
```bash
curl -X POST http://localhost:8080/api/ai/code-review \
  -H "Content-Type: application/json" \
  -d '{
    "code": "public class UserController {\n    @GetMapping(\"/user/{id}\")\n    public User getUser(@PathVariable String id) {\n        String sql = \"SELECT * FROM users WHERE id = \" + id;\n        return jdbcTemplate.queryForObject(sql, User.class);\n    }\n}",
    "language": "java",
    "analysisType": "SECURITY"
  }'
```

**Expected Response:**
```json
{
  "analysis": "Critical security vulnerability detected: SQL injection attack vector present in user query construction.",
  "issues": [
    {
      "type": "SECURITY",
      "severity": "CRITICAL",
      "line": 4,
      "description": "SQL injection vulnerability - user input directly concatenated into SQL query",
      "recommendation": "Use parameterized queries or JPA repository methods",
      "cwe": "CWE-89"
    }
  ],
  "overallScore": 2.1,
  "securityScore": 1.0,
  "language": "java"
}
```

##### **Example 2: Performance Analysis**
```bash
curl -X POST http://localhost:8080/api/ai/code-review \
  -H "Content-Type: application/json" \
  -d '{
    "code": "public List<Product> getProducts() {\n    List<Product> products = new ArrayList<>();\n    for (int i = 0; i < 1000; i++) {\n        Product p = productService.getById(i);\n        if (p != null) {\n            products.add(p);\n        }\n    }\n    return products;\n}",
    "language": "java",
    "analysisType": "PERFORMANCE"
  }'
```

##### **Example 3: Code Improvement with Fixes**
```bash
curl -X POST http://localhost:8080/api/ai/code-review/with-improvements \
  -H "Content-Type: application/json" \
  -d '{
    "code": "public class DataProcessor {\n    public void processData(List<String> data) {\n        for (String item : data) {\n            System.out.println(\"Processing: \" + item);\n            // Some processing logic\n            Thread.sleep(100);\n        }\n    }\n}",
    "language": "java"
  }'
```

**Expected Response:**
```json
{
  "originalCode": "public class DataProcessor { ... }",
  "improvedCode": "@Component\n@Slf4j\npublic class DataProcessor {\n    \n    @Async\n    public CompletableFuture<Void> processDataAsync(List<String> data) {\n        return CompletableFuture.runAsync(() -> {\n            data.parallelStream().forEach(item -> {\n                log.info(\"Processing: {}\", item);\n                // Processing logic here\n                try {\n                    Thread.sleep(100);\n                } catch (InterruptedException e) {\n                    Thread.currentThread().interrupt();\n                    throw new RuntimeException(e);\n                }\n            });\n        });\n    }\n}",
  "improvements": [
    "Added @Component annotation for Spring dependency injection",
    "Replaced System.out.println with proper logging (Slf4j)",
    "Implemented async processing with @Async annotation",
    "Used parallel streams for better performance",
    "Added proper exception handling for InterruptedException",
    "Returned CompletableFuture for non-blocking execution"
  ],
  "performanceImprovements": 3,
  "qualityImprovements": 4
}
```

### 🩺 Health Check Examples

#### **Basic Health Check**
```bash
curl http://localhost:8080/api/dev/health
```

**Response:**
```json
{
  "status": "UP",
  "timestamp": "2024-01-15T10:30:00Z",
  "uptime": "2h 45m 30s",
  "checks": {
    "azure-openai": "UP",
    "database": "UP",
    "memory": "OK"
  }
}
```

#### **Application Information**
```bash
curl http://localhost:8080/api/dev/info
```

#### **Echo Test (Connectivity)**
```bash
curl -X POST http://localhost:8080/api/dev/echo \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello TechKnowledge Assistant!", "timestamp": "2024-01-15T10:30:00Z"}'
```

### 🧪 Testing Your Setup

#### **Quick Validation Script**
```bash
#!/bin/bash
# test-setup.sh - Validate your TechKnowledge Assistant setup

echo "🧪 Testing TechKnowledge Assistant Setup..."

# 1. Health Check
echo "1️⃣ Checking application health..."
curl -f http://localhost:8080/api/dev/health || echo "❌ Health check failed"

# 2. Echo Test
echo "2️⃣ Testing echo service..."
curl -X POST http://localhost:8080/api/dev/echo \
  -H "Content-Type: application/json" \
  -d '{"message": "test"}' || echo "❌ Echo test failed"

# 3. AI Consultation Test
echo "3️⃣ Testing AI consultation..."
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{
    "question": "What is Spring Boot?",
    "expertiseArea": "GENERAL"
  }' || echo "❌ AI consultation failed"

# 4. Code Review Test
echo "4️⃣ Testing code review..."
curl -X POST http://localhost:8080/api/ai/code-review \
  -H "Content-Type: application/json" \
  -d '{
    "code": "public class Test { public void hello() { System.out.println(\"Hello\"); } }",
    "language": "java",
    "analysisType": "QUALITY"
  }' || echo "❌ Code review failed"

echo "✅ Setup validation complete!"
```

### 🎨 Integration Examples

#### **Frontend Integration (JavaScript)**
```javascript
// Example: Integrating with a React application
const TechKnowledgeAPI = {
  baseURL: 'http://localhost:8080/api',
  
  async consultExpert(question, expertiseArea, context = '') {
    const response = await fetch(`${this.baseURL}/ai/technical-consultation`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${getAuthToken()}`
      },
      body: JSON.stringify({ question, expertiseArea, context })
    });
    return response.json();
  },
  
  async reviewCode(code, language, analysisType = 'COMPREHENSIVE') {
    const response = await fetch(`${this.baseURL}/ai/code-review`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${getAuthToken()}`
      },
      body: JSON.stringify({ code, language, analysisType })
    });
    return response.json();
  }
};

// Usage example
const result = await TechKnowledgeAPI.consultExpert(
  "How to implement JWT authentication?",
  "SECURITY",
  "Spring Boot REST API"
);
console.log(result.response);
```

#### **CI/CD Integration (GitHub Actions)**
```yaml
# .github/workflows/code-review.yml
name: AI Code Review
on: [pull_request]

jobs:
  ai-code-review:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: AI Code Review
        run: |
          # Get changed files and run AI code review
          git diff --name-only origin/main...HEAD | grep '\.java$' | while read file; do
            curl -X POST ${{ secrets.TECHKNOWLEDGE_API_URL }}/api/ai/code-review \
              -H "Content-Type: application/json" \
              -d "{\"code\": \"$(cat $file)\", \"language\": \"java\", \"analysisType\": \"COMPREHENSIVE\"}"
          done
```

## 🔗 References

- [LangChain4j Documentation](https://docs.langchain4j.dev/)
- [Azure OpenAI Service](https://azure.microsoft.com/en-us/products/ai-services/openai-service)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

## 📄 License

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/f3ac65a05ed8c8ea70b653875ccac0c6dbc10ba1/LICENSE)

## 👤 Author

- GitHub: <https://github.com/shinyay>
- Twitter: <https://twitter.com/yanashin18618>
- Mastodon: <https://mastodon.social/@yanashin>
