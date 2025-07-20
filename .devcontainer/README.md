# VibeCode Studio - Dev Container

This directory contains the development container configuration for **VibeCode Studio**, providing a complete, reproducible development environment for the Java Spring Boot application.

## 📁 Dev Container Structure

The Dev Container is organized into three separate files for better maintainability:

- **`devcontainer.json`** - Main configuration with VS Code settings and extensions
- **`docker-compose.yml`** - Multi-service orchestration (app, PostgreSQL, pgAdmin)
- **`Dockerfile`** - Custom container image with all development tools

### File Responsibilities

| File | Purpose |
|------|---------|
| `devcontainer.json` | VS Code configuration, extensions, port forwarding, environment |
| `docker-compose.yml` | Service orchestration, volumes, networking, dependencies |
| `Dockerfile` | Base image setup, tool installation, MCP servers |

## 🚀 Quick Start

### Option 1: Azure CLI Setup (Recommended)
```bash
# 1. Open in Dev Container
# - Open this repository in VS Code
# - Click "Reopen in Container" when prompted
# - Or: Ctrl/Cmd + Shift + P → "Dev Containers: Reopen in Container"

# 2. Authenticate with Azure
bash .devcontainer/azure-auth.sh login

# 3. Set up Azure resources and configuration  
bash .devcontainer/setup-azure.sh

# 4. Start the application with Azure configuration
mvn spring-boot:run -Dspring-boot.run.profiles=azure
```

### Option 2: Development Mode (No Azure Required)
```bash
# 1. Open in Dev Container (same as above)

# 2. Start with development profile (uses H2 database and mock AI)
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Prerequisites
- **Visual Studio Code** with the [Dev Containers extension](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)
- **Docker Desktop** or **Docker Engine**

### Getting Started

1. **Open in Dev Container**:
   - Open this repository in VS Code
   - When prompted, click "Reopen in Container" 
   - Or: `Ctrl/Cmd + Shift + P` → "Dev Containers: Reopen in Container"

2. **Wait for Setup**: The container will automatically:
   - Install Java 21 and Maven
   - Install Azure CLI and Node.js
   - Set up PostgreSQL database
   - Install MCP (Model Context Protocol) servers
   - Install VS Code extensions
   - Run `mvn clean compile`

3. **Choose Your Development Path**:
   ```bash
   # For Azure-integrated development
   bash .devcontainer/azure-auth.sh login
   bash .devcontainer/setup-azure.sh
   mvn spring-boot:run -Dspring-boot.run.profiles=azure
   
   # For local development (no Azure required)
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```

## 🏗️ Container Architecture

```
┌─────────────────────────────────────────────────────────┐
│                    Dev Container                        │
├─────────────────────┬───────────────────────────────────┤
│   Java App          │         PostgreSQL                │
│   Container          │         Container                 │
│                     │                                   │
│ • Java 21           │ • PostgreSQL 15                  │
│ • Maven 3.9         │ • Database: vibecode_studio      │
│ • Spring Boot       │ • User: vibecode                  │
│ • Azure CLI         │ • Auto-initialization             │
│ • Node.js 20        │                                   │
│ • MCP Servers       │                                   │
│ • VS Code Extensions│                                   │
└─────────────────────┴───────────────────────────────────┘
```

## 🛠️ Services & Ports

| Service | Port | Description |
|---------|------|-------------|
| **Spring Boot App** | `8080` | Main application API |
| **PostgreSQL** | `5432` | Primary database |
| **pgAdmin** | `8081` | Database management UI |

## 📊 Database Access

### PostgreSQL Connection
- **Host**: `postgres` (within container) or `localhost` (from host)
- **Port**: `5432`
- **Database**: `vibecode_studio`
- **Username**: `vibecode`
- **Password**: `vibecode123`

### pgAdmin Access
- **URL**: http://localhost:8081
- **Email**: `admin@vibecode.com`
- **Password**: `admin123`

### H2 Console (Development Profile)
When running with `dev` profile, you can also access the H2 in-memory database:
- **URL**: http://localhost:8080/api/h2-console
- **JDBC URL**: `jdbc:h2:mem:vibecode_dev`
- **Username**: `sa`
- **Password**: (empty)

## 🔧 Development Features

### Pre-installed VS Code Extensions
- **Java Extension Pack** - Complete Java development tools
- **Spring Boot Extensions** - Spring Boot development and dashboard
- **Maven for Java** - Maven integration
- **Azure CLI Tools** - Azure cloud operations and management
- **Thunder Client** - API testing (alternative to Postman)
- **GitLens** - Enhanced Git capabilities
- **Docker** - Container management

### Cloud & AI Integration
- **Azure CLI** - Manage Azure resources and services with integrated authentication
- **Azure Authentication Helper** - Simplified Azure login and configuration
- **Azure Resource Setup** - Automated Azure AI service provisioning
- **MCP Servers** - Model Context Protocol for enhanced AI development
  - GitHub integration for repository operations
  - FileSystem access for project structure analysis
  - Brave Search for web research capabilities
  - SQLite for database operations
  - Memory for conversation context management
  - **Azure MCP Server** - Azure resource management and deployment operations with CLI authentication

### Environment Variables
- `SPRING_PROFILES_ACTIVE=dev` - Activates development profile
- `AI_PROVIDER=mock` - Uses mock AI client for development (can be changed to `azure`)
- `JAVA_HOME` - Java 21 installation path
- `MAVEN_HOME` - Maven installation path
- `NODE_PATH` - Node.js modules path for MCP servers

**Note**: Java 21 and Maven are automatically added to PATH and available as standard commands (`java`, `javac`, `mvn`) in all terminal sessions within the Dev Container.

#### Azure Configuration (Optional - for Azure AI integration)
- `AZURE_CLI_AUTH=true` - Use Azure CLI authentication (recommended)
- `AZURE_TENANT_ID` - Azure Directory (tenant) ID (auto-detected from `az cli`)
- `AZURE_SUBSCRIPTION_ID` - Azure Subscription ID (auto-detected from `az cli`)
- `AZURE_AI_ENDPOINT` - Azure AI service endpoint (auto-configured by setup script)
- `AZURE_AI_KEY` - Azure AI service key (auto-configured by setup script)
- `AZURE_AI_DEPLOYMENT` - Azure AI model deployment name (default: gpt-4)

#### MCP Integration (Optional - for enhanced AI development)
- `GITHUB_TOKEN` - GitHub Personal Access Token for MCP GitHub server
- `BRAVE_API_KEY` - Brave Search API key for MCP search capabilities
- `AZURE_CLIENT_ID` - Azure Application (client) ID (alternative to CLI auth)
- `AZURE_CLIENT_SECRET` - Azure Client Secret (alternative to CLI auth)

### Auto-completion & IntelliSense
- Java language support with error detection
- Spring Boot configuration auto-completion
- Maven dependency management
- Automatic imports and refactoring

## 🧪 Testing & Development Workflow

```bash
# Clean and compile
mvn clean compile

# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=GuardRailEngineTest

# Run with specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Package the application
mvn package

# Run integration tests
mvn verify

# Azure CLI operations (after authentication)
bash .devcontainer/azure-auth.sh status
bash .devcontainer/azure-auth.sh test
az account show
az webapp list

# Setup Azure resources for AI integration
bash .devcontainer/setup-azure.sh

# Test MCP servers
mcp list-servers
mcp inspect filesystem
mcp inspect azure  # Requires Azure CLI authentication
```

## 🔍 Troubleshooting

### Container Won't Start
1. Ensure Docker is running
2. Check available disk space (>2GB recommended)
3. Restart Docker Desktop
4. Try: `Dev Containers: Rebuild Container`

### Database Connection Issues
1. Verify PostgreSQL container is running: `docker ps`
2. Check logs: `docker logs <postgres-container-name>`
3. Ensure port 5432 is not used by another service

### Java/Maven Issues
1. Rebuild container to get latest Java setup
2. Clear Maven cache: `mvn dependency:purge-local-repository`
3. Check Java version: `java -version` (should be 21)

### Performance Issues
1. Increase Docker memory allocation (recommended: 4GB+)
2. Close unnecessary VS Code extensions
3. Use `.gitignore` to exclude `target/` from file watching

## 📚 Additional Resources

- [Dev Containers Documentation](https://code.visualstudio.com/docs/devcontainers/containers)
- [Spring Boot Developer Tools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)
- [VS Code Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- [Azure CLI Documentation](https://docs.microsoft.com/en-us/cli/azure/)
- [MCP Integration Guide](.devcontainer/MCP_INTEGRATION.md) - Detailed MCP servers documentation
- [Azure AI Integration Guide](../AZURE_AI_INTEGRATION.md) - Azure AI Foundry setup

## 🔐 Security Notes

- The dev container uses default passwords for development convenience
- PostgreSQL and pgAdmin credentials are for development only
- For production deployment, use secure credentials and environment variables
- The mock AI provider is used by default to avoid API costs during development